package com.common.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.base.service.BaseService;
import com.common.entity.Field;
import com.common.mapper.FieldMapper;

@Service
public class FieldService extends BaseService<Field> {
	@Autowired
	FieldMapper fieldMapper;

	public List<Field> getField(String entityName) {
		return fieldMapper.getField(entityName);
	}

}
