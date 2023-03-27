package com.sgcc.code.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sgcc.code.common.utils.UapPage;
import com.sgcc.code.common.web.RequestModel;
import com.sgcc.code.common.web.ResponseModel;
import com.sgcc.code.entity.Todo;
import com.sgcc.code.entity.io.todo.*;

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
public interface TodoService extends IService<Todo> {

	/**
	 * 查询待办分页数据
	 * @param body
	 * @return
	 */
	public ResponseModel<UapPage<TodoIo>> query(RequestModel<TodoQueryIo> body);
	
	/**
	 * 查询待办记录
	 * @param body
	 * @return
	 */
	public ResponseModel<List<TodoIo>> queryAll(RequestModel<TodoQueryIo> body, HttpServletRequest httpServletRequest);
	
	/**
	 * 获取单条待办
	 * @param body
	 * @return
	 */
	public ResponseModel<TodoIo> view(RequestModel<TodoViewIo> body);
	
	/**
	 * 保存待办
	 * @param body
	 * @return
	 */
	public ResponseModel<Long> add(RequestModel<TodoAddIo> body, HttpServletRequest httpServletRequest);
	
	/**
	 * 删除待办
	 * @param body
	 * @return
	 */
	public ResponseModel<String> delete(RequestModel<String> body);
	
	/**
	 * 修改待办
	 * @param body
	 * @return
	 */
	public ResponseModel<Long> update(RequestModel<TodoUpdateIo> body);
	
	
	/**
	 * 导入待办
	 * @param body
	 * @return
	 */
	public ResponseModel<String> imports(RequestModel<List<TodoAddIo>> body);
	

	
    
}