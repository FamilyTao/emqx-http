package com.unionman.mqtt.service;

import java.util.Map;

import net.sf.json.JSONObject;

public interface IPublishService {

	/**获取发布主题*/
	JSONObject getPublishTopicInfo();

	/**发布主题信息*/
	JSONObject publishTopicInfo(Map<String, Object> params);

}
