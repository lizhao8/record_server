package com.common.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 反射工具类.
 */
public class ReflectionUtils {

	public static void setStaticFiled(Class<? extends Object> clazz, Map<String, Object> map) {
		for (String name : map.keySet()) {
			try {
				Field field = null;
				try {
					field = clazz.getDeclaredField(name);
				} catch (Exception e) {
					try {
						field = clazz.getField(name);
					} catch (Exception e1) {
						continue;
					}
				}
				if (field == null) {
					continue;
				}
				field.setAccessible(true);
				field.set(null, map.get(name));
			} catch (Exception e) {
				continue;
			}
		}
	}

	public static List<Field> getAnnotationField(Class clazz, Class annotation) {
		List<Field> fieldList = new ArrayList<>();
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			if (field.isAnnotationPresent(annotation)) {
				fieldList.add(field);
			}
		}
		return fieldList;
	}

}
