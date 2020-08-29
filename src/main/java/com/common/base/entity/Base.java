package com.common.base.entity;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import lombok.Data;

@Data
public class Base {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Transient
	private Date insertTime; // 插入时间
	private Integer insertId; // 插入人id
	@Transient
	private Date updateTime; // 修改时间
	private Integer updateId; // 修改人id

	@Override
	public boolean equals(Object object) {
		if (object instanceof Base) {
			Base baseEntity = (Base) object;
			if (baseEntity.id == this.id) {
				return true;
			} else {
				return false;
			}
		} else {
			return super.equals(object);
		}
	}

	@Override
	public int hashCode() {
		if (id != null) {
			return id.hashCode();
		} else {
			return super.hashCode();
		}

	}
}
