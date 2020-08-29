package com.bilibili.entity.outer;

import com.bilibili.entity.inner.user.Nameplate;
import com.bilibili.entity.inner.user.Official;
import com.bilibili.entity.inner.user.Pendant;
import com.bilibili.entity.inner.user.Sys_notice;
import com.bilibili.entity.inner.user.Theme;
import com.bilibili.entity.inner.user.Vip;

import lombok.Data;

@Data
public class User {
	private int mid;
	private String name;
	private String sex;
	private String face;
	private String sign;
	private int rank;
	private int level;
	private long jointime;
	private int moral;
	private int silence;
	private String birthday;
	private int coins;
	private boolean fans_badge;
	private Official official;
	private Vip vip;
	private Pendant pendant;
	private Nameplate nameplate;
	private boolean is_followed;
	private String top_photo;
	private Theme theme;
	private Sys_notice sys_notice;
}