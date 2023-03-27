package com.sgcc.code.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sgcc.code.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户控制类
 * </p>
 *
 * @author yangrui
 * @since Wed Dec 28 14:46:28 CST 2022
 */
 @Mapper
public interface UserMapper extends BaseMapper<User> {

     // 获取用户项目列表（普通用户权限）
    List<Map<String,Object>> getProjects(@Param("userid") String userid);

    // 获取全部项目列表（管理员权限）
    List<Map<String, Object>> getAllProjects();

    // 分页查询
    //IPage<Map<String, Object>> selectPageList(IPage<User> page, @Param(Constants.WRAPPER) QueryWrapper<Map<String, Object>> queryWrapper);

}