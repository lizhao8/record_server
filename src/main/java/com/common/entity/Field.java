package com.common.entity;

import java.util.ArrayList;
import java.util.List;

import com.common.base.entity.BaseEntity;
import com.common.base.entity.NoNameLableEntity;

import lombok.Data;

@Data
public class Field extends NoNameLableEntity {
	private Integer classId;

	private Short typeId;

	private Integer width;

	public List<Enum> enumList;

	static final List<Enum> statusList = new ArrayList<Enum>();
	static {
		statusList.add(new Enum() {
			{
				setNo(BaseEntity.ENABLE);
				setName("enable");
				setLabel("启用");
			}
		});
		statusList.add(new Enum() {
			{
				setNo(BaseEntity.DISABLE);
				setName("disable");
				setLabel("禁用");
			}
		});

	}

	public List<Enum> getEnumList() {
		if (enumList == null || enumList.size() == 0) {
			enumList = statusList;
		}
		return enumList;
	}
}
