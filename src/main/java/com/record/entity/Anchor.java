package com.record.entity;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.bilibili.entity.outer.User;
import com.common.base.entity.BaseEntity;

import lombok.Data;

@Data
public class Anchor extends BaseEntity {
	@NotNull
	@Min(1)
	private Integer uid; // uid(B站)
	private String name; // 用户名
	private String face;// 头像
	private String sign;// 签名
	private Short sex; // 性别
	private String birthday;// 生日
	private Integer roomId;// 直播间号
	private Short follow; // 关注
	private Short special; // 特别关注

	public static Anchor _init(User user) {
		return new Anchor().init(user);
	}

	public Anchor init(User user) {
		this.uid = user.getMid();
		this.name = user.getName();
		this.face = user.getFace();
		this.sign = user.getSign();
		this.birthday = user.getBirthday();
		// 1011anchor.setSex(Enum.get(anchor::getSex, user.getSex()));
		return this;

	}
}