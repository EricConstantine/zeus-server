package com.sgcc.code.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sgcc.code.common.utils.UapPage;
import com.sgcc.code.common.web.RequestModel;
import com.sgcc.code.common.web.ResponseModel;
import com.sgcc.code.entity.WebLibrary;
import com.sgcc.code.entity.io.webLibrary.*;

import java.util.List;

/**
 * <p>
 * 网页库事务
 * </p>
 *
 * @author liugohua
 * @since Mon Mar 06 17:53:18 CST 2023
 */
public interface WebLibraryService extends IService<WebLibrary> {

	/**
	 * 查询网页库分页数据
	 * @param body
	 * @return
	 */
	public ResponseModel<UapPage<WebLibraryIo>> query(RequestModel<WebLibraryQueryIo> body);
	
	/**
	 * 查询网页库记录
	 * @param body
	 * @return
	 */
	public ResponseModel<List<WebLibraryIo>> queryAll(RequestModel<WebLibraryQueryIo> body);
	
	/**
	 * 获取单条网页库
	 * @param body
	 * @return
	 */
	public ResponseModel<WebLibraryIo> view(RequestModel<WebLibraryViewIo> body);
	
	/**
	 * 保存网页库
	 * @param body
	 * @return
	 */
	public ResponseModel<Integer> add(RequestModel<WebLibraryAddIo> body);
	
	/**
	 * 删除网页库
	 * @param body
	 * @return
	 */
	public ResponseModel<String> delete(RequestModel<String> body);
	
	/**
	 * 修改网页库
	 * @param body
	 * @return
	 */
	public ResponseModel<Integer> update(RequestModel<WebLibraryUpdateIo> body);
	
	
	/**
	 * 导入网页库
	 * @param body
	 * @return
	 */
	public ResponseModel<String> imports(RequestModel<List<WebLibraryAddIo>> body);

	/**
	 * 普通用户-查询网页库分页数据
	 * @param body
	 * @return
	 */
	ResponseModel<UapPage<WebLibraryIo>> userQuery(RequestModel<WebLibraryQueryIo> body);
}