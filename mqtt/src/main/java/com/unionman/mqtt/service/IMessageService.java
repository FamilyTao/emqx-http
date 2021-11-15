package com.unionman.mqtt.service;

import java.util.Map;

import net.sf.json.JSONObject;

public interface IMessageService {

	/**获取需要的mqtt信息*/
	void getMessageInfo(Map<String, Object> params);

	/**获取设备发布的信息*/
	JSONObject getPublishMessageInfo(Map<String, Object> params);

}
