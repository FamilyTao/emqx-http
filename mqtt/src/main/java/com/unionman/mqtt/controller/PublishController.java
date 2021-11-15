package com.unionman.mqtt.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.unionman.mqtt.common.ConditionConst;
import com.unionman.mqtt.exception.UnionmanException;
import com.unionman.mqtt.service.IPublishService;
import com.unionman.mqtt.util.JsonUtil;
import com.unionman.mqtt.util.StringUtil;

import net.sf.json.JSONObject;

@RequestMapping("/publish")
@RestController
public class PublishController {

	private final static Logger logger = LoggerFactory.getLogger(PublishController.class);

	@Autowired
	private IPublishService service;

	@RequestMapping(value = "/getPublishTopicInfo", method = RequestMethod.GET)
	public JSONObject getPublishTopicInfo() {
		try {
			JSONObject publishTopicInfo = service.getPublishTopicInfo();
			return publishTopicInfo;
		} catch (UnionmanException e) {
			logger.error(e.getMessage());
			return JsonUtil.errInfo(e.getErrorCode(), e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage());
			return JsonUtil.errInfo(ConditionConst.fail_code, StringUtil.getErrorInfo(e.getMessage()));
		}
	}

	@PostMapping("/publishTopicInfo")
	public JSONObject publishTopicInfo(@RequestBody() Map<String, Object> params) {
		try {
			JSONObject publishTopicInfo = service.publishTopicInfo(params);
			return publishTopicInfo;
		} catch (UnionmanException e) {
			logger.error(e.getMessage());
			return JsonUtil.errInfo(e.getErrorCode(), e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage());
			return JsonUtil.errInfo(ConditionConst.fail_code, StringUtil.getErrorInfo(e.getMessage()));
		}
	}

}
