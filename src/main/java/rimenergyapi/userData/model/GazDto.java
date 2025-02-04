package rimenergyapi.userData.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class GazDto {

	private int gazId;
	private BigDecimal in1;
	private Timestamp time;
	
	public GazDto() {}	

	public GazDto(int gazId, BigDecimal in1, Timestamp time) {
		super();
		this.gazId = gazId;
		this.in1 = in1;
		this.time = time;
	}



	public int getGazId() {
		return gazId;
	}
	public void setGazId(int gazId) {
		this.gazId = gazId;
	}
	public BigDecimal getIn1() {
		return in1;
	}
	public void setIn1(BigDecimal in1) {
		this.in1 = in1;
	}
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	
	
}
