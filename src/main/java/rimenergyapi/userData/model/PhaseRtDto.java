package rimenergyapi.userData.model;

import java.sql.Timestamp;

public class PhaseRtDto {
	private Timestamp time;
	private double eAct, eReact, eApp, eFund;
	private double vmoy;
	private double cmoy;
	private double pmoy;
	private double phmoy;

	public PhaseRtDto(Timestamp time, double eAct, double eReact, double eApp, double eFund) {
		super();
		this.time = time;
		this.eAct = eAct;
		this.eReact = eReact;
		this.eApp = eApp;
		this.eFund = eFund;
	}

	public PhaseRtDto(Timestamp time, double eAct, double eReact, double eApp, double eFund, double vmoy, double cmoy,
			double pmoy, double phmoy) {
		this.time = time;
		this.eAct = eAct;
		this.eReact = eReact;
		this.eApp = eApp;
		this.eFund = eFund;
		this.vmoy = vmoy;
		this.cmoy = cmoy;
		this.pmoy = pmoy;
		this.phmoy = phmoy;
	}

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public double geteAct() {
		return eAct;
	}

	public void seteAct(double eAct) {
		this.eAct = eAct;
	}

	public double geteReact() {
		return eReact;
	}

	public void seteReact(double eReact) {
		this.eReact = eReact;
	}

	public double geteApp() {
		return eApp;
	}

	public void seteApp(double eApp) {
		this.eApp = eApp;
	}

	public double geteFund() {
		return eFund;
	}

	public void seteFund(double eFund) {
		this.eFund = eFund;
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
	
	

}
