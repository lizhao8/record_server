package com.common.util;

public enum DateFormat {
	H24("yyyy-MM-dd HH:mm:ss"),
	H24_File("yyyy-MM-dd_HHmmss");

	DateFormat(String value) {
		this.value = value;
	}

	private String value = "";

	@Override
	public String toString() {
		return value;
	}
}
