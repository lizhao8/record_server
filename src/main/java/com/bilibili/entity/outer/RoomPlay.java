/**
  * Copyright 2020 bejson.com 
  */
package com.bilibili.entity.outer;

import java.util.List;

import com.bilibili.entity.inner.roomPlay.Play_url;

import lombok.Data;

@Data
public class RoomPlay {
	private int room_id;
	private int short_id;
	private long uid;
	private int need_p2p;
	private boolean is_hidden;
	private boolean is_locked;
	private boolean is_portrait;
	private int live_status;
	private int hidden_till;
	private int lock_till;
	private boolean encrypted;
	private boolean pwd_verified;
	private long live_time;
	private int room_shield;
	private int is_sp;
	private int special_type;
	private Play_url play_url;
	private List<String> all_special_types;
}