package com.unionman.mqtt.data;

public class PublishTopicData {
	
	/**主键id*/
	public long id;
	
	/**发布的主题*/
	public String topic;
	
	/**发布主题说明*/
	public String topic_msg;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getTopic_msg() {
		return topic_msg;
	}

	public void setTopic_msg(String topic_msg) {
		this.topic_msg = topic_msg;
	}
	
}
