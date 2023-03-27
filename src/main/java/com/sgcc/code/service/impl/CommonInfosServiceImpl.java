package com.sgcc.code.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sgcc.code.common.exception.CommonException;
import com.sgcc.code.common.utils.BeanUtil;
import com.sgcc.code.common.utils.ExcelUtil;
import com.sgcc.code.common.utils.StringUtil;
import com.sgcc.code.common.utils.UapPage;
import com.sgcc.code.common.web.RequestModel;
import com.sgcc.code.common.web.ResponseModel;
import com.sgcc.code.entity.CommonInfos;
import com.sgcc.code.entity.io.commonInfos.*;
import com.sgcc.code.mapper.CommonInfosMapper;
import com.sgcc.code.service.CommonInfosService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * 项目公共配置信息事务
 * </p>
 *
 * @author liugohua
 * @since Tue Jan 31 10:16:22 CST 2023
 */
@Service
public class CommonInfosServiceImpl extends ServiceImpl<CommonInfosMapper, CommonInfos> implements CommonInfosService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	//查询项目公共配置信息分页数据
	public ResponseModel<UapPage<CommonInfosIo>> query(RequestModel<CommonInfosQueryIo> body){
		logger.info("查询项目公共配置信息分页数据 ===> " + body.getParameter());
		QueryWrapper<CommonInfos> queryWrapper = new QueryWrapper<>();
		if(body.getParameter().getProjectid() != null) queryWrapper.eq("projectid", body.getParameter().getProjectid());
		if(StringUtils.isNotEmpty(body.getParameter().getDescribes())) queryWrapper.like("describes", body.getParameter().getDescribes());
		if(StringUtils.isNotEmpty(body.getParameter().getContent())) queryWrapper.like("content", body.getParameter().getContent());
		//查询出mybatisplus的分页对象 并转换成内部分页对象
        IPage<CommonInfos> iPage = super.page(new Page(body.getCurrent(), body.getSize()), queryWrapper);
        return ResponseModel.success(BeanUtil.convertPage(iPage,CommonInfosIo.class), body.getHeader());
	}
	
	//查询项目公共配置信息记录
	public ResponseModel<List<CommonInfosIo>> queryAll(RequestModel<CommonInfosQueryIo> body){
		logger.info("查询项目公共配置信息数据列表 ===> " + body.getParameter());
		QueryWrapper<CommonInfos> queryWrapper = new QueryWrapper<>();
		if(body.getParameter().getProjectid() != null) queryWrapper.eq("projectid", body.getParameter().getProjectid());
		queryWrapper.orderByDesc("create_time");
        List<CommonInfos> commonInfoss = super.list(queryWrapper);
        if(commonInfoss == null) return ResponseModel.failure("获取数据发生异常，请重试");
		for (CommonInfos c : commonInfoss) {
			String content = c.getContent();
			List<Map> contents = StringUtils.isNotBlank(content)?JSON.parseArray(c.getContent(), Map.class):new ArrayList<>();
			c.setContents(contents);
		}
        return ResponseModel.success(BeanUtil.convert(commonInfoss,CommonInfosIo.class),body.getHeader());
	}
	
	//获取单条项目公共配置信息
	public ResponseModel<CommonInfosIo> view(RequestModel<CommonInfosViewIo> body){
		logger.info("查询项目公共配置信息详情数据 ===> " + body.getParameter());
		CommonInfos commonInfos = super.getById(body.getParameter().getId());
		if(commonInfos == null) return ResponseModel.failure("未找到对应的数据记录");
		return ResponseModel.success(BeanUtil.convert(commonInfos,CommonInfosIo.class),body.getHeader());
	}
	
	//保存项目公共配置信息
	public ResponseModel<Long> add(RequestModel<CommonInfosAddIo> body){
		logger.info("保存项目公共配置信息数据 ===> " + body.getParameter());
		CommonInfos commonInfos = BeanUtil.convert(body.getParameter(),CommonInfos.class);
		if(commonInfos.getContents()!=null){
			commonInfos.setContent(JSON.toJSONString(commonInfos.getContents()));
		}
		super.save(commonInfos);
		return ResponseModel.success("保存成功",commonInfos.getId(),body.getHeader());
	}
	
	//删除项目公共配置信息
	public ResponseModel<String> delete(RequestModel<String> body){
		logger.info("删除项目公共配置信息数据 ===> " + body.getParameter());
		StringUtil.isValidIds(body.getParameter());//验证ID是否合法
		String[] idsArr = body.getParameter().split(",");
	    for(String idStr:idsArr){
            super.removeById(Integer.parseInt(idStr));
        }
		return ResponseModel.success("删除成功",body.getParameter(),body.getHeader());
	}
	
	//修改项目公共配置信息
	public ResponseModel<Long> update(RequestModel<CommonInfosUpdateIo> body){
		logger.info("修改项目公共配置信息数据 ===> " + body.getParameter());
		CommonInfos commonInfos = BeanUtil.convert(body.getParameter(),CommonInfos.class);
		if(commonInfos.getContents()!=null){
			commonInfos.setContent(JSON.toJSONString(commonInfos.getContents()));
		}
		super.updateById(commonInfos);
		return ResponseModel.success("修改成功",commonInfos.getId(),body.getHeader());
	}
	
	
	// 导入项目公共配置信息
	@Transactional(rollbackFor = CommonException.class)
	public ResponseModel<String> imports(RequestModel<List<CommonInfosAddIo>> body){
		logger.info("导入项目公共配置信息数据 ===> 文件" );
		int imp_num = 0;
		String[] titleNameArray = {"项目id","公共配置信息描述","公共配置信息内容","创建时间"};
		String[] fieldNameArray = {"projectid","describes","content","createTime"};
		try {
			for (CommonInfosAddIo commonInfosAddIo : body.getParameter()) {
				CommonInfos commonInfos = BeanUtil.convert(commonInfosAddIo,CommonInfos.class);
				super.save(commonInfos);
				imp_num++;
			}
		}catch (Exception e) {
			throw new CommonException(500,"导入失败,请确认Excel内容是否正确。</br>错误行号："+(imp_num+2)+"。</br>错误信息："+ExcelUtil.getPointOfException(e.getMessage(),fieldNameArray,titleNameArray)+"。");
		}
		return ResponseModel.success("导入成功，总条数："+imp_num+"条！");
	}
	

	
}