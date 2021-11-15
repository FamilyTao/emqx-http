package com.unionman.mqtt.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	
	/**
	 * 获取当前日期格式为yyyy-MM-dd HH:mm:ss.SSS的字符串
	 * 
	 * @return
	 */
	public static String getDateFormatyyyyMMddHHmmss() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		return format.format(new Date());
	}
}
