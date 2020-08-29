package com.record.controller;

import java.io.Serializable;
import java.util.function.Function;

/**
 * 使Function获取序列化能力
 */
@FunctionalInterface
public interface SelfFunction<T, R> extends Function<T, R>, Serializable {
	
}