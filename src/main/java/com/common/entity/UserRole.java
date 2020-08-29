package com.common.entity;

import com.common.base.entity.Base;

import lombok.Data;

@Data
public class UserRole extends Base {
	private Integer roleId;
	private Integer userId;
}
