package com.sgcc.code.controller;

import com.sgcc.code.common.utils.UapPage;
import com.sgcc.code.common.web.RequestModel;
import com.sgcc.code.common.web.ResponseModel;
import com.sgcc.code.entity.io.projectuser.*;
import com.sgcc.code.service.ProjectuserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 项目成员控制类
 * </p>
 *
 * @author liugohua
 * @since Tue Jan 03 10:07:47 CST 2023
 */
@Api(tags= {"项目成员"})
@RestController
@RequestMapping("/projectuser")
public class ProjectuserController{

	@Autowired
	ProjectuserService projectuserService;

	@ApiOperation(value="查询项目成员分页数据", notes = "查询项目成员分页数据")
	@PostMapping("/query")
	public ResponseModel<UapPage<ProjectuserIo>> query(@Valid @RequestBody RequestModel<ProjectuserQueryIo> body){
		return projectuserService.query(body);
	}
	@ApiOperation(value="查询项目成员数据", notes = "查询项目成员数据，不带分页")
	@PostMapping("/queryAll")
	public ResponseModel<List<ProjectuserIo>> queryAll(@Valid @RequestBody RequestModel<ProjectuserQueryIo> body){
		return projectuserService.queryAll(body);
	}
	@ApiOperation(value = "查询项目成员详情数据" ,notes = "根据ID获取单条数据")
	@PostMapping("/view")
	public ResponseModel<ProjectuserIo> view(@Valid @RequestBody RequestModel<ProjectuserViewIo> body){
		return projectuserService.view(body);
	}
	@ApiOperation(value="保存项目成员", notes = "保存项目成员")
	@PostMapping("/add")
	public ResponseModel<Long> add(@Valid @RequestBody RequestModel<ProjectuserAddIo> body)  {
		return projectuserService.add(body);
	}
	@ApiOperation(value = "删除项目成员数据", notes = "删除项目成员" )
	@PostMapping("/delete")
	public ResponseModel<String> delete(@Valid @RequestBody RequestModel<String> body) {
		return projectuserService.delete(body);
	}
	@ApiOperation(value="修改项目成员数据", notes = "修改项目成员")
	@PostMapping("/update")
	public ResponseModel<Long> update(@Valid @RequestBody RequestModel<ProjectuserUpdateIo> body)  {
		return projectuserService.update(body);
	}
	@ApiOperation(value = "导入项目成员数据", notes = "导入项目成员")
	@PostMapping("/imports")
	public ResponseModel<String> imports(@RequestBody RequestModel<List<ProjectuserAddIo>> body) {
		return projectuserService.imports(body);
	}
	@ApiOperation(value="查询全部用户和项目成员", notes = "查询全部用户和项目成员")
	@PostMapping("/queryUserAndProjectuser")
	public ResponseModel<Map<String,Object>> queryUserAndProjectuser(@Valid @RequestBody RequestModel<ProjectuserQueryIo> body){
		return projectuserService.queryUserAndProjectuser(body);
	}

}