package com.sgcc.code.controller;

import com.sgcc.code.common.utils.UapPage;
import com.sgcc.code.common.web.RequestModel;
import com.sgcc.code.common.web.ResponseModel;
import com.sgcc.code.entity.io.config.*;
import com.sgcc.code.service.ConfigService;
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
import java.util.Map;

/**
 * <p>
 * 配置控制类
 * </p>
 *
 * @author liugohua
 * @since Tue Jan 10 09:42:21 CST 2023
 */
@Api(tags= {"配置"})
@RestController
@RequestMapping("/config")
public class ConfigController{

	@Autowired
	ConfigService configService;

	@ApiOperation(value="查询配置分页数据", notes = "查询配置分页数据")
	@PostMapping("/query")
	public ResponseModel<UapPage<ConfigIo>> query(@Valid @RequestBody RequestModel<ConfigQueryIo> body, HttpServletRequest httpServletRequest){
		return configService.query(body, httpServletRequest);
	}
	@ApiOperation(value="查询配置数据", notes = "查询配置数据，不带分页")
	@PostMapping("/queryAll")
	public ResponseModel<List<ConfigIo>> queryAll(@Valid @RequestBody RequestModel<ConfigQueryIo> body){
		return configService.queryAll(body);
	}
	@ApiOperation(value = "查询配置详情数据" ,notes = "根据ID获取单条数据")
	@PostMapping("/view")
	public ResponseModel<ConfigIo> view(@Valid @RequestBody RequestModel<ConfigViewIo> body){
		return configService.view(body);
	}
	@ApiOperation(value="保存配置", notes = "保存配置")
	@PostMapping("/add")
	public ResponseModel<Boolean> add(@Valid @RequestBody RequestModel<ConfigAddIo> body, HttpServletRequest httpServletRequest)  {
		return configService.add(body, httpServletRequest);
	}
	@ApiOperation(value = "删除配置数据", notes = "删除配置" )
	@PostMapping("/delete")
	public ResponseModel<Boolean> delete(@Valid @RequestBody RequestModel<String> body) {
		return configService.delete(body);
	}
	@ApiOperation(value="修改配置数据", notes = "修改配置")
	@PostMapping("/update")
	public ResponseModel<Boolean> update(@Valid @RequestBody RequestModel<ConfigUpdateIo> body, HttpServletRequest httpServletRequest)  {
		return configService.update(body,httpServletRequest);
	}
	@ApiOperation(value = "导入配置数据", notes = "导入配置")
	@PostMapping("/imports")
	public ResponseModel<String> imports(@RequestBody RequestModel<List<ConfigAddIo>> body) {
		return configService.imports(body);
	}
	@ApiOperation(value = "获取用户配置列表" ,notes = "获取用户配置列表" )
	@PostMapping(value="/getConfigs")
	public ResponseModel<List<Map<String,Object>>> getConfigs(HttpServletRequest httpServletRequest){
		return configService.getConfigs(httpServletRequest);
	}
	@ApiOperation(value = "获取配置项目详情" ,notes = "获取配置项目详情" )
	@PostMapping(value="/getProjectDetail")
	public ResponseModel<Map<String,Object>> getProjectDetail(@Valid @RequestBody RequestModel<ConfigViewIo> body){
		return configService.getProjectDetail(body);
	}

	@ApiOperation(value = "测试数据库连接" ,notes = "测试数据库连接" )
	@PostMapping(value="/testDb")
	public ResponseModel<Map> testDb(@Valid @RequestBody RequestModel<ConfigConnIo> body){
		return configService.testDb(body);
	}

	@ApiOperation(value = "获取配置项目数据库表信息" ,notes = "获取配置项目数据库表信息" )
	@PostMapping(value="/getConnTables")
	public ResponseModel<List<Map>> getConnTables(@Valid @RequestBody RequestModel<ConfigConnIo> body){
		return configService.getConnTables(body);
	}

	@ApiOperation(value = "获取配置项目数据库表信息详情" ,notes = "获取配置项目数据库表信息详情" )
	@PostMapping(value="/getConnTableView")
	public ResponseModel<List<Map>> getConnTableView(@Valid @RequestBody RequestModel<ConfigConnIo> body){
		return configService.getConnTableView(body);
	}

}