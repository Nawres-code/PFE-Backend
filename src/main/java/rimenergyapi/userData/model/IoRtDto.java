package rimenergyapi.userData.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class IoRtDto {

	private int ioId;
	private BigDecimal value;
	private BigDecimal globalCount;
	private Timestamp lastTime;
	
	public IoRtDto() {}	

	public IoRtDto(int ioId, BigDecimal value, BigDecimal globalCount, Timestamp time) {
		super();
		this.ioId = ioId;
		this.value = value;
		this.globalCount = globalCount;
		this.lastTime = time;
	}


	public Timestamp getLastTime() {
		return lastTime;
	}
	
	public void setLastTime(Timestamp lastTime) {
		this.lastTime = lastTime;
	}

	public int getIoId() {
		return ioId;
	}

	public void setIoId(int ioId) {
		this.ioId = ioId;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public BigDecimal getGlobalCount() {
		return globalCount;
	}
	public void setGlobalCount(BigDecimal globalCount) {
		this.globalCount = globalCount;
	}
	
	
	
	
}
