package com.unionman.mqtt.dao.master;

import java.util.List;

import com.unionman.mqtt.data.MqttCommonData;
import com.unionman.mqtt.data.PublishTopicData;

public interface IPublishMapper {

	/**获取发布的主题*/
	List<PublishTopicData> getPublishTopicInfo();

	/**获取mqtt连接的通用配置*/
	MqttCommonData getMqttCommonInfo();

}
