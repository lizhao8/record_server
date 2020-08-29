package com.record.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.common.base.mapper.BaseMapper;
import com.record.entity.Record;

@Mapper
public interface RecordMapper extends BaseMapper<Record> {
	@Select("update record set status=0 where status=1")
	public void resetStatus();
}
