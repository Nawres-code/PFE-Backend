package rimenergyapi.userData.model;

import java.sql.Timestamp;

public class RtUserEnergyTuple {
	
	private int zoneId; 
	private int installationId;
	private int groupId;
	private int phaseId;
	private int categoryId;
	private String groupType;
	private int groupFatherId;
	private Timestamp time;
	private double actEnergyDay;
	private double reactEnergyDay;
	private double fundEnergyDay;
	private double appEnergyDay;
	private double vInst;
	private double cInst; 
	private double pInst;
	private double phInst;
	
	public int getZoneId() {
		return zoneId;
	}
	public void setZoneId(int zoneId) {
		this.zoneId = zoneId;
	}
	public int getInstallationId() {
		return installationId;
	}
	public void setInstallationId(int installationId) {
		this.installationId = installationId;
	}
	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	public int getPhaseId() {
		return phaseId;
	}
	public void setPhaseId(int phaseId) {
		this.phaseId = phaseId;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public String getGroupType() {
		return groupType;
	}
	public void setGroupType(String groupType) {
		this.groupType = groupType;
	}
	public int getGroupFatherId() {
		return groupFatherId;
	}
	public void setGroupFatherId(int groupFatherId) {
		this.groupFatherId = groupFatherId;
	}
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	public double getActEnergyDay() {
		return actEnergyDay;
	}
	public void setActEnergyDay(double actEnergyDay) {
		this.actEnergyDay = actEnergyDay;
	}
	public double getReactEnergyDay() {
		return reactEnergyDay;
	}
	public void setReactEnergyDay(double reactEnergyDay) {
		this.reactEnergyDay = reactEnergyDay;
	}
	public double getFundEnergyDay() {
		return fundEnergyDay;
	}
	public void setFundEnergyDay(double fundEnergyDay) {
		this.fundEnergyDay = fundEnergyDay;
	}
	public double getAppEnergyDay() {
		return appEnergyDay;
	}
	public void setAppEnergyDay(double appEnergyDay) {
		this.appEnergyDay = appEnergyDay;
	}
	public double getvInst() {
		return vInst;
	}
	public void setvInst(double vInst) {
		this.vInst = vInst;
	}
	public double getcInst() {
		return cInst;
	}
	public void setcInst(double cInst) {
		this.cInst = cInst;
	}
	public double getpInst() {
		return pInst;
	}
	public void setpInst(double pInst) {
		this.pInst = pInst;
	}
	public double getPhInst() {
		return phInst;
	}
	public void setPhInst(double phInst) {
		this.phInst = phInst;
	}  
	
}
