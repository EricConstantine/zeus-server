package com.sgcc.code.controller;

import com.sgcc.code.common.utils.UapPage;
import com.sgcc.code.common.web.RequestModel;
import com.sgcc.code.common.web.ResponseModel;
import com.sgcc.code.entity.io.project.*;
import com.sgcc.code.service.ProjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 项目控制类
 * </p>
 *
 * @author yangrui
 * @since Thu Dec 29 17:20:21 CST 2022
 */
@Api(tags= {"项目"})
@RestController
@RequestMapping("/project")
public class ProjectController{

	@Autowired
	ProjectService projectService;

	@ApiOperation(value="查询项目分页数据", notes = "查询项目分页数据")
	@PostMapping("/query")
	public ResponseModel<UapPage<ProjectIo>> query(@Valid @RequestBody RequestModel<ProjectQueryIo> body){
		return projectService.query(body);
	}
	@ApiOperation(value="查询项目数据", notes = "查询项目数据，不带分页")
	@PostMapping("/queryAll")
	public ResponseModel<List<ProjectIo>> queryAll(@Valid @RequestBody RequestModel<ProjectQueryIo> body){
		return projectService.queryAll(body);
	}
	@ApiOperation(value = "查询项目详情数据" ,notes = "根据ID获取单条数据")
	@PostMapping("/view")
	public ResponseModel<ProjectIo> view(@Valid @RequestBody RequestModel<ProjectViewIo> body){
		return projectService.view(body);
	}
	@ApiOperation(value="保存项目", notes = "保存项目")
	@PostMapping("/add")
	public ResponseModel<ProjectIo> add(@Valid @RequestBody RequestModel<ProjectAddIo> body)  {
		return projectService.add(body);
	}
	@ApiOperation(value = "删除项目数据", notes = "删除项目" )
	@PostMapping("/delete")
	public ResponseModel<Boolean> delete(@Valid @RequestBody RequestModel<String> body) {
		return projectService.delete(body);
	}
	@ApiOperation(value="修改项目数据", notes = "修改项目")
	@PostMapping("/update")
	public ResponseModel<Boolean> update(@Valid @RequestBody RequestModel<ProjectUpdateIo> body)  {
		return projectService.update(body);
	}
	@ApiOperation(value = "导入项目数据", notes = "导入项目")
	@PostMapping("/imports")
	public ResponseModel<String> imports(@RequestBody RequestModel<List<ProjectAddIo>> body) {
		return projectService.imports(body);
	}
	@ApiOperation(value="复制项目数据", notes = "复制项目")
	@PostMapping("/copy")
	public ResponseModel<Boolean> copy(@Valid @RequestBody RequestModel<ProjectUpdateIo> body)  {
		return projectService.copy(body);
	}


}