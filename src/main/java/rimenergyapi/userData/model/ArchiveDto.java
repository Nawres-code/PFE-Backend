package rimenergyapi.userData.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

public class ArchiveDto {

	private double voltageMax;
    private double voltageMin;
    private double voltageMoy;
    private double voltageEt;
    private double voltageInst;
    private double currentMax;
    private double currentMin;
    private double currentMoy;
    private double currentEt;
    private double currentInst;
    private double phaseMax;
    private double phaseMin;
    private double phaseMoy;
    private double phaseEt;
    private double phaseInst;
    private double freqMax;
    private double freqMin;
    private double freqMoy;
    private double freqEt;
    private double freqInst;
    private double energyAct;
    private double energyReact;
    private double energyFund;
    private double energyApp;
    private int power;
    private int flags;
    private int addedFlag;
    private int addedInfo;
    private double thd;
    private Integer intVal1;
    private Integer intVal2;
    private Integer intVal3;
    private Double doubleVal1;
    private Double doubleVal2;
    private Double doubleVal3;
    private double powerMax;
    private double powerMin;
    private double powerMoy;
    private double powerEt;
    private double powerInst;

   public ArchiveDto() {
   }
   
   

public ArchiveDto(double voltageMax, double voltageMin, double voltageMoy, double voltageEt, double voltageInst,
		double currentMax, double currentMin, double currentMoy, double currentEt, double currentInst, double phaseMax,
		double phaseMin, double phaseMoy, double phaseEt, double phaseInst, double freqMax, double freqMin,
		double freqMoy, double freqEt, double freqInst, double energyAct, double energyReact, double energyFund,
		double energyApp, int power, int flags, int addedFlag, int addedInfo, double thd, Integer intVal1,
		Integer intVal2, Integer intVal3, Double doubleVal1, Double doubleVal2, Double doubleVal3, double powerMax,
		double powerMin, double powerMoy, double powerEt, double powerInst) {
	super();
	this.voltageMax = voltageMax;
	this.voltageMin = voltageMin;
	this.voltageMoy = voltageMoy;
	this.voltageEt = voltageEt;
	this.voltageInst = voltageInst;
	this.currentMax = currentMax;
	this.currentMin = currentMin;
	this.currentMoy = currentMoy;
	this.currentEt = currentEt;
	this.currentInst = currentInst;
	this.phaseMax = phaseMax;
	this.phaseMin = phaseMin;
	this.phaseMoy = phaseMoy;
	this.phaseEt = phaseEt;
	this.phaseInst = phaseInst;
	this.freqMax = freqMax;
	this.freqMin = freqMin;
	this.freqMoy = freqMoy;
	this.freqEt = freqEt;
	this.freqInst = freqInst;
	this.energyAct = energyAct;
	this.energyReact = energyReact;
	this.energyFund = energyFund;
	this.energyApp = energyApp;
	this.power = power;
	this.flags = flags;
	this.addedFlag = addedFlag;
	this.addedInfo = addedInfo;
	this.thd = thd;
	this.intVal1 = intVal1;
	this.intVal2 = intVal2;
	this.intVal3 = intVal3;
	this.doubleVal1 = doubleVal1;
	this.doubleVal2 = doubleVal2;
	this.doubleVal3 = doubleVal3;
	this.powerMax = powerMax;
	this.powerMin = powerMin;
	this.powerMoy = powerMoy;
	this.powerEt = powerEt;
	this.powerInst = powerInst;
}



public double getVoltageMax() {
	return voltageMax;
}

public void setVoltageMax(double voltageMax) {
	this.voltageMax = voltageMax;
}

public double getVoltageMin() {
	return voltageMin;
}

public void setVoltageMin(double voltageMin) {
	this.voltageMin = voltageMin;
}

public double getVoltageMoy() {
	return voltageMoy;
}

public void setVoltageMoy(double voltageMoy) {
	this.voltageMoy = voltageMoy;
}

public double getVoltageEt() {
	return voltageEt;
}

public void setVoltageEt(double voltageEt) {
	this.voltageEt = voltageEt;
}

public double getVoltageInst() {
	return voltageInst;
}

public void setVoltageInst(double voltageInst) {
	this.voltageInst = voltageInst;
}

public double getCurrentMax() {
	return currentMax;
}

public void setCurrentMax(double currentMax) {
	this.currentMax = currentMax;
}

public double getCurrentMin() {
	return currentMin;
}

public void setCurrentMin(double currentMin) {
	this.currentMin = currentMin;
}

public double getCurrentMoy() {
	return currentMoy;
}

public void setCurrentMoy(double currentMoy) {
	this.currentMoy = currentMoy;
}

public double getCurrentEt() {
	return currentEt;
}

public void setCurrentEt(double currentEt) {
	this.currentEt = currentEt;
}

public double getCurrentInst() {
	return currentInst;
}

public void setCurrentInst(double currentInst) {
	this.currentInst = currentInst;
}

public double getPhaseMax() {
	return phaseMax;
}

public void setPhaseMax(double phaseMax) {
	this.phaseMax = phaseMax;
}

public double getPhaseMin() {
	return phaseMin;
}

public void setPhaseMin(double phaseMin) {
	this.phaseMin = phaseMin;
}

public double getPhaseMoy() {
	return phaseMoy;
}

public void setPhaseMoy(double phaseMoy) {
	this.phaseMoy = phaseMoy;
}

public double getPhaseEt() {
	return phaseEt;
}

public void setPhaseEt(double phaseEt) {
	this.phaseEt = phaseEt;
}

public double getPhaseInst() {
	return phaseInst;
}

public void setPhaseInst(double phaseInst) {
	this.phaseInst = phaseInst;
}

public double getFreqMax() {
	return freqMax;
}

public void setFreqMax(double freqMax) {
	this.freqMax = freqMax;
}

public double getFreqMin() {
	return freqMin;
}

public void setFreqMin(double freqMin) {
	this.freqMin = freqMin;
}

public double getFreqMoy() {
	return freqMoy;
}

public void setFreqMoy(double freqMoy) {
	this.freqMoy = freqMoy;
}

public double getFreqEt() {
	return freqEt;
}

public void setFreqEt(double freqEt) {
	this.freqEt = freqEt;
}

public double getFreqInst() {
	return freqInst;
}

public void setFreqInst(double freqInst) {
	this.freqInst = freqInst;
}

public double getEnergyAct() {
	return energyAct;
}

public void setEnergyAct(double energyAct) {
	this.energyAct = energyAct;
}

public double getEnergyReact() {
	return energyReact;
}

public void setEnergyReact(double energyReact) {
	this.energyReact = energyReact;
}

public double getEnergyFund() {
	return energyFund;
}

public void setEnergyFund(double energyFund) {
	this.energyFund = energyFund;
}

public double getEnergyApp() {
	return energyApp;
}

public void setEnergyApp(double energyApp) {
	this.energyApp = energyApp;
}

public int getPower() {
	return power;
}

public void setPower(int power) {
	this.power = power;
}

public int getFlags() {
	return flags;
}

public void setFlags(int flags) {
	this.flags = flags;
}

public int getAddedFlag() {
	return addedFlag;
}

public void setAddedFlag(int addedFlag) {
	this.addedFlag = addedFlag;
}

public int getAddedInfo() {
	return addedInfo;
}

public void setAddedInfo(int addedInfo) {
	this.addedInfo = addedInfo;
}

public double getThd() {
	return thd;
}

public void setThd(double thd) {
	this.thd = thd;
}

public Integer getIntVal1() {
	return intVal1;
}

public void setIntVal1(Integer intVal1) {
	this.intVal1 = intVal1;
}

public Integer getIntVal2() {
	return intVal2;
}

public void setIntVal2(Integer intVal2) {
	this.intVal2 = intVal2;
}

public Integer getIntVal3() {
	return intVal3;
}

public void setIntVal3(Integer intVal3) {
	this.intVal3 = intVal3;
}

public Double getDoubleVal1() {
	return doubleVal1;
}

public void setDoubleVal1(Double doubleVal1) {
	this.doubleVal1 = doubleVal1;
}

public Double getDoubleVal2() {
	return doubleVal2;
}

public void setDoubleVal2(Double doubleVal2) {
	this.doubleVal2 = doubleVal2;
}

public Double getDoubleVal3() {
	return doubleVal3;
}

public void setDoubleVal3(Double doubleVal3) {
	this.doubleVal3 = doubleVal3;
}

public double getPowerMax() {
	return powerMax;
}

public void setPowerMax(double powerMax) {
	this.powerMax = powerMax;
}

public double getPowerMin() {
	return powerMin;
}

public void setPowerMin(double powerMin) {
	this.powerMin = powerMin;
}

public double getPowerMoy() {
	return powerMoy;
}

public void setPowerMoy(double powerMoy) {
	this.powerMoy = powerMoy;
}

public double getPowerEt() {
	return powerEt;
}

public void setPowerEt(double powerEt) {
	this.powerEt = powerEt;
}

public double getPowerInst() {
	return powerInst;
}

public void setPowerInst(double powerInst) {
	this.powerInst = powerInst;
}
   
   

}
