package com.sgcc.code.mapper;

import com.sgcc.code.entity.Dictionary;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 字典控制类
 * </p>
 *
 * @author liugohua
 * @since Wed Jan 04 12:23:13 CST 2023
 */
 @Mapper
public interface DictionaryMapper extends BaseMapper<Dictionary> {


}