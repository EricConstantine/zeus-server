package com.sgcc.code.controller;

import com.sgcc.code.common.utils.UapPage;
import com.sgcc.code.common.web.RequestModel;
import com.sgcc.code.common.web.ResponseModel;
import com.sgcc.code.entity.io.template.TemplateAddIo;
import com.sgcc.code.entity.io.template.TemplateIo;
import com.sgcc.code.entity.io.template.TemplateQueryIo;
import com.sgcc.code.entity.io.template.TemplateUpdateIo;
import com.sgcc.code.service.TemplateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 模板控制类
 * </p>
 *
 * @author liugohua
 * @since Fri Dec 30 17:52:56 CST 2022
 */
@Api(tags= {"模板"})
@RestController
@RequestMapping("/template")
public class TemplateController{

	@Autowired
	TemplateService templateService;

	@ApiOperation(value="查询模板分页数据", notes = "查询模板分页数据")
	@PostMapping("/query")
	public ResponseModel<UapPage<TemplateIo>> query(@Valid @RequestBody RequestModel<TemplateQueryIo> body){
		return templateService.query(body);
	}
	@ApiOperation(value="查询模板数据", notes = "查询模板数据，不带分页")
	@PostMapping("/queryAll")
	public ResponseModel<List<TemplateIo>> queryAll(@Valid @RequestBody RequestModel<TemplateQueryIo> body){
		return templateService.queryAll(body);
	}
	@ApiOperation(value = "查询模板详情数据" ,notes = "根据ID获取单条数据")
	@PostMapping("/view")
	public ResponseModel<TemplateIo> view(@Valid @RequestBody RequestModel<TemplateIo> body){
		return templateService.view(body);
	}
	@ApiOperation(value="保存模板", notes = "保存模板")
	@PostMapping("/add")
	public ResponseModel<Long> add(@Valid @RequestBody RequestModel<TemplateAddIo> body)  {
		return templateService.add(body);
	}
	@ApiOperation(value = "删除模板数据", notes = "删除模板" )
	@PostMapping("/delete")
	public ResponseModel<String> delete(@Valid @RequestBody RequestModel<String> body) {
		return templateService.delete(body);
	}
	@ApiOperation(value="修改模板数据", notes = "修改模板")
	@PostMapping("/update")
	public ResponseModel<Boolean> update(@Valid @RequestBody RequestModel<TemplateUpdateIo> body)  {
		return templateService.update(body);
	}
	@ApiOperation(value = "导入模板数据", notes = "导入模板")
	@PostMapping("/imports")
	public ResponseModel<String> imports(@RequestBody RequestModel<List<TemplateAddIo>> body) {
		return templateService.imports(body);
	}
	@ApiOperation(value="生成代码", notes = "生成代码")
	@PostMapping("/makeCode")
	public ResponseModel<String> makeCode(String configId, String treeids, String dictionarys, String dbTable, Integer dbType, String dbTableName, String columnJson, String columns, HttpServletRequest httpServletRequest) {
		return templateService.makeCode(configId, treeids, dictionarys, dbTable, dbType, dbTableName, columnJson, columns, httpServletRequest);
	}

}