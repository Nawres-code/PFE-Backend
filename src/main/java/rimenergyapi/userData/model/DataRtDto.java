package rimenergyapi.userData.model;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

public class DataRtDto {

	private HashMap<Integer,ZoneRtDto> zonesRtDto = new HashMap<>();
	private Map<Integer, CategoryRtDto> eActPerCat;
	private double eAct,eReact,eApp,eFund,iPower;
	private Map<Integer, Double> lastDayInstallationsEnegies = new HashMap<Integer, Double>();
	
	public DataRtDto() {
		super();
		this.zonesRtDto = new HashMap<Integer, ZoneRtDto>();
		this.seteActPerCat(new HashMap<>());
		this.eAct = 0;
		this.eReact = 0;
		this.eApp = 0;
		this.eFund = 0;
		this.iPower=0;
		// this.eActPerCat.put(4, new CategoryRtDto(0,0));//Create other categorie and initiliza it by 0
	}
	public DataRtDto(HashMap<Integer, ZoneRtDto> zonesRtDto, double eAct, double eReact, double eApp, double eFund) {
		super();
		this.zonesRtDto = zonesRtDto;
		this.eAct = eAct;
		this.eReact = eReact;
		this.eApp = eApp;
		this.eFund = eFund;
		this.iPower = iPower;
	}
	public DataRtDto(HashMap<Integer, ZoneRtDto> zonesRtDto, double eAct, double eReact, double eApp, double eFund, Map<Integer, Double> lastDayInstallationsEnegies) {
		super();
		this.zonesRtDto = zonesRtDto;
		this.eAct = eAct;
		this.eReact = eReact;
		this.eApp = eApp;
		this.eFund = eFund;
		this.iPower = iPower;
		this.lastDayInstallationsEnegies = lastDayInstallationsEnegies;
	}
	public HashMap<Integer, ZoneRtDto> getZonesRtDto() {
		return zonesRtDto;
	}
	public void setZonesRtDto(HashMap<Integer, ZoneRtDto> zonesRtDto) {
		this.zonesRtDto = zonesRtDto;
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
	public Map<Integer, Double> getLastDayInstallationsEnegies() {
		return lastDayInstallationsEnegies;
	}
	public void setLastDayInstallationsEnegies(Map<Integer, Double> lastDayInstallationsEnegies) {
		this.lastDayInstallationsEnegies = lastDayInstallationsEnegies;
	}
	
	
}
