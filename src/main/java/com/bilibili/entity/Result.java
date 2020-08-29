package com.bilibili.entity;

import lombok.Data;

@Data
public class Result<T> {
	public static final int SUCCESS = 0;
	private int code;
	private String message;
	private int ttl;
	private T data;

	public boolean isSuccess() {
		return code == SUCCESS;
	}
}