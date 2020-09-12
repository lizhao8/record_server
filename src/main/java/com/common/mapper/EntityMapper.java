package com.common.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.common.base.mapper.BaseMapper;
import com.common.entity.Entity;

@Mapper
public interface EntityMapper extends BaseMapper<Entity> {

}
