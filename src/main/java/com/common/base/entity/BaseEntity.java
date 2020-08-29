package com.common.base.entity;

import lombok.Data;

@Data
public class BaseEntity extends Base {
	public static final Short ENABLE = 1;
	public static final Short DISABLE = 0;

	public static final Short RUNNING = ENABLE;
	public static final Short STOP = DISABLE;

	public static final String STATUS_COLUMN = "status";
	public static final String SELECT_ENABLE = STATUS_COLUMN + "=" + ENABLE;

	private Short status; // 状态:基本为1-启用；0-停用
	private String description;// 描述
}
