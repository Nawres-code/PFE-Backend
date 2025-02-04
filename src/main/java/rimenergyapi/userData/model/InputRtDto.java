package rimenergyapi.userData.model;

import java.sql.Timestamp;

public class InputRtDto {
	private int lastValue;
	private Timestamp lastTime;

	public InputRtDto() {
	}

	public InputRtDto(int lastValue, Timestamp lastTime) {
		this.lastValue = lastValue;
		this.lastTime = lastTime;
	}
	
	public InputRtDto(RtInputTuple inputTuple) {
		this.lastValue = inputTuple.getValue();
		this.lastTime = inputTuple.getTime();
	}
	
	
	public int getLastValue() {
		return lastValue;
	}

	public void setLastValue(int lastValue) {
		this.lastValue = lastValue;
	}

	public Timestamp getLastTime() {
		return lastTime;
	}

	public void setLastTime(Timestamp lastTime) {
		this.lastTime = lastTime;
	}

}
