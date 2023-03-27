package com.sgcc.code.controller;

import com.sgcc.code.common.utils.UapPage;
import com.sgcc.code.common.web.RequestModel;
import com.sgcc.code.common.web.ResponseModel;
import com.sgcc.code.entity.io.user.*;
import com.sgcc.code.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户控制类
 * </p>
 *
 * @author yangrui
 * @since Wed Dec 28 14:46:28 CST 2022
 */
@Api(tags= {"用户"})
@RestController
@RequestMapping("/user")
public class UserController{

	@Autowired
	UserService userService;

	@ApiOperation(value="查询用户分页数据", notes = "查询用户分页数据")
	@PostMapping("/query")
	public ResponseModel<UapPage<UserIo>> query(@Valid @RequestBody RequestModel<UserQueryIo> body){
		return userService.query(body);
	}

	@ApiOperation(value="查询用户数据", notes = "查询用户数据，不带分页")
	@PostMapping("/queryAll")
	public ResponseModel<List<UserIo>> queryAll(@Valid @RequestBody RequestModel<UserQueryIo> body){
		return userService.queryAll(body);
	}

	@ApiOperation(value = "查询用户详情数据" ,notes = "根据ID获取单条数据")
	@PostMapping("/view")
	public ResponseModel<UserIo> view(@Valid @RequestBody RequestModel<UserViewIo> body){
		return userService.view(body);
	}

	@ApiOperation(value="保存用户", notes = "保存用户")
	@PostMapping("/add")
	public ResponseModel<Boolean> add(@Valid @RequestBody RequestModel<UserAddIo> body)  {
		return userService.add(body);
	}

	@ApiOperation(value = "删除用户数据", notes = "删除用户" )
	@PostMapping("/delete")
	public ResponseModel<Boolean> delete(@Valid @RequestBody RequestModel<String> body) {
		return userService.delete(body);
	}

	@ApiOperation(value="修改用户数据", notes = "修改用户")
	@PostMapping("/update")
	public ResponseModel<Boolean> update(@Valid @RequestBody RequestModel<UserUpdateIo> body)  {
		return userService.update(body);
	}

	@ApiOperation(value = "导入用户数据", notes = "导入用户")
	@PostMapping("/imports")
	public ResponseModel<String> imports(@RequestBody RequestModel<List<UserAddIo>> body) {
		return userService.imports(body);
	}

	@ApiOperation(value = "用户登录" ,notes = "用户登录" )
	@PostMapping("/login")
	public ResponseModel<Map> login(@RequestBody RequestModel<UserQueryIo> body) {
		return userService.login(body);
	}

	@ApiOperation(value = "获取用户信息" ,notes = "获取用户信息" )
	@PostMapping("/info")
	public ResponseModel<Map> info(HttpServletRequest httpServletRequest) {
		return userService.info(httpServletRequest);
	}

	@ApiOperation(value = "根据用户名查询用户是否存在" ,notes = "根据用户名查询用户是否存在" )
	@PostMapping(value="/findByUsername")
	public ResponseModel<Boolean> findByUsername(@RequestBody RequestModel<UserQueryIo> body){
		return userService.findByUsername(body);
	}

	@ApiOperation(value = "获取用户项目列表" ,notes = "获取用户项目列表" )
	@PostMapping(value="/getProjects")
	public ResponseModel<List<Map<String,Object>>> getProjects(HttpServletRequest httpServletRequest){
		return userService.getProjects(httpServletRequest);
	}


}