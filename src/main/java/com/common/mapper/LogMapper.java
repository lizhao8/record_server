package com.common.mapper;

import java.util.Date;

import org.apache.ibatis.annotations.Mapper;

import com.common.base.mapper.BaseMapper;
import com.common.entity.Log;

@Mapper
public interface LogMapper extends BaseMapper<Log>{
	Integer deleteBeforeDate(String dateString);	
	Integer deleteBeforeDate(Date date);	
}
