package com.sgcc.code.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sgcc.code.common.utils.UapPage;
import com.sgcc.code.common.web.RequestModel;
import com.sgcc.code.common.web.ResponseModel;
import com.sgcc.code.entity.Shell;
import com.sgcc.code.entity.io.shell.*;

import javax.servlet.http.HttpServletRequest;
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
public interface ShellService extends IService<Shell> {

	/**
	 * 查询脚本管理分页数据
	 * @param body
	 * @return
	 */
	public ResponseModel<UapPage<ShellIo>> query(RequestModel<ShellQueryIo> body, HttpServletRequest httpServletRequest);
	
	/**
	 * 查询脚本管理记录
	 * @param body
	 * @return
	 */
	public ResponseModel<List<ShellIo>> queryAll(RequestModel<ShellQueryIo> body);
	
	/**
	 * 获取单条脚本管理
	 * @param body
	 * @return
	 */
	public ResponseModel<ShellIo> view(RequestModel<ShellViewIo> body);
	
	/**
	 * 保存脚本管理
	 * @param body
	 * @return
	 */
	public ResponseModel<Boolean> add(RequestModel<ShellAddIo> body, HttpServletRequest httpServletRequest);
	
	/**
	 * 删除脚本管理
	 * @param body
	 * @return
	 */
	public ResponseModel<Boolean> delete(RequestModel<String> body);
	
	/**
	 * 修改脚本管理
	 * @param body
	 * @return
	 */
	public ResponseModel<Boolean> update(RequestModel<ShellUpdateIo> body, HttpServletRequest httpServletRequest);
	
	
	/**
	 * 导入脚本管理
	 * @param body
	 * @return
	 */
	public ResponseModel<String> imports(RequestModel<List<ShellAddIo>> body);

	/**
	 * 执行脚本
	 * @param body
	 * @return
	 */
	ResponseModel<Map> execueteShell(RequestModel<ShellViewIo> body, HttpServletRequest httpServletRequest);
}