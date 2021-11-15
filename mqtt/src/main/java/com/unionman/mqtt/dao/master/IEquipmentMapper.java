package com.unionman.mqtt.dao.master;

import java.util.List;
import java.util.Map;

import com.unionman.mqtt.data.DeviceMacData;
import com.unionman.mqtt.data.EquipmentData;

public interface IEquipmentMapper {

	/**判断设备信息是否存在*/
	int checkEquipmentExist(EquipmentData data);
	
	/**保存设备信息*/
	void insertEquipmentInfo(EquipmentData data);

	/**获取设备信息*/
	List<EquipmentData> getEquipmentInfo(EquipmentData data);

	/**更新设备信息*/
	void updateEquipmentInfo(EquipmentData data);

	/**保存设备发布信息*/
	void insertMessageInfo(Map<String, Object> params);

	/**获取所有mac*/
	List<EquipmentData> getAllMacInfo();

	/**获取下挂所有mac*/
	List<DeviceMacData> getDeviceMacInfo(Map<String, Object> params);
	
	/**删除旧的下挂信息*/
	void deleteOldDeviceInfo(String mac);

	/**保存下挂信息*/
	void insertDeviceInfo(DeviceMacData device);

}
