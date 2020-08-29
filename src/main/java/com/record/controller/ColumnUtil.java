package com.record.controller;

import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ColumnUtil {

	public static <T> String getName(SelfFunction<T, ?> fn) {
		// 从function取出序列化方法
		Method writeReplaceMethod;
		try {
			writeReplaceMethod = fn.getClass().getDeclaredMethod("writeReplace");
		} catch (NoSuchMethodException e) {
			throw new RuntimeException(e);
		}

		// 从序列化方法取出序列化的lambda信息
		boolean isAccessible = writeReplaceMethod.isAccessible();
		writeReplaceMethod.setAccessible(true);
		SerializedLambda serializedLambda;
		try {
			serializedLambda = (SerializedLambda) writeReplaceMethod.invoke(fn);
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new RuntimeException(e);
		}
		writeReplaceMethod.setAccessible(isAccessible);

		// 从lambda信息取出method、field、class等
		String fieldName = serializedLambda.getImplMethodName().substring("get".length());
		fieldName = fieldName.replaceFirst(fieldName.charAt(0) + "", (fieldName.charAt(0) + "").toLowerCase());
		Field field;
		try {
			field = Class.forName(serializedLambda.getImplClass().replace("/", ".")).getDeclaredField(fieldName);
		} catch (ClassNotFoundException | NoSuchFieldException e) {
			throw new RuntimeException(e);
		}
		return fieldName;
	}
}