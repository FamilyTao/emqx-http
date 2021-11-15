package com.unionman.mqtt.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.unionman.mqtt.common.ConditionConst;
import com.unionman.mqtt.exception.UnionmanException;
import com.unionman.mqtt.service.IEquipmentService;
import com.unionman.mqtt.util.JsonUtil;
import com.unionman.mqtt.util.StringUtil;

import net.sf.json.JSONObject;

@RequestMapping("/equipment")
@RestController
public class EquipmentController {
	
	private final static Logger logger = LoggerFactory.getLogger(EquipmentController.class);
	
	@Autowired
	private IEquipmentService service;
	
	
    @PostMapping("/register")
    public JSONObject register(@RequestBody() Map<String,Object> params){
    	logger.info("注册传入数据" + params.toString());
    	try {
    		JSONObject register = service.register(params);
    		logger.info("注册传入数据" + register.toString());
    		return register;
    	}catch(UnionmanException e) {
    		logger.error(e.getMessage());
    		return JsonUtil.errInfo(e.getErrorCode(), e.getMessage());
    	}catch(Exception e) {
    		logger.error(e.getMessage());
    		return JsonUtil.errInfo(ConditionConst.fail_code, StringUtil.getErrorInfo(e.getMessage()));
    	}
    }
	
    @PostMapping("/authValidation")
    public void authValidation(@RequestParam() Map<String, Object> params, HttpServletRequest request, HttpServletResponse response){
    	logger.info("验证传入数据" + params.toString());
    	try {
    		if(service.authValidation(params)) {
    			response.setStatus(200);
    		}else {
    			response.setStatus(400);
    		}
    	}catch(Exception e) {
    		logger.error(e.getMessage());
    		response.setStatus(400);
    	}
    }
    
    @GetMapping("/aclValidation")
    public void aclValidation(@RequestParam() Map<String, Object> params, HttpServletResponse response){
    	try {
    		service.aclValidation(params);
    		response.setStatus(200);
    	}catch(Exception e) {
    		logger.error(e.getMessage());
    		response.setStatus(400);
    	}
    }
	
    @PostMapping("/getEquipmentInfo")
    public JSONObject getEquipmentInfo(@RequestBody() Map<String,Object> params){
    	try {
    		JSONObject equipmentInfo = service.getEquipmentInfo(params);
    		return equipmentInfo;
    	}catch(UnionmanException e) {
    		logger.error(e.getMessage());
    		return JsonUtil.errInfo(e.getErrorCode(), e.getMessage());
    	}catch(Exception e) {
    		logger.error(e.getMessage());
    		return JsonUtil.errInfo(ConditionConst.fail_code, StringUtil.getErrorInfo(e.getMessage()));
    	}
    }
    
	@RequestMapping(value = "/getAllMacInfo", method = RequestMethod.GET)
	public JSONObject getAllMacInfo() {
		try {
			JSONObject macsInfo = service.getAllMacInfo();
			return macsInfo;
		} catch (UnionmanException e) {
			logger.error(e.getMessage());
			return JsonUtil.errInfo(e.getErrorCode(), e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage());
			return JsonUtil.errInfo(ConditionConst.fail_code, StringUtil.getErrorInfo(e.getMessage()));
		}
	}
	
	@RequestMapping(value = "/getDeviceMacInfo", method = RequestMethod.GET)
	public JSONObject getDeviceMacInfo(@RequestParam() Map<String,Object> params) {
		try {
			JSONObject macsInfo = service.getDeviceMacInfo(params);
			return macsInfo;
		} catch (UnionmanException e) {
			logger.error(e.getMessage());
			return JsonUtil.errInfo(e.getErrorCode(), e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage());
			return JsonUtil.errInfo(ConditionConst.fail_code, StringUtil.getErrorInfo(e.getMessage()));
		}
	}
}
