package rimenergyapi.userData.model;

public class ArchiveMetosDto {
	private int idStation;
	private int batteryVoltage;
	private double sollarPanel;
	private double windSpeed;
	private double windDirection;
	private double rainfall;
	private double airTemperature;
	private double solarRadiation;
	private int airHumidity;
	private double atmosphericPressure;
	private double vaporPressureDeficit;
	private double soilMoisture;
	private double soilTemperature;
	private double leafWetness;
	private double  conductivity;
	private double ph;
	private double waterLevel;
	private double waterTemperature;
	private double barometricPressure;
	private double orp;
	private double tds;
	private double salinity;
	private double ruggedDissolvedOxygen;
	private double turbidity;
	private double tss;
	private double ammonium;
	private double nitrate;
	private double chloride;
	private double pressure;
	public ArchiveMetosDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ArchiveMetosDto(int idStation, int batteryVoltage, double sollarPanel, double windSpeed,
			double windDirection, double rainfall, double airTemperature, double solarRadiation, int airHumidity,
			double atmosphericPressure, double vaporPressureDeficit, double soilMoisture, double soilTemperature,
			double leafWetness, double conductivity, double ph, double waterLevel, double waterTemperature,
			double barometricPressure, double orp, double tds, double salinity, double ruggedDissolvedOxygen,
			double turbidity, double tss, double ammonium, double nitrate, double chloride, double pressure) {
		super();
		this.idStation = idStation;
		this.batteryVoltage = batteryVoltage;
		this.sollarPanel = sollarPanel;
		this.windSpeed = windSpeed;
		this.windDirection = windDirection;
		this.rainfall = rainfall;
		this.airTemperature = airTemperature;
		this.solarRadiation = solarRadiation;
		this.airHumidity = airHumidity;
		this.atmosphericPressure = atmosphericPressure;
		this.vaporPressureDeficit = vaporPressureDeficit;
		this.soilMoisture = soilMoisture;
		this.soilTemperature = soilTemperature;
		this.leafWetness = leafWetness;
		this.conductivity = conductivity;
		this.ph = ph;
		this.waterLevel = waterLevel;
		this.waterTemperature = waterTemperature;
		this.barometricPressure = barometricPressure;
		this.orp = orp;
		this.tds = tds;
		this.salinity = salinity;
		this.ruggedDissolvedOxygen = ruggedDissolvedOxygen;
		this.turbidity = turbidity;
		this.tss = tss;
		this.ammonium = ammonium;
		this.nitrate = nitrate;
		this.chloride = chloride;
		this.pressure = pressure;
	}
	public int getIdStation() {
		return idStation;
	}
	public void setIdStation(int idStation) {
		this.idStation = idStation;
	}
	public int getBatteryVoltage() {
		return batteryVoltage;
	}
	public void setBatteryVoltage(int batteryVoltage) {
		this.batteryVoltage = batteryVoltage;
	}
	public double getSollarPanel() {
		return sollarPanel;
	}
	public void setSollarPanel(double sollarPanel) {
		this.sollarPanel = sollarPanel;
	}
	public double getWindSpeed() {
		return windSpeed;
	}
	public void setWindSpeed(double windSpeed) {
		this.windSpeed = windSpeed;
	}
	public double getWindDirection() {
		return windDirection;
	}
	public void setWindDirection(double windDirection) {
		this.windDirection = windDirection;
	}
	public double getRainfall() {
		return rainfall;
	}
	public void setRainfall(double rainfall) {
		this.rainfall = rainfall;
	}
	public double getAirTemperature() {
		return airTemperature;
	}
	public void setAirTemperature(double airTemperature) {
		this.airTemperature = airTemperature;
	}
	public double getSolarRadiation() {
		return solarRadiation;
	}
	public void setSolarRadiation(double solarRadiation) {
		this.solarRadiation = solarRadiation;
	}
	public int getAirHumidity() {
		return airHumidity;
	}
	public void setAirHumidity(int airHumidity) {
		this.airHumidity = airHumidity;
	}
	public double getAtmosphericPressure() {
		return atmosphericPressure;
	}
	public void setAtmosphericPressure(double atmosphericPressure) {
		this.atmosphericPressure = atmosphericPressure;
	}
	public double getVaporPressureDeficit() {
		return vaporPressureDeficit;
	}
	public void setVaporPressureDeficit(double vaporPressureDeficit) {
		this.vaporPressureDeficit = vaporPressureDeficit;
	}
	public double getSoilMoisture() {
		return soilMoisture;
	}
	public void setSoilMoisture(double soilMoisture) {
		this.soilMoisture = soilMoisture;
	}
	public double getSoilTemperature() {
		return soilTemperature;
	}
	public void setSoilTemperature(double soilTemperature) {
		this.soilTemperature = soilTemperature;
	}
	public double getLeafWetness() {
		return leafWetness;
	}
	public void setLeafWetness(double leafWetness) {
		this.leafWetness = leafWetness;
	}
	public double getConductivity() {
		return conductivity;
	}
	public void setConductivity(double conductivity) {
		this.conductivity = conductivity;
	}
	public double getPh() {
		return ph;
	}
	public void setPh(double ph) {
		this.ph = ph;
	}
	public double getWaterLevel() {
		return waterLevel;
	}
	public void setWaterLevel(double waterLevel) {
		this.waterLevel = waterLevel;
	}
	public double getWaterTemperature() {
		return waterTemperature;
	}
	public void setWaterTemperature(double waterTemperature) {
		this.waterTemperature = waterTemperature;
	}
	public double getBarometricPressure() {
		return barometricPressure;
	}
	public void setBarometricPressure(double barometricPressure) {
		this.barometricPressure = barometricPressure;
	}
	public double getOrp() {
		return orp;
	}
	public void setOrp(double orp) {
		this.orp = orp;
	}
	public double getTds() {
		return tds;
	}
	public void setTds(double tds) {
		this.tds = tds;
	}
	public double getSalinity() {
		return salinity;
	}
	public void setSalinity(double salinity) {
		this.salinity = salinity;
	}
	public double getRuggedDissolvedOxygen() {
		return ruggedDissolvedOxygen;
	}
	public void setRuggedDissolvedOxygen(double ruggedDissolvedOxygen) {
		this.ruggedDissolvedOxygen = ruggedDissolvedOxygen;
	}
	public double getTurbidity() {
		return turbidity;
	}
	public void setTurbidity(double turbidity) {
		this.turbidity = turbidity;
	}
	public double getTss() {
		return tss;
	}
	public void setTss(double tss) {
		this.tss = tss;
	}
	public double getAmmonium() {
		return ammonium;
	}
	public void setAmmonium(double ammonium) {
		this.ammonium = ammonium;
	}
	public double getNitrate() {
		return nitrate;
	}
	public void setNitrate(double nitrate) {
		this.nitrate = nitrate;
	}
	public double getChloride() {
		return chloride;
	}
	public void setChloride(double chloride) {
		this.chloride = chloride;
	}
	public double getPressure() {
		return pressure;
	}
	public void setPressure(double pressure) {
		this.pressure = pressure;
	}
	
	
}
