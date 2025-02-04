package rimenergyapi.userData.model;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

public class ProviderRtDto {

	private Timestamp time;
	private double eAct, eReact, eApp, eFund, iPower;
	private Map<Integer,GroupRtDto> groupsRtDto;
	private Map<Integer, CategoryRtDto> eActPerCat;

	
	public ProviderRtDto(Timestamp time, double eAct, double eReact, double eApp, double eFund,
			Map<Integer, GroupRtDto> groupsRtDto) {
		super();
		this.time = time;
		this.eAct = eAct;
		this.eReact = eReact;
		this.eApp = eApp;
		this.eFund = eFund;
		this.groupsRtDto = groupsRtDto;
		this.eActPerCat=new HashMap<>();
		this.eActPerCat.put(4, new CategoryRtDto(0,0));//Create other categorie and initiliza it by 0
	}
	public ProviderRtDto(Timestamp time, double eAct, double eReact, double eApp, double eFund) {
		super();
		this.time = time;
		this.eAct = eAct;
		this.eReact = eReact;
		this.eApp = eApp;
		this.eFund = eFund;
		this.groupsRtDto = new HashMap<Integer, GroupRtDto>();
		this.eActPerCat=new HashMap<>();
		this.eActPerCat.put(4, new CategoryRtDto(0,0));//Create other categorie and initiliza it by 0
	}
	public ProviderRtDto(Timestamp time, double eAct, double eReact, double eApp, double eFund,
			Map<Integer, GroupRtDto> groupsRtDto, Map<Integer, CategoryRtDto> eActPerCat) {
		super();
		this.time = time;
		this.eAct = eAct;
		this.eReact = eReact;
		this.eApp = eApp;
		this.eFund = eFund;
		this.groupsRtDto = groupsRtDto;
		this.eActPerCat = eActPerCat;
	}
	
	public ProviderRtDto(InstallationRtDto instRt) {
		this.time = instRt.getTime();
		this.eAct = instRt.geteAct();
		this.eReact = instRt.geteReact();
		this.eApp = instRt.geteApp();
		this.eFund = instRt.geteFund();
		this.iPower = instRt.getiPower();
		this.groupsRtDto = instRt.getGroupsRtDto();
		this.eActPerCat = instRt.geteActPerCat();
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
	public Map<Integer, GroupRtDto> getGroupsRtDto() {
		return groupsRtDto;
	}
	public void setGroupsRtDto(Map<Integer, GroupRtDto> groupsRtDto) {
		this.groupsRtDto = groupsRtDto;
	}
	public Map<Integer, CategoryRtDto> geteActPerCat() {
		return eActPerCat;
	}
	public void seteActPerCat(Map<Integer, CategoryRtDto> eActPerCat) {
		this.eActPerCat = eActPerCat;
	}

	public double getiPower() {
		return iPower;
	}
	public void setiPower(double iPower) {
		this.iPower = iPower;
	}
	
}
