package com.unionman.mqtt.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unionman.mqtt.common.ConditionConst;
import com.unionman.mqtt.dao.master.IEquipmentMapper;
import com.unionman.mqtt.dao.master.IMessageMapper;
import com.unionman.mqtt.data.DeviceMacData;
import com.unionman.mqtt.data.EquipmentData;
import com.unionman.mqtt.data.PublishMessageData;
import com.unionman.mqtt.service.IMessageService;
import com.unionman.mqtt.util.DateUtil;
import com.unionman.mqtt.util.IdWorker;
import com.unionman.mqtt.util.JsonUtil;
import com.unionman.mqtt.util.MapUtil;
import com.unionman.mqtt.util.PageUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service
public class MessageServiceImpl implements IMessageService {
	
	@Autowired
	private IEquipmentMapper equipmentMapper;
	
	@Autowired
	private IMessageMapper mapper;
	
	@Override
	public void getMessageInfo(Map<String, Object> params) {
		String username = MapUtil.getString(params, "username");
		String action = MapUtil.getString(params, "action");
		if(action.equals(ConditionConst.eqmx_action_client_connected)) {
			updateEquipmentInfo(ConditionConst.connect_status_1, username);
		}else if(action.equals(ConditionConst.eqmx_action_client_disconnected)) {
			updateEquipmentInfo(ConditionConst.connect_status_0, username);
		}else if(action.equals(ConditionConst.eqmx_action_message_publish)) {
			getMessage(params);
		}
	}

	/**
	 * 获取需要的信息
	 * @param params
	 */
	private void getMessage(Map<String, Object> params) {
		String topic = MapUtil.getString(params, "topic");
		if(topic.contains("response")) {
			String payload = MapUtil.getString(params, "payload");
			if(JsonUtil.checkArrayDecode(payload)) {
				JSONArray decode = JsonUtil.getArrayDecode(payload);
				JSONObject msgInfo = decode.getJSONObject(0);
				JSONObject topicInfo = decode.getJSONObject(1);
				if(msgInfo.containsKey("code")) {
					String request_id = topicInfo.getString("request_id");
					int status = 0;
					if(topicInfo.containsKey("timewait")) {
						boolean timewait = topicInfo.getBoolean("timewait");
						if(timewait) {
							status = 1;
						}else {
							int size = mapper.checkTimewaitMessageInfoExist(request_id);
							if(size > 0) {
								mapper.deleteTimewaitMessageInfo(request_id);
							}
						}
					}
					String sign_topic = topicInfo.getString("type");
					if(sign_topic.equals(ConditionConst.topic_get_device_brief_info)) {
						saveDeviceInfo(params, msgInfo);
					}
					PublishMessageData data = getPublishMessageData(params, sign_topic, msgInfo, request_id, status);
					mapper.insertMessageInfo(data);
				}
			}
		}
	}

	/**
	 * 保存当前设备下辖mac信息
	 * @param params
	 * @param msgInfo
	 */
	private void saveDeviceInfo(Map<String, Object> params, JSONObject msgInfo) {
		String mac = MapUtil.getString(params, "from_username");
		if(msgInfo.containsKey("list")) {
			equipmentMapper.deleteOldDeviceInfo(mac);
			JSONArray deviceInfos = msgInfo.getJSONArray("list");
			if(!JsonUtil.checkJSONArrayNull(deviceInfos)) {
				for(int i = 0; i < deviceInfos.size(); i++) {
					JSONObject deviceInfo = deviceInfos.getJSONObject(i);
					DeviceMacData device = new DeviceMacData();
					device.setMac(mac);
					device.setActive(deviceInfo.getBoolean("active"));
					device.setDevice_mac(deviceInfo.getString("mac"));
					equipmentMapper.insertDeviceInfo(device);
				}
			}
		}
	}

	private PublishMessageData getPublishMessageData(Map<String, Object> params, String sign_topic, JSONObject msgInfo, String request_id, int status) {
		IdWorker id= new IdWorker();
		PublishMessageData data = new PublishMessageData();
		data.setClient_id(MapUtil.getString(params, "from_client_id"));
		data.setId(id.nextId());
		data.setPayload(msgInfo.toString());
		data.setQos(MapUtil.getInt(params, "qos"));
		data.setTopic(sign_topic);
		data.setTimewait_status(status);
		data.setRequest_id(request_id);
		data.setUsername(MapUtil.getString(params, "from_username"));
		data.setReceive_time(DateUtil.getDateFormatyyyyMMddHHmmss());
		return data;
	}

	/**
	 * 更新设备信息
	 * @param connect_status
	 * @param username
	 */
	private void updateEquipmentInfo(int connect_status, String username) {
		EquipmentData data = new EquipmentData();
		data.setMac(username);
		data.setConnect_status(connect_status);
		int size = equipmentMapper.checkEquipmentExist(data);
		if(size > 0) {
			equipmentMapper.updateEquipmentInfo(data);
		}
	}

	@Override
	public JSONObject getPublishMessageInfo(Map<String, Object> params) {
		String client_id = MapUtil.getString(params, "client_id");
		String username = MapUtil.getString(params, "username");
		String topic = MapUtil.getString(params, "topic");
		PublishMessageData data = new PublishMessageData();
		data.setClient_id(client_id);
		data.setUsername(username);
		data.setTopic(topic);
		List<PublishMessageData> datas = mapper.getPublishMessageInfo(data);
		PageUtil<PublishMessageData> page = new PageUtil<PublishMessageData>();
		page.setCurrentPages(MapUtil.getInt(params, "page"));
		page.setSize(MapUtil.getInt(params, "size"));
		page.setDataList(datas);
		JSONObject json = JsonUtil.successInfo();
		json.put("data", page);
		return json;
	}

}
