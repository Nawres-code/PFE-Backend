package rimenergyapi.userData.model;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import rimenergyapi.dto.OutputRtDto;

public class InstallationRtDto {

	private Timestamp time;
	private double eAct,eReact,eApp,eFund,iPower;
	private Map<Integer,GroupRtDto> groupsRtDto;
	private Map<Integer,GroupRtMappedDto> groupsRtMappedDto;
	private Map<Integer, CategoryRtDto> eActPerCat;
	private Map<Double, CalTemperatureDto> sondesRtDto = new HashMap<Double, CalTemperatureDto>();
	private Map<Integer, CalcArchivePointDto> pointRtDto;
	private Map<Integer, OutputRtDto> outputsRt = new HashMap<Integer, OutputRtDto>();
	private boolean outputMode;
	private ProviderRtDto provider;
	private Map<Integer, InputRtDto> inputsRtDto = new HashMap<Integer, InputRtDto>();
	
	public InstallationRtDto(Timestamp time, double eAct, double eReact, double eApp, double eFund,
			Map<Integer, GroupRtDto> groupsRtDto) {
		super();
		this.time = time;
		this.eAct = eAct;
		this.eReact = eReact;
		this.eApp = eApp;
		this.eFund = eFund;
		this.groupsRtDto = groupsRtDto;
		this.sondesRtDto = new HashMap<Double, CalTemperatureDto>();
		this.eActPerCat=new HashMap<>();
		this.eActPerCat.put(4, new CategoryRtDto(0,0));//Create other categorie and initiliza it by 0
		this.groupsRtMappedDto=new HashMap<>();
	}
	public InstallationRtDto(Timestamp time, double eAct, double eReact, double eApp, double eFund) {
		super();
		this.time = time;
		this.eAct = eAct;
		this.eReact = eReact;
		this.eApp = eApp;
		this.eFund = eFund;
		this.groupsRtDto = new HashMap<Integer, GroupRtDto>();
		this.sondesRtDto = new HashMap<Double, CalTemperatureDto>();
		this.pointRtDto = new HashMap<Integer, CalcArchivePointDto>();
		this.eActPerCat=new HashMap<>();
		this.eActPerCat.put(4, new CategoryRtDto(0,0));//Create other categorie and initiliza it by 0
		this.groupsRtMappedDto=new HashMap<>();
	}
	
	public InstallationRtDto(Long time, double eAct, double eReact, double eApp, double eFund, double iPower) {
		this.time = new Timestamp(time);
		this.eAct = eAct;
		this.eReact = eReact;
		this.eApp = eApp;
		this.eFund = eFund;
		this.iPower = iPower;
		this.groupsRtDto = new HashMap<Integer, GroupRtDto>();
		this.sondesRtDto = new HashMap<Double, CalTemperatureDto>();
		this.pointRtDto = new HashMap<Integer, CalcArchivePointDto>();
		this.eActPerCat=new HashMap<>();
		this.groupsRtMappedDto=new HashMap<>();
	}
	
	public InstallationRtDto(Long time, double eAct, double eReact, double eApp, double eFund, double iPower, Map<Integer, InputRtDto> inputsRtDto,
			 Map<Double, CalTemperatureDto> sondesRtDto , Map<Integer, OutputRtDto> outputsRt, boolean outputMode) {
		this.time = new Timestamp(time);
		this.eAct = eAct;
		this.eReact = eReact;
		this.eApp = eApp;
		this.eFund = eFund;
		this.iPower = iPower;
		this.groupsRtDto = new HashMap<Integer, GroupRtDto>();
		this.sondesRtDto = new HashMap<Double, CalTemperatureDto>(sondesRtDto);
		this.pointRtDto = new HashMap<Integer, CalcArchivePointDto>();
		this.eActPerCat=new HashMap<>();
		this.groupsRtMappedDto=new HashMap<>();
		this.inputsRtDto = new HashMap<>(inputsRtDto);
		this.outputsRt = new HashMap<>(outputsRt);
		this.outputMode = outputMode;
	}
	
	public InstallationRtDto(Timestamp time, double eAct, double eReact, double eApp, double eFund,
			Map<Integer, GroupRtDto> groupsRtDto, Map<Integer, CategoryRtDto> eActPerCat,
			Map<Double, CalTemperatureDto> sondesRtDto,
			Map<Integer, CalcArchivePointDto> pointRtDto) {
		super();
		this.time = time;
		this.eAct = eAct;
		this.eReact = eReact;
		this.eApp = eApp;
		this.eFund = eFund;
		this.groupsRtDto = groupsRtDto;
		this.eActPerCat = eActPerCat;
		this.sondesRtDto = sondesRtDto;
		this.pointRtDto = pointRtDto;
		this.groupsRtMappedDto=new HashMap<>();
	}
	
	public InstallationRtDto(Long time, double eAct, double eReact, double eApp, double eFund, double iPower,
			Map<Integer, CalcArchivePointDto> pointRtDto) {
		super();
		this.time = new Timestamp(time);
		this.eAct = eAct;
		this.eReact = eReact;
		this.eApp = eApp;
		this.eFund = eFund;
		this.iPower = iPower;
		this.groupsRtDto = groupsRtDto;
		this.eActPerCat = eActPerCat;
		this.pointRtDto = pointRtDto;
		this.groupsRtMappedDto=new HashMap<>();
	}
	public InstallationRtDto(long orElse, double d, double e, double f, double g, double h,
			Map<Integer, RtInputTuple> value) {
		// TODO Auto-generated constructor stub
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
	public Map<Double, CalTemperatureDto> getSondesRtDto() {
		return sondesRtDto;
	}
	public void setSondesRtDto(Map<Double, CalTemperatureDto> sondesRtDto) {
		this.sondesRtDto = sondesRtDto;
	}
	public double getiPower() {
		return iPower;
	}
	public void setiPower(double iPower) {
		this.iPower = iPower;
	}
	public Map<Integer, CalcArchivePointDto> getPointRtDto() {
		return pointRtDto;
	}
	public void setPointRtDto(Map<Integer, CalcArchivePointDto> pointRtDto) {
		this.pointRtDto = pointRtDto;
	}
	public Map<Integer, GroupRtMappedDto> getGroupsRtMappedDto() {
		return groupsRtMappedDto;
	}
	public void setGroupsRtMappedDto(Map<Integer, GroupRtMappedDto> groupsRtMappedDto) {
		this.groupsRtMappedDto = groupsRtMappedDto;
	}
	
	public Map<Integer, OutputRtDto> getOutputsRt() {
		return outputsRt;
	}
	
	public void setOutputsRt(Map<Integer, OutputRtDto> outputsRt) {
		this.outputsRt = outputsRt;
	}
	
	public boolean isOutputMode() {
		return outputMode;
	}
	public void setOutputMode(boolean outputMode) {
		this.outputMode = outputMode;
	}
	public ProviderRtDto getProvider() {
		return provider;
	}
	public void setProvider(ProviderRtDto provider) {
		this.provider = provider;
	}
	public Map<Integer, InputRtDto> getInputsRtDto() {
		return inputsRtDto;
	}
	public void setInputsRtDto(Map<Integer, InputRtDto> inputsRtDto) {
		this.inputsRtDto = inputsRtDto;
	}
}
