package com.sgcc.code.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sgcc.code.common.exception.CommonException;
import com.sgcc.code.common.utils.*;
import com.sgcc.code.common.web.RequestModel;
import com.sgcc.code.common.web.ResponseModel;
import com.sgcc.code.entity.Shell;
import com.sgcc.code.entity.User;
import com.sgcc.code.entity.io.shell.*;
import com.sgcc.code.mapper.ShellMapper;
import com.sgcc.code.service.ShellService;
import com.sgcc.code.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * 脚本管理事务
 * </p>
 *
 * @author liugohua
 * @since Mon Mar 06 09:59:00 CST 2023
 */
@Service
public class ShellServiceImpl extends ServiceImpl<ShellMapper, Shell> implements ShellService {

	/**
	 * MD5加密 盐值
	 */
	@Value("${zeus.salt}")
	private String SALTER;

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ShellMapper shellMapper;

	@Autowired
	private UserService userService;

	//查询脚本管理分页数据
	public ResponseModel<UapPage<ShellIo>> query(RequestModel<ShellQueryIo> body, HttpServletRequest httpServletRequest){
		logger.info("查询脚本管理分页数据 ===> " + body.getParameter());
		QueryWrapper<Map> queryWrapper = new QueryWrapper<>();
		User user = userService.findByUsername(httpServletRequest);
		if(user!=null) queryWrapper.eq("userid", user.getUserid());
		if(StringUtils.isNotEmpty(body.getParameter().getTitle())) queryWrapper.like("title", body.getParameter().getTitle());
		if(StringUtils.isNotEmpty(body.getParameter().getShellDescribe())) queryWrapper.like("shell_describe", body.getParameter().getShellDescribe());
		if(StringUtils.isNotEmpty(body.getParameter().getShellIp())) queryWrapper.like("shell_ip", body.getParameter().getShellIp());
		if(StringUtils.isNotEmpty(body.getParameter().getShellPort())) queryWrapper.like("shell_port", body.getParameter().getShellPort());
		if(StringUtils.isNotEmpty(body.getParameter().getShellUsername())) queryWrapper.like("shell_username", body.getParameter().getShellUsername());
		if(StringUtils.isNotEmpty(body.getParameter().getShellPassword())) queryWrapper.like("shell_password", body.getParameter().getShellPassword());
		if(StringUtils.isNotEmpty(body.getParameter().getShellContent())) queryWrapper.like("shell_content", body.getParameter().getShellContent());
		//查询出mybatisplus的分页对象 并转换成内部分页对象
        //IPage<Shell> iPage = super.page(new Page(body.getCurrent(), body.getSize()), queryWrapper);
		// 自定义分页查询
		queryWrapper.orderByDesc("create_time");
		IPage<Map> iPage = shellMapper.selectPageList(new Page(body.getCurrent(), body.getSize()), queryWrapper);
        return ResponseModel.success(BeanUtil.convertPage(iPage,ShellIo.class), body.getHeader());
	}
	
	//查询脚本管理记录
	public ResponseModel<List<ShellIo>> queryAll(RequestModel<ShellQueryIo> body){
		logger.info("查询脚本管理数据列表 ===> " + body.getParameter());
		QueryWrapper<Shell> queryWrapper = new QueryWrapper<>();
		if(StringUtils.isNotEmpty(body.getParameter().getTitle())) queryWrapper.like("title", body.getParameter().getTitle().trim());
		if(StringUtils.isNotEmpty(body.getParameter().getShellDescribe())) queryWrapper.like("shell_describe", body.getParameter().getShellDescribe().trim());
		if(StringUtils.isNotEmpty(body.getParameter().getShellIp())) queryWrapper.like("shell_ip", body.getParameter().getShellIp().trim());
		if(StringUtils.isNotEmpty(body.getParameter().getShellPort())) queryWrapper.like("shell_port", body.getParameter().getShellPort().trim());
		if(StringUtils.isNotEmpty(body.getParameter().getShellUsername())) queryWrapper.like("shell_username", body.getParameter().getShellUsername().trim());
		if(StringUtils.isNotEmpty(body.getParameter().getShellPassword())) queryWrapper.like("shell_password", body.getParameter().getShellPassword().trim());
		if(StringUtils.isNotEmpty(body.getParameter().getShellContent())) queryWrapper.like("shell_content", body.getParameter().getShellContent().trim());
        List<Shell> shells = super.list(queryWrapper);
        if(shells == null) return ResponseModel.failure("获取数据发生异常，请重试");
        return ResponseModel.success(BeanUtil.convert(shells,ShellIo.class),body.getHeader());
	}
	
	//获取单条脚本管理
	public ResponseModel<ShellIo> view(RequestModel<ShellViewIo> body){
		logger.info("查询脚本管理详情数据 ===> " + body.getParameter());
		Shell shell = shellMapper.getDetail(body.getParameter().getId());
		if(shell == null) return ResponseModel.success("未找到对应的数据记录", null, null);
		return ResponseModel.success(BeanUtil.convert(shell,ShellIo.class),body.getHeader());
	}
	
