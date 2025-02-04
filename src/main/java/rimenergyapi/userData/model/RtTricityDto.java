package rimenergyapi.userData.model;

import java.sql.Timestamp;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class RtTricityDto {

	private int id;
    private Timestamp time;
    private double iPower;
    private double vmoy;
    private double cmoy;
    private double pmoy;
    private double phmoy;
    private boolean isGeneral=false;

    public RtTricityDto() {
    }
    
	public RtTricityDto(int id, Timestamp time, double vmoy, double cmoy, double pmoy, double phmoy,double iPower) {
		super();
		this.id = id;
		this.time = time;
		this.vmoy = vmoy;
		this.cmoy = cmoy;
		this.pmoy = pmoy;
		this.phmoy = phmoy;
		this.iPower = iPower;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	

	public double getVmoy() {
		return vmoy;
	}

	public void setVmoy(double vmoy) {
		this.vmoy = vmoy;
	}

	public double getCmoy() {
		return cmoy;
	}

	public void setCmoy(double cmoy) {
		this.cmoy = cmoy;
	}

	public double getPmoy() {
		return pmoy;
	}

	public void setPmoy(double pmoy) {
		this.pmoy = pmoy;
	}

	public double getPhmoy() {
		return phmoy;
	}

	public void setPhmoy(double phmoy) {
		this.phmoy = phmoy;
	}

	public boolean isGeneral() {
		return isGeneral;
	}

	public void setGeneral(boolean isGeneral) {
		this.isGeneral = isGeneral;
	}

	public double getiPower() {
		return iPower;
	}

	public void setiPower(double iPower) {
		this.iPower = iPower;
	}
	
}
