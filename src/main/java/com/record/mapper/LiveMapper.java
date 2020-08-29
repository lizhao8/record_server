package com.record.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.common.base.mapper.BaseMapper;
import com.record.entity.Live;

@Mapper
public interface LiveMapper extends BaseMapper<Live> {
	@Select("update live set status=0 where status=1")
	public void resetStatus();	
}
