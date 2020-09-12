package com.common.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.common.base.mapper.BaseMapper;
import com.common.entity.Field;

@Mapper
public interface FieldMapper extends BaseMapper<Field> {
	public List<Field> getField(String entityName);
}
