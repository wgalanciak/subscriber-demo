package mqtt.subscriber;

import mqtt.subscriber.util.Utils;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;

public class MqttSubscriber {

	public static final String BROKER_URL = "tcp://broker.mqttdashboard.com:1883";
	
	private static final String TOPIC = "home/temperature";

	private String clientId = Utils.getMacAddress() + "-sub";
	
	private MqttClient mqttClient;

	public MqttSubscriber() {
		try {
			mqttClient = new MqttClient(BROKER_URL, clientId);
		} catch (MqttException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	public void start() {
		try {
			mqttClient.setCallback(new SubscribeCallback());
			mqttClient.connect();
			mqttClient.subscribe(TOPIC);
			System.out.println("Subscriber is now listening to " + TOPIC);
		} catch (MqttException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	public static void main(String... args) {
		final MqttSubscriber subscriber = new MqttSubscriber();
		subscriber.start();
	}

}
