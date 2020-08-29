package com.record.controller;

import com.record.entity.Anchor;

public class IPerson {
	public static void main(String[] args) {
		System.out.println(ColumnUtil.getName(Anchor::getSex));
	}
}