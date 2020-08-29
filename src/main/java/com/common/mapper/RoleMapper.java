package com.common.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.common.base.mapper.BaseMapper;
import com.common.entity.Role;

@Mapper
public interface RoleMapper extends BaseMapper<Role>{

	public List<Role> getByUserId(int id);

}
