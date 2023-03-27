package com.sgcc.code.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sgcc.code.common.utils.UapPage;
import com.sgcc.code.common.web.RequestModel;
import com.sgcc.code.common.web.ResponseModel;
import com.sgcc.code.entity.CommonInfos;
import com.sgcc.code.entity.io.commonInfos.*;

import java.util.List;

/**
 * <p>
 * 项目公共配置信息事务
 * </p>
 *
 * @author liugohua
 * @since Tue Jan 31 10:16:22 CST 2023
 */
public interface CommonInfosService extends IService<CommonInfos> {

	/**
	 * 查询项目公共配置信息分页数据
	 * @param body
	 * @return
	 */
	public ResponseModel<UapPage<CommonInfosIo>> query(RequestModel<CommonInfosQueryIo> body);
	
	/**
	 * 查询项目公共配置信息记录
	 * @param body
	 * @return
	 */
	public ResponseModel<List<CommonInfosIo>> queryAll(RequestModel<CommonInfosQueryIo> body);
	
	/**
	 * 获取单条项目公共配置信息
	 * @param body
	 * @return
	 */
	public ResponseModel<CommonInfosIo> view(RequestModel<CommonInfosViewIo> body);
	
	/**
	 * 保存项目公共配置信息
	 * @param body
	 * @return
	 */
	public ResponseModel<Long> add(RequestModel<CommonInfosAddIo> body);
	
	/**
	 * 删除项目公共配置信息
	 * @param body
	 * @return
	 */
	public ResponseModel<String> delete(RequestModel<String> body);
	
	/**
	 * 修改项目公共配置信息
	 * @param body
	 * @return
	 */
	public ResponseModel<Long> update(RequestModel<CommonInfosUpdateIo> body);
	
	
	/**
	 * 导入项目公共配置信息
	 * @param body
	 * @return
	 */
	public ResponseModel<String> imports(RequestModel<List<CommonInfosAddIo>> body);
	

	
    
}