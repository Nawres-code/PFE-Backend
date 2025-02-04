package rimenergyapi.userData.model;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

public class ZoneRtDto {
	
	private Timestamp time;
	private double eAct,eReact,eApp,eFund,iPower, recovery;
	private Map<Integer,InstallationRtDto> installationsRtDto = new HashMap<Integer,InstallationRtDto>();
	private HashMap<Integer, CategoryRtDto> eActPerCat = new HashMap<Integer, CategoryRtDto>();
	
	
	public ZoneRtDto(Timestamp time, double eAct, double eReact, double eApp, double eFund) {
		this.time = time;
		this.eAct = eAct;
		this.eReact = eReact;
		this.eApp = eApp;
		this.eFund = eFund;
		this.installationsRtDto = new HashMap<Integer, InstallationRtDto>();
		this.eActPerCat = new HashMap<>();
		// this.eActPerCat.put(4, new CategoryRtDto(0,0));//Create other categorie and initiliza it by 0
	}

	public ZoneRtDto(Timestamp time) {
		this.time = time;
	}

	public ZoneRtDto(Timestamp time, double eAct, double eReact, double eApp, double eFund,
			Map<Integer, InstallationRtDto> installationsRtDto) {
		super();
		this.time = time;
		this.eAct = eAct;
		this.eReact = eReact;
		this.eApp = eApp;
		this.eFund = eFund;
		this.installationsRtDto = installationsRtDto;
		this.eActPerCat=new HashMap<>();
		// this.eActPerCat.put(4, new CategoryRtDto(0,0));//Create other categorie and initiliza it by 0
	}

	

	public ZoneRtDto(Timestamp time, double recovery) {
		super();
		this.time = time;
		this.recovery = recovery;
	}


	public ZoneRtDto(long time, double eAct, double eReact, double eApp, double eFund, double iPower) {
		this.time = new Timestamp(time);
		this.eAct = eAct;
		this.eReact = eReact;
		this.eApp = eApp;
		this.eFund = eFund;
		this.iPower = iPower;
		this.installationsRtDto = new HashMap<Integer, InstallationRtDto>();
		this.eActPerCat = new HashMap<>();
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


	public Map<Integer, InstallationRtDto> getInstallationsRtDto() {
		return installationsRtDto;
	}


	public void setInstallationsRtDto(Map<Integer, InstallationRtDto> installationsRtDto) {
		this.installationsRtDto = installationsRtDto;
	}


	public HashMap<Integer, CategoryRtDto> geteActPerCat() {
		return eActPerCat;
	}


	public void seteActPerCat(HashMap<Integer, CategoryRtDto> eActPerCat) {
		this.eActPerCat = eActPerCat;
	}


	public double getiPower() {
		return iPower;
	}


	public void setiPower(double iPower) {
		this.iPower = iPower;
	}


	public double getRecovery() {
		return recovery;
	}


	public void setRecovery(double recovery) {
		this.recovery = recovery;
	}
	
	
	
}
