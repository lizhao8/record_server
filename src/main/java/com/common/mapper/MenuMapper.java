package com.common.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.common.base.mapper.BaseMapper;
import com.common.entity.Menu;

@Mapper
public interface MenuMapper extends BaseMapper<Menu> {

	public List<Menu> getByUserId(Integer id);
}
