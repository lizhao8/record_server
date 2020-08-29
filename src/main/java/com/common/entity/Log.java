package com.common.entity;

import com.common.base.entity.Base;

import lombok.Data;

@Data
public class Log extends Base {

	public static short TYPE_SUCCESS = 1;
	public static short TYPE_ERROR = 0;

	private String title; // 标题
	private Short type;// 类型（1：正常日志；0：错误日志）
	private Long timer;// 耗时
	private String ip;// 操作用户的IP地址
	private String useragent;// 操作用户代理信息
	private String requestUri;// 操作的URI
	private String method; // 调用方式
	private String params;// 操作提交的数据
	private String browserName; // 浏览器名称
	private String osName; // 操作系统名称
}
