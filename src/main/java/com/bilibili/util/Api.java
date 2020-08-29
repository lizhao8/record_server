package com.bilibili.util;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import com.bilibili.entity.Result;
import com.bilibili.entity.inner.roomPlay.Play_url;
import com.bilibili.entity.outer.RoomInfo;
import com.bilibili.entity.outer.RoomBaseInfo;
import com.bilibili.entity.outer.RoomPlay;
import com.bilibili.entity.outer.User;
import com.bilibili.property.UrlProperty;
import com.common.util.JsonUtil;
import com.fasterxml.jackson.core.type.TypeReference;

import lombok.extern.slf4j.Slf4j;
import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;

@Slf4j
public class Api {
	/**
	 * 
	 * @param uid
	 * @return
	 */
	public static User getUserInfo(int uid) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("mid", uid);
		return get(UrlProperty.getUserInfo, map, new User());
	}

	/**
	 * 
	 * @param uid
	 * @return
	 */
	public static RoomBaseInfo getRoomBaseInfo(int uid) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("mid", uid);
		return  get(UrlProperty.getRoomInfoOld, map, new RoomBaseInfo());
	}

	/**
	 * 
	 * @param roomId
	 * @return
	 */
	public static RoomInfo getInfoByRoom(int roomId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("room_id", roomId);
		return get(UrlProperty.getInfoByRoom, map, new RoomInfo());
	}

	public static final int defaultQuality = 4;

	/**
	 * 
	 * @param roomId
	 * @return
	 */
	public static RoomPlay getRoomPlayInfo(int roomId) {
		return checkQuality(roomId, getRoomPlayInfo(roomId, defaultQuality));
	}

	public static RoomPlay getRoomPlayInfo(int roomId, int quality) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("room_id", roomId);
		map.put("play_url", 1);
		map.put("qn", defaultQuality);
		map.put("platform", "web");
		return get(UrlProperty.getRoomPlayInfo, map, new RoomPlay());
	}

	private static RoomPlay checkQuality(int roomId, RoomPlay roomPlay) {
		try {
			int quality = roomPlay.getPlay_url().getAccept_quality().get(0);
			if (quality != defaultQuality) {
				return getRoomPlayInfo(roomId, quality);
			}
		} catch (Exception e) {
			log.info("直播间[" + roomId + "]检测错误");
		}
		return roomPlay;
	}

	public static <T> T get(String url, T t) {
		return get(url, null, t);
	}

	public static <T> T get(String url, Map<String, Object> map, T t) {
		Type[] types = { t.getClass() };
		final ParameterizedTypeImpl type = ParameterizedTypeImpl.make(Result.class, types, Result.class.getDeclaringClass());
		TypeReference<Result<T>> typeReference = new TypeReference<Result<T>>() {
			@Override
			public Type getType() {
				return type;
			}
		};
		String content = ApiClient.get(url, map);
		Result<T> result = JsonUtil.readValue(content, typeReference);
		if (result.isSuccess()) {
			return result.getData();
		}
		return null;
	}
}
