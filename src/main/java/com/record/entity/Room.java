package com.record.entity;

import java.util.Date;

import com.bilibili.entity.inner.RoomInfo.Room_info;
import com.bilibili.entity.outer.RoomInfo;
import com.common.base.entity.BaseEntity;

import lombok.Data;

@Data
public class Room extends BaseEntity {
	public static final short roomStatus_normal = 0; // 封禁
	public static final short roomStatus_lock = 1; // 正常
	public static final short roomStatus_hidden = 2; // 隐藏

	public static final short liveStatus_living = 1;
	public static final short liveStatus_unlive = 0;

	private Integer roomId;// 直播间号
	private Integer uid; // uid
	private String tags; // 标签
	private String background; // 背景
	private String news; // 公告
	private Short roomStatus; // 直播间状态
	private Date endTime;// 结束时间
	private Short liveStatus; // 直播状态
	private Short record; // 是否录制

	private Anchor anchor;

	public void setRoomStatus(Room_info room_info) {
		if (room_info.getHidden_status() == 1) {
			this.roomStatus = 2; // 已隐藏
			this.endTime = new Date(room_info.getHidden_time() * 1000);
		}
		if (room_info.getLock_status() == 1) {
			this.roomStatus = 0; // 已封禁
			this.endTime = new Date(room_info.getLock_time() * 1000);
		}
	}
	public static Room _init(RoomInfo roomInfo) {
		return new Room().init(roomInfo);
	}
	public Room init(RoomInfo roomInfo) {
		Room_info room_info = roomInfo.getRoom_info();
		this.uid = room_info.getUid();
		this.roomId = room_info.getRoom_id();
		this.tags = room_info.getTags();
		this.background = room_info.getBackground();
		this.news = roomInfo.getNews_info().getContent();
		this.setRoomStatus(room_info);
		return this;
	}
}
