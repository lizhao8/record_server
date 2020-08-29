package com.bilibili.entity.outer;

import com.bilibili.entity.inner.RoomInfo.Activity_init_info;
import com.bilibili.entity.inner.RoomInfo.Ad_banner_info;
import com.bilibili.entity.inner.RoomInfo.Anchor_info;
import com.bilibili.entity.inner.RoomInfo.Area_rank_info;
import com.bilibili.entity.inner.RoomInfo.Battle_rank_entry_info;
import com.bilibili.entity.inner.RoomInfo.Gift_memory_info;
import com.bilibili.entity.inner.RoomInfo.Lol_info;
import com.bilibili.entity.inner.RoomInfo.New_switch_info;
import com.bilibili.entity.inner.RoomInfo.News_info;
import com.bilibili.entity.inner.RoomInfo.Player_throttle_info;
import com.bilibili.entity.inner.RoomInfo.Rankdb_info;
import com.bilibili.entity.inner.RoomInfo.Record_switch_info;
import com.bilibili.entity.inner.RoomInfo.Room_config_info;
import com.bilibili.entity.inner.RoomInfo.Room_info;
import com.bilibili.entity.inner.RoomInfo.Silent_room_info;
import com.bilibili.entity.inner.RoomInfo.Skin_info;
import com.bilibili.entity.inner.RoomInfo.Super_chat_info;
import com.bilibili.entity.inner.RoomInfo.Switch_info;
import com.bilibili.entity.inner.RoomInfo.Tab_info;
import com.bilibili.entity.inner.RoomInfo.Voice_join_info;
import com.bilibili.entity.inner.RoomInfo.Web_banner_info;
import com.bilibili.entity.inner.RoomInfo.Wish_list_info;

import lombok.Data;

@Data
public class RoomInfo {

	private Room_info room_info;
	private Anchor_info anchor_info;
	private News_info news_info;
	private Rankdb_info rankdb_info;
	private Area_rank_info area_rank_info;
	private Battle_rank_entry_info battle_rank_entry_info;
	private Tab_info tab_info;
	private Activity_init_info activity_init_info;
	private Voice_join_info voice_join_info;
	private Ad_banner_info ad_banner_info;
	private Skin_info skin_info;
	private Web_banner_info web_banner_info;
	private Lol_info lol_info;
	private Wish_list_info wish_list_info;
	private String score_card_info;
	private String pk_info;
	private String battle_info;
	private Silent_room_info silent_room_info;
	private Switch_info switch_info;
	private Record_switch_info record_switch_info;
	private Room_config_info room_config_info;
	private Gift_memory_info gift_memory_info;
	//private New_switch_info new_switch_info;
	private Super_chat_info super_chat_info;
	private String video_connection_info;
	private Player_throttle_info player_throttle_info;
}