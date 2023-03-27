package com.sgcc.code.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sgcc.code.common.utils.UapPage;
import com.sgcc.code.common.web.RequestModel;
import com.sgcc.code.common.web.ResponseModel;
import com.sgcc.code.entity.Config;
import com.sgcc.code.entity.io.config.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 配置事务
 * </p>
 *
 * @author liugohua
 * @since Tue Jan 10 09:42:21 CST 2023
 */
public interface ConfigService extends IService<Config> {

	/**
	 * 查询配置分页数据
	 * @param body
	 * @return
	 */
	public ResponseModel<UapPage<ConfigIo>> query(RequestModel<ConfigQueryIo> body, HttpServletRequest httpServletRequest);
	
	/**
	 * 查询配置记录
	 * @param body
	 * @return
	 */
	public ResponseModel<List<ConfigIo>> queryAll(RequestModel<ConfigQueryIo> body);
	
	/**
	 * 获取单条配置
	 * @param body
	 * @return
	 */
	public ResponseModel<ConfigIo> view(RequestModel<ConfigViewIo> body);
	
	/**
	 * 保存配置
	 * @param body
	 * @return
	 */
	public ResponseModel<Boolean> add(RequestModel<ConfigAddIo> body, HttpServletRequest httpServletRequest);
	
	/**
	 * 删除配置
	 * @param body
	 * @return
	 */
	public ResponseModel<Boolean> delete(RequestModel<String> body);
	
	/**
	 * 修改配置
	 * @param body
	 * @return
	 */
	public ResponseModel<Boolean> update(RequestModel<ConfigUpdateIo> body, HttpServletRequest httpServletRequest);
	
	
	/**
	 * 导入配置
	 * @param body
	 * @return
	 */
	public ResponseModel<String> imports(RequestModel<List<ConfigAddIo>> body);

	/**
	 * 获取用户配置列表
	 * @param httpServletRequest
	 * @return
	 */
	ResponseModel<List<Map<String, Object>>> getConfigs(HttpServletRequest httpServletRequest);

	/**
	 * 获取配置项目详情
	 * @param body
	 * @return
	 */
	ResponseModel<Map<String, Object>> getProjectDetail(RequestModel<ConfigViewIo> body);

	/**
	 * 测试数据库连接
	 * @param body
	 * @return
	 */
	ResponseModel<Map> testDb(RequestModel<ConfigConnIo> body);

	/**
	 * 获取配置项目数据库表信息
	 * @param body
	 * @return
	 */
	ResponseModel<List<Map>> getConnTables(RequestModel<ConfigConnIo> body);

	/**
	 * 获取配置项目数据库表信息详情
	 * @param body
	 * @return
	 */
	ResponseModel<List<Map>> getConnTableView(RequestModel<ConfigConnIo> body);
}