package com.unionman.mqtt.util;

import java.util.Random;

import com.unionman.mqtt.common.NumberConst;

public class StringUtil {

	/**
	 * 判断是否为空
	 * 
	 * @param vlaue
	 * @return
	 */
	public static boolean checkNull(String vlaue) {
		if (vlaue == null || vlaue.length() <= 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 随机生成固定位字符串
	 * 
	 * @param length
	 * @return
	 */
	public static String getRandomString(int length) {
		String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(62);
			sb.append(str.charAt(number));
		}
		return sb.toString();
	}
	
	/**
	 * 获取错误数据
	 * @param message
	 * @return
	 */
	public static String getErrorInfo(String message) {
		if(message.length() > NumberConst.one_thousand_number){
			String info = message.substring(NumberConst.zero_number, NumberConst.one_thousand_number);
			return info;
		}else{
			return message;
		}
	}
	
	/**
	 * 字符串转化成为16进制字符串
	 * @param s
	 * @return
	 */
	public static String strTo16(String s) {
	    String str = "";
	    for (int i = 0; i < s.length(); i++) {
	        int ch = (int) s.charAt(i);
	        String s4 = Integer.toHexString(ch);
	        str = str + s4;
	    }
	    return str;
	}
}
