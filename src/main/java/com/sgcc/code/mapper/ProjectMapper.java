package com.sgcc.code.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sgcc.code.entity.Dictionary;
import com.sgcc.code.entity.Project;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 项目控制类
 * </p>
 *
 * @author yangrui
 * @since Thu Dec 29 17:20:21 CST 2022
 */
 @Mapper
public interface ProjectMapper extends BaseMapper<Project> {

}