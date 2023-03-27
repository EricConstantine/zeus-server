package com.sgcc.code.controller;

import com.sgcc.code.common.utils.UapPage;
import com.sgcc.code.common.web.RequestModel;
import com.sgcc.code.common.web.ResponseModel;
import com.sgcc.code.entity.io.shell.*;
import com.sgcc.code.service.ShellService;
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
 * 脚本控制类
 * </p>
 *
 * @author liugohua
 * @since Mon Mar 06 09:59:00 CST 2023
 */
@Api(tags= {"脚本"})
@RestController
@RequestMapping("/shell")
public class ShellController{

	@Autowired
	ShellService shellService;

	@ApiOperation(value="查询脚本管理分页数据", notes = "查询脚本管理分页数据")
	@PostMapping("/query")
	public ResponseModel<UapPage<ShellIo>> query(@Valid @RequestBody RequestModel<ShellQueryIo> body, HttpServletRequest httpServletRequest){
		return shellService.query(body, httpServletRequest);
	}
	@ApiOperation(value="查询脚本管理数据", notes = "查询脚本管理数据，不带分页")
	@PostMapping("/queryAll")
	public ResponseModel<List<ShellIo>> queryAll(@Valid @RequestBody RequestModel<ShellQueryIo> body){
		return shellService.queryAll(body);
	}
	@ApiOperation(value = "查询脚本管理详情数据" ,notes = "根据ID获取单条数据")
	@PostMapping("/view")
	public ResponseModel<ShellIo> view(@Valid @RequestBody RequestModel<ShellViewIo> body){
		return shellService.view(body);
	}
	@ApiOperation(value="保存脚本管理", notes = "保存脚本管理")
	@PostMapping("/add")
	public ResponseModel<Boolean> add(@Valid @RequestBody RequestModel<ShellAddIo> body, HttpServletRequest httpServletRequest)  {
		return shellService.add(body, httpServletRequest);
	}
	@ApiOperation(value = "删除脚本管理数据", notes = "删除脚本管理" )
	@PostMapping("/delete")
	public ResponseModel<Boolean> delete(@Valid @RequestBody RequestModel<String> body) {
		return shellService.delete(body);
	}
	@ApiOperation(value="修改脚本管理数据", notes = "修改脚本管理")
	@PostMapping("/update")
	public ResponseModel<Boolean> update(@Valid @RequestBody RequestModel<ShellUpdateIo> body, HttpServletRequest httpServletRequest)  {
		return shellService.update(body, httpServletRequest);
	}
	@ApiOperation(value = "导入脚本管理数据", notes = "导入脚本管理")
	@PostMapping("/imports")
	public ResponseModel<String> imports(@RequestBody RequestModel<List<ShellAddIo>> body) {
		return shellService.imports(body);
	}
	@ApiOperation(value = "执行脚本", notes = "执行脚本")
	@PostMapping("/execueteShell")
	public ResponseModel<Map> execueteShell(@Valid @RequestBody RequestModel<ShellViewIo> body, HttpServletRequest httpServletRequest) {
		return shellService.execueteShell(body, httpServletRequest);
	}


}