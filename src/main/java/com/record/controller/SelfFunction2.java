package com.record.controller;

/**
 * 使Function获取序列化能力
 */
public class SelfFunction2<T, R> implements SelfFunction<T, R> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2465929551163423179L;

	public SelfFunction2() {
		ColumnUtil.getName(this);
	}

	@Override
	public R apply(T t) {

		return null;
	}

}