package com.sgcc.code.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sgcc.code.common.exception.CommonException;
import com.sgcc.code.common.utils.*;
import com.sgcc.code.common.web.RequestModel;
import com.sgcc.code.common.web.ResponseModel;
import com.sgcc.code.entity.Versions;
import com.sgcc.code.entity.io.versions.*;
import com.sgcc.code.mapper.VersionsMapper;
import com.sgcc.code.service.VersionsService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.List;


/**
 * <p>
 * 版本事务
 * </p>
 *
 * @author liugohua
 * @since Wed Jan 04 16:35:58 CST 2023
 */
@Service
public class VersionsServiceImpl extends ServiceImpl<VersionsMapper, Versions> implements VersionsService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Value("${web.user-data-path}")
	private String userDataPath;

	@Autowired
	private VersionsMapper versionsMapper;

	//查询版本分页数据
	public ResponseModel<UapPage<VersionsIo>> query(RequestModel<VersionsQueryIo> body){
		logger.info("查询版本分页数据 ===> " + body.getParameter());
		QueryWrapper<Versions> queryWrapper = new QueryWrapper<>();
		if(StringUtils.isNotEmpty(body.getParameter().getVerson())) queryWrapper.like("verson", body.getParameter().getVerson());
		if(StringUtils.isNotEmpty(body.getParameter().getRemark())) queryWrapper.like("remark", body.getParameter().getRemark());
		if(StringUtils.isNotEmpty(body.getParameter().getWinPath())) queryWrapper.like("win_path", body.getParameter().getWinPath());
		if(StringUtils.isNotEmpty(body.getParameter().getMacPath())) queryWrapper.like("mac_path", body.getParameter().getMacPath());
		if(StringUtils.isNotEmpty(body.getParameter().getCurrentVersion())) queryWrapper.like("current_version", body.getParameter().getCurrentVersion());
		queryWrapper.orderByDesc("create_time");
		//查询出mybatisplus的分页对象 并转换成内部分页对象
        IPage<Versions> iPage = super.page(new Page(body.getCurrent(), body.getSize()), queryWrapper);
        return ResponseModel.success(BeanUtil.convertPage(iPage,VersionsIo.class), body.getHeader());
	}
	
	//查询版本记录
	public ResponseModel<List<VersionsIo>> queryAll(RequestModel<VersionsQueryIo> body){
		logger.info("查询版本数据列表 ===> " + body.getParameter());
		QueryWrapper<Versions> queryWrapper = new QueryWrapper<>();
		if(StringUtils.isNotEmpty(body.getParameter().getVerson())) queryWrapper.like("verson", body.getParameter().getVerson().trim());
		if(StringUtils.isNotEmpty(body.getParameter().getRemark())) queryWrapper.like("remark", body.getParameter().getRemark().trim());
		if(StringUtils.isNotEmpty(body.getParameter().getWinPath())) queryWrapper.like("win_path", body.getParameter().getWinPath());
		if(StringUtils.isNotEmpty(body.getParameter().getMacPath())) queryWrapper.like("mac_path", body.getParameter().getMacPath());
		if(StringUtils.isNotEmpty(body.getParameter().getCurrentVersion())) queryWrapper.like("current_version", body.getParameter().getCurrentVersion().trim());
        List<Versions> versionss = super.list(queryWrapper);
        if(versionss == null) return ResponseModel.failure("获取数据发生异常，请重试");
        return ResponseModel.success(BeanUtil.convert(versionss,VersionsIo.class),body.getHeader());
	}
	
	//获取单条版本
	public ResponseModel<VersionsIo> view(RequestModel<VersionsViewIo> body){
		logger.info("查询版本详情数据 ===> " + body.getParameter());
		QueryWrapper<Versions> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("versionid", body.getParameter().getVersionid());
		Versions versions = super.getOne(queryWrapper);
		if(versions == null) return ResponseModel.failure("未找到对应的数据记录");
		return ResponseModel.success(BeanUtil.convert(versions,VersionsIo.class),body.getHeader());
	}
	
	//保存版本
	@Transactional(rollbackFor = CommonException.class)
	public ResponseModel<Boolean> add(MultipartFile winFile, MultipartFile macFile, VersionsAddIo body) {
		logger.info("保存版本数据 ===> " + body);
		Versions versions = BeanUtil.convert(body,Versions.class);
		versions.setVersionid(UUIDUtils.uuid32());
		//默认最新版本
		versions.setCurrentVersion("1");
		try {
			//清空所有版本
			versionsMapper.updateDefaultVersion();
			if(versions.getUploadType()==1){
				// 上传windows文件
				if (winFile != null && !winFile.isEmpty()) {
					File winFileDir = new File(userDataPath + "/versions/win/");
					if (!winFileDir.exists()) {
						//如果没有目录应该创建目录
						winFileDir.mkdirs();
					}
					String file_name = versions.getVersionid() + winFile.getOriginalFilename().substring(winFile.getOriginalFilename().lastIndexOf("."));
					String file_path = userDataPath + "/versions/win/" + file_name;
					String fileName = winFile.getOriginalFilename().substring(0, winFile.getOriginalFilename().lastIndexOf("."));
					String fileType = winFile.getOriginalFilename().substring(winFile.getOriginalFilename().lastIndexOf(".")+1);
					// /home/data...被认为是相对路径了   这里处理一下明确为绝对路径
					File dest = new File(new File(file_path).getAbsolutePath());
					winFile.transferTo(dest);
					versions.setWinPath("versions/win/" + file_name);
					versions.setFileName(fileName);
					versions.setFileType(fileType);
				}
				// 上传mac文件
				if (macFile != null && !macFile.isEmpty()) {
					File macFileDir = new File(userDataPath + "/versions/mac/");
					if (!macFileDir.exists()) {
						//如果没有目录应该创建目录
						macFileDir.mkdirs();
					}
					String file_name = versions.getVersionid() + macFile.getOriginalFilename().substring(macFile.getOriginalFilename().lastIndexOf("."));
					String file_path = userDataPath + "/versions/mac/" + file_name;
					// /home/data...被认为是相对路径了   这里处理一下明确为绝对路径
					File dest = new File(new File(file_path).getAbsolutePath());
					macFile.transferTo(dest);
					versions.setMacPath("versions/mac/" + file_name);
				}
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return ResponseModel.success("保存成功",super.save(versions));
	}
	
	//删除版本
	public ResponseModel<String> delete(RequestModel<String> body){
		logger.info("删除版本数据 ===> " + body.getParameter());
		StringUtil.isValidIds(body.getParameter());//验证ID是否合法
		String[] idsArr = body.getParameter().split(",");
	    for(String idStr:idsArr){
			QueryWrapper<Versions> queryWrapper = new QueryWrapper<>();
			queryWrapper.eq("versionid", idStr);
			Versions versions = super.getOne(queryWrapper);
			// 删除文件
			QueryWrapper<Versions> deleteWrapper = new QueryWrapper<>();
			deleteWrapper.eq("versionid", idStr);
			versionsMapper.delete(deleteWrapper);
			// 同时删除线上文件
			String winPath = versions.getWinPath();
			String macPath = versions.getMacPath();
			File winFile = new File("D:\\home\\data"+"\\"+winPath);
			File macFile = new File("D:\\home\\data"+"\\"+macPath);
			// 判断目录或文件是否存在
			if (winFile.exists()) {  // 不存在返回 false
				// 判断是否为文件
				if (winFile.isFile()) {  // 为文件时调用删除文件方法
					winFile.delete();
				}
			}
			// 判断目录或文件是否存在
			if (macFile.exists()) {  // 不存在返回 false
				// 判断是否为文件
				if (macFile.isFile()) {  // 为文件时调用删除文件方法
					macFile.delete();
				}
			}
		}
		return ResponseModel.success("删除成功",body.getParameter(),body.getHeader());
	}
	
	//修改版本
	public ResponseModel<Boolean> update(MultipartFile winFile, MultipartFile macFile, VersionsUpdateIo body){
		logger.info("修改版本数据 ===> " + body);
		Versions versions = BeanUtil.convert(body,Versions.class);
		try{
			if(versions.getUploadType()==1){
				// 上传windows文件
				if (winFile != null && !winFile.isEmpty()) {
					File winFileDir = new File(userDataPath + "/versions/win/");
					if (!winFileDir.exists()) {
						//如果没有目录应该创建目录
						winFileDir.mkdirs();
					}
					String file_name = versions.getVersionid() + winFile.getOriginalFilename().substring(winFile.getOriginalFilename().lastIndexOf("."));
					String file_path = userDataPath + "/versions/win/" + file_name;
					String fileName = winFile.getOriginalFilename().substring(0, winFile.getOriginalFilename().lastIndexOf("."));
					String fileType = winFile.getOriginalFilename().substring(winFile.getOriginalFilename().lastIndexOf(".")+1);
					// /home/data...被认为是相对路径了   这里处理一下明确为绝对路径
					File dest = new File(new File(file_path).getAbsolutePath());
					winFile.transferTo(dest);
					versions.setWinPath("versions/win/" + file_name);
					versions.setFileName(fileName);
					versions.setFileType(fileType);
				}
				// 上传mac文件
				if (macFile != null && !macFile.isEmpty()) {
					File macFileDir = new File(userDataPath + "/versions/mac/");
					if (!macFileDir.exists()) {
						//如果没有目录应该创建目录
						macFileDir.mkdirs();
					}
					String file_name = versions.getVersionid() + macFile.getOriginalFilename().substring(macFile.getOriginalFilename().lastIndexOf("."));
					String file_path = userDataPath + "/versions/mac/" + file_name;
					// /home/data...被认为是相对路径了   这里处理一下明确为绝对路径
					File dest = new File(new File(file_path).getAbsolutePath());
					macFile.transferTo(dest);
					versions.setMacPath("versions/mac/" + file_name);
				}
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return ResponseModel.success("修改成功",super.updateById(versions));
	}
	
	
	// 导入版本
	@Transactional(rollbackFor = CommonException.class)
	public ResponseModel<String> imports(RequestModel<List<VersionsAddIo>> body){
		logger.info("导入版本数据 ===> 文件" );
		int imp_num = 0;
		String[] titleNameArray = {"版本号","更新内容","更新包路径","当前版本标识 1是0否","创建时间","更新时间"};
		String[] fieldNameArray = {"verson","remark","relativePath","currentVersion","createTime","updateTime"};
		try {
			for (VersionsAddIo versionsAddIo : body.getParameter()) {
				Versions versions = BeanUtil.convert(versionsAddIo,Versions.class);
				super.save(versions);
				imp_num++;
			}
		}catch (Exception e) {
			throw new CommonException(500,"导入失败,请确认Excel内容是否正确。</br>错误行号："+(imp_num+2)+"。</br>错误信息："+ ExcelUtil.getPointOfException(e.getMessage(),fieldNameArray,titleNameArray)+"。");
		}
		return ResponseModel.success("导入成功，总条数："+imp_num+"条！");
	}

	//获取最新版本数据
	@Override
	public ResponseModel<VersionsIo> latestversion() {
		logger.info("获取最新版本数据 ===> ");
		QueryWrapper<Versions> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("current_version", "1");
		Versions versions = super.getOne(queryWrapper);
		if(versions == null) return ResponseModel.failure("获取数据发生异常，请重试");
		return ResponseModel.success(BeanUtil.convert(versions,VersionsIo.class));
	}

	// 文件下载接口
	@Override
	public void downloadFile(String filePath, HttpServletResponse response) {
		logger.info("获取文件路径 ===> " + filePath);
		// 服务器保存的文件地址，即你要下载的文件地址(全路径)
		//String realpath = System.getProperty("user.dir") + File.separator + filePath;
		File file = new File(filePath);
		String fileName = file.getName();
		InputStream inputStream = null;
		OutputStream outputStream = null;
		try {
			inputStream = new BufferedInputStream(new FileInputStream(file));
			byte[] buffer = new byte[inputStream.available()];
			inputStream.read(buffer);
			response.reset();
			response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
			response.addHeader("Content-Length", "" + file.length());
			response.setContentType("application/octet-stream");
			outputStream = new BufferedOutputStream(response.getOutputStream());
			outputStream.write(buffer);
			outputStream.flush();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if (outputStream != null) {
					outputStream.close();
				}
				if (inputStream != null) {
					inputStream.close();
				}
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
