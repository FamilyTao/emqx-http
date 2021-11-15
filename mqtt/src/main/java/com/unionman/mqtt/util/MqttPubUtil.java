package com.unionman.mqtt.util;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import com.unionman.mqtt.data.MqttPropertiesData;

public class MqttPubUtil {
	
	private static MqttClient mqttClient;
	private static MqttPropertiesData properties;
	
	/**
	 * 获取mqttclient对象
	 * @param properties
	 * @return
	 * @throws MqttException
	 */
	public static MqttClient connect() throws MqttException {
		MemoryPersistence persistence = new MemoryPersistence();
		MqttConnectOptions connOpts = new MqttConnectOptions();
		connOpts.setCleanSession(properties.isCleanSession());
		connOpts.setUserName(properties.getUserName());
		connOpts.setPassword(properties.getPassWord().toCharArray());
		connOpts.setConnectionTimeout(properties.getConnectionTimeout());
		connOpts.setKeepAliveInterval(properties.getKeepAliveInterval());
		mqttClient = new MqttClient(properties.getBroker(), properties.getClientId(), persistence);
		mqttClient.setCallback(new PushCallback());
		mqttClient.connect(connOpts);
		return mqttClient;
	}
	
	public static void pub(MqttClient sampleClient, String msg,String topic) 
			throws MqttPersistenceException, MqttException {
		MqttMessage message = new MqttMessage(msg.getBytes());
		message.setQos(properties.getQos());
		message.setRetained(false);
		sampleClient.publish(topic, message);
	}
	
	public void publish(MqttPropertiesData mqttPubProperties) throws MqttException{
		properties = mqttPubProperties;
		MqttClient mqttClient = connect();
		if (mqttClient != null) {
			pub(mqttClient, properties.getMsg(), properties.getTopic());
		}
		if (mqttClient != null) {
			mqttClient.disconnect();
		}
	}
}
