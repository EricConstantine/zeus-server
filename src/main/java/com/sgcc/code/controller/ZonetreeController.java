package com.sgcc.code.controller;

import com.sgcc.code.common.utils.UapPage;
import com.sgcc.code.common.web.RequestModel;
import com.sgcc.code.common.web.ResponseModel;
import com.sgcc.code.entity.io.zonetree.*;
import com.sgcc.code.service.ZonetreeService;
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
 * 空间树控制类
 * </p>
 *
 * @author liugohua
 * @since Fri Dec 30 09:58:13 CST 2022
 */
@Api(tags= {"空间树"})
@RestController
@RequestMapping("/zonetree")
public class ZonetreeController{

	@Autowired
	ZonetreeService zonetreeService;

	@ApiOperation(value="查询空间树分页数据", notes = "查询空间树分页数据")
	@PostMapping("/query")
	public ResponseModel<UapPage<ZonetreeIo>> query(@Valid @RequestBody RequestModel<ZonetreeQueryIo> body){
		return zonetreeService.query(body);
	}
	@ApiOperation(value="查询空间树数据", notes = "查询空间树数据，不带分页")
	@PostMapping("/queryAll")
	public ResponseModel<List<ZonetreeIo>> queryAll(@Valid @RequestBody RequestModel<ZonetreeQueryIo> body){
		return zonetreeService.queryAll(body);
	}
	@ApiOperation(value = "查询空间树详情数据" ,notes = "根据ID获取单条数据")
	@PostMapping("/view")
	public ResponseModel<ZonetreeIo> view(@Valid @RequestBody RequestModel<ZonetreeViewIo> body){
		return zonetreeService.view(body);
	}
	@ApiOperation(value="保存空间树", notes = "保存空间树")
	@PostMapping("/add")
	public ResponseModel<Boolean> add(@Valid @RequestBody RequestModel<ZonetreeAddIo> body)  {
		return zonetreeService.add(body);
	}
	@ApiOperation(value = "删除空间树数据", notes = "删除空间树" )
	@PostMapping("/delete")
	public ResponseModel<Boolean> delete(@Valid @RequestBody RequestModel<ZonetreeDeleteIo> body) {
		return zonetreeService.delete(body);
	}
	@ApiOperation(value="修改空间树数据", notes = "修改空间树")
	@PostMapping("/update")
	public ResponseModel<Boolean> update(@Valid @RequestBody RequestModel<ZonetreeUpdateIo> body)  {
		return zonetreeService.update(body);
	}
	@ApiOperation(value = "导入空间树数据", notes = "导入空间树")
	@PostMapping("/imports")
	public ResponseModel<String> imports(@RequestBody RequestModel<List<ZonetreeAddIo>> body) {
		return zonetreeService.imports(body);
	}


}