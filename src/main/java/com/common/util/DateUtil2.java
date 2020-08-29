package com.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 
 * @author NANOHA
 *
 */
public class DateUtil2 {

	public static final String formatYear = "yyyy";
	public static final String formatMonth = "yyyyMM";
	public static final String formatDay = "yyyyMMdd";
	public static final String format = "yyMMddHHmmss";

	public static final String format_Md = "MM-dd";

	public static final String formatDate = "yyyy-MM-dd";
	public static final String formatDatePoint = "yyyy.MM.dd";
	public static final String formatDateMonth = "yyyy.MM";
	public static final String formatDate_12H = "yyyy-MM-dd hh";
	public static final String formatDate_24H = "yyyy-MM-dd HH";
	public static final String formatDate_12HM = "yyyy-MM-dd hh:mm";
	public static final String formatDate_24HM = "yyyy-MM-dd HH:mm";
	public static final String formatDate_12HMS = "yyyy-MM-dd hh:mm:ss";
	public static final String formatDate_24HMS = "yyyy-MM-dd HH:mm:ss";
	public static final String formatDate_24HMS_N = "yyyy-MM-dd HHmmss";
	public static final String formatDate_24HMsS = "yyyyMMdd_HHmmssSSS";

	/**
	 * 判断日期是否为空
	 * 
	 * @date 2015年3月10日
	 * @param date Date
	 * @return boolean
	 */
	public static boolean isNull(Date date) {
		if (null != date) {
			return true;
		}
		return false;
	}

	/**
	 * 获取当前特定格式的日期时间字符串
	 * 
	 * @date 2015年3月10日
	 * @param format String 特定格式
	 * @return String
	 */
	public static String getCurrentDate(String format) {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}

	/**
	 * 获取当前日期时间
	 * 
	 * @date 2015年3月10日
	 * @return Date
	 */
	public static Date getCurrentDate() {
		Date date = new Date();
		return date;
	}

	/**
	 * 获取当前日期时间
	 * 
	 * @date 2015年3月10日
	 * @return Date
	 */
	public static String getCurrentDateString() {
		SimpleDateFormat format = new SimpleDateFormat(formatDate_24HMS);
		return format.format(new Date());
	}

	/**
	 * 将日期字符串转化特定格式的日期
	 * 
	 * @verson v1.0
	 * @date 2015年3月10日
	 * @param dateStr String 日期格式字符串
	 * @param format  String 转化格式
	 * @return Date
	 */
	public static Date stringToDate(String dateStr, String format) {
		Date date = null;
		if (null != dateStr && !"".equals(dateStr)) {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			try {
				date = sdf.parse(dateStr);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return date;
	}

	/**
	 * 把字符串转换为时间类型 格式为yyyy-MM-dd hh:mm:ss
	 * 
	 * @param s
	 * @return
	 */
	public static Date changetypedate(String s) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date t;
		try {
			t = formatter.parse(s);
			return t;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 把字符串转换为时间类型 填写格式为yyyy-MM-dd
	 * 
	 * @param str
	 * @return date
	 */
	public static Date changeStringToDate(String str) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date;
		try {
			date = formatter.parse(str);
			return date;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	/**
	 * 将日期转化特定格式的日期字符串
	 * 
	 * @verson v1.0
	 * @date 2015年3月10日
	 * @param date   Date 日期
	 * @param format String 转化格式
	 * @return String
	 */
	public static String dateToString(Date date, String format) {
		String dateStr = "";
		if (isNull(date)) {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			dateStr = sdf.format(date);
		}
		return dateStr;
	}

	/**
	 * 比较两个日期的大小
	 * 
	 * @date 2015年3月10日
	 * @param beginDate Date 开始日期
	 * @param endDate   Date 结束日期
	 * @return int -1 beginDate>endDate 1 beginDate=endDate 1 beginDate<endDate
	 */
	public static int compareDate(Date beginDate, Date endDate) {

		if (isNull(beginDate) || isNull(endDate)) {
			long beginTime = beginDate.getTime();
			long endTime = endDate.getTime();

			if (beginTime > endTime) {
				return -1;
			} else if (beginTime < endTime) {
				return 1;
			}
		}
		return 0;
	}

	public static Date getDate(Date date, int number, int _calendar) {
		Calendar calendar = Calendar.getInstance(); // 得到日历
		calendar.setTime(date);// 把当前时间赋给日历
		calendar.add(_calendar, number);
		return calendar.getTime();
	}
}
