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
import com.sgcc.code.entity.Template;
import com.sgcc.code.entity.Zonetree;
import com.sgcc.code.entity.io.template.TemplateAddIo;
import com.sgcc.code.entity.io.zonetree.*;
import com.sgcc.code.mapper.ZonetreeMapper;
import com.sgcc.code.service.TemplateService;
import com.sgcc.code.service.ZonetreeService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * <p>
 * 空间树事务
 * </p>
 *
 * @author liugohua
 * @since Fri Dec 30 09:58:13 CST 2022
 */
@Service
public class ZonetreeServiceImpl extends ServiceImpl<ZonetreeMapper, Zonetree> implements ZonetreeService {


	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ZonetreeMapper zonetreeMapper;

	@Autowired
	private TemplateService templateService;

	//查询空间树分页数据
	public ResponseModel<UapPage<ZonetreeIo>> query(RequestModel<ZonetreeQueryIo> body){
		logger.info("查询空间树分页数据 ===> " + body.getParameter());
		QueryWrapper<Zonetree> queryWrapper = new QueryWrapper<>();
		if(body.getParameter().getPid() != null) queryWrapper.eq("pid", body.getParameter().getPid());
		if(StringUtils.isNotEmpty(body.getParameter().getProjectid())) queryWrapper.like("projectid", body.getParameter().getProjectid());
		if(StringUtils.isNotEmpty(body.getParameter().getName())) queryWrapper.like("name", body.getParameter().getName());
		if(StringUtils.isNotEmpty(body.getParameter().getType())) queryWrapper.like("type", body.getParameter().getType());
		if(StringUtils.isNotEmpty(body.getParameter().getPath())) queryWrapper.like("path", body.getParameter().getPath());
		//查询出mybatisplus的分页对象 并转换成内部分页对象
        IPage<Zonetree> iPage = super.page(new Page(body.getCurrent(), body.getSize()), queryWrapper);
        return ResponseModel.success(BeanUtil.convertPage(iPage,ZonetreeIo.class), body.getHeader());
	}
	
	//查询空间树记录
	public ResponseModel<List<ZonetreeIo>> queryAll(RequestModel<ZonetreeQueryIo> body){
		logger.info("查询空间树数据列表 ===> " + body.getParameter());
		QueryWrapper<Zonetree> queryWrapper = new QueryWrapper<>();
		if(StringUtils.isNotEmpty(body.getParameter().getProjectid())) queryWrapper.like("projectid", body.getParameter().getProjectid());
		List<Zonetree> zonetrees = super.list(queryWrapper);
        if(zonetrees==null && zonetrees.size()==0) return ResponseModel.failure("获取项目根节点数据发生异常，请重试");

		//获取顶点数据，即最上层数据,该数据的pid为0
		List<Zonetree> zonetreeList = new ArrayList<>();
		Zonetree zoneTreeParent = zonetrees	.stream().filter(t -> t.getPid().equals(0L)).findFirst().get();
		zoneTreeParent.setChild(findChildren(zonetrees, zoneTreeParent.getId().toString()));
		zonetreeList.add(zoneTreeParent);
		return ResponseModel.success(BeanUtil.convert(zonetreeList,ZonetreeIo.class),body.getHeader());
	}


	//获取全部的子集合
	private List<Zonetree> findChildren(List<Zonetree> zonetrees, String id) {
		if (zonetrees != null && zonetrees.size() == 1) {
			return new ArrayList<>();
		}
		List<Zonetree> zonetreeList = zonetrees.stream()
			.filter(t -> t.getPid()!=null)
			.filter(t -> id.equals(t.getPid().toString()))
			.peek(t -> {
				if("1".equals(t.getType())){
					t.setChild(findChildren(zonetrees, t.getId().toString()));
				}else{
					t.setChild(null);
				}
			})
			.collect(Collectors.toList());
		return zonetreeList;
	}

	//获取单条空间树
	public ResponseModel<ZonetreeIo> view(RequestModel<ZonetreeViewIo> body){
		logger.info("查询空间树详情数据 ===> " + body.getParameter());
		QueryWrapper<Zonetree> queryWrapper = new QueryWrapper<>();
		if(StringUtils.isNotEmpty(body.getParameter().getId())) queryWrapper.like("id", body.getParameter().getId());
		if(StringUtils.isNotEmpty(body.getParameter().getProjectid())) queryWrapper.like("projectid", body.getParameter().getProjectid());
		Zonetree zonetree = super.getOne(queryWrapper);
		if(zonetree == null) return ResponseModel.failure("未找到对应的数据记录");
		return ResponseModel.success(BeanUtil.convert(zonetree,ZonetreeIo.class),body.getHeader());
	}
	
	//保存空间树
	public ResponseModel<Boolean> add(RequestModel<ZonetreeAddIo> body){
		logger.info("保存空间树数据 ===> " + body.getParameter());
		Zonetree zonetree = BeanUtil.convert(body.getParameter(),Zonetree.class);
		// 获取上级节点
		Zonetree lastZoneTree = super.getById(zonetree.getPid());
		if(lastZoneTree!=null){
			zonetree.setPath(lastZoneTree.getPath()+"/"+zonetree.getName());
		}else{
			zonetree.setPath("/" + zonetree.getName());
		}
		super.save(zonetree);
		// 如果是文件类型 默认新增空模板
		if("2".equals(zonetree.getType())){
			Template template = new Template();
			template.setTemplateContent("");
			template.setTreeid(zonetree.getId().toString());
			templateService.add(RequestModel.getInstance(BeanUtil.convert(template, TemplateAddIo.class)));
		}
		return ResponseModel.success("保存成功",true,body.getHeader());
	}

