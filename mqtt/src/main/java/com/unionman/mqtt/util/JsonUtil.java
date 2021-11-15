package com.unionman.mqtt.util;

import com.unionman.mqtt.common.ConditionConst;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JsonUtil {
	
	/**
	 * 判断该string是否json
	 * 
	 * @param decode
	 */
	public static boolean checkDecode(String decode) {
		try {
			JSONObject.fromObject(decode);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 将string转成json
	 * 
	 * @param decode
	 */
	public static JSONObject getDecode(String decode) {
		JSONObject json = null;
		try {
			json = JSONObject.fromObject(decode);
			return json;
		} catch (Exception e) {
			return json;
		}
	}
	
	/**
	 * 判断该string是否json
	 * 
	 * @param decode
	 */
	public static boolean checkArrayDecode(String decode) {
		try {
			JSONArray.fromObject(decode);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * 将string转成JSONArray
	 * 
	 * @param decode
	 */
	public static JSONArray getArrayDecode(String decode) {
		JSONArray array = null;
		try {
			array = JSONArray.fromObject(decode);
			return array;
		} catch (Exception e) {
			return array;
		}
	}
	
	/**
	 * 判断JSONObject是否为空
	 * @param info
	 * @return
	 */
	public static boolean checkJSONObjectNull(JSONObject info) {
		if (info == null || info.isEmpty() || info.isNullObject() || "null".equals(info.toString())) {
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 判断JSONArray是否为空
	 * @param info
	 * @return
	 */
	public static boolean checkJSONArrayNull(JSONArray info) {
		if (info.isEmpty() || info.size() < 1) {
			return true;
		}else{
			if(info.size() == 1){
				Object object = info.get(0);
				if(object instanceof Integer){
					return false;
				}else if(object instanceof String){
					return false;
				}else{
					JSONObject jsonObject = JSONObject.fromObject(object);
					if(checkJSONObjectNull(jsonObject)){
						return true;
					}else{
						return false;
					}
				}
			}else{
				return false;
			}
		}
	}
	
	/**
	 * 返回成功信息
	 * 
	 * @return
	 */
	public static JSONObject successInfo() {
		JSONObject json = new JSONObject();
		json.put("resultMsg", ConditionConst.success_message);
		json.put("resultCode", ConditionConst.success_code);
		return json;
	}
	
	/**
	 * 返回失败信息
	 * 
	 * @param message
	 * @return
	 */
	public static JSONObject errInfo(String code, String message) {
		JSONObject json = new JSONObject();
		json.put("resultMsg", StringUtil.getErrorInfo(message));
		json.put("resultCode", code);
		return json;
	}

}
