package com.sgcc.code.controller;

import com.sgcc.code.common.utils.UapPage;
import com.sgcc.code.common.web.RequestModel;
import com.sgcc.code.common.web.ResponseModel;
import com.sgcc.code.entity.io.commonInfos.*;
import com.sgcc.code.service.CommonInfosService;
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
 * 项目公共配置信息控制类
 * </p>
 *
 * @author liugohua
 * @since Tue Jan 31 10:16:22 CST 2023
 */
@Api(tags= {"项目公共配置信息"})
@RestController
@RequestMapping("/commonInfos")
public class CommonInfosController{

	@Autowired
	CommonInfosService commonInfosService;

	@ApiOperation(value="查询项目公共配置信息分页数据", notes = "查询项目公共配置信息分页数据")
	@PostMapping("/query")
	public ResponseModel<UapPage<CommonInfosIo>> query(@Valid @RequestBody RequestModel<CommonInfosQueryIo> body){
		return commonInfosService.query(body);
	}
	@ApiOperation(value="查询项目公共配置信息数据", notes = "查询项目公共配置信息数据，不带分页")
	@PostMapping("/queryAll")
	public ResponseModel<List<CommonInfosIo>> queryAll(@Valid @RequestBody RequestModel<CommonInfosQueryIo> body){
		return commonInfosService.queryAll(body);
	}
	@ApiOperation(value = "查询项目公共配置信息详情数据" ,notes = "根据ID获取单条数据")
	@PostMapping("/view")
	public ResponseModel<CommonInfosIo> view(@Valid @RequestBody RequestModel<CommonInfosViewIo> body){
		return commonInfosService.view(body);
	}
	@ApiOperation(value="保存项目公共配置信息", notes = "保存项目公共配置信息")
	@PostMapping("/add")
	public ResponseModel<Long> add(@Valid @RequestBody RequestModel<CommonInfosAddIo> body)  {
		return commonInfosService.add(body);
	}
	@ApiOperation(value = "删除项目公共配置信息数据", notes = "删除项目公共配置信息" )
	@PostMapping("/delete")
	public ResponseModel<String> delete(@Valid @RequestBody RequestModel<String> body) {
		return commonInfosService.delete(body);
	}
	@ApiOperation(value="修改项目公共配置信息数据", notes = "修改项目公共配置信息")
	@PostMapping("/update")
	public ResponseModel<Long> update(@Valid @RequestBody RequestModel<CommonInfosUpdateIo> body)  {
		return commonInfosService.update(body);
	}
	@ApiOperation(value = "导入项目公共配置信息数据", notes = "导入项目公共配置信息")
	@PostMapping("/imports")
	public ResponseModel<String> imports(@RequestBody RequestModel<List<CommonInfosAddIo>> body) {
		return commonInfosService.imports(body);
	}


}