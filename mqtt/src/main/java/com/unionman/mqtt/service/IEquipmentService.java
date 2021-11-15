package com.unionman.mqtt.service;

import java.util.Map;

import net.sf.json.JSONObject;

public interface IEquipmentService {

	/**设备注册*/
	JSONObject register(Map<String, Object> params);

	/**连接验证*/
	boolean authValidation(Map<String, Object> params);

	/**访问控制*/
	void aclValidation(Map<String, Object> params);

	/**获取设备信息*/
	JSONObject getEquipmentInfo(Map<String, Object> params);

	/**获取所有mac*/
	JSONObject getAllMacInfo();

	/**获取下挂所有mac*/
	JSONObject getDeviceMacInfo(Map<String, Object> params);

}
