package rimenergyapi.dto;

import java.io.IOException;
import java.io.Serializable;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author cheyma
 *
 */
public class OutputMqttMessage implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long time;
	private int idOutputDevice;
	private String cmd;

	public Long getTime() {
		return time;
	}

	public void setTime(Long time) {
		this.time = time;
	}

	public int getIdOutputDevice() {
		return idOutputDevice;
	}

	public void setIdOutputDevice(int id) {
		this.idOutputDevice = id;
	}

	public String getCmd() {
		return cmd;
	}

	public void setCmd(String cmd) {
		this.cmd = cmd;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cmd == null) ? 0 : cmd.hashCode());
		result = prime * result + idOutputDevice;
		result = prime * result + ((time == null) ? 0 : time.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OutputMqttMessage other = (OutputMqttMessage) obj;
		if (cmd == null) {
			if (other.cmd != null)
				return false;
		} else if (!cmd.equals(other.cmd))
			return false;
		if (idOutputDevice != other.idOutputDevice)
			return false;
		if (time == null) {
			if (other.time != null)
				return false;
		} else if (!time.equals(other.time))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MqttMessage [time=" + time + ", idOutputDevice=" + idOutputDevice + ", cmd=" + cmd + "]";
	}
	
	public String toJsonString() {
		return "{time=" + time + ", idOutputDevice=" + idOutputDevice + ", cmd=" + cmd + "}";
	}

	public OutputMqttMessage() {
		
	}
	
	public OutputMqttMessage(byte[] payload) throws JsonParseException, JsonMappingException, IOException {
		OutputMqttMessage msg = new ObjectMapper().readValue(new String(payload),
				OutputMqttMessage.class);
		setCmd(msg.getCmd());
		setTime(msg.getTime());
		setCmd(getCmd());
	}

}
