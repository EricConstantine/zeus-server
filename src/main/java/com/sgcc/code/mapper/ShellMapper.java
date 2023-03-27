package com.sgcc.code.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.sgcc.code.entity.Shell;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * <p>
 * 脚本管理控制类
 * </p>
 *
 * @author liugohua
 * @since Mon Mar 06 09:59:00 CST 2023
 */
 @Mapper
public interface ShellMapper extends BaseMapper<Shell> {

    // 自定义配置分页
    IPage<Map> selectPageList(IPage<Shell> page, @Param(Constants.WRAPPER) QueryWrapper<Map> queryWrapper);

    // 获取脚本详情
    Shell getDetail(@Param("id") Integer id);
}