	//删除空间树
	public ResponseModel<Boolean> delete(RequestModel<ZonetreeDeleteIo> body){
		logger.info("删除空间树数据 ===> " + body.getParameter());
		boolean flag = false;
		String ids = body.getParameter().getId();
		String projectid = body.getParameter().getProjectid();
		StringUtil.isValidIds(ids);//验证ID是否合法
		String[] idsArr = ids.split(",");
	    for(String idStr : idsArr){
			// 删除模板
			QueryWrapper<Template> wrapper = new QueryWrapper<>();
			if(StringUtils.isNotEmpty(body.getParameter().getId())) wrapper.eq("treeid", idStr);
			templateService.remove(wrapper);
			flag = super.removeById(Integer.parseInt(idStr));
			QueryWrapper<Zonetree> queryWrapper = new QueryWrapper<>();
			if(StringUtils.isNotEmpty(projectid)) queryWrapper.eq("projectid", projectid);
			queryWrapper.eq("pid", idStr);
			List<Zonetree> zonetrees = super.list(queryWrapper);
			//递归删除该节点下所有节点
			if (zonetrees!=null && zonetrees.size()>0){
				for (Zonetree z : zonetrees){
					QueryWrapper<Template> wrapper2 = new QueryWrapper<>();
					if(StringUtils.isNotEmpty(body.getParameter().getId())) wrapper2.eq("treeid", z.getId().toString());
					templateService.remove(wrapper);
					delete(RequestModel.getInstance(new ZonetreeDeleteIo(z.getId().toString(),body.getParameter().getProjectid())));
				}
			}
        }
		return ResponseModel.success("删除成功",flag,body.getHeader());
	}
	
	//修改空间树
	public ResponseModel<Boolean> update(RequestModel<ZonetreeUpdateIo> body){
		logger.info("修改空间树数据 ===> " + body.getParameter());
		Zonetree zonetree = BeanUtil.convert(body.getParameter(),Zonetree.class);
		Zonetree oldZonetree = super.getById(zonetree.getId());
		String oldPath = oldZonetree.getPath();
		// 获取上级节点
		Zonetree lastZoneTree = super.getById(zonetree.getPid());
		if(lastZoneTree!=null){
			String newPath = lastZoneTree.getPath() + "/" + zonetree.getName();
			zonetree.setPath(newPath);
			QueryWrapper<Zonetree> queryWrapper = new QueryWrapper<>();
			if(StringUtils.isNotEmpty(zonetree.getId().toString())) queryWrapper.eq("pid", zonetree.getId().toString());
			List<Zonetree> zonetrees = super.list(queryWrapper);
			// 递归更新下级节点
			recursionUpdateZonetree(zonetrees, oldPath, newPath);
		}else{
			zonetree.setPath("/" + zonetree.getName());
		}
		return ResponseModel.success("修改成功",super.updateById(zonetree),body.getHeader());
	}

	// 递归更新下级节点
	private void recursionUpdateZonetree(List<Zonetree> zonetrees, String oldPath, String newPath) {
		if(zonetrees!=null && zonetrees.size()>0){
			for (Zonetree z : zonetrees) {
				z.setPath(z.getPath().replace(oldPath, newPath));
				super.updateById(z);
				QueryWrapper<Zonetree> queryWrapper = new QueryWrapper<>();
				if(StringUtils.isNotEmpty(z.getId().toString())) queryWrapper.eq("pid", z.getId().toString());
				List<Zonetree> nextZonetrees = super.list(queryWrapper);
				if(nextZonetrees.size()>0){
					recursionUpdateZonetree(nextZonetrees, oldPath, newPath);
				}
			}
		}
	}


	// 导入空间树
	@Transactional(rollbackFor = CommonException.class)
	public ResponseModel<String> imports(RequestModel<List<ZonetreeAddIo>> body){
		logger.info("导入空间树数据 ===> 文件" );
		int imp_num = 0;
		String[] titleNameArray = {"父级id","项目id","当前层级名称","类型 1文件夹 2文件","文件路径","创建时间","更新时间"};
		String[] fieldNameArray = {"pid","projectid","name","type","path","createTime","updateTime"};
		try {
			for (ZonetreeAddIo zonetreeAddIo : body.getParameter()) {
				Zonetree zonetree = BeanUtil.convert(zonetreeAddIo,Zonetree.class);
				super.save(zonetree);
				imp_num++;
			}
		}catch (Exception e) {
			throw new CommonException(500,"导入失败,请确认Excel内容是否正确。</br>错误行号："+(imp_num+2)+"。</br>错误信息："+ExcelUtil.getPointOfException(e.getMessage(),fieldNameArray,titleNameArray)+"。");
		}
		return ResponseModel.success("导入成功，总条数："+imp_num+"条！");
	}

}