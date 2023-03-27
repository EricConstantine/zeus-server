package com.sgcc.code.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sgcc.code.entity.Todo;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 待办控制类
 * </p>
 *
 * @author liugohua
 * @since Tue Jan 31 10:16:45 CST 2023
 */
 @Mapper
public interface TodoMapper extends BaseMapper<Todo> {


}