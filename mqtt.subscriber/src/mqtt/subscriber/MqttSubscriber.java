package mqtt.subscriber;

import mqtt.subscriber.util.Utils;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;

public class MqttSubscriber {

	public static final String BROKER_URL = "tcp://broker.mqttdashboard.com:1883";

	String clientId = Utils.getMacAddress() + "-sub";
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

			// Subscribe to all subtopics of home
			final String topic = "home/temperature";
			mqttClient.subscribe(topic);

			System.out.println("Subscriber is now listening to " + topic);

			final String topicLWT = "home/LWT";
			mqttClient.subscribe(topicLWT);

			System.out.println("Subscriber is now listening to " + topicLWT);

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
