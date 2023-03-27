package com.sgcc.code.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sgcc.code.common.utils.UapPage;
import com.sgcc.code.common.web.RequestModel;
import com.sgcc.code.common.web.ResponseModel;
import com.sgcc.code.entity.User;
import com.sgcc.code.entity.io.user.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户事务
 * </p>
 *
 * @author yangrui
 * @since Wed Dec 28 14:46:28 CST 2022
 */
public interface UserService extends IService<User> {

	/**
	 * 查询用户分页数据
	 * @param body
	 * @return
	 */
	public ResponseModel<UapPage<UserIo>> query(RequestModel<UserQueryIo> body);
	
	/**
	 * 查询用户记录
	 * @param body
	 * @return
	 */
	public ResponseModel<List<UserIo>> queryAll(RequestModel<UserQueryIo> body);
	
	/**
	 * 获取单条用户
	 * @param body
	 * @return
	 */
	public ResponseModel<UserIo> view(RequestModel<UserViewIo> body);
	
	/**
	 * 保存用户
	 * @param body
	 * @return
	 */
	public ResponseModel<Boolean> add(RequestModel<UserAddIo> body);
	
	/**
	 * 删除用户
	 * @param body
	 * @return
	 */
	public ResponseModel<Boolean> delete(RequestModel<String> body);
	
	/**
	 * 修改用户
	 * @param body
	 * @return
	 */
	public ResponseModel<Boolean> update(RequestModel<UserUpdateIo> body);
	
	
	/**
	 * 导入用户
	 * @param body
	 * @return
	 */
	public ResponseModel<String> imports(RequestModel<List<UserAddIo>> body);

	/**
	 * 用户登录
	 * @param body
	 * @return
	 */
	ResponseModel<Map> login(RequestModel<UserQueryIo> body);

	/**
	 * 获取用户信息
	 * @param httpServletRequest
	 * @return
	 */
	ResponseModel<Map> info(HttpServletRequest httpServletRequest);

	/**
	 * 根据用户名查询用户是否存在
	 * @param body
	 * @return
	 */
	ResponseModel<Boolean> findByUsername(RequestModel<UserQueryIo> body);

	/**
	 * HttpServletRequest httpServletRequest,
	 * @param httpServletRequest
	 * @return
	 */
	ResponseModel<List<Map<String,Object>>> getProjects(HttpServletRequest httpServletRequest);

	/**
	 * 根据request请求获取用户信息
	 * @param httpServletRequest
	 * @return
	 */
	User findByUsername(HttpServletRequest httpServletRequest);
}