package rimenergyapi.config;

import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;

public class Mqtt {

	private static final String MQTT_CLIENT_ID = "spring-server";
	private static final String MQTT_SERVER_ADDRES = "tcp://127.0.0.1:1883";
//	private static MemoryPersistence persistence = new MemoryPersistence();
	private static IMqttClient instance;

	public static IMqttClient getInstance() {
		try {
			if (instance == null) {
				instance = new MqttClient(MQTT_SERVER_ADDRES, MQTT_CLIENT_ID/*, persistence*/);
			}

			if (!instance.isConnected()) {
				MqttConnectOptions options = new MqttConnectOptions();
				options.setAutomaticReconnect(true);
				options.setCleanSession(true);
			//	options.setConnectionTimeout(1);
				options.setUserName("guest");
				options.setPassword("guest".toCharArray());
				instance.connect(options);
			}
		} catch (MqttException e) {
			 System.out.println("reason "+e.getReasonCode());
		        System.out.println("msg "+e.getMessage());
		        System.out.println("loc "+e.getLocalizedMessage());
		        System.out.println("cause "+e.getCause());
		        System.out.println("excep "+e);
			e.printStackTrace();
		}

		return instance;
	}
	
	public static boolean isConnected() {
		return instance.isConnected();
	}
	
	public static void disconnect() {
		if(isConnected()) {
			try {
				instance.disconnect();
			} catch (MqttException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private Mqtt() {

	}
}
