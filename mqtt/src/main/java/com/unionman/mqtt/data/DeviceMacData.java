package com.unionman.mqtt.data;

import lombok.Data;

@Data
public class DeviceMacData {
	
	/**mac地址*/
	public String mac;

	/**下挂设备mac地址*/
	public String device_mac;
	
	/**在线状态*/
	public boolean active;

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getDevice_mac() {
		return device_mac;
	}

	public void setDevice_mac(String device_mac) {
		this.device_mac = device_mac;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
}
