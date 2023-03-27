package com.sgcc.code.service;

import com.sgcc.code.entity.Dictionary;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sgcc.code.entity.io.dictionary.DictionaryIo;
import com.sgcc.code.entity.io.dictionary.DictionaryQueryIo;
import com.sgcc.code.entity.io.dictionary.DictionaryViewIo;
import com.sgcc.code.entity.io.dictionary.DictionaryAddIo;
import com.sgcc.code.entity.io.dictionary.DictionaryUpdateIo;
import com.sgcc.code.common.web.RequestModel;
import com.sgcc.code.common.web.ResponseModel;

import java.util.List;

import com.sgcc.code.common.utils.UapPage;

/**
 * <p>
 * 字典事务
 * </p>
 *
 * @author liugohua
 * @since Wed Jan 04 12:23:13 CST 2023
 */
public interface DictionaryService extends IService<Dictionary> {

	/**
	 * 查询字典分页数据
	 * @param body
	 * @return
	 */
	public ResponseModel<UapPage<DictionaryIo>> query(RequestModel<DictionaryQueryIo> body);
	
	/**
	 * 查询字典记录
	 * @param body
	 * @return
	 */
	public ResponseModel<List<DictionaryIo>> queryAll(RequestModel<DictionaryQueryIo> body);
	
	/**
	 * 获取单条字典
	 * @param body
	 * @return
	 */
	public ResponseModel<DictionaryIo> view(RequestModel<DictionaryViewIo> body);
	
	/**
	 * 保存字典
	 * @param body
	 * @return
	 */
	public ResponseModel<Long> add(RequestModel<DictionaryAddIo> body);
	
	/**
	 * 删除字典
	 * @param body
	 * @return
	 */
	public ResponseModel<String> delete(RequestModel<String> body);
	
	/**
	 * 修改字典
	 * @param body
	 * @return
	 */
	public ResponseModel<Long> update(RequestModel<DictionaryUpdateIo> body);
	
	
	/**
	 * 导入字典
	 * @param body
	 * @return
	 */
	public ResponseModel<String> imports(RequestModel<List<DictionaryAddIo>> body);
	

	
    
}