package com.bilibili.entity.inner.RoomInfo;

import lombok.Data;

@Data
public class Rankdb_info {

	private long roomid;
	private String rank_desc;
	private String color;
	private String h5_url;
	private String web_url;
	private long timestamp;
}