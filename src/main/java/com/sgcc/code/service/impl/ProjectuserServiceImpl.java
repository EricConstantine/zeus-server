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
import com.sgcc.code.entity.Projectuser;
import com.sgcc.code.entity.User;
import com.sgcc.code.entity.io.projectuser.*;
import com.sgcc.code.service.ProjectuserService;
import com.sgcc.code.service.UserService;
import com.sgcc.code.mapper.ProjectuserMapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * <p>
 * 项目成员事务
 * </p>
 *
 * @author liugohua
 * @since Tue Jan 03 10:07:47 CST 2023
 */
@Service
public class ProjectuserServiceImpl extends ServiceImpl<ProjectuserMapper, Projectuser> implements ProjectuserService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserService userService;

	//查询项目成员分页数据
	public ResponseModel<UapPage<ProjectuserIo>> query(RequestModel<ProjectuserQueryIo> body){
		logger.info("查询项目成员分页数据 ===> " + body.getParameter());
		QueryWrapper<Projectuser> queryWrapper = new QueryWrapper<>();
		if(StringUtils.isNotEmpty(body.getParameter().getProjectid())) queryWrapper.like("projectid", body.getParameter().getProjectid());
		if(StringUtils.isNotEmpty(body.getParameter().getUserid())) queryWrapper.like("userid", body.getParameter().getUserid());
		if(StringUtils.isNotEmpty(body.getParameter().getStatus())) queryWrapper.like("status", body.getParameter().getStatus());
		//查询出mybatisplus的分页对象 并转换成内部分页对象
        IPage<Projectuser> iPage = super.page(new Page(body.getCurrent(), body.getSize()), queryWrapper);
        return ResponseModel.success(BeanUtil.convertPage(iPage,ProjectuserIo.class), body.getHeader());
	}
	
	//查询项目成员记录
	public ResponseModel<List<ProjectuserIo>> queryAll(RequestModel<ProjectuserQueryIo> body){
		logger.info("查询项目成员数据列表 ===> " + body.getParameter());
		QueryWrapper<Projectuser> queryWrapper = new QueryWrapper<>();
		if(StringUtils.isNotEmpty(body.getParameter().getProjectid())) queryWrapper.like("projectid", body.getParameter().getProjectid().trim());
		if(StringUtils.isNotEmpty(body.getParameter().getUserid())) queryWrapper.like("userid", body.getParameter().getUserid().trim());
		if(StringUtils.isNotEmpty(body.getParameter().getStatus())) queryWrapper.like("status", body.getParameter().getStatus().trim());
        List<Projectuser> projectusers = super.list(queryWrapper);
        if(projectusers == null) return ResponseModel.failure("获取数据发生异常，请重试");
        return ResponseModel.success(BeanUtil.convert(projectusers,ProjectuserIo.class),body.getHeader());
	}
	
	//获取单条项目成员
	public ResponseModel<ProjectuserIo> view(RequestModel<ProjectuserViewIo> body){
		logger.info("查询项目成员详情数据 ===> " + body.getParameter());
		Projectuser projectuser = super.getById(body.getParameter().getId());
		if(projectuser == null) return ResponseModel.failure("未找到对应的数据记录");
		return ResponseModel.success(BeanUtil.convert(projectuser,ProjectuserIo.class),body.getHeader());
	}
	
	//保存项目成员
	public ResponseModel<Long> add(RequestModel<ProjectuserAddIo> body){
		logger.info("保存项目成员数据 ===> " + body.getParameter());
		Projectuser projectuser = BeanUtil.convert(body.getParameter(),Projectuser.class);
		super.save(projectuser);
		return ResponseModel.success("保存成功",projectuser.getId(),body.getHeader());
	}
	
	//删除项目成员
	public ResponseModel<String> delete(RequestModel<String> body){
		logger.info("删除项目成员数据 ===> " + body.getParameter());
		StringUtil.isValidIds(body.getParameter());//验证ID是否合法
		String[] idsArr = body.getParameter().split(",");
	    for(String idStr:idsArr){
            super.removeById(Integer.parseInt(idStr));
        }
		return ResponseModel.success("删除成功",body.getParameter(),body.getHeader());
	}
	
	//修改项目成员
	public ResponseModel<Long> update(RequestModel<ProjectuserUpdateIo> body){
		logger.info("修改项目成员数据 ===> " + body.getParameter());
		Projectuser projectuser = BeanUtil.convert(body.getParameter(),Projectuser.class);
		super.updateById(projectuser);
		return ResponseModel.success("修改成功",projectuser.getId(),body.getHeader());
	}
	
	
	// 导入项目成员
	@Transactional(rollbackFor = CommonException.class)
	public ResponseModel<String> imports(RequestModel<List<ProjectuserAddIo>> body){
		logger.info("导入项目成员数据 ===> 文件" );
		int imp_num = 0;
		String[] titleNameArray = {"项目id","用户id","人员状态 1启用0禁用","创建时间"};
		String[] fieldNameArray = {"projectid","userid","status","createTime"};
		try {
			for (ProjectuserAddIo projectuserAddIo : body.getParameter()) {
				Projectuser projectuser = BeanUtil.convert(projectuserAddIo,Projectuser.class);
				super.save(projectuser);
				imp_num++;
			}
		}catch (Exception e) {
			throw new CommonException(500,"导入失败,请确认Excel内容是否正确。</br>错误行号："+(imp_num+2)+"。</br>错误信息："+ExcelUtil.getPointOfException(e.getMessage(),fieldNameArray,titleNameArray)+"。");
		}
		return ResponseModel.success("导入成功，总条数："+imp_num+"条！");
	}

	// 查询全部用户和项目成员
	@Override
	public ResponseModel<Map<String, Object>> queryUserAndProjectuser(RequestModel<ProjectuserQueryIo> body) {
		logger.info("查询全部用户和项目成员 ===> " + body.getParameter());
		Map<String,Object> resultMap = new HashMap<>();
		QueryWrapper<Projectuser> queryWrapper = new QueryWrapper<>();
		if(StringUtils.isNotEmpty(body.getParameter().getProjectid())) queryWrapper.like("projectid", body.getParameter().getProjectid().trim());
		List<Projectuser> projectusers = super.list(queryWrapper);
		// 查询全部用户
		QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
		List<User> users = userService.list(userQueryWrapper);
		// 区分用户和已添加用户
		String ids = projectusers.stream().map(p -> p.getUserid()).collect(Collectors.joining(","));
		List<User> fileterUsers = users.stream().filter(u -> !ids.contains(u.getUserid())).collect(Collectors.toList());
		resultMap.put("projectusers", projectusers);
		resultMap.put("users", fileterUsers);
		return ResponseModel.success(resultMap,body.getHeader());
	}
}