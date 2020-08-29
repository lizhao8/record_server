package com.record.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bilibili.entity.outer.RoomBaseInfo;
import com.bilibili.entity.outer.RoomInfo;
import com.bilibili.entity.outer.User;
import com.bilibili.util.Api;
import com.common.base.entity.Result;
import com.common.base.service.BaseService;
import com.record.entity.Anchor;
import com.record.entity.Room;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AnchorService extends BaseService<Anchor> {

	@Autowired
	RoomService roomService;

	public Result<Anchor> saveFromUid(Integer uid) {
		User user = Api.getUserInfo(uid);
		if (user == null) {
			return Result.FAIL("uid不存在");
		}
		Anchor anchor = Anchor._init(user);
		RoomBaseInfo roomBaseInfo = Api.getRoomBaseInfo(uid);
		if (roomBaseInfo != null) {
			int roomId = roomBaseInfo.getRoomid();
			if (roomId > 0) {
				RoomInfo roomInfo = Api.getInfoByRoom(roomId);
				if (roomInfo != null) {
					Room room = Room._init(roomInfo);
					room.setStatus(Room.ENABLE);
					roomService.save(room);
				}
				anchor.setRoomId(roomId);
			}
		}

		anchor.setStatus(Anchor.ENABLE);
		super.save(anchor);
		return Result.SUCCESS();
	}
}
