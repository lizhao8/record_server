package com.record.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.common.base.mapper.BaseMapper;
import com.record.entity.Room;

@Mapper
public interface RoomMapper extends BaseMapper<Room> {
	public List<Room> getEnableList(short liveStatus);
	
	@Select("update room set live_status=0 where live_status=1")
	public void resetLiveStatus();

}
