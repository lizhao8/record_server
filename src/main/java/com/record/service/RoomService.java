package com.record.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bilibili.entity.inner.roomPlay.Durl;
import com.bilibili.entity.inner.roomPlay.Play_url;
import com.bilibili.entity.outer.RoomBaseInfo;
import com.bilibili.entity.outer.RoomInfo;
import com.bilibili.entity.outer.RoomPlay;
import com.bilibili.util.Api;
import com.common.base.service.BaseService;
import com.record.entity.Anchor;
import com.record.entity.Live;
import com.record.entity.Record;
import com.record.entity.Room;
import com.record.mapper.LiveMapper;
import com.record.mapper.RecordMapper;
import com.record.mapper.RoomMapper;
import com.record.task.RecordTask;
import com.record.thread.RecordThread;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class RoomService extends BaseService<Room> {
	@Autowired
	RoomMapper roomMapper;
	@Autowired
	LiveMapper liveMapper;
	@Autowired
	RecordMapper recordMapper;

	public List<Room> getEnableList(short liveStatus) {
		return roomMapper.getEnableList(liveStatus);
	}

	public void unLiveCheck(Room room) {
		int roomId = room.getRoomId();
		RoomInfo roomInfo = Api.getInfoByRoom(roomId);
		short liveStatus = roomInfo.getRoom_info().getLive_status();
		if (Room.liveStatus_living == liveStatus) {
			// room.init(roomInfo);
			room.setLiveStatus(liveStatus);
			this.update(room);
			Live live = new Live(roomInfo.getRoom_info());
			live.setStatus(Room.liveStatus_living);
			Live live_query=new Live();
			live_query.setRoomId(roomId);
			live_query.setStartTime(live.getStartTime());			
			live_query=liveMapper.query(live_query);
			if(live_query!=null) {
				live.setId(live_query.getId());
				liveMapper.update(live);
			}else {
				liveMapper.save(live);
			}
			
			int uid = room.getUid();
			log.info("用户:[" + room.getAnchor().getName() + "(" + uid + ")]直播中~~~");
			if (room.getRecord() == Room.ENABLE) {
				RoomPlay roomPlay = Api.getRoomPlayInfo(roomId);
				Play_url playUrl = roomPlay.getPlay_url();
				List<Durl> durlList = playUrl.getDurl();
				int urlIndex = 1;
				if (room.getAnchor().getSpecial() == Anchor.ENABLE) {
					urlIndex = durlList.size();
				}
				for (int i = 0; i < urlIndex; i++) {
					Durl durl = durlList.get(i);
					String url = durl.getUrl();
					String threadName = RecordTask.name + "_" + uid + "_" + roomId + "_" + (i + 1);
					Record record = new Record();
					record.setLiveId(live.getId());
					record.setUid(uid);
					record.setRoomId(roomId);
					record.setUrl(url);
					record.setThreadName(threadName);
					record.setName(room.getAnchor().getName());
					new RecordThread(record).start();
				}
			}
		}
	}

	public void liveingCheck(Room room) {
		int uid = room.getUid();
		RoomBaseInfo roomBaseInfo = Api.getRoomBaseInfo(uid);
		short liveStatus = roomBaseInfo.getLiveStatus();
		if (Room.liveStatus_unlive == liveStatus) {
			room.setLiveStatus(liveStatus);
			this.update(room);
			Live live = new Live();
			live.setUid(uid);
			live.setStatus(Live.RUNNING);
			live = liveMapper.query(live);
			if (live != null) {
				live.endTime(new Date());
				live.setStatus(Live.STOP);
				liveMapper.update(live);
			}
			log.info("用户:[" + room.getAnchor().getName() + "(" + room.getUid() + ")]直播已结束~~~");

			Record record = new Record();
			record.setUid(uid);
			record.setStatus(Room.RUNNING);
			record = recordMapper.query(record);
			if (record != null) {
				RecordThread thread = RecordTask.threadGroup.getThread(record.getThreadName());
				if(thread!=null) {
					thread.stopRecord();
					log.info(record.getThreadName()+"已开始停止");
				}
			}
		}
	}

	public void restLiveRecord() {
		roomMapper.resetLiveStatus();
		liveMapper.resetStatus();
		recordMapper.resetStatus();
	}
}
