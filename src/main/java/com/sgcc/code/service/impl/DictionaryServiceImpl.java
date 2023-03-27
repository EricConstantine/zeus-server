package com.sgcc.code.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sgcc.code.entity.Dictionary;
import com.sgcc.code.entity.io.dictionary.DictionaryIo;
import com.sgcc.code.entity.io.dictionary.DictionaryQueryIo;
import com.sgcc.code.entity.io.dictionary.DictionaryViewIo;
import com.sgcc.code.entity.io.dictionary.DictionaryAddIo;
import com.sgcc.code.entity.io.dictionary.DictionaryUpdateIo;
import com.sgcc.code.common.exception.CommonException;
import com.sgcc.code.service.DictionaryService;
import com.sgcc.code.mapper.DictionaryMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sgcc.code.common.utils.BeanUtil;
import com.sgcc.code.common.utils.ExcelUtil;
import com.sgcc.code.common.web.RequestModel;
import com.sgcc.code.common.web.ResponseModel;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import com.sgcc.code.common.utils.StringUtil;
import com.sgcc.code.common.utils.UapPage;


/**
 * <p>
 * 字典事务
 * </p>
 *
 * @author liugohua
 * @since Wed Jan 04 12:23:13 CST 2023
 */
@Service
public class DictionaryServiceImpl extends ServiceImpl<DictionaryMapper, Dictionary> implements DictionaryService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	//查询字典分页数据
	public ResponseModel<UapPage<DictionaryIo>> query(RequestModel<DictionaryQueryIo> body){
		logger.info("查询字典分页数据 ===> " + body.getParameter());
		QueryWrapper<Dictionary> queryWrapper = new QueryWrapper<>();
		if(StringUtils.isNotEmpty(body.getParameter().getProjectid())) queryWrapper.like("projectid", body.getParameter().getProjectid());
		if(StringUtils.isNotEmpty(body.getParameter().getDictionaryKey())) queryWrapper.like("dictionary_key", body.getParameter().getDictionaryKey());
		if(StringUtils.isNotEmpty(body.getParameter().getDictionaryValue())) queryWrapper.like("dictionary_value", body.getParameter().getDictionaryValue());
		if(StringUtils.isNotEmpty(body.getParameter().getDictionaryDescribe())) queryWrapper.like("dictionary_describe", body.getParameter().getDictionaryDescribe());
		//查询出mybatisplus的分页对象 并转换成内部分页对象
        IPage<Dictionary> iPage = super.page(new Page(body.getCurrent(), body.getSize()), queryWrapper);
        return ResponseModel.success(BeanUtil.convertPage(iPage,DictionaryIo.class), body.getHeader());
	}
	
	//查询字典记录
	public ResponseModel<List<DictionaryIo>> queryAll(RequestModel<DictionaryQueryIo> body){
		logger.info("查询字典数据列表 ===> " + body.getParameter());
		QueryWrapper<Dictionary> queryWrapper = new QueryWrapper<>();
		if(StringUtils.isNotEmpty(body.getParameter().getProjectid())) queryWrapper.like("projectid", body.getParameter().getProjectid().trim());
		if(StringUtils.isNotEmpty(body.getParameter().getDictionaryKey())) queryWrapper.like("dictionary_key", body.getParameter().getDictionaryKey().trim());
		if(StringUtils.isNotEmpty(body.getParameter().getDictionaryValue())) queryWrapper.like("dictionary_value", body.getParameter().getDictionaryValue().trim());
		if(StringUtils.isNotEmpty(body.getParameter().getDictionaryDescribe())) queryWrapper.like("dictionary_describe", body.getParameter().getDictionaryDescribe().trim());
        List<Dictionary> dictionarys = super.list(queryWrapper);
        if(dictionarys == null) return ResponseModel.failure("获取数据发生异常，请重试");
        return ResponseModel.success(BeanUtil.convert(dictionarys,DictionaryIo.class),body.getHeader());
	}
	
	//获取单条字典
	public ResponseModel<DictionaryIo> view(RequestModel<DictionaryViewIo> body){
		logger.info("查询字典详情数据 ===> " + body.getParameter());
		Dictionary dictionary = super.getById(body.getParameter().getId());
		if(dictionary == null) return ResponseModel.failure("未找到对应的数据记录");
		return ResponseModel.success(BeanUtil.convert(dictionary,DictionaryIo.class),body.getHeader());
	}
	
	//保存字典
	public ResponseModel<Long> add(RequestModel<DictionaryAddIo> body){
		logger.info("保存字典数据 ===> " + body.getParameter());
		Dictionary dictionary = BeanUtil.convert(body.getParameter(),Dictionary.class);
		super.save(dictionary);
		return ResponseModel.success("保存成功",dictionary.getId(),body.getHeader());
	}
	
	//删除字典
	public ResponseModel<String> delete(RequestModel<String> body){
		logger.info("删除字典数据 ===> " + body.getParameter());
		StringUtil.isValidIds(body.getParameter());//验证ID是否合法
		String[] idsArr = body.getParameter().split(",");
	    for(String idStr:idsArr){
            super.removeById(Integer.parseInt(idStr));
        }
		return ResponseModel.success("删除成功",body.getParameter(),body.getHeader());
	}
	
	//修改字典
	public ResponseModel<Long> update(RequestModel<DictionaryUpdateIo> body){
		logger.info("修改字典数据 ===> " + body.getParameter());
		Dictionary dictionary = BeanUtil.convert(body.getParameter(),Dictionary.class);
		super.updateById(dictionary);
		return ResponseModel.success("修改成功",dictionary.getId(),body.getHeader());
	}
	
	
	// 导入字典
	@Transactional(rollbackFor = CommonException.class)
	public ResponseModel<String> imports(RequestModel<List<DictionaryAddIo>> body){
		logger.info("导入字典数据 ===> 文件" );
		int imp_num = 0;
		String[] titleNameArray = {"项目id","字典key","字典value","描述","创建时间","更新时间"};
		String[] fieldNameArray = {"projectid","dictionaryKey","dictionaryValue","dictionaryDescribe","createTime","updateTime"};
		try {
			for (DictionaryAddIo dictionaryAddIo : body.getParameter()) {
				Dictionary dictionary = BeanUtil.convert(dictionaryAddIo,Dictionary.class);
				super.save(dictionary);
				imp_num++;
			}
		}catch (Exception e) {
			throw new CommonException(500,"导入失败,请确认Excel内容是否正确。</br>错误行号："+(imp_num+2)+"。</br>错误信息："+ExcelUtil.getPointOfException(e.getMessage(),fieldNameArray,titleNameArray)+"。");
		}
		return ResponseModel.success("导入成功，总条数："+imp_num+"条！");
	}
	

	
}