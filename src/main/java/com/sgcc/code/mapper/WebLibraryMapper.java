package com.sgcc.code.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sgcc.code.entity.Shell;
import com.sgcc.code.entity.WebLibrary;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * <p>
 * 网页库控制类
 * </p>
 *
 * @author liugohua
 * @since Mon Mar 06 17:53:18 CST 2023
 */
 @Mapper
public interface WebLibraryMapper extends BaseMapper<WebLibrary> {

    // 自定义配置分页
    IPage<Map> selectPageList(IPage<Shell> page, @Param("projectId") Integer projectId);
}