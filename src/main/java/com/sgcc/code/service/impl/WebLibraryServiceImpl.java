package com.sgcc.code.service.impl;

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
import com.sgcc.code.entity.WebLibrary;
import com.sgcc.code.entity.io.webLibrary.*;
import com.sgcc.code.mapper.WebLibraryMapper;
import com.sgcc.code.service.WebLibraryService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;


/**
 * <p>
 * 网页库事务
 * </p>
 *
 * @author liugohua
 * @since Mon Mar 06 17:53:18 CST 2023
 */
@Service
public class WebLibraryServiceImpl extends ServiceImpl<WebLibraryMapper, WebLibrary> implements WebLibraryService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private WebLibraryMapper webLibraryMapper;

	//查询网页库分页数据
	public ResponseModel<UapPage<WebLibraryIo>> query(RequestModel<WebLibraryQueryIo> body){
		logger.info("查询网页库分页数据 ===> " + body.getParameter());
		QueryWrapper<WebLibrary> queryWrapper = new QueryWrapper<>();
		if(body.getParameter().getProjectId() != null) queryWrapper.eq("project_id", body.getParameter().getProjectId());
		if(StringUtils.isNotEmpty(body.getParameter().getIspublic())) queryWrapper.like("ispublic", body.getParameter().getIspublic());
		if(StringUtils.isNotEmpty(body.getParameter().getWebTitle())) queryWrapper.like("web_title", body.getParameter().getWebTitle());
		if(StringUtils.isNotEmpty(body.getParameter().getWebDescribe())) queryWrapper.like("web_describe", body.getParameter().getWebDescribe());
		if(StringUtils.isNotEmpty(body.getParameter().getWebContent())) queryWrapper.like("web_content", body.getParameter().getWebContent());
		//查询出mybatisplus的分页对象 并转换成内部分页对象
        IPage<WebLibrary> iPage = super.page(new Page(body.getCurrent(), body.getSize()), queryWrapper);
        return ResponseModel.success(BeanUtil.convertPage(iPage,WebLibraryIo.class), body.getHeader());
	}

	//普通用户-查询网页库分页数据
	public ResponseModel<UapPage<WebLibraryIo>> userQuery(RequestModel<WebLibraryQueryIo> body){
		logger.info("普通用户-查询网页库分页数据 ===> " + body.getParameter());
		// 自定义分页查询
		IPage<Map> iPage = webLibraryMapper.selectPageList(new Page(body.getCurrent(), body.getSize()), body.getParameter().getProjectId());
		return ResponseModel.success(BeanUtil.convertPage(iPage,WebLibraryIo.class), body.getHeader());
	}

	//查询网页库记录
	public ResponseModel<List<WebLibraryIo>> queryAll(RequestModel<WebLibraryQueryIo> body){
		logger.info("查询网页库数据列表 ===> " + body.getParameter());
		QueryWrapper<WebLibrary> queryWrapper = new QueryWrapper<>();
		if(body.getParameter().getProjectId() != null) queryWrapper.eq("project_id", body.getParameter().getProjectId());
		if(StringUtils.isNotEmpty(body.getParameter().getIspublic())) queryWrapper.like("ispublic", body.getParameter().getIspublic().trim());
		if(StringUtils.isNotEmpty(body.getParameter().getWebTitle())) queryWrapper.like("web_title", body.getParameter().getWebTitle().trim());
		if(StringUtils.isNotEmpty(body.getParameter().getWebDescribe())) queryWrapper.like("web_describe", body.getParameter().getWebDescribe().trim());
		if(StringUtils.isNotEmpty(body.getParameter().getWebContent())) queryWrapper.like("web_content", body.getParameter().getWebContent().trim());
        List<WebLibrary> webLibrarys = super.list(queryWrapper);
        if(webLibrarys == null) return ResponseModel.failure("获取数据发生异常，请重试");
        return ResponseModel.success(BeanUtil.convert(webLibrarys,WebLibraryIo.class),body.getHeader());
	}
	
	//获取单条网页库
	public ResponseModel<WebLibraryIo> view(RequestModel<WebLibraryViewIo> body){
		logger.info("查询网页库详情数据 ===> " + body.getParameter());
		WebLibrary webLibrary = super.getById(body.getParameter().getId());
		if(webLibrary == null) return ResponseModel.failure("未找到对应的数据记录");
		return ResponseModel.success(BeanUtil.convert(webLibrary,WebLibraryIo.class),body.getHeader());
	}
	
	//保存网页库
	public ResponseModel<Integer> add(RequestModel<WebLibraryAddIo> body){
		logger.info("保存网页库数据 ===> " + body.getParameter());
		WebLibrary webLibrary = BeanUtil.convert(body.getParameter(),WebLibrary.class);
		super.save(webLibrary);
		return ResponseModel.success("保存成功",webLibrary.getId(),body.getHeader());
	}
	
	//删除网页库
	public ResponseModel<String> delete(RequestModel<String> body){
		logger.info("删除网页库数据 ===> " + body.getParameter());
		StringUtil.isValidIds(body.getParameter());//验证ID是否合法
		String[] idsArr = body.getParameter().split(",");
	    for(String idStr:idsArr){
            super.removeById(Integer.parseInt(idStr));
        }
		return ResponseModel.success("删除成功",body.getParameter(),body.getHeader());
	}
	
	//修改网页库
	public ResponseModel<Integer> update(RequestModel<WebLibraryUpdateIo> body){
		logger.info("修改网页库数据 ===> " + body.getParameter());
		WebLibrary webLibrary = BeanUtil.convert(body.getParameter(),WebLibrary.class);
		super.updateById(webLibrary);
		return ResponseModel.success("修改成功",webLibrary.getId(),body.getHeader());
	}
	
	
	// 导入网页库
	@Transactional(rollbackFor = CommonException.class)
	public ResponseModel<String> imports(RequestModel<List<WebLibraryAddIo>> body){
		logger.info("导入网页库数据 ===> 文件" );
		int imp_num = 0;
		String[] titleNameArray = {"项目ID","是否公开","网页库标题","网页库描述","网页库内容","创建时间"};
		String[] fieldNameArray = {"projectId","ispublic","webTitle","webDescribe","webContent","createTime"};
		try {
			for (WebLibraryAddIo webLibraryAddIo : body.getParameter()) {
				WebLibrary webLibrary = BeanUtil.convert(webLibraryAddIo,WebLibrary.class);
				super.save(webLibrary);
				imp_num++;
			}
		}catch (Exception e) {
			throw new CommonException(500,"导入失败,请确认Excel内容是否正确。</br>错误行号："+(imp_num+2)+"。</br>错误信息："+ExcelUtil.getPointOfException(e.getMessage(),fieldNameArray,titleNameArray)+"。");
		}
		return ResponseModel.success("导入成功，总条数："+imp_num+"条！");
	}
	

	
}