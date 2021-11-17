package com.unionman.mqtt.data;

public class PublishMessageData {
	
	/**主键id*/
	public long id;
	
	/**客户端id*/
	public String client_id;
	
	/**用户名*/
	public String username;
	
	/**命令名称*/
	public String topic_msg;
	
	/**命令标识*/
	public String topic;
	
	/**消息可能性:0:最多一次 1:最少一次 2:只有一次*/
	public int qos;
	
	/**接收发布信息时间*/
	public String receive_time;
	
	/**内容*/
	public String payload;
	
	/**请求编号*/
	public String request_id;
	
	/**是否延迟处理*/
	public int timewait_status;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getClient_id() {
		return client_id;
	}

	public void setClient_id(String client_id) {
		this.client_id = client_id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getTopic_msg() {
		return topic_msg;
	}

	public void setTopic_msg(String topic_msg) {
		this.topic_msg = topic_msg;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public int getQos() {
		return qos;
	}

	public void setQos(int qos) {
		this.qos = qos;
	}

	public String getReceive_time() {
		return receive_time;
	}

	public void setReceive_time(String receive_time) {
		this.receive_time = receive_time;
	}

	public String getPayload() {
		return payload;
	}

	public void setPayload(String payload) {
		this.payload = payload;
	}

	public String getRequest_id() {
		return request_id;
	}

	public void setRequest_id(String request_id) {
		this.request_id = request_id;
	}

	public int getTimewait_status() {
		return timewait_status;
	}

	public void setTimewait_status(int timewait_status) {
		this.timewait_status = timewait_status;
	}
}
