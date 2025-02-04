package rimenergyapi.userData.model;


import java.util.HashMap;
import java.util.Map;

public class CategoryRtDto {

	private double eAct,iPower;
	private Map<Integer,GroupRtDto> groupsRtDto;
	public CategoryRtDto(double eAct,double iPower) {
		super();
		this.eAct = eAct;
		this.iPower=iPower;
		this.groupsRtDto=new HashMap<>();
	}
	public double geteAct() {
		return eAct;
	}
	public void seteAct(double eAct) {
		this.eAct = eAct;
	}
	public Map<Integer, GroupRtDto> getGroupsRtDto() {
		return groupsRtDto;
	}
	public void setGroupsRtDto(Map<Integer, GroupRtDto> groupsRtDto) {
		this.groupsRtDto = groupsRtDto;
	}
	public double getiPower() {
		return iPower;
	}
	public void setiPower(double iPower) {
		this.iPower = iPower;
	}
}
