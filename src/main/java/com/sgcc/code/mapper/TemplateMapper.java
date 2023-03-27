package com.sgcc.code.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sgcc.code.entity.Template;
import com.sgcc.code.entity.io.template.TemplateIo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 模板控制类
 * </p>
 *
 * @author liugohua
 * @since Fri Dec 30 17:52:56 CST 2022
 */
 @Mapper
public interface TemplateMapper extends BaseMapper<Template> {

    Boolean updateTemplate(Template template);

    List<Map> queryTemplates(@Param("list") List<String> treeidsArr);

    List<String> queryZoreTreeIds(String projectid);
}