package rimenergyapi.dto;

import java.util.List;

public class GroupsInfoDto {
	private int id;
    private int nbPhase;
    private byte enabled;
    private String name;
    private String color;
    private int tenantId;
    private String type;
    private int categoryId;
    private Integer threshold;
    private Integer thresholdDay;
    private Integer thresholdNight;
    private Integer thresholdWeek;
    private Integer thresholdWeekDays;
    private Integer thresholdWeekend;
    private Integer thresholdInstCuurent;
    private Integer x;
    private Integer y;
    private Integer z;
    private Integer hauteur;
    private Integer largeur;
    private List<PhaseInfoDto> phases; 
    private List<GroupsInfoDto> children;
    
    private int deviceId;
    private int parentId;
    private int installationId;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getNbPhase() {
		return nbPhase;
	}
	public void setNbPhase(int nbPhase) {
		this.nbPhase = nbPhase;
	}
	public byte getEnabled() {
		return enabled;
	}
	public void setEnabled(byte enabled) {
		this.enabled = enabled;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public int getTenantId() {
		return tenantId;
	}
	public void setTenantId(int tenantId) {
		this.tenantId = tenantId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public Integer getThreshold() {
		return threshold;
	}
	public void setThreshold(Integer threshold) {
		this.threshold = threshold;
	}
	public Integer getThresholdDay() {
		return thresholdDay;
	}
	public void setThresholdDay(Integer thresholdDay) {
		this.thresholdDay = thresholdDay;
	}
	public Integer getThresholdNight() {
		return thresholdNight;
	}
	public void setThresholdNight(Integer thresholdNight) {
		this.thresholdNight = thresholdNight;
	}
	public Integer getThresholdWeek() {
		return thresholdWeek;
	}
	public void setThresholdWeek(Integer thresholdWeek) {
		this.thresholdWeek = thresholdWeek;
	}
	public Integer getThresholdWeekDays() {
		return thresholdWeekDays;
	}
	public void setThresholdWeekDays(Integer thresholdWeekDays) {
		this.thresholdWeekDays = thresholdWeekDays;
	}
	public Integer getThresholdWeekend() {
		return thresholdWeekend;
	}
	public void setThresholdWeekend(Integer thresholdWeekend) {
		this.thresholdWeekend = thresholdWeekend;
	}
	public Integer getThresholdInstCuurent() {
		return thresholdInstCuurent;
	}
	public void setThresholdInstCuurent(Integer thresholdInstCuurent) {
		this.thresholdInstCuurent = thresholdInstCuurent;
	}
	public Integer getX() {
		return x;
	}
	public void setX(Integer x) {
		this.x = x;
	}
	public Integer getY() {
		return y;
	}
	public void setY(Integer y) {
		this.y = y;
	}
	public Integer getZ() {
		return z;
	}
	public void setZ(Integer z) {
		this.z = z;
	}
	public Integer getHauteur() {
		return hauteur;
	}
	public void setHauteur(Integer hauteur) {
		this.hauteur = hauteur;
	}
	public Integer getLargeur() {
		return largeur;
	}
	public void setLargeur(Integer largeur) {
		this.largeur = largeur;
	}
	public List<PhaseInfoDto> getPhases() {
		return phases;
	}
	public void setPhases(List<PhaseInfoDto> phases) {
		this.phases = phases;
	}
	public List<GroupsInfoDto> getChildren() {
		return children;
	}
	public void setChildren(List<GroupsInfoDto> children) {
		this.children = children;
	}
    public int getDeviceId() {
		return deviceId;
	}
    public void setDeviceId(int deviceId) {
		this.deviceId = deviceId;
	}
    public int getParentId() {
		return parentId;
	}
    public void setParentId(int parentId) {
		this.parentId = parentId;
	}
    public int getInstallationId() {
		return installationId;
	}
    public void setInstallationId(int installationId) {
		this.installationId = installationId;
	}
}
