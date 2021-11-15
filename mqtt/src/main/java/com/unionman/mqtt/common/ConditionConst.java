package com.unionman.mqtt.common;

public interface ConditionConst {
	
	/**成功code*/
	public static final int success_code = 0;
	
	/**成功msg*/
	public static final String success_message = "成功";
	
	/**未捕获异常code*/
	public static final String fail_code = "999999";
	
	/**连接状态 0:未连接*/
	public static final int connect_status_0 = 0;	
	
	/**连接状态 1:已连接*/
	public static final int connect_status_1 = 1;
	
	/**连接状态(包含未连接已连接)*/
	public static final int connect_status_2 = 2;
	
	/**eqmx action标识：设备连接*/
	public static final String eqmx_action_client_connected = "client_connected";
	
	/**eqmx action标识：设备端口连接*/
	public static final String eqmx_action_client_disconnected = "client_disconnected";
	
	/**eqmx action标识：设备发布信息*/
	public static final String eqmx_action_message_publish = "message_publish";
	
	/**eqmx action标识：设备订阅信息*/
	public static final String eqmx_action_client_subscribe = "client_subscribe";
	
	/**完整性密钥生成key*/
	public static final String hash_secret_key = "unionman_auth";
	
	/**共享密钥生成key*/
	public static final String en_secret_key = "bEN3phjFp4VKQtta";
	
	/**特殊topic:重启网络*/
	public static final String topic_network_restart = "um_diag_network_restart";
	
	/**特殊topic:获取设备下挂设备简略信息*/
	public static final String topic_get_device_brief_info = "um_mon_get_device_brief_info";
	
	/**特殊topic:获取设备下挂设备详细信息*/
	public static final String topic_get_device_detailed_info = "um_mon_get_device_detailed_info";
	
	/**特殊topic:检测网络连通性*/
	public static final String topic_get_diag_detect_connect = "um_diag_detect_connect";
	
	/**发布主题*/
	public static final String publish_topic_info = "/umplugin/mac/request";
	
	/**特殊topic:重启网络*/
	public static final String subscribe_topic_info = "/umplugin/mac/response";

}
