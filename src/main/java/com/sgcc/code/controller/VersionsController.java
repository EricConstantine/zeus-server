package com.sgcc.code.controller;

import com.sgcc.code.common.utils.UapPage;
import com.sgcc.code.common.web.RequestModel;
import com.sgcc.code.common.web.ResponseModel;
import com.sgcc.code.entity.io.versions.*;
import com.sgcc.code.service.VersionsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 版本控制类
 * </p>
 *
 * @author liugohua
 * @since Wed Jan 04 16:35:58 CST 2023
 */
@Api(tags= {"版本"})
@RestController
@RequestMapping("/versions")
public class VersionsController{

	@Autowired
	VersionsService versionsService;

	@ApiOperation(value="查询版本分页数据", notes = "查询版本分页数据")
	@PostMapping("/query")
	public ResponseModel<UapPage<VersionsIo>> query(@Valid @RequestBody RequestModel<VersionsQueryIo> body){
		return versionsService.query(body);
	}
	@ApiOperation(value="查询版本数据", notes = "查询版本数据，不带分页")
	@PostMapping("/queryAll")
	public ResponseModel<List<VersionsIo>> queryAll(@Valid @RequestBody RequestModel<VersionsQueryIo> body){
		return versionsService.queryAll(body);
	}
	@ApiOperation(value = "查询版本详情数据" ,notes = "根据ID获取单条数据")
	@PostMapping("/view")
	public ResponseModel<VersionsIo> view(@Valid @RequestBody RequestModel<VersionsViewIo> body){
		return versionsService.view(body);
	}
	@ApiOperation(value="保存版本", notes = "保存版本")
		@PostMapping("/add")
	public ResponseModel<Boolean> add(@RequestParam(value = "winFile", required = false) MultipartFile winFile,
									  @RequestParam(value = "macFile", required = false) MultipartFile macFile,
									  VersionsAddIo body)  {
		return versionsService.add(winFile, macFile, body);
	}
	@ApiOperation(value = "删除版本数据", notes = "删除版本" )
	@PostMapping("/delete")
	public ResponseModel<String> delete(@Valid @RequestBody RequestModel<String> body) {
		return versionsService.delete(body);
	}
	@ApiOperation(value="修改版本数据", notes = "修改版本")
	@PostMapping("/update")
	public ResponseModel<Boolean> update(@RequestParam(value = "winFile", required = false) MultipartFile winFile, @RequestParam(value = "macFile", required = false) MultipartFile macFile, VersionsUpdateIo body)  {
		return versionsService.update(winFile, macFile, body);
	}
	@ApiOperation(value = "导入版本数据", notes = "导入版本")
	@PostMapping("/imports")
	public ResponseModel<String> imports(@RequestBody RequestModel<List<VersionsAddIo>> body) {
		return versionsService.imports(body);
	}
	@ApiOperation(value = "获取最新版本数据" ,notes = "获取最新版本数据")
	@PostMapping("/latestversion")
	public ResponseModel<VersionsIo> latestversion(){
		return versionsService.latestversion();
	}

	@ApiOperation(value = "文件下载接口" ,notes = "文件下载接口")
	@PostMapping("/downloadFile")
	public void downloadFile(String filePath, HttpServletResponse response){
		versionsService.downloadFile(filePath, response);
	}


}