	//保存脚本管理
	public ResponseModel<Boolean> add(RequestModel<ShellAddIo> body, HttpServletRequest httpServletRequest){
		logger.info("保存脚本管理数据 ===> " + body.getParameter());
		User user = userService.findByUsername(httpServletRequest);
		Shell shell = BeanUtil.convert(body.getParameter(),Shell.class);
		shell.setUserid(user.getUserid());
		//用户名密码加密
		String sk = DigestUtils.md5Hex(user.getUsername()+SALTER);
		try {
			String encodeName = ThreeDESUtils.encode(shell.getShellUsername(), sk.substring(0,24));
			String encodePwd = ThreeDESUtils.encode(shell.getShellPassword(), sk.substring(0,24));
			shell.setShellUsername(encodeName);
			shell.setShellPassword(encodePwd);
		}catch (Exception e){
			e.printStackTrace();
		}
		return ResponseModel.success("保存成功",super.save(shell),body.getHeader());
	}
	
	//删除脚本管理
	public ResponseModel<Boolean> delete(RequestModel<String> body){
		logger.info("删除脚本管理数据 ===> " + body.getParameter());
		boolean flag = false;
		StringUtil.isValidIds(body.getParameter());//验证ID是否合法
		String[] idsArr = body.getParameter().split(",");
	    for(String idStr:idsArr){
			flag = super.removeById(Integer.parseInt(idStr));
        }
		return ResponseModel.success("删除成功",flag,body.getHeader());
	}
	
	//修改脚本管理
	public ResponseModel<Boolean> update(RequestModel<ShellUpdateIo> body, HttpServletRequest httpServletRequest){
		logger.info("修改脚本管理数据 ===> " + body.getParameter());
		User user = userService.findByUsername(httpServletRequest);
		Shell shell = BeanUtil.convert(body.getParameter(),Shell.class);
		//用户名密码加密
		String sk = DigestUtils.md5Hex(user.getUsername()+SALTER);
		try {
			String encodeName = ThreeDESUtils.encode(shell.getShellUsername(), sk.substring(0,24));
			String encodePwd = ThreeDESUtils.encode(shell.getShellPassword(), sk.substring(0,24));
			shell.setShellUsername(encodeName);
			shell.setShellPassword(encodePwd);
		}catch (Exception e){
			e.printStackTrace();
		}
		return ResponseModel.success("修改成功",super.updateById(shell),body.getHeader());
	}
	
	
	// 导入脚本管理
	@Transactional(rollbackFor = CommonException.class)
	public ResponseModel<String> imports(RequestModel<List<ShellAddIo>> body){
		logger.info("导入脚本管理数据 ===> 文件" );
		int imp_num = 0;
		String[] titleNameArray = {"脚本标题","脚本描述","iP","端口号","用户名","密码","脚本内容","创建时间"};
		String[] fieldNameArray = {"title","shellDescribe","shellIp","shellPort","shellUsername","shellPassword","shellContent","createTime"};
		try {
			for (ShellAddIo shellAddIo : body.getParameter()) {
				Shell shell = BeanUtil.convert(shellAddIo,Shell.class);
				super.save(shell);
				imp_num++;
			}
		}catch (Exception e) {
			throw new CommonException(500,"导入失败,请确认Excel内容是否正确。</br>错误行号："+(imp_num+2)+"。</br>错误信息："+ExcelUtil.getPointOfException(e.getMessage(),fieldNameArray,titleNameArray)+"。");
		}
		return ResponseModel.success("导入成功，总条数："+imp_num+"条！");
	}

	// 执行脚本
	@Override
	public ResponseModel<Map> execueteShell(RequestModel<ShellViewIo> body, HttpServletRequest httpServletRequest) {
		logger.info("执行脚本 ===> " + body.getParameter());
		String executeContent = "";
		Map resultMap = new HashMap<>();
		User user = userService.findByUsername(httpServletRequest);
		Shell shell = shellMapper.selectById(body.getParameter().getId());
		String sk = DigestUtils.md5Hex(user.getUsername()+SALTER);
		try {
			String shellIp = shell.getShellIp();
			int shellPort = Integer.parseInt(shell.getShellPort());
			String shellContent = shell.getShellContent();
			String decodeName = ThreeDESUtils.decode(shell.getShellUsername(), sk.substring(0,24));
			String decodePwd = ThreeDESUtils.decode(shell.getShellPassword(), sk.substring(0,24));
			executeContent = SSHUtil.execRemoteCommand(shellIp, shellPort, decodeName, decodePwd, shellContent);
			if(executeContent.length()>100){
				resultMap.put("msg", "脚本执行成功！");
				resultMap.put("flag", true);
				resultMap.put("data", executeContent);
			}else{
				resultMap.put("msg", executeContent);
				resultMap.put("flag", false);
				resultMap.put("data", null);
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return ResponseModel.success("操作成功", resultMap);
	}
}