package com.sgcc.code.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sgcc.code.common.exception.CommonException;
import com.sgcc.code.common.utils.*;
import com.sgcc.code.common.web.RequestModel;
import com.sgcc.code.common.web.ResponseModel;
import com.sgcc.code.entity.ColumnClass;
import com.sgcc.code.entity.Config;
import com.sgcc.code.entity.Template;
import com.sgcc.code.entity.User;
import com.sgcc.code.entity.io.template.TemplateAddIo;
import com.sgcc.code.entity.io.template.TemplateIo;
import com.sgcc.code.entity.io.template.TemplateQueryIo;
import com.sgcc.code.entity.io.template.TemplateUpdateIo;
import com.sgcc.code.mapper.TemplateMapper;
import com.sgcc.code.service.ConfigService;
import com.sgcc.code.service.TemplateService;
import com.sgcc.code.service.UserService;
import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.ObjectWrapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.sgcc.code.common.utils.GsonUtil.gson;


/**
 * <p>
 * 模板事务
 * </p>
 *
 * @author liugohua
 * @since Fri Dec 30 17:52:56 CST 2022
 */
@Service
public class TemplateServiceImpl extends ServiceImpl<TemplateMapper, Template> implements TemplateService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private TemplateMapper templateMapper;

	@Autowired
	private UserService userService;

	@Autowired
	private ConfigService configService;

	@Value("${web.user-data-path}")
	String userDataPath;

	//查询模板分页数据
	public ResponseModel<UapPage<TemplateIo>> query(RequestModel<TemplateQueryIo> body){
		logger.info("查询模板分页数据 ===> " + body.getParameter());
		QueryWrapper<Template> queryWrapper = new QueryWrapper<>();
		if(StringUtils.isNotEmpty(body.getParameter().getTreeid())) queryWrapper.like("treeid", body.getParameter().getTreeid());
		if(StringUtils.isNotEmpty(body.getParameter().getTemplateName())) queryWrapper.like("template_name", body.getParameter().getTemplateName());
		if(StringUtils.isNotEmpty(body.getParameter().getTemplateContent())) queryWrapper.like("template_content", body.getParameter().getTemplateContent());
		if(StringUtils.isNotEmpty(body.getParameter().getRemark())) queryWrapper.like("remark", body.getParameter().getRemark());
		//查询出mybatisplus的分页对象 并转换成内部分页对象
        IPage<Template> iPage = super.page(new Page(body.getCurrent(), body.getSize()), queryWrapper);
        return ResponseModel.success(BeanUtil.convertPage(iPage,TemplateIo.class), body.getHeader());
	}
	
	//查询模板记录
	public ResponseModel<List<TemplateIo>> queryAll(RequestModel<TemplateQueryIo> body){
		logger.info("查询模板数据列表 ===> " + body.getParameter());
		QueryWrapper<Template> queryWrapper = new QueryWrapper<>();
		if(StringUtils.isNotEmpty(body.getParameter().getTreeid())) queryWrapper.like("treeid", body.getParameter().getTreeid().trim());
		if(StringUtils.isNotEmpty(body.getParameter().getTemplateName())) queryWrapper.like("template_name", body.getParameter().getTemplateName().trim());
		if(StringUtils.isNotEmpty(body.getParameter().getTemplateContent())) queryWrapper.like("template_content", body.getParameter().getTemplateContent().trim());
		if(StringUtils.isNotEmpty(body.getParameter().getRemark())) queryWrapper.like("remark", body.getParameter().getRemark().trim());
        List<Template> templates = super.list(queryWrapper);
        if(templates == null) return ResponseModel.failure("获取数据发生异常，请重试");
        return ResponseModel.success(BeanUtil.convert(templates,TemplateIo.class),body.getHeader());
	}
	
	//获取单条模板
	public ResponseModel<TemplateIo> view(RequestModel<TemplateIo> body){
		logger.info("查询模板详情数据 ===> " + body.getParameter());
		QueryWrapper<Template> queryWrapper = new QueryWrapper<>();
		if(StringUtils.isNotEmpty(body.getParameter().getTreeid())) queryWrapper.eq("treeid", body.getParameter().getTreeid().trim());
		Template template = super.getOne(queryWrapper);
		if(template == null) return ResponseModel.success("未找到对应的数据记录",null,null);
		return ResponseModel.success(BeanUtil.convert(template,TemplateIo.class),body.getHeader());
	}
	
	//保存模板
	public ResponseModel<Long> add(RequestModel<TemplateAddIo> body){
		logger.info("保存模板数据 ===> " + body.getParameter());
		Template template = BeanUtil.convert(body.getParameter(),Template.class);
		super.save(template);
		return ResponseModel.success("保存成功",template.getId(),body.getHeader());
	}
	
	//删除模板
	public ResponseModel<String> delete(RequestModel<String> body){
		logger.info("删除模板数据 ===> " + body.getParameter());
		StringUtil.isValidIds(body.getParameter());//验证ID是否合法
		String[] idsArr = body.getParameter().split(",");
	    for(String idStr:idsArr){
            super.removeById(Integer.parseInt(idStr));
        }
		return ResponseModel.success("删除成功",body.getParameter(),body.getHeader());
	}
	
	//修改模板
	public ResponseModel<Boolean> update(RequestModel<TemplateUpdateIo> body){
		logger.info("修改模板数据 ===> " + body.getParameter());
		Template template = BeanUtil.convert(body.getParameter(),Template.class);
		return ResponseModel.success("修改成功",templateMapper.updateTemplate(template),body.getHeader());
	}
	
	
	// 导入模板
	@Transactional(rollbackFor = CommonException.class)
	public ResponseModel<String> imports(RequestModel<List<TemplateAddIo>> body){
		logger.info("导入模板数据 ===> 文件" );
		int imp_num = 0;
		String[] titleNameArray = {"空间树id","模板名称","模板内容","备注","创建时间","更新时间"};
		String[] fieldNameArray = {"treeid","templateName","templateContent","remark","createTime","updateTime"};
		try {
			for (TemplateAddIo templateAddIo : body.getParameter()) {
				Template template = BeanUtil.convert(templateAddIo,Template.class);
				super.save(template);
				imp_num++;
			}
		}catch (Exception e) {
			throw new CommonException(500,"导入失败,请确认Excel内容是否正确。</br>错误行号："+(imp_num+2)+"。</br>错误信息："+ExcelUtil.getPointOfException(e.getMessage(),fieldNameArray,titleNameArray)+"。");
		}
		return ResponseModel.success("导入成功，总条数："+imp_num+"条！");
	}

	//生成代码
	@Override
	public ResponseModel<String> makeCode(String configId, String treeids, String dictionarys, String dbTable, Integer dbType,  String dbTableName, String columnJson, String columns, HttpServletRequest httpServletRequest) {
		logger.info("生成代码 ===> ");
		String codePath = "";
		String KeyJavaType = "";
		String uploadid = UUID.randomUUID().toString().replace("-", "");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<String> paths = new ArrayList<>();
		try {
			//自定义参数
			Map<String, Object> dataMap = new HashMap<>();
			List<String> treeidsArr = new ArrayList<>(Arrays.asList(treeids.split(",")));
			//代码生成到这个目录下
			codePath = userDataPath + "/codes/" + uploadid;

			//表模型字段内容数据转换 循环补充四个字段 Java类型 Xml类型 字段大小驼峰
			List<Map<String, String>> javaColumnList = gson.fromJson(columnJson, List.class);


			//数据库类型转换
			List<ColumnClass> columnClassList = new ArrayList<>();
			String className = "";
			String modalName = "";

			if(dbType == 1){
				// mysql 转换
				columnClassList = NameMysqlUtils.convertMapToClassOfList(javaColumnList);
				// 模板内置参数初始化
				className = NameMysqlUtils.replaceUnderLineAndUpperCase(dbTable);//code_user 转换 CodeUser(类名)	modalCName
				modalName = NameMysqlUtils.getTableDataTFName(dbTable);//code_user 转换 codeUser
			}else if(dbType == 2){
				// Oracle 转换
				columnClassList = NameOracleUtils.convertMapToClassOfList(javaColumnList);
				// 模板内置参数初始化
				className = NameOracleUtils.replaceUnderLineAndUpperCase(dbTable);//code_user 转换 CodeUser(类名)	modalCName
				modalName = NameOracleUtils.getTableDataTFName(dbTable);//code_user 转换 codeUser
			}else{
				throw new CommonException(500,"数据库类型不支持");
			}

			//根据指定字段过滤前端所需字段数组
//			List<ColumnClass> tableList = columnClassList.stream().filter(x -> columns.contains(x.getFiledName())).collect(Collectors.toList());

			//获取主键类型 用于兼容JPA
			for (ColumnClass columnClass : columnClassList) {
				if("true".equals(columnClass.getIsKey())){
					KeyJavaType = columnClass.getJavaType();
					break;
				}
			}
			columnClassList.stream().forEach(x->{
				System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
				System.out.println(x.toString());
				System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
			});


			//解析外置参数
			List<com.sgcc.code.entity.Dictionary> dictionaries = JSONArray.parseArray(dictionarys, com.sgcc.code.entity.Dictionary.class);
			User user = userService.findByUsername(httpServletRequest);
			//获取根节点名称
			Config config = configService.getById(configId);
			List<String> zeroNames = templateMapper.queryZoreTreeIds(config.getProjectid().toString());
			for (String zeroName : zeroNames) {
				//动态替换外置参数
				if (dictionaries != null && dictionaries.size() > 0) {
					for (com.sgcc.code.entity.Dictionary dic : dictionaries) {
						zeroName = zeroName.replaceAll("\\$\\{"+dic.getDictionaryKey()+"}",dic.getDictionaryValue());
					}
				}
				paths.add(codePath + "/" + zeroName);
			}
			//freemarker初始化
			Configuration cfg = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
			cfg.setCacheStorage(new freemarker.cache.NullCacheStorage());
			//查找生成文件模板
			List<Map> templatesList = templateMapper.queryTemplates(treeidsArr);
			if (templatesList != null && templatesList.size() > 0) {
				for (Map temp : templatesList) {
					String path = temp.get("path").toString();//文件相对路径
					String templateContent = temp.get("template_content").toString();//模板内容
					path = path.replaceAll("\\$\\{TableName}", className)
							.replaceAll("\\$\\{tableName}",modalName)
							.replaceAll("\\$\\{table_name}",dbTable)
							.replaceAll("\\$\\{tableDesc}",dbTableName)
							.replaceAll("\\$\\{author}",user.getUsername())
							.replaceAll("\\$\\{date}",sdf.format(new Date()));

					//动态替换外置参数
					if (dictionaries != null && dictionaries.size() > 0) {
						for (com.sgcc.code.entity.Dictionary dic : dictionaries) {
							path = path.replaceAll("\\$\\{"+dic.getDictionaryKey()+"}",dic.getDictionaryValue());
						}
					}

					String newFilePath = codePath + path;
					File newFile = new File(newFilePath);
					if (!newFile.getParentFile().exists()) {
						newFile.getParentFile().mkdirs();
					}

					dataMap.put("table_name", dbTable);//表名，例如：user
					dataMap.put("tableDesc", dbTableName);//表名描述,例如：用户
					dataMap.put("TableName", className);//类名，例如：User
					dataMap.put("tableName", modalName);//表名，code_user 转换 codeUser

					//dataMap.put("dbName",databaseTFName);//数据库名称，例如zlink
					dataMap.put("FiledList", columnClassList);//表字段描述数组
					dataMap.put("keyJavaType", KeyJavaType);//主键类型 兼容JPA

					dataMap.put("date", sdf.format(new Date()));//"date" -> "Mon Oct 18 14:55:37 CST 2021"
					dataMap.put("author", user.getUsername());//例如：qinhao
//					dataMap.put("TableList", tableList);//前端form表单、table列表操作字段数组
					//dataMap.put("mapperXml", true);//例如：true

					//动态增加外置参数
					if (dictionaries != null && dictionaries.size() > 0) {
						for (com.sgcc.code.entity.Dictionary dic : dictionaries) {
							dataMap.put(dic.getDictionaryKey(), dic.getDictionaryValue());
						}
					}

					String templateName = path.substring(path.lastIndexOf("/") + 1, path.lastIndexOf("."));
					System.out.println("动态模板名称为 =====> "+templateName);
					//初始化freemarker
					StringTemplateLoader stringTemplateLoader = new StringTemplateLoader();
					stringTemplateLoader.putTemplate(templateName, templateContent);
					cfg.setTemplateLoader(stringTemplateLoader);
					freemarker.template.Template template = cfg.getTemplate(templateName, "utf-8");

					//生成代码文件
					FileOutputStream fos = new FileOutputStream(newFile);
					Writer out = new BufferedWriter(new OutputStreamWriter(fos, "utf-8"), 10240);
					try {
						template.process(dataMap, out, ObjectWrapper.BEANS_WRAPPER);
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						out.close();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new CommonException(500, "生成模板失败！");
		}

		//开始压缩
		String zipPath = userDataPath + "/zip/codes/" + uploadid + "/";
		File zipPathFile = new File(zipPath);
		if (!zipPathFile.exists()) {
			zipPathFile.mkdirs();
		}
		zipPath += "code.zip";
		ZipCompressor zc = new ZipCompressor(zipPath);
		//单组
		zc.compressAll(paths);
		return ResponseModel.success("代码生成成功！", "zip/codes/" + uploadid + "/code.zip");
	}
}