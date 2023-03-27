package com.sgcc.code.controller;

import com.sgcc.code.common.utils.UapPage;
import com.sgcc.code.common.web.RequestModel;
import com.sgcc.code.common.web.ResponseModel;
import com.sgcc.code.entity.io.todo.*;
import com.sgcc.code.service.TodoService;
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
 * 待办控制类
 * </p>
 *
 * @author liugohua
 * @since Tue Jan 31 10:16:45 CST 2023
 */
@Api(tags= {"待办"})
@RestController
@RequestMapping("/todo")
public class TodoController{

	@Autowired
	TodoService todoService;

	@ApiOperation(value="查询待办分页数据", notes = "查询待办分页数据")
	@PostMapping("/query")
	public ResponseModel<UapPage<TodoIo>> query(@Valid @RequestBody RequestModel<TodoQueryIo> body){
		return todoService.query(body);
	}
	@ApiOperation(value="查询待办数据", notes = "查询待办数据，不带分页")
	@PostMapping("/queryAll")
	public ResponseModel<List<TodoIo>> queryAll(@Valid @RequestBody RequestModel<TodoQueryIo> body, HttpServletRequest httpServletRequest){
		return todoService.queryAll(body, httpServletRequest);
	}
	@ApiOperation(value = "查询待办详情数据" ,notes = "根据ID获取单条数据")
	@PostMapping("/view")
	public ResponseModel<TodoIo> view(@Valid @RequestBody RequestModel<TodoViewIo> body){
		return todoService.view(body);
	}
	@ApiOperation(value="保存待办", notes = "保存待办")
	@PostMapping("/add")
	public ResponseModel<Long> add(@Valid @RequestBody RequestModel<TodoAddIo> body, HttpServletRequest httpServletRequest)  {
		return todoService.add(body, httpServletRequest);
	}
	@ApiOperation(value = "删除待办数据", notes = "删除待办" )
	@PostMapping("/delete")
	public ResponseModel<String> delete(@Valid @RequestBody RequestModel<String> body) {
		return todoService.delete(body);
	}
	@ApiOperation(value="修改待办数据", notes = "修改待办")
	@PostMapping("/update")
	public ResponseModel<Long> update(@Valid @RequestBody RequestModel<TodoUpdateIo> body)  {
		return todoService.update(body);
	}
	@ApiOperation(value = "导入待办数据", notes = "导入待办")
	@PostMapping("/imports")
	public ResponseModel<String> imports(@RequestBody RequestModel<List<TodoAddIo>> body) {
		return todoService.imports(body);
	}


}