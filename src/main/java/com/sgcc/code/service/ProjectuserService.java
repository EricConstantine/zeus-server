package com.sgcc.code.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sgcc.code.common.utils.UapPage;
import com.sgcc.code.common.web.RequestModel;
import com.sgcc.code.common.web.ResponseModel;
import com.sgcc.code.entity.Projectuser;
import com.sgcc.code.entity.io.projectuser.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 项目成员事务
 * </p>
 *
 * @author liugohua
 * @since Tue Jan 03 10:07:47 CST 2023
 */
public interface ProjectuserService extends IService<Projectuser> {

	/**
	 * 查询项目成员分页数据
	 * @param body
	 * @return
	 */
	public ResponseModel<UapPage<ProjectuserIo>> query(RequestModel<ProjectuserQueryIo> body);
	
	/**
	 * 查询项目成员记录
	 * @param body
	 * @return
	 */
	public ResponseModel<List<ProjectuserIo>> queryAll(RequestModel<ProjectuserQueryIo> body);
	
	/**
	 * 获取单条项目成员
	 * @param body
	 * @return
	 */
	public ResponseModel<ProjectuserIo> view(RequestModel<ProjectuserViewIo> body);
	
	/**
	 * 保存项目成员
	 * @param body
	 * @return
	 */
	public ResponseModel<Long> add(RequestModel<ProjectuserAddIo> body);
	
	/**
	 * 删除项目成员
	 * @param body
	 * @return
	 */
	public ResponseModel<String> delete(RequestModel<String> body);
	
	/**
	 * 修改项目成员
	 * @param body
	 * @return
	 */
	public ResponseModel<Long> update(RequestModel<ProjectuserUpdateIo> body);
	
	
	/**
	 * 导入项目成员
	 * @param body
	 * @return
	 */
	public ResponseModel<String> imports(RequestModel<List<ProjectuserAddIo>> body);

	/**
	 * 查询全部用户和项目成员
	 * @param body
	 * @return
	 */
    ResponseModel<Map<String, Object>> queryUserAndProjectuser(RequestModel<ProjectuserQueryIo> body);
}