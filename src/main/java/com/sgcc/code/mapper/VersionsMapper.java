package com.sgcc.code.mapper;

import com.sgcc.code.entity.Versions;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 版本控制类
 * </p>
 *
 * @author liugohua
 * @since Wed Jan 04 16:35:58 CST 2023
 */
 @Mapper
public interface VersionsMapper extends BaseMapper<Versions> {

    // 清空所有版本
    void updateDefaultVersion();

}