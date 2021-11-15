package com.unionman.mqtt.util;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class PushCallback  implements MqttCallback {

	@Override
	public void connectionLost(Throwable cause) {
		System.out.println("aaaaaaaaaaaa");  
		
	}

	@Override
	public void messageArrived(String topic, MqttMessage message) throws Exception {
		System.out.println("接收消息主题 : " + topic);  
        System.out.println("接收消息Qos : " + message.getQos());  
        System.out.println("接收消息内容 : " + new String(message.getPayload()));
	}

	@Override
	public void deliveryComplete(IMqttDeliveryToken token) {
		// TODO Auto-generated method stub
		
	}

}
