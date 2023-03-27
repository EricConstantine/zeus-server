package com.sgcc.code.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sgcc.code.common.utils.UapPage;
import com.sgcc.code.common.web.RequestModel;
import com.sgcc.code.common.web.ResponseModel;
import com.sgcc.code.entity.Versions;
import com.sgcc.code.entity.io.versions.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 * 版本事务
 * </p>
 *
 * @author liugohua
 * @since Wed Jan 04 16:35:58 CST 2023
 */
public interface VersionsService extends IService<Versions> {

	/**
	 * 查询版本分页数据
	 * @param body
	 * @return
	 */
	public ResponseModel<UapPage<VersionsIo>> query(RequestModel<VersionsQueryIo> body);
	
	/**
	 * 查询版本记录
	 * @param body
	 * @return
	 */
	public ResponseModel<List<VersionsIo>> queryAll(RequestModel<VersionsQueryIo> body);
	
	/**
	 * 获取单条版本
	 * @param body
	 * @return
	 */
	public ResponseModel<VersionsIo> view(RequestModel<VersionsViewIo> body);

	/**
	 * 保存版本
	 * @param winFile
	 * @param macFile
	 * @param body
	 * @return
	 */
	public ResponseModel<Boolean> add(MultipartFile winFile, MultipartFile macFile, VersionsAddIo body);
	
	/**
	 * 删除版本
	 * @param body
	 * @return
	 */
	public ResponseModel<String> delete(RequestModel<String> body);

	/**
	 * 修改版本
	 * @param winFile
	 * @param macFile
	 * @param body
	 * @return
	 */
	public ResponseModel<Boolean> update(MultipartFile winFile, MultipartFile macFile, VersionsUpdateIo body);
	
	
	/**
	 * 导入版本
	 * @param body
	 * @return
	 */
	public ResponseModel<String> imports(RequestModel<List<VersionsAddIo>> body);

	/**
	 * 获取最新版本数据
	 * @return
	 */
	ResponseModel<VersionsIo> latestversion();

	/**
	 * 文件下载接口
	 * @param filePath
	 * @param response
	 */
	void downloadFile(String filePath, HttpServletResponse response);
}