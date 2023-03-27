package com.sgcc.code.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sgcc.code.common.exception.CommonException;
import com.sgcc.code.common.utils.*;
import com.sgcc.code.common.web.RequestModel;
import com.sgcc.code.common.web.ResponseModel;
import com.sgcc.code.entity.User;
import com.sgcc.code.entity.Versions;
import com.sgcc.code.entity.io.user.*;
import com.sgcc.code.mapper.UserMapper;
import com.sgcc.code.service.UserService;
import com.sgcc.code.service.VersionsService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
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
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private VersionsService versionsService;

	//查询用户分页数据
	public ResponseModel<UapPage<UserIo>> query(RequestModel<UserQueryIo> body){
		logger.info("查询用户分页数据 ===> " + body.getParameter());
		QueryWrapper<User> queryWrapper = new QueryWrapper<>();
		if(StringUtils.isNotEmpty(body.getParameter().getUserid())) queryWrapper.like("userid", body.getParameter().getUserid());
		if(StringUtils.isNotEmpty(body.getParameter().getUsername())) queryWrapper.like("username", body.getParameter().getUsername());
		if(StringUtils.isNotEmpty(body.getParameter().getPassword())) queryWrapper.like("password", body.getParameter().getPassword());
		if(body.getParameter().getType() != null) queryWrapper.eq("type", body.getParameter().getType());
		queryWrapper.orderByDesc("create_time");
		//查询出mybatisplus的分页对象 并转换成内部分页对象
		// 自定义分页查询
		IPage<User> iPage = super.page(new Page(body.getCurrent(), body.getSize()), queryWrapper);
        return ResponseModel.success(BeanUtil.convertPage(iPage,UserIo.class), body.getHeader());
	}
	
	//查询用户记录
	public ResponseModel<List<UserIo>> queryAll(RequestModel<UserQueryIo> body){
		logger.info("查询用户数据列表 ===> " + body.getParameter());
		QueryWrapper<User> queryWrapper = new QueryWrapper<>();
		if(StringUtils.isNotEmpty(body.getParameter().getUserid())) queryWrapper.like("userid", body.getParameter().getUserid().trim());
		if(StringUtils.isNotEmpty(body.getParameter().getUsername())) queryWrapper.like("username", body.getParameter().getUsername().trim());
		if(StringUtils.isNotEmpty(body.getParameter().getPassword())) queryWrapper.like("password", body.getParameter().getPassword().trim());
		if(body.getParameter().getType() != null) queryWrapper.eq("type", body.getParameter().getType());
		queryWrapper.orderByDesc("create_time");
		List<User> users = super.list(queryWrapper);
        if(users == null) return ResponseModel.failure("获取数据发生异常，请重试");
        return ResponseModel.success(BeanUtil.convert(users,UserIo.class),body.getHeader());
	}
	
	//获取单条用户
	public ResponseModel<UserIo> view(RequestModel<UserViewIo> body){
		logger.info("查询用户详情数据 ===> " + body.getParameter());
		User user = super.getById(body.getParameter().getId());
		if(user == null) return ResponseModel.failure("未找到对应的数据记录");
		return ResponseModel.success(BeanUtil.convert(user,UserIo.class),body.getHeader());
	}
	
	//保存用户
	public ResponseModel<Boolean> add(RequestModel<UserAddIo> body){
		logger.info("保存用户数据 ===> " + body.getParameter());
		User user = BeanUtil.convert(body.getParameter(),User.class);
		QueryWrapper<User> wrapper = new QueryWrapper<>();
		wrapper.eq("username", user.getUsername());
		List<User> users = super.list(wrapper);
		if(users!=null && users.size()>0){
			return ResponseModel.failure("用户名已存在");
		}
		String md5_password = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
		user.setUserid(UUIDUtils.uuid32());
		user.setPassword(md5_password);
		user.setCreateTime(new Timestamp(System.currentTimeMillis()));
		return ResponseModel.success("保存成功",super.save(user),body.getHeader());
	}
	
	//删除用户
	public ResponseModel<Boolean> delete(RequestModel<String> body){
		logger.info("删除用户数据 ===> " + body.getParameter());
		boolean flag = false;
		StringUtil.isValidIds(body.getParameter());//验证ID是否合法
		String[] idsArr = body.getParameter().split(",");
	    for(String idStr:idsArr){
			flag = super.removeById(Integer.parseInt(idStr));
        }
		return ResponseModel.success("删除成功",flag,body.getHeader());
	}
	
	//修改用户
	public ResponseModel<Boolean> update(RequestModel<UserUpdateIo> body){
		logger.info("修改用户数据 ===> " + body.getParameter());
		User user = BeanUtil.convert(body.getParameter(),User.class);
		String md5_password = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
		user.setPassword(md5_password);
		return ResponseModel.success("修改成功",super.updateById(user),body.getHeader());
	}
	
	
	// 导入用户
	@Transactional(rollbackFor = CommonException.class)
	public ResponseModel<String> imports(RequestModel<List<UserAddIo>> body){
		logger.info("导入用户数据 ===> 文件" );
		int imp_num = 0;
		String[] titleNameArray = {"用户id","用户名称","用户密码","用户类型 1系统管理员 2普通用户","创建时间"};
		String[] fieldNameArray = {"userid","username","password","type","createTime"};
		try {
			for (UserAddIo userAddIo : body.getParameter()) {
				User user = BeanUtil.convert(userAddIo,User.class);
				super.save(user);
				imp_num++;
			}
		}catch (Exception e) {
			throw new CommonException(500,"导入失败,请确认Excel内容是否正确。</br>错误行号："+(imp_num+2)+"。</br>错误信息："+ ExcelUtil.getPointOfException(e.getMessage(),fieldNameArray,titleNameArray)+"。");
		}
		return ResponseModel.success("导入成功，总条数："+imp_num+"条！");
	}

	// 用户登录
	@Override
	public ResponseModel<Map> login(RequestModel<UserQueryIo> body) {
		logger.info("获取用户登录数据 ===> " + body.getParameter() );
		String token = "";
		String username = body.getParameter().getUsername();
		String password = body.getParameter().getPassword();
		if (StringUtils.isEmpty(username)){
			return ResponseModel.failure("登录失败,用户名不能为空");
		}
		QueryWrapper<User> wrapper = new QueryWrapper<>();
		wrapper.eq("username", username);
		User loginUser = super.getOne(wrapper);
		if(loginUser==null){
			return ResponseModel.failure("登录失败,用户不存在");
		}else {
			if (StringUtils.isEmpty(password)){
				return ResponseModel.failure("登录失败,密码不能为空");
			}else if (!loginUser.getPassword().equals(DigestUtils.md5DigestAsHex(password.getBytes()))){
				return ResponseModel.failure("登录失败,密码错误");
			}else {
				token= JWT.create().withAudience(loginUser.getUsername()).sign(Algorithm.HMAC256(loginUser.getPassword()));
				QueryWrapper<Versions> queryWrapper = new QueryWrapper<>();
				queryWrapper.eq("current_version", "1");
				Versions versions = versionsService.getOne(queryWrapper);
				Map<String, Object> map = new HashMap<>();
				map.put("token", token);
				map.put("versions", versions);
				return ResponseModel.success("登录成功", map);
			}
		}
	}

	// 根据用户名查询用户是否存在
	@Override
	public ResponseModel<Boolean> findByUsername(RequestModel<UserQueryIo> body) {
		logger.info("获取用户数据 ===> " + body.getParameter() );
		String username = body.getParameter().getUsername();
		QueryWrapper<User> wrapper = new QueryWrapper<>();
		wrapper.eq("username", username);
		List<User> users = super.list(wrapper);
		if(users!=null && users.size()>0){
			return ResponseModel.failure("用户名已存在");
		}
		return ResponseModel.success(true);
	}

	// 获取用户信息
	@Override
	public ResponseModel<Map> info(HttpServletRequest httpServletRequest) {
		User user = findByUsername(httpServletRequest);
		Map<String, Object> map = new HashMap<>();
		ArrayList<String> roles = new ArrayList<>();
		roles.add(user.getType()==1?"admin":"editor");
		map.put("username", user.getUsername());
		map.put("roles", roles);
		map.put("nickname", user.getNickname());
		return ResponseModel.success("获取登陆人信息成功", map);
	}

	// 获取用户项目列表
	@Override
	public ResponseModel<List<Map<String,Object>>> getProjects(HttpServletRequest httpServletRequest) {
		logger.info("获取用户登录数据 ===> ");
		User loginUser = findByUsername(httpServletRequest);
		List<Map<String,Object>> projects = new ArrayList<>();
		if(loginUser.getType()==1){
			projects = userMapper.getAllProjects();
		}else{
			projects = userMapper.getProjects(loginUser.getUserid());
		}
		return ResponseModel.success(projects);
	}

	public User findByUsername(HttpServletRequest httpServletRequest){
		logger.info("根据用户名获取用户登录数据 ===> ");
		String token = httpServletRequest.getHeader("authorization");// 从 http 请求头中取出 token
		// 执行认证
		if (token == null) {
			throw new RuntimeException("无token，请重新登录");
		}
		// 获取 token 中的 user id
		String username;
		try {
			username = JWT.decode(token).getAudience().get(0);
		} catch (JWTDecodeException j) {
			throw new RuntimeException("401");
		}
		QueryWrapper<User> wrapper = new QueryWrapper<>();
		wrapper.eq("username", username);
		User user = super.getOne(wrapper);
		if (user == null) {
			throw new RuntimeException("用户不存在，请重新登录");
		}
		return user;
	}

}