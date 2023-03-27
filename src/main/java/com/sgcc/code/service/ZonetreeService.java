package com.sgcc.code.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sgcc.code.common.utils.UapPage;
import com.sgcc.code.common.web.RequestModel;
import com.sgcc.code.common.web.ResponseModel;
import com.sgcc.code.entity.Zonetree;
import com.sgcc.code.entity.io.zonetree.*;

import java.util.List;

/**
 * <p>
 * 空间树事务
 * </p>
 *
 * @author liugohua
 * @since Fri Dec 30 09:58:13 CST 2022
 */
public interface ZonetreeService extends IService<Zonetree> {

	/**
	 * 查询空间树分页数据
	 * @param body
	 * @return
	 */
	public ResponseModel<UapPage<ZonetreeIo>> query(RequestModel<ZonetreeQueryIo> body);
	
	/**
	 * 查询空间树记录
	 * @param body
	 * @return
	 */
	public ResponseModel<List<ZonetreeIo>> queryAll(RequestModel<ZonetreeQueryIo> body);
	
	/**
	 * 获取单条空间树
	 * @param body
	 * @return
	 */
	public ResponseModel<ZonetreeIo> view(RequestModel<ZonetreeViewIo> body);
	
	/**
	 * 保存空间树
	 * @param body
	 * @return
	 */
	public ResponseModel<Boolean> add(RequestModel<ZonetreeAddIo> body);
	
	/**
	 * 删除空间树
	 * @param body
	 * @return
	 */
	public ResponseModel<Boolean> delete(RequestModel<ZonetreeDeleteIo> body);
	
	/**
	 * 修改空间树
	 * @param body
	 * @return
	 */
	public ResponseModel<Boolean> update(RequestModel<ZonetreeUpdateIo> body);
	
	
	/**
	 * 导入空间树
	 * @param body
	 * @return
	 */
	public ResponseModel<String> imports(RequestModel<List<ZonetreeAddIo>> body);
	

	
    
}