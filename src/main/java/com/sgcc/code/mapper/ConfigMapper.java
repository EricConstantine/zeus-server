package com.sgcc.code.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.sgcc.code.entity.Config;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 配置控制类
 * </p>
 *
 * @author liugohua
 * @since Tue Jan 10 09:42:21 CST 2023
 */
 @Mapper
public interface ConfigMapper extends BaseMapper<Config> {

    // 获取用户项目列表
    List<Map<String,Object>> getConfigs(@Param("userid") String userid);

    // 自定义配置分页
    IPage<Map> selectPageList(IPage<Config> page, @Param(Constants.WRAPPER) QueryWrapper<Map> queryWrapper);

    // 查询全部
    List<Map> queryAll(@Param(Constants.WRAPPER) QueryWrapper<Map> queryWrapper);

    // 获取配置项目数据库表信息（Mysql）
    List<Map> queryMysqlTables(@Param("dbname") String dbname);

    // 获取配置项目数据库表信息（Oracle）
    List<Map> queryOracleTables(@Param("dbuser") String dbuser);

    // 获取配置项目数据库表信息详情（Mysql）
    List<Map> getMysqlTableView(@Param("dbname") String dbname, @Param("tableName") String tableName);

    // 获取配置项目数据库表信息详情（Oracle）
    List<Map> getOracleTableView(@Param("dbname") String dbname, @Param("tableName") String tableName);
}