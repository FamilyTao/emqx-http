package com.unionman.mqtt.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unionman.mqtt.common.ConditionConst;
import com.unionman.mqtt.common.ErrorCode;
import com.unionman.mqtt.dao.master.IPublishMapper;
import com.unionman.mqtt.data.MqttCommonData;
import com.unionman.mqtt.data.MqttPropertiesData;
import com.unionman.mqtt.data.PublishTopicData;
import com.unionman.mqtt.exception.UnionmanException;
import com.unionman.mqtt.service.IPublishService;
import com.unionman.mqtt.util.IdWorker;
import com.unionman.mqtt.util.JsonUtil;
import com.unionman.mqtt.util.MapUtil;
import com.unionman.mqtt.util.MqttPubUtil;
import com.unionman.mqtt.util.StringUtil;

import net.sf.json.JSONObject;

@Service
public class PublishServiceImpl implements IPublishService {
	
	private final static Logger logger = LoggerFactory.getLogger(PublishServiceImpl.class);
	
	@Autowired
	private IPublishMapper mapper;

	@Override
	public JSONObject getPublishTopicInfo() {
		List<PublishTopicData> datas = mapper.getPublishTopicInfo();
		JSONObject json = JsonUtil.successInfo();
		json.put("data", datas);
		return json;
	}

	@Override
	public JSONObject publishTopicInfo(Map<String, Object> params) {
		MqttCommonData commonData = mapper.getMqttCommonInfo();
		String macs = MapUtil.getString(params, "macs");
		if(!StringUtil.checkNull(macs)) {
			String[] split = macs.split(",");
			try {
				for(int i = 0; i < split.length; i++) {
					String mac = split[i].trim();
					sendMqttMessage(params, commonData, mac);
				}
				JSONObject json = JsonUtil.successInfo();
				return json;
			}catch (Exception e) {
				throw new UnionmanException(ErrorCode.publish_fail.getCode(), ErrorCode.publish_fail.getMessage());
			}
		}else {
			throw new UnionmanException(ErrorCode.macs_null.getCode(), ErrorCode.macs_null.getMessage());
		}
	}

	/**
	 * 发布信息
	 * @param params
	 * @param commonData
	 * @param mac
	 */
	private void sendMqttMessage(Map<String, Object> params, MqttCommonData commonData, String mac) {
		try {
			MqttPropertiesData data = getMqttPropertiesInfo(params, commonData, mac);
			MqttPubUtil pub = new MqttPubUtil();
			pub.publish(data);
		}catch (Exception e) {
			logger.error("发送命令失败:" + e.getMessage());
		}
	}

	/**
	 * 组装发布信息
	 * @param params
	 * @param commonData
	 * @param mac
	 * @return
	 */
	private MqttPropertiesData getMqttPropertiesInfo(Map<String, Object> params, MqttCommonData commonData, String mac) {
		MqttPropertiesData data = new MqttPropertiesData();
		data.setBroker(commonData.getBroker());
		data.setCleanSession(false);
		data.setClientId(commonData.getClientid());
		data.setConnectionTimeout(10);
		data.setKeepAliveInterval(20);
		String sign_topic = MapUtil.getString(params, "sign_topic"); //命令
		int network = MapUtil.getInt(params, "network");	//重启网络
		int connect = MapUtil.getInt(params, "connect"); //网络检测
		System.out.println("######connect: "+connect);
		int active_status = MapUtil.getInt(params, "active_status"); //在线状态
		int delay_status = MapUtil.getInt(params, "delay_status");
		int period = MapUtil.getInt(params, "period");
		int period_count = MapUtil.getInt(params, "period_count");
		data.setPassWord(commonData.getPassword());
		int qos = MapUtil.getInt(params, "qos");
		data.setQos(qos);
		String publishTopicInfo = ConditionConst.publish_topic_info;
		if(sign_topic.equals(ConditionConst.topic_get_device_detailed_info)) {
			String[] split = mac.split("/");
			String topic = publishTopicInfo.replaceAll("mac", split[0]);
			String device_mac = split[1];
			data.setMsg(getMsg(sign_topic, network, active_status, device_mac,connect, delay_status, period, period_count));
			data.setTopic(topic);
		}else {
			String topic = publishTopicInfo.replaceAll("mac", mac);
			data.setMsg(getMsg(sign_topic, network, active_status, null,connect, delay_status, period, period_count));
			data.setTopic(topic);
		}
		data.setUserName(commonData.getUsername());
		return data;
	}

	/**
	 * 组装发布内容
	 * @param topic
	 * @param network
	 * @param active_status 
	 * @param device_mac 
	 * @param connect
	 * @param period_count 
	 * @param period 
	 * @param delay_status 
	 * @return
	 */
	private String getMsg(String topic, int network, int active_status, String device_mac,int connect, int delay_status, int period, int period_count) {
		JSONObject json = new JSONObject();
		json.put("request", topic);
		if(topic.equals(ConditionConst.topic_network_restart)) { //判断是否是网络重启,设置参数
			JSONObject parameter = getCommonInfo(delay_status, period, period_count);
			parameter.put("request_id", network);
			json.put("parameter", parameter);
		}else if(topic.equals(ConditionConst.topic_get_device_brief_info)) { //是获取网络简略信息,设置参数
			JSONObject parameter = getCommonInfo(delay_status, period, period_count);
			parameter.put("active", active_status);
			json.put("parameter", parameter);
		}else if(topic.equals(ConditionConst.topic_get_device_detailed_info)) { //获取网络详细信息,设置参数
			JSONObject parameter = getCommonInfo(delay_status, period, period_count);
			String[] split = device_mac.split("#");
			parameter.put("mac", split);
			json.put("parameter", parameter);
		}else if(topic.equals(ConditionConst.topic_get_diag_detect_connect)) { //检测网络连通性,设置参数
			JSONObject parameter = getCommonInfo(delay_status, period, period_count);
			parameter.put("request_id", connect);
			json.put("parameter", parameter);
		}else {
			JSONObject parameter = getCommonInfo(delay_status, period, period_count);
			json.put("parameter", parameter);
		}
		return json.toString();
	}

	private JSONObject getCommonInfo(int delay_status, int period, int period_count) {
		JSONObject info = new JSONObject();
		if(delay_status == 0) {
			info.put("timewait", false);
		}else {
			info.put("timewait", true);
		}
		if(period != 0) {
			info.put("periodicity", getPeriodicity(period, period_count));
		}
		IdWorker id = new IdWorker();
		info.put("request_id", id.nextId());
		return info;
	}

	private JSONObject getPeriodicity(int period, int period_count) {
		JSONObject info = new JSONObject();
		info.put("period", period);
		info.put("count", period_count);
		return info;
	}

}
