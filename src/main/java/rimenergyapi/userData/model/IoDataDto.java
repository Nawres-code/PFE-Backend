package rimenergyapi.userData.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class IoDataDto {

	private int ioId;
	private BigDecimal value;
	private BigDecimal globalCount;
	private Timestamp time;
	
	public IoDataDto() {}	

	public IoDataDto(int ioId, BigDecimal value, BigDecimal globalCount, Timestamp time) {
		super();
		this.ioId = ioId;
		this.value = value;
		this.globalCount = globalCount;
		this.time = time;
	}


public Timestamp getTime() {
	return time;
}
public void setTime(Timestamp time) {
	this.time = time;
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
