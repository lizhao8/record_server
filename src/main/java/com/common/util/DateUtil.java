package com.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * @author NANOHA
 *
 */
public class DateUtil {

	public static String getDate(DateFormat dateFormat) {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat.toString());
		return sdf.format(date);
	}
}
