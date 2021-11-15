package com.unionman.mqtt.data;

import lombok.Data;

@Data
public class MqttCommonData {
	
	/**mqtt安装的服务器地址*/
	public String broker;
	
	public String getBroker() {
		return broker;
	}

	public void setBroker(String broker) {
		this.broker = broker;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getClientid() {
		return clientid;
	}

	public void setClientid(String clientid) {
		this.clientid = clientid;
	}

	/**用户名*/
	public String username;
	
	/**密码*/
	public String password;
	
	/**客户端id*/
	public String clientid;

}
