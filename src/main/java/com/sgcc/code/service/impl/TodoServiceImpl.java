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
import com.sgcc.code.entity.Todo;
import com.sgcc.code.entity.User;
import com.sgcc.code.entity.io.todo.*;
import com.sgcc.code.mapper.TodoMapper;
import com.sgcc.code.service.TodoService;
import com.sgcc.code.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * <p>
 * 待办事务
 * </p>
 *
 * @author liugohua
 * @since Tue Jan 31 10:16:45 CST 2023
 */
@Service
public class TodoServiceImpl extends ServiceImpl<TodoMapper, Todo> implements TodoService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserService userService;

	//查询待办分页数据
	public ResponseModel<UapPage<TodoIo>> query(RequestModel<TodoQueryIo> body){
		logger.info("查询待办分页数据 ===> " + body.getParameter());
		QueryWrapper<Todo> queryWrapper = new QueryWrapper<>();
		if(StringUtils.isNotEmpty(body.getParameter().getUserid())) queryWrapper.like("userid", body.getParameter().getUserid());
		if(StringUtils.isNotEmpty(body.getParameter().getTitle())) queryWrapper.like("title", body.getParameter().getTitle());
		if(StringUtils.isNotEmpty(body.getParameter().getContent())) queryWrapper.like("content", body.getParameter().getContent());
		if(body.getParameter().getStatus() != null) queryWrapper.eq("status", body.getParameter().getStatus());
		//查询出mybatisplus的分页对象 并转换成内部分页对象
        IPage<Todo> iPage = super.page(new Page(body.getCurrent(), body.getSize()), queryWrapper);
        return ResponseModel.success(BeanUtil.convertPage(iPage,TodoIo.class), body.getHeader());
	}
	
	//查询待办记录
	public ResponseModel<List<TodoIo>> queryAll(RequestModel<TodoQueryIo> body, HttpServletRequest httpServletRequest){
		logger.info("查询待办数据列表 ===> " + body.getParameter());
		QueryWrapper<Todo> queryWrapper = new QueryWrapper<>();
		User user = userService.findByUsername(httpServletRequest);
		queryWrapper.eq("userid", user.getUserid());
		queryWrapper.orderByAsc("status");
		queryWrapper.orderByDesc("create_time");
        List<Todo> todos = super.list(queryWrapper);
        if(todos == null) return ResponseModel.failure("获取数据发生异常，请重试");
        return ResponseModel.success(BeanUtil.convert(todos,TodoIo.class),body.getHeader());
	}
	
	//获取单条待办
	public ResponseModel<TodoIo> view(RequestModel<TodoViewIo> body){
		logger.info("查询待办详情数据 ===> " + body.getParameter());
		Todo todo = super.getById(body.getParameter().getId());
		if(todo == null) return ResponseModel.failure("未找到对应的数据记录");
		return ResponseModel.success(BeanUtil.convert(todo,TodoIo.class),body.getHeader());
	}
	
	//保存待办
	public ResponseModel<Long> add(RequestModel<TodoAddIo> body, HttpServletRequest httpServletRequest){
		logger.info("保存待办数据 ===> " + body.getParameter());
		Todo todo = BeanUtil.convert(body.getParameter(),Todo.class);
		User user = userService.findByUsername(httpServletRequest);
		todo.setUserid(user.getUserid());
		todo.setStatus(0);//默认未完成状态
		super.save(todo);
		return ResponseModel.success("保存成功",todo.getId(),body.getHeader());
	}
	
	//删除待办
	public ResponseModel<String> delete(RequestModel<String> body){
		logger.info("删除待办数据 ===> " + body.getParameter());
		StringUtil.isValidIds(body.getParameter());//验证ID是否合法
		String[] idsArr = body.getParameter().split(",");
	    for(String idStr:idsArr){
            super.removeById(Integer.parseInt(idStr));
        }
		return ResponseModel.success("删除成功",body.getParameter(),body.getHeader());
	}
	
	//修改待办
	public ResponseModel<Long> update(RequestModel<TodoUpdateIo> body){
		logger.info("修改待办数据 ===> " + body.getParameter());
		Todo todo = BeanUtil.convert(body.getParameter(),Todo.class);
		super.updateById(todo);
		return ResponseModel.success("修改成功",todo.getId(),body.getHeader());
	}
	
	
	// 导入待办
	@Transactional(rollbackFor = CommonException.class)
	public ResponseModel<String> imports(RequestModel<List<TodoAddIo>> body){
		logger.info("导入待办数据 ===> 文件" );
		int imp_num = 0;
		String[] titleNameArray = {"用户id","代办标题","代办内容","代办状态 0未完成 1已完成","创建时间"};
		String[] fieldNameArray = {"userid","title","content","status","createTime"};
		try {
			for (TodoAddIo todoAddIo : body.getParameter()) {
				Todo todo = BeanUtil.convert(todoAddIo,Todo.class);
				super.save(todo);
				imp_num++;
			}
		}catch (Exception e) {
			throw new CommonException(500,"导入失败,请确认Excel内容是否正确。</br>错误行号："+(imp_num+2)+"。</br>错误信息："+ExcelUtil.getPointOfException(e.getMessage(),fieldNameArray,titleNameArray)+"。");
		}
		return ResponseModel.success("导入成功，总条数："+imp_num+"条！");
	}
	

	
}