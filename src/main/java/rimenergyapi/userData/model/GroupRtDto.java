package rimenergyapi.userData.model;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

public class GroupRtDto {

	private Timestamp time;
	private double eAct,eReact,eApp,eFund,iPower;
	private Map<Integer,PhaseRtDto> phasesRtDto;
	private Map<Integer, Double> eActPerCat;
	
	public GroupRtDto(Timestamp time, double eAct, double eReact, double eApp, double eFund) {
		super();
		this.time = time;
		this.eAct = eAct;
		this.eReact = eReact;
		this.eApp = eApp;
		this.eFund = eFund;
		this.phasesRtDto = new HashMap<Integer, PhaseRtDto>();
		this.seteActPerCat(new HashMap<>());
		this.eActPerCat.put(4, 0.0);//Create other categorie and initiliza it by 0
	}
	
	public GroupRtDto(Long time, double eAct, double eReact, double eApp, double eFund, double iPower) {
		this.time = new Timestamp(time);
		this.eAct = eAct;
		this.eReact = eReact;
		this.eApp = eApp;
		this.eFund = eFund;
		this.iPower = iPower;
		this.phasesRtDto = new HashMap<Integer, PhaseRtDto>();
		this.seteActPerCat(new HashMap<>());
	}
	
	public GroupRtDto(Timestamp time, double eAct, double eReact, double eApp, double eFund,
			Map<Integer, PhaseRtDto> phasesRtDto) {
		super();
		this.time = time;
		this.eAct = eAct;
		this.eReact = eReact;
		this.eApp = eApp;
		this.eFund = eFund;
		this.phasesRtDto = phasesRtDto;

		this.seteActPerCat(new HashMap<>());
		this.eActPerCat.put(4, 0.0);//Create other categorie and initiliza it by 0
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
	public Map<Integer, PhaseRtDto> getPhasesRtDto() {
		return phasesRtDto;
	}
	public void setPhasesRtDto(Map<Integer, PhaseRtDto> phasesRtDto) {
		this.phasesRtDto = phasesRtDto;
	}

	public Map<Integer, Double> geteActPerCat() {
		return eActPerCat;
	}

	public void seteActPerCat(Map<Integer, Double> eActPerCat) {
		this.eActPerCat = eActPerCat;
	}

	public double getiPower() {
		return iPower;
	}

	public void setiPower(double iPower) {
		this.iPower = iPower;
	}
	
	void appendEnergy(boolean isGeneral,double eAct, double eReact, double eApp, double eFund) {
		if(isGeneral) {
			this.eAct+=eAct;
			this.eReact+=eReact;
			this.eApp+=eApp;
			this.eFund+=eFund;
		}else {
			this.eAct-=eAct;
			this.eReact-=eReact;
			this.eApp-=eApp;
			this.eFund-=eFund;
		}
	}
	
	
}
