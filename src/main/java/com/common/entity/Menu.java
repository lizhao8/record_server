package com.common.entity;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Transient;

import com.common.base.entity.NameLableEntity;

import lombok.Data;

@Data
public class Menu extends NameLableEntity {

	private Integer sequence; // 菜单序号
	private Integer parentId; // 父类id
	private String url; // 菜单url
	private String icon; // 菜单图标
	@Transient
	private boolean checked; // 是否被选中
	@Transient
	private Set<Menu> children = new LinkedHashSet<Menu>(); // 子菜单
}
