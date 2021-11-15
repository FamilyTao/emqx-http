package com.unionman.mqtt.util;

import java.util.Map;

import com.unionman.mqtt.common.ErrorCode;
import com.unionman.mqtt.exception.UnionmanException;

public class MapUtil {
	
	/**
	 * 获取不为null的 字符串值
	 * @param key
	 * @return
	 */
	public static String getString(Map<String, Object> map, String key) {
		String value = "";
		if (null == map) {
			return value;
		}
		try {
			value = map.get(key) == null ? "" : map.get(key).toString();
		} catch (Exception e) {
			throw new UnionmanException(ErrorCode.map_error.getCode(), ErrorCode.map_error.getMessage());
		}
		return value;
	}
	
	/**
	 * 判断map是否为空
	 * 
	 * @param map
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static boolean checkNull(Map map) {
		if (map != null && !map.isEmpty()) {
			return false;
		} else {
			return true;
		}
	}
	
	/**
	 * 获取int
	 * 
	 * @param map
	 * @param key
	 * @return
	 */
	public static int getInt(Map<String, Object> map, String key) {
		String value = "";
		int result = 0;
		try {
			if (null == map) {
				return 0;
			}
			value = map.get(key) == null ? "" : map.get(key).toString();
			if(value.equals("")) {
				result = 0;
			}else {
				result = Integer.parseInt(value);	
			}
		} catch (Exception e) {
			throw new UnionmanException(ErrorCode.map_error.getCode(), ErrorCode.map_error.getMessage());
		}
		return result;
	}
}
