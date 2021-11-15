package com.unionman.mqtt.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unionman.mqtt.common.ConditionConst;
import com.unionman.mqtt.exception.UnionmanException;
import com.unionman.mqtt.service.IMessageService;
import com.unionman.mqtt.util.JsonUtil;
import com.unionman.mqtt.util.StringUtil;

import net.sf.json.JSONObject;

@RequestMapping("/message")
@RestController
public class MessageController {

	private final static Logger logger = LoggerFactory.getLogger(MessageController.class);
	
	@Autowired
	private IMessageService service;

	@PostMapping("/getMessageInfo")
	public void getMessageInfo(@RequestBody() Map<String, Object> params) {
		logger.info("emqx数据" + params.toString());
    	try {
    		service.getMessageInfo(params);
    	}catch(Exception e) {
    		logger.error(e.getMessage());
    	}
	}
	
	@PostMapping("/getPublishMessageInfo")
	public JSONObject getPublishMessageInfo(@RequestBody() Map<String, Object> params) {
		try {
			JSONObject publishMessageInfo = service.getPublishMessageInfo(params);
			return publishMessageInfo;
		} catch (UnionmanException e) {
			logger.error(e.getMessage());
			return JsonUtil.errInfo(e.getErrorCode(), e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage());
			return JsonUtil.errInfo(ConditionConst.fail_code, StringUtil.getErrorInfo(e.getMessage()));
		}
	}
}
