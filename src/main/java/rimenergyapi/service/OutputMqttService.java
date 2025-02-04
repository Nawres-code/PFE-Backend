package rimenergyapi.service;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.springframework.stereotype.Service;

import rimenergyapi.config.Mqtt;
import rimenergyapi.dto.OutputMqttMessage;

@Service
public class OutputMqttService {
	
	private final String TOPIC = "output/cmd1";
	private final String RESPONSE_TOPIC = "response1";

	public int publish(OutputMqttMessage msg) {
		ExecutorService service = Executors.newSingleThreadExecutor();
		Future<Integer> future = service.submit(new OutputCmdPublisher(msg));
		try {
			return future.get();
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}

	class OutputCmdPublisher implements Callable<Integer> {
		private Integer result = -1;
		private OutputMqttMessage message;

		public OutputCmdPublisher(OutputMqttMessage message) {
			this.message = message;
		}

		public void setMsg(OutputMqttMessage message) {
			this.message = message;
		}

		public Integer call() throws MqttPersistenceException, MqttException {

			MqttMessage msg = new MqttMessage(message.toJsonString().getBytes());
			msg.setQos(0);
			// msg.setRetained(true);
			IMqttClient client = Mqtt.getInstance();
			try {
				client.subscribe(RESPONSE_TOPIC, 1, (s, mqttResponseMsg) -> {
					if (new OutputMqttMessage(mqttResponseMsg.getPayload()).equals(message)) {
						result = 1;
					}
				});
				client.publish(TOPIC, msg);
				System.out.println("Message published " + message);
				try {
					Thread.sleep(1000 * 3);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (MqttException e) {
				e.printStackTrace();
			}
			return result;
		}
	}
}
