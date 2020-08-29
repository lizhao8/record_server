package com.common.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.common.base.mapper.BaseMapper;
import com.common.entity.User;

@Mapper
public interface UserMapper extends BaseMapper<User> {
	
}
