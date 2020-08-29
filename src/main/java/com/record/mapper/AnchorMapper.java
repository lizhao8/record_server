package com.record.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.common.base.mapper.BaseMapper;
import com.record.entity.Anchor;

@Mapper
public interface AnchorMapper extends BaseMapper<Anchor> {
	public Anchor getByUid(int uid);
}
