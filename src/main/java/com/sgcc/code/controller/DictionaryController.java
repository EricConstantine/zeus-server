package com.sgcc.code.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.sgcc.code.entity.io.dictionary.DictionaryIo;
import com.sgcc.code.entity.io.dictionary.DictionaryQueryIo;
import com.sgcc.code.entity.io.dictionary.DictionaryViewIo;
import com.sgcc.code.entity.io.dictionary.DictionaryAddIo;
import com.sgcc.code.entity.io.dictionary.DictionaryUpdateIo;
import com.sgcc.code.service.DictionaryService;
import com.sgcc.code.common.web.RequestModel;
import com.sgcc.code.common.web.ResponseModel;

import java.util.List;
import javax.validation.Valid;
import com.sgcc.code.common.utils.UapPage;

/**
 * <p>
 * 字典控制类
 * </p>
 *
 * @author liugohua
 * @since Wed Jan 04 12:23:13 CST 2023
 */
@Api(tags= {"字典"})
@RestController
@RequestMapping("/dictionary")
public class DictionaryController{

	@Autowired
	DictionaryService dictionaryService;

	@ApiOperation(value="查询字典分页数据", notes = "查询字典分页数据")
	@PostMapping("/query")
	public ResponseModel<UapPage<DictionaryIo>> query(@Valid @RequestBody RequestModel<DictionaryQueryIo> body){
		return dictionaryService.query(body);
	}
	@ApiOperation(value="查询字典数据", notes = "查询字典数据，不带分页")
	@PostMapping("/queryAll")
	public ResponseModel<List<DictionaryIo>> queryAll(@Valid @RequestBody RequestModel<DictionaryQueryIo> body){
		return dictionaryService.queryAll(body);
	}
	@ApiOperation(value = "查询字典详情数据" ,notes = "根据ID获取单条数据")
	@PostMapping("/view")
	public ResponseModel<DictionaryIo> view(@Valid @RequestBody RequestModel<DictionaryViewIo> body){
		return dictionaryService.view(body);
	}
	@ApiOperation(value="保存字典", notes = "保存字典")
	@PostMapping("/add")
	public ResponseModel<Long> add(@Valid @RequestBody RequestModel<DictionaryAddIo> body)  {
		return dictionaryService.add(body);
	}
	@ApiOperation(value = "删除字典数据", notes = "删除字典" )
	@PostMapping("/delete")
	public ResponseModel<String> delete(@Valid @RequestBody RequestModel<String> body) {
		return dictionaryService.delete(body);
	}
	@ApiOperation(value="修改字典数据", notes = "修改字典")
	@PostMapping("/update")
	public ResponseModel<Long> update(@Valid @RequestBody RequestModel<DictionaryUpdateIo> body)  {
		return dictionaryService.update(body);
	}
	@ApiOperation(value = "导入字典数据", notes = "导入字典")
	@PostMapping("/imports")
	public ResponseModel<String> imports(@RequestBody RequestModel<List<DictionaryAddIo>> body) {
		return dictionaryService.imports(body);
	}


}