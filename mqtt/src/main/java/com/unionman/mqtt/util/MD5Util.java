package com.unionman.mqtt.util;

import java.security.MessageDigest;

public class MD5Util {

	/**
	 * md5 32位加密
	 * 
	 * @param info
	 * @return
	 */
	public static String encrypt32(String info) {
		MessageDigest md5;
		try {
			md5 = MessageDigest.getInstance("MD5");
			byte[] md5Bytes = md5.digest(info.getBytes());
			StringBuffer hexValue = new StringBuffer();
			for (int i = 0; i < md5Bytes.length; i++) {
				int val = ((int) md5Bytes[i]) & 0xff;
				if (val < 16)
					hexValue.append("0");
				hexValue.append(Integer.toHexString(val));
			}
			info = hexValue.toString();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return info;
	}

	/**
	 * md5 16位加密
	 * 
	 * @param info
	 * @return
	 */
	public static String encrypt16(String info) {
		return encrypt32(info).substring(8, 24);
	}

}
