package com.common.entity;

import javax.persistence.Transient;

import com.common.base.entity.NameLableEntity;

import lombok.Data;

@Data
public class Role extends NameLableEntity {

	@Transient
	private boolean checked;
}
