package com.record.thread;

import java.util.Date;

import com.common.util.DateFormat;
import com.common.util.DateUtil;
import com.common.util.SpringUtils;
import com.record.entity.Record;
import com.record.property.RecordProperty;
import com.record.service.LiveService;
import com.record.service.RecordService;
import com.record.task.RecordTask;
import com.record.util.RecordUtil;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class RecordThread extends Thread {
	LiveService liveService = SpringUtils.getBean(LiveService.class);

	RecordService recordService = SpringUtils.getBean(RecordService.class);

	private int uid;
	private int roomId;
	private Record record;
	private RecordUtil recordUtil;

	public RecordThread(Record record) {
		super(RecordTask.threadGroup, record.getThreadName());
		this.record = record;
		this.uid = record.getUid();
		this.roomId = record.getRoomId();
	}

	@Override
	public void run() {
		String fileName = uid + "_" + roomId + "_" + DateUtil.getDate(DateFormat.H24_File) + ".flv";
		fileName = RecordProperty.fileBasePath + uid + "/" + fileName;
		record.setStartTime(new Date());
		record.setStatus(Record.RUNNING);
		record.setPath(fileName);
		recordService.save(record);
		log.info("用户:[" + record.getName() + "(" + uid + ")]直播录制中~~~");
		recordUtil = new RecordUtil();
		recordUtil.record(record.getUrl(), fileName);
		record.endTime(new Date());
		record.setStatus(Record.STOP);
		recordService.update(record);
		log.info("用户uid:[" + record.getName() + "(" + uid + ")]直播录制结束~~~");
	}

	public void stopRecord() {
		recordUtil.stop();
	}
}
