package com.common.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.common.base.exception.RunException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class JsonUtil {
	public static ObjectMapper objectMapper;

	@Autowired
	public void setObjectMapper(ObjectMapper objectMapper) {
		JsonUtil.objectMapper = objectMapper;
	}

	public static String writeValueAsString(Object object) {
		try {
			return objectMapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RunException(e);
		}
	}

	public static <T> T readValue(String content, Class<T> valueType) {
		try {
			return objectMapper.readValue(content, valueType);
		} catch (JsonProcessingException e) {
			throw new RunException(e);
		}
	}

	public static <T> T readValue(String content, JavaType valueType) {
		try {
			return objectMapper.readValue(content, valueType);
		} catch (JsonProcessingException e) {
			throw new RunException(e);
		}
	}

	public static <T> T readValue(String content, TypeReference<T> valueTypeRef) {
		try {
			return objectMapper.readValue(content, valueTypeRef);
		} catch (JsonProcessingException e) {
			throw new RunException(e);
		}
	}

	public static <T> T convertValue(Object fromValue, Class<T> toValueType) {
		return objectMapper.convertValue(fromValue, toValueType);
	}

	public static <T> T convertValue(Object fromValue, TypeReference<T> toValueTypeRef) {
		return objectMapper.convertValue(fromValue, toValueTypeRef);
	}

	public static <T> T convertValue(Object fromValue, JavaType toValueType) {
		return objectMapper.convertValue(fromValue, toValueType);
	}

}
