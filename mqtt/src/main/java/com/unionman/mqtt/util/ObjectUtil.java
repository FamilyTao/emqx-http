package com.unionman.mqtt.util;


public class ObjectUtil {

	/**
	 * 判断对象是否为空
	 * @param value
	 * @return
	 */
	public static boolean checkNull(Object value){
		if(value == null || "".equals(value)){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 获取空对象
	 * @return
	 */
	public static Object getObjectNull(){
		Object value =null;
		return value;
	}
}
