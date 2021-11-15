package com.unionman.mqtt.dao.master;

import java.util.List;

import com.unionman.mqtt.data.PublishMessageData;

public interface IMessageMapper {

	/**保存设备发布的信息*/
	void insertMessageInfo(PublishMessageData data);

	/**获取设备发布的信息*/
	List<PublishMessageData> getPublishMessageInfo(PublishMessageData data);

}
