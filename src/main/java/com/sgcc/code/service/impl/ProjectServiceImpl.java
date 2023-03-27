package com.sgcc.code.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sgcc.code.common.exception.CommonException;
import com.sgcc.code.common.utils.BeanUtil;
import com.sgcc.code.common.utils.ExcelUtil;
import com.sgcc.code.common.utils.StringUtil;
import com.sgcc.code.common.utils.UapPage;
import com.sgcc.code.common.web.RequestModel;
import com.sgcc.code.common.web.ResponseModel;
import com.sgcc.code.entity.*;
import com.sgcc.code.entity.io.project.*;
import com.sgcc.code.mapper.ProjectMapper;
import com.sgcc.code.service.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


/**
 * <p>
 * 项目事务
 * </p>
 *
 * @author yangrui
 * @since Thu Dec 29 17:20:21 CST 2022
 */
@Service
public class ProjectServiceImpl extends ServiceImpl<ProjectMapper, Project> implements ProjectService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ProjectMapper projectMapper;

	@Autowired
	private DictionaryService dictionaryService;

	@Autowired
	private ZonetreeService zonetreeService;

	@Autowired
	private TemplateService templateService;

	@Autowired
	private ProjectuserService projectuserService;

	//查询项目分页数据
	public ResponseModel<UapPage<ProjectIo>> query(RequestModel<ProjectQueryIo> body){
		logger.info("查询项目分页数据 ===> " + body.getParameter());
		QueryWrapper<Project> queryWrapper = new QueryWrapper<>();
		List<String> timeInterval = body.getParameter().getTimeInterval();
		if(timeInterval!=null && timeInterval.size()>0){
			queryWrapper.gt("create_time", timeInterval.get(0));
			if(timeInterval.size()>1){
				queryWrapper.lt("create_time",timeInterval.get(1));
			}
		}
		if(StringUtils.isNotEmpty(body.getParameter().getProjectName())) queryWrapper.like("project_name", body.getParameter().getProjectName());
		if(StringUtils.isNotEmpty(body.getParameter().getProjectDescribe())) queryWrapper.like("project_describe", body.getParameter().getProjectDescribe());
		if(StringUtils.isNotEmpty(body.getParameter().getIspublic())) queryWrapper.like("ispublic", body.getParameter().getIspublic());
		queryWrapper.orderByDesc("create_time");
		//查询出mybatisplus的分页对象 并转换成内部分页对象
        IPage<Project> iPage = super.page(new Page(body.getCurrent(), body.getSize()), queryWrapper);
		// 自定义分页查询
		//IPage<Map> iPage = projectMapper.selectPageList(new Page(body.getCurrent(), body.getSize()), queryWrapper);
        return ResponseModel.success(BeanUtil.convertPage(iPage,ProjectIo.class), body.getHeader());
	}
	
	//查询项目记录
	public ResponseModel<List<ProjectIo>> queryAll(RequestModel<ProjectQueryIo> body){
		logger.info("查询项目数据列表 ===> " + body.getParameter());
		QueryWrapper<Project> queryWrapper = new QueryWrapper<>();
		if(StringUtils.isNotEmpty(body.getParameter().getProjectName())) queryWrapper.like("project_name", body.getParameter().getProjectName().trim());
		if(StringUtils.isNotEmpty(body.getParameter().getProjectDescribe())) queryWrapper.like("project_describe", body.getParameter().getProjectDescribe().trim());
		if(StringUtils.isNotEmpty(body.getParameter().getIspublic())) queryWrapper.like("ispublic", body.getParameter().getIspublic().trim());
        List<Project> projects = super.list(queryWrapper);
        if(projects == null) return ResponseModel.failure("获取数据发生异常，请重试");
        return ResponseModel.success(BeanUtil.convert(projects,ProjectIo.class),body.getHeader());
	}
	
	//获取单条项目
	public ResponseModel<ProjectIo> view(RequestModel<ProjectViewIo> body){
		logger.info("查询项目详情数据 ===> " + body.getParameter());
		Project project = super.getById(body.getParameter().getId());
		if(project == null) return ResponseModel.failure("未找到对应的数据记录");
		// 查询项目字典配置
		QueryWrapper<Dictionary> dictionaryWrapper = new QueryWrapper<>();
		dictionaryWrapper.eq("projectid",project.getId());
		dictionaryWrapper.orderByDesc("create_time");
		List<Dictionary> dictionaryList = dictionaryService.list(dictionaryWrapper);
		project.setDictionaryList(dictionaryList);
		// 查询项目成员
		QueryWrapper<Projectuser> queryWrapper = new QueryWrapper<>();
		if(body.getParameter().getId() != null) queryWrapper.like("projectid", body.getParameter().getId());
		List<Projectuser> projectusers = projectuserService.list(queryWrapper);
		// 区分用户和已添加用户
		List<String> checkedUserids = projectusers.stream().map(p -> p.getUserid()).collect(Collectors.toList());
		project.setUserList(checkedUserids);
		return ResponseModel.success(BeanUtil.convert(project,ProjectIo.class),body.getHeader());
	}
	
	//保存项目
	@Transactional(rollbackFor = CommonException.class)
	public ResponseModel<ProjectIo> add(RequestModel<ProjectAddIo> body){
		logger.info("保存项目数据 ===> " + body.getParameter());
		Project project = BeanUtil.convert(body.getParameter(),Project.class);
		super.save(project);
		//新增字典配置信息
		List<Dictionary> dictionaryList = project.getDictionaryList();
		if(dictionaryList!=null && dictionaryList.size()>0){
			//数据字典增加三种类型 1字符串 2数组 3字段属性列表
			for (Dictionary dic : dictionaryList) {
				if(dic.getTypes()==1){
					dic.setTypes(1);
				}
				dic.setProjectid(project.getId().toString());
			}
			dictionaryService.saveBatch(dictionaryList);
		}
		//新增项目人员信息
		List<Projectuser> projectUserList = new ArrayList<>();
		List<String> userList = project.getUserList();
		if(userList!=null && userList.size()>0){
			for (int i = 0; i < userList.size(); i++) {
				Projectuser projectuser = new Projectuser();
				projectuser.setProjectid(project.getId().toString());
				projectuser.setUserid(userList.get(i));
				projectuser.setStatus("1");// 默认人员启用状态
				projectUserList.add(projectuser);
			}
			projectuserService.saveBatch(projectUserList);
		}
		//新增项目后，自动生成一个目录根节点
		Zonetree zonetree = new Zonetree(null,0L,project.getId().toString(),"根节点","1","",new Timestamp(System.currentTimeMillis()),new Timestamp(System.currentTimeMillis()));
		zonetreeService.save(zonetree);
		return ResponseModel.success("保存成功",BeanUtil.convert(project, ProjectIo.class),body.getHeader());
	}
	
	//删除项目
	@Transactional(rollbackFor = CommonException.class)
	public ResponseModel<Boolean> delete(RequestModel<String> body){
		logger.info("删除项目数据 ===> " + body.getParameter());
		StringUtil.isValidIds(body.getParameter());//验证ID是否合法
		String[] idsArr = body.getParameter().split(",");
	    for(String idStr:idsArr){
			// 删除项目
			super.removeById(Integer.parseInt(idStr));
			// 删除项目字典配置
			QueryWrapper<Dictionary> dictionaryWrapper = new QueryWrapper<>();
			dictionaryWrapper.eq("projectid",idStr);
			dictionaryService.remove(dictionaryWrapper);
			// 删除空间树
			QueryWrapper<Zonetree> treeWrapper = new QueryWrapper<>();
			treeWrapper.eq("projectid",idStr);
			zonetreeService.remove(treeWrapper);
			// 删除人员配置
			QueryWrapper<Projectuser> projectuserWrapper = new QueryWrapper<>();
			projectuserWrapper.eq("projectid",idStr);
			projectuserService.remove(projectuserWrapper);
        }
		return ResponseModel.success("删除成功",true,body.getHeader());
	}
	
	//修改项目
	@Transactional(rollbackFor = CommonException.class)
	public ResponseModel<Boolean> update(RequestModel<ProjectUpdateIo> body){
		logger.info("修改项目数据 ===> " + body.getParameter());
		Project project = BeanUtil.convert(body.getParameter(),Project.class);
		super.updateById(project);
		//删除当前项目字典配置
		QueryWrapper<Dictionary> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("projectid",project.getId());
		dictionaryService.remove(queryWrapper);
		//新增字典配置信息
		List<Dictionary> dictionaryList = project.getDictionaryList();
		if(dictionaryList!=null && dictionaryList.size()>0){
			//数据字典增加三种类型 1字符串 2数组 3字段属性列表
			for (Dictionary dic : dictionaryList) {
				if(dic.getTypes()==1){
					dic.setTypes(1);
				}
				dic.setProjectid(project.getId().toString());
			}
			dictionaryService.saveBatch(dictionaryList);
		}
		//删除项目人员
		QueryWrapper<Projectuser> projectuserQueryWrapper = new QueryWrapper<>();
		projectuserQueryWrapper.eq("projectid",project.getId());
		projectuserService.remove(projectuserQueryWrapper);
		//新增项目人员信息
		List<Projectuser> projectUserList = new ArrayList<>();
		List<String> userList = project.getUserList();
		if(userList!=null && userList.size()>0){
			for (int i = 0; i < userList.size(); i++) {
				Projectuser projectuser = new Projectuser();
				projectuser.setProjectid(project.getId().toString());
				projectuser.setUserid(userList.get(i));
				projectuser.setStatus("1");// 默认人员启用状态
				projectUserList.add(projectuser);
			}
			projectuserService.saveBatch(projectUserList);
		}
		return ResponseModel.success("修改成功",true,body.getHeader());
	}
	
	
	// 导入项目
	@Transactional(rollbackFor = CommonException.class)
	public ResponseModel<String> imports(RequestModel<List<ProjectAddIo>> body){
		logger.info("导入项目数据 ===> 文件" );
		int imp_num = 0;
		String[] titleNameArray = {"项目名称","项目描述","是否公开 1是 0否","创建时间"};
		String[] fieldNameArray = {"projectName","projectDescribe","ispublic","createTime"};
		try {
			for (ProjectAddIo projectAddIo : body.getParameter()) {
				Project project = BeanUtil.convert(projectAddIo,Project.class);
				super.save(project);
				imp_num++;
			}
		}catch (Exception e) {
			throw new CommonException(500,"导入失败,请确认Excel内容是否正确。</br>错误行号："+(imp_num+2)+"。</br>错误信息："+ExcelUtil.getPointOfException(e.getMessage(),fieldNameArray,titleNameArray)+"。");
		}
		return ResponseModel.success("导入成功，总条数："+imp_num+"条！");
	}

	//复制项目
	@Override
	@Transactional(rollbackFor = CommonException.class)
	public ResponseModel<Boolean> copy(RequestModel<ProjectUpdateIo> body) {
		logger.info("复制项目主键id ===> " + body.getParameter().getId());
		Project project = super.getById(body.getParameter().getId());
		if(project == null) return ResponseModel.success("未找到对应的数据记录", false);
		Project copyProject = BeanUtil.convert(project, Project.class);
		copyProject.setId(null);
		copyProject.setCreateTime(new Timestamp(System.currentTimeMillis()));
		super.save(copyProject);
		// 查询项目字典配置
		QueryWrapper<Dictionary> dictionaryWrapper = new QueryWrapper<>();
		dictionaryWrapper.eq("projectid",project.getId());
		dictionaryWrapper.orderByDesc("create_time");
		List<Dictionary> dictionaryList = dictionaryService.list(dictionaryWrapper);
		dictionaryList.stream().forEach(x -> {
			x.setId(null);
			x.setProjectid(copyProject.getId().toString());
			x.setCreateTime(new Date());
			x.setUpdateTime(new Date());
		});
		dictionaryService.saveBatch(dictionaryList);
		// 查询项目成员
		QueryWrapper<Projectuser> queryWrapper = new QueryWrapper<>();
		if(body.getParameter().getId() != null) queryWrapper.like("projectid", body.getParameter().getId());
		List<Projectuser> projectusers = projectuserService.list(queryWrapper);
		projectusers.stream().forEach(x -> {
			x.setId(null);
			x.setProjectid(copyProject.getId().toString());
			x.setCreateTime(new Date());
		});
		projectuserService.saveBatch(projectusers);
		// 复制空间树
		QueryWrapper<Zonetree> zonetreeQueryWrapper = new QueryWrapper<>();
		zonetreeQueryWrapper.eq("projectid", project.getId().toString());
		List<Zonetree> zonetrees = zonetreeService.list(zonetreeQueryWrapper);
		if(zonetrees==null && zonetrees.size()==0) return ResponseModel.failure("获取项目根节点数据发生异常，请重试");

		//获取顶点数据，即最上层数据,该数据的pid为0
		List<Zonetree> zonetreeList = new ArrayList<>();
		Zonetree zoneTreeParent = zonetrees.stream().filter(t -> t.getPid().equals(0L)).findFirst().get();
		Zonetree zonetree = BeanUtil.convert(zoneTreeParent, Zonetree.class);
		zonetree.setId(null);
		zonetree.setProjectid(copyProject.getId().toString());
		zonetree.setCreateTime(new Timestamp(System.currentTimeMillis()));
		zonetree.setUpdateTime(new Timestamp(System.currentTimeMillis()));
		zonetreeService.save(zonetree);
		copyZoneTree(zonetrees, zoneTreeParent.getId().toString(), copyProject.getId().toString(), zonetree.getId());
		return ResponseModel.success("复制成功",true,body.getHeader());
	}

	//递归复制树节点和模板内容
	private void copyZoneTree(List<Zonetree> zonetrees, String id, String copyProjectId, Long zonetreeId) {
		try {
			if (zonetrees != null) {
				zonetrees.stream()
						.filter(t -> t.getPid()!=null)
						.filter(t -> id.equals(t.getPid().toString()))
						.forEach(t -> {
							Zonetree zonetree = BeanUtil.convert(t, Zonetree.class);
							zonetree.setId(null);
							zonetree.setProjectid(copyProjectId);
							zonetree.setPid(zonetreeId);
							zonetree.setCreateTime(new Timestamp(System.currentTimeMillis()));
							zonetree.setUpdateTime(new Timestamp(System.currentTimeMillis()));
							zonetreeService.save(zonetree);
							if("2".equals(t.getType())){
								//复制模板
								QueryWrapper<Template> templateQueryWrapper = new QueryWrapper<>();
								templateQueryWrapper.eq("treeid", t.getId());
								Template template = templateService.getOne(templateQueryWrapper);
								if(template!=null){
									Template copyTemplate = BeanUtil.convert(template, Template.class);
									copyTemplate.setId(null);
									copyTemplate.setTreeid(zonetree.getId().toString());
									copyTemplate.setCreateTime(new Date());
									copyTemplate.setUpdateTime(new Date());
									templateService.save(copyTemplate);
								}
							}
							copyZoneTree(zonetrees, t.getId().toString(), copyProjectId, zonetree.getId());
						});
			}
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}