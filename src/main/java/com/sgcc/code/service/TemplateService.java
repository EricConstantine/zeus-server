package com.sgcc.code.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sgcc.code.common.utils.UapPage;
import com.sgcc.code.common.web.RequestModel;
import com.sgcc.code.common.web.ResponseModel;
import com.sgcc.code.entity.Template;
import com.sgcc.code.entity.io.template.TemplateAddIo;
import com.sgcc.code.entity.io.template.TemplateIo;
import com.sgcc.code.entity.io.template.TemplateQueryIo;
import com.sgcc.code.entity.io.template.TemplateUpdateIo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 模板事务
 * </p>
 *
 * @author liugohua
 * @since Fri Dec 30 17:52:56 CST 2022
 */
public interface TemplateService extends IService<Template> {

	/**
	 * 查询模板分页数据
	 * @param body
	 * @return
	 */
	public ResponseModel<UapPage<TemplateIo>> query(RequestModel<TemplateQueryIo> body);
	
	/**
	 * 查询模板记录
	 * @param body
	 * @return
	 */
	public ResponseModel<List<TemplateIo>> queryAll(RequestModel<TemplateQueryIo> body);
	
	/**
	 * 获取单条模板
	 * @param body
	 * @return
	 */
	public ResponseModel<TemplateIo> view(RequestModel<TemplateIo> body);
	
	/**
	 * 保存模板
	 * @param body
	 * @return
	 */
	public ResponseModel<Long> add(RequestModel<TemplateAddIo> body);
	
	/**
	 * 删除模板
	 * @param body
	 * @return
	 */
	public ResponseModel<String> delete(RequestModel<String> body);
	
	/**
	 * 修改模板
	 * @param body
	 * @return
	 */
	public ResponseModel<Boolean> update(RequestModel<TemplateUpdateIo> body);
	
	
	/**
	 * 导入模板
	 * @param body
	 * @return
	 */
	public ResponseModel<String> imports(RequestModel<List<TemplateAddIo>> body);

	/**
	 * 生成代码
	 * @param configId
	 * @param treeids
	 * @param dictionarys
	 * @param dbTable
	 * @param dbTableName
	 * @param columnJson
	 * @param httpServletRequest
	 * @return
	 */
    ResponseModel<String> makeCode(String configId, String treeids, String dictionarys, String dbTable, Integer dbType, String dbTableName, String columnJson, String columns, HttpServletRequest httpServletRequest);
}