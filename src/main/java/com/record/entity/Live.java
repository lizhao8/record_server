package com.record.entity;

import java.util.Date;

import org.apache.ibatis.type.JdbcType;

import com.bilibili.entity.inner.RoomInfo.Room_info;
import com.common.base.entity.BaseEntity;
import com.common.base.entity.Duration;
import com.common.base.handler.DurationTypeHandler;

import lombok.Data;
import tk.mybatis.mapper.annotation.ColumnType;

@Data
public class Live extends BaseEntity {
	private Integer uid; // UID
	private Integer roomId; // 直播间号
	private String title; // 标题
	private String cover; // 封面
	private Date startTime; // 直播开始时间
	private Date endTime; // 直播结束时间
	private String areaName; // 分区
	private String parentAreaName; // 父分区
	private String keyframe; // 关键帧
	@ColumnType(column = "duration", jdbcType = JdbcType.TIME, typeHandler = DurationTypeHandler.class)
	private Duration duration; // 时长

	public Live() {

	}

	public void endTime(Date endTime) {
		this.endTime = endTime;
		this.duration = Duration.ofMillis(endTime.getTime() - startTime.getTime());
	}

	public Live(Room_info room_info) {
		this.uid = room_info.getUid();
		this.roomId = room_info.getRoom_id();
		this.title = room_info.getTitle();
		this.cover = room_info.getCover();
		this.startTime = new Date(room_info.getLive_start_time() * 1000);
		this.areaName = room_info.getArea_name();
		this.parentAreaName = room_info.getParent_area_name();
		this.keyframe = room_info.getKeyframe();
	}
}
