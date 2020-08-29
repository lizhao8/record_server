package com.record.task;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.record.entity.Room;
import com.record.service.RoomService;
import com.record.thread.RecordThread;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@EnableScheduling
public class RecordTask {

	public static final String name = "record";

	public static final ThreadGroup<RecordThread> threadGroup = new ThreadGroup<RecordThread>(name);

	@Autowired
	RoomService roomService;

	int count = 0;

	// @Scheduled(cron = "0 */1 * * * ?")
	@Scheduled(fixedDelay = 1000)
	public void unLiveCheckTask() {
		List<Room> roomList = roomService.getEnableList(Room.liveStatus_unlive);

		for (Room room : roomList) {
			try {
				roomService.unLiveCheck(room);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Scheduled(fixedDelay = 10000)
	public void liveingCheckTask() {
		List<Room> roomList = roomService.getEnableList(Room.liveStatus_living);

		for (Room room : roomList) {
			try {
				roomService.liveingCheck(room);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@PostConstruct
	public void restLiveRecord() {
		roomService.restLiveRecord();
	}
}
