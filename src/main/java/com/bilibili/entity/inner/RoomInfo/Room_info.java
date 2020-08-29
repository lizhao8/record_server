package com.bilibili.entity.inner.RoomInfo;

import lombok.Data;

@Data
public class Room_info {

	private int uid;
	private int room_id;
	private int short_id;
	private String title;
	private String cover;
	private String tags;
	private String background;
	private String description;
	private short live_status;
	private long live_start_time;
	private int live_screen_type;
	private int lock_status;
	private long lock_time;
	private int hidden_status;
	private long hidden_time;
	private int area_id;
	private String area_name;
	private int parent_area_id;
	private String parent_area_name;
	private String keyframe;
	private int special_type;
	private String up_session;
	private int pk_status;
	private boolean is_studio;
	private Pendants pendants;
	private int on_voice_join;
	private int online;
	// private Room_type room_type;
}