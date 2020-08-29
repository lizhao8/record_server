package com.record.entity;

import java.util.Date;

import javax.persistence.Transient;

import org.apache.ibatis.type.JdbcType;

import com.common.base.entity.BaseEntity;
import com.common.base.entity.Duration;
import com.common.base.handler.DurationTypeHandler;

import lombok.Data;
import tk.mybatis.mapper.annotation.ColumnType;

@Data
public class Record extends BaseEntity {
	private Integer liveId; // 直播id
	private Integer uid; // uid
	private Integer roomId; // 直播间号
	private Date startTime; // 录播开始时间
	private Date endTime; // 录播结束时间
	private String url; // 源地址
	private String path;// 文件路径
	private String threadName; // 线程名称
	@Transient
	private String name;// 用户名
	@ColumnType(column = "duration", jdbcType = JdbcType.TIME, typeHandler = DurationTypeHandler.class)
	private Duration duration; // 时长

	public Record() {

	}

	public void endTime(Date endTime) {
		this.endTime = endTime;
		this.duration = Duration.ofMillis(endTime.getTime() - startTime.getTime());
	}
}