package com.unionman.mqtt.data;

import lombok.Data;

@Data
public class EquipmentData {
	
	/**mac地址*/
	public String mac;
	
	/**连接状态 0:未连接 1:已连接*/
	public int connect_status;

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public int getConnect_status() {
		return connect_status;
	}

	public void setConnect_status(int connect_status) {
		this.connect_status = connect_status;
	}

}
