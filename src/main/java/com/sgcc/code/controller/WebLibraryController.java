package com.sgcc.code.controller;

import com.sgcc.code.common.utils.UapPage;
import com.sgcc.code.common.web.RequestModel;
import com.sgcc.code.common.web.ResponseModel;
import com.sgcc.code.entity.io.webLibrary.*;
import com.sgcc.code.service.WebLibraryService;
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
 * 网页库控制类
 * </p>
 *
 * @author liugohua
 * @since Mon Mar 06 17:53:18 CST 2023
 */
@Api(tags= {"网页库"})
@RestController
@RequestMapping("/webLibrary")
public class WebLibraryController{

	@Autowired
	WebLibraryService webLibraryService;

	@ApiOperation(value="查询网页库分页数据", notes = "查询网页库分页数据")
	@PostMapping("/query")
	public ResponseModel<UapPage<WebLibraryIo>> query(@Valid @RequestBody RequestModel<WebLibraryQueryIo> body){
		return webLibraryService.query(body);
	}
	@ApiOperation(value="普通用户-查询网页库分页数据", notes = "普通用户-查询网页库分页数据")
	@PostMapping("/userQuery")
	public ResponseModel<UapPage<WebLibraryIo>> userQuery(@Valid @RequestBody RequestModel<WebLibraryQueryIo> body){
		return webLibraryService.userQuery(body);
	}
	@ApiOperation(value="查询网页库数据", notes = "查询网页库数据，不带分页")
	@PostMapping("/queryAll")
	public ResponseModel<List<WebLibraryIo>> queryAll(@Valid @RequestBody RequestModel<WebLibraryQueryIo> body){
		return webLibraryService.queryAll(body);
	}
	@ApiOperation(value = "查询网页库详情数据" ,notes = "根据ID获取单条数据")
	@PostMapping("/view")
	public ResponseModel<WebLibraryIo> view(@Valid @RequestBody RequestModel<WebLibraryViewIo> body){
		return webLibraryService.view(body);
	}
	@ApiOperation(value="保存网页库", notes = "保存网页库")
	@PostMapping("/add")
	public ResponseModel<Integer> add(@Valid @RequestBody RequestModel<WebLibraryAddIo> body)  {
		return webLibraryService.add(body);
	}
	@ApiOperation(value = "删除网页库数据", notes = "删除网页库" )
	@PostMapping("/delete")
	public ResponseModel<String> delete(@Valid @RequestBody RequestModel<String> body) {
		return webLibraryService.delete(body);
	}
	@ApiOperation(value="修改网页库数据", notes = "修改网页库")
	@PostMapping("/update")
	public ResponseModel<Integer> update(@Valid @RequestBody RequestModel<WebLibraryUpdateIo> body)  {
		return webLibraryService.update(body);
	}
	@ApiOperation(value = "导入网页库数据", notes = "导入网页库")
	@PostMapping("/imports")
	public ResponseModel<String> imports(@RequestBody RequestModel<List<WebLibraryAddIo>> body) {
		return webLibraryService.imports(body);
	}


}