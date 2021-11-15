package com.unionman.mqtt.data;

import lombok.Data;

@Data
public class MqttPropertiesData {
	
	/**消息可能性:0:最多一次 1:最少一次 2:只有一次*/
	private int qos;
	
	/**tcp://MQTT安装的服务器地址:MQTT定义的端口号*/
	private String broker;
	
	/**定义MQTT的ID*/
	private String clientId;
	
	/**定义一个主题*/
	private String topic;
	
	public int getQos() {
		return qos;
	}

	public void setQos(int qos) {
		this.qos = qos;
	}

	public String getBroker() {
		return broker;
	}

	public void setBroker(String broker) {
		this.broker = broker;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public boolean isCleanSession() {
		return cleanSession;
	}

	public void setCleanSession(boolean cleanSession) {
		this.cleanSession = cleanSession;
	}

	public int getConnectionTimeout() {
		return connectionTimeout;
	}

	public void setConnectionTimeout(int connectionTimeout) {
		this.connectionTimeout = connectionTimeout;
	}

	public int getKeepAliveInterval() {
		return keepAliveInterval;
	}

	public void setKeepAliveInterval(int keepAliveInterval) {
		this.keepAliveInterval = keepAliveInterval;
	}

	/**发送数据*/
	private String msg;
	
	/**用户名*/
	private String userName;
	
	/**密码*/
	private String passWord;
	
	/**是否清空session*/
	private boolean cleanSession;
	
	/**设置超时时间*/
	private int connectionTimeout;
	
	/**设置会话心跳时间*/
	private int keepAliveInterval;
}
