package com.sgcc.code.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sgcc.code.common.utils.UapPage;
import com.sgcc.code.common.web.RequestModel;
import com.sgcc.code.common.web.ResponseModel;
import com.sgcc.code.entity.Project;
import com.sgcc.code.entity.io.project.*;

import java.util.List;

/**
 * <p>
 * 项目事务
 * </p>
 *
 * @author yangrui
 * @since Thu Dec 29 17:20:21 CST 2022
 */
public interface ProjectService extends IService<Project> {

	/**
	 * 查询项目分页数据
	 * @param body
	 * @return
	 */
	public ResponseModel<UapPage<ProjectIo>> query(RequestModel<ProjectQueryIo> body);
	
	/**
	 * 查询项目记录
	 * @param body
	 * @return
	 */
	public ResponseModel<List<ProjectIo>> queryAll(RequestModel<ProjectQueryIo> body);
	
	/**
	 * 获取单条项目
	 * @param body
	 * @return
	 */
	public ResponseModel<ProjectIo> view(RequestModel<ProjectViewIo> body);
	
	/**
	 * 保存项目
	 * @param body
	 * @return
	 */
	public ResponseModel<ProjectIo> add(RequestModel<ProjectAddIo> body);
	
	/**
	 * 删除项目
	 * @param body
	 * @return
	 */
	public ResponseModel<Boolean> delete(RequestModel<String> body);
	
	/**
	 * 修改项目
	 * @param body
	 * @return
	 */
	public ResponseModel<Boolean> update(RequestModel<ProjectUpdateIo> body);
	
	
	/**
	 * 导入项目
	 * @param body
	 * @return
	 */
	public ResponseModel<String> imports(RequestModel<List<ProjectAddIo>> body);

	/**
	 * 复制项目
	 * @param body
	 * @return
	 */
    ResponseModel<Boolean> copy(RequestModel<ProjectUpdateIo> body);
}