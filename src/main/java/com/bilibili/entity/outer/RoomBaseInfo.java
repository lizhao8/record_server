package com.bilibili.entity.outer;

import lombok.Data;

@Data
public class RoomBaseInfo {

	private int roomStatus;
	private int roundStatus;
	private short liveStatus;
	private String url;
	private String title;
	private String cover;
	private int online;
	private int roomid;
	private int broadcast_type;
	private int online_hidden;

}