package com.unionman.mqtt.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unionman.mqtt.common.ConditionConst;
import com.unionman.mqtt.common.ErrorCode;
import com.unionman.mqtt.dao.master.IEquipmentMapper;
import com.unionman.mqtt.data.DeviceMacData;
import com.unionman.mqtt.data.EquipmentData;
import com.unionman.mqtt.exception.UnionmanException;
import com.unionman.mqtt.service.IEquipmentService;
import com.unionman.mqtt.util.HMACSHA256;
import com.unionman.mqtt.util.JsonUtil;
import com.unionman.mqtt.util.MD5Util;
import com.unionman.mqtt.util.MapUtil;
import com.unionman.mqtt.util.PageUtil;
import com.unionman.mqtt.util.StringUtil;

import net.sf.json.JSONObject;

@Service
public class EquipmentServiceImpl implements IEquipmentService {
	
	@Autowired
	private IEquipmentMapper mapper;

	@Override
	public JSONObject register(Map<String, Object> params) {
		String mac = MapUtil.getString(params, "mac");
		if(StringUtil.checkNull(mac)) {
			throw new UnionmanException(ErrorCode.mac_null.getCode(), ErrorCode.mac_null.getMessage());
		}
		JSONObject json = JsonUtil.successInfo();
		json.put("enSecretKey", StringUtil.strTo16(MD5Util.encrypt16(mac + ConditionConst.en_secret_key)));
		json.put("hashSecretKey", StringUtil.strTo16(MD5Util.encrypt32(mac + ConditionConst.hash_secret_key)));
		EquipmentData data = new EquipmentData();
		data.setMac(mac);
		data.setConnect_status(ConditionConst.connect_status_0);
		int size = mapper.checkEquipmentExist(data);
		if(size == 0) {
			mapper.insertEquipmentInfo(data);
		}
		return json;
	}
	
	public static void main(String[] args) {
		String mac = "0.0.0.0.0.0";
		String hashSecretKey = StringUtil.strTo16(MD5Util.encrypt32(mac + ConditionConst.hash_secret_key));
		System.out.println(hashSecretKey);
		String hmacSHA256 = HMACSHA256.hmacSHA256(mac, hashSecretKey);
		System.out.println(hmacSHA256);
	}

	@Override
	public boolean authValidation(Map<String, Object> params) {
		String username = MapUtil.getString(params, "username");
		if(StringUtil.checkNull(username)) {
			throw new UnionmanException(ErrorCode.username_null.getCode(), ErrorCode.username_null.getMessage());
		}
		String password = MapUtil.getString(params, "password");
		if(StringUtil.checkNull(password)) {
			throw new UnionmanException(ErrorCode.password_null.getCode(), ErrorCode.password_null.getMessage());
		}
		String md5 = MD5Util.encrypt32(username + ConditionConst.hash_secret_key);
		String hmacSHA256 = HMACSHA256.hmacSHA256(username, StringUtil.strTo16(md5));
		if(password.equals(hmacSHA256)) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public void aclValidation(Map<String, Object> params) {
		
		
	}

	@Override
	public JSONObject getEquipmentInfo(Map<String, Object> params) {
		String mac = MapUtil.getString(params, "mac");
		int connect_status = MapUtil.getInt(params, "connect_status");
		EquipmentData data = new EquipmentData();
		data.setMac(mac);
		data.setConnect_status(connect_status);
		List<EquipmentData> datas = mapper.getEquipmentInfo(data);
		PageUtil<EquipmentData> page = new PageUtil<EquipmentData>();
		page.setCurrentPages(MapUtil.getInt(params, "page"));
		page.setSize(MapUtil.getInt(params, "size"));
		page.setDataList(datas);
		JSONObject json = JsonUtil.successInfo();
		json.put("data", page);
		return json;
	}

	@Override
	public JSONObject getAllMacInfo() {
		List<EquipmentData> datas = mapper.getAllMacInfo();
		JSONObject json = JsonUtil.successInfo();
		json.put("data", datas);
		return json;
	}

	@Override
	public JSONObject getDeviceMacInfo(Map<String, Object> params) {
		List<DeviceMacData> datas = mapper.getDeviceMacInfo(params);
		JSONObject json = JsonUtil.successInfo();
		json.put("data", datas);
		return json;
	}

}
