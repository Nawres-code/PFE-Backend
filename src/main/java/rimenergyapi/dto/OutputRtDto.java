package rimenergyapi.dto;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

public class OutputRtDto {
	private Timestamp lastTime;
	private int id;
	private Boolean isOn;
	private Set<Integer> 
	selectedProgramsIds = new HashSet<Integer>();
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Boolean isOn() {
		return isOn;
	}
	public void setOn(Boolean isOn) {
		this.isOn = isOn;
	}
	public Set<Integer> getSelectedProgramsIds() {
		return selectedProgramsIds;
	}
	public void setSelectedProgramsIds(Set<Integer> selectedProgramsIds) {
		this.selectedProgramsIds = selectedProgramsIds;
	}
	
	public OutputRtDto(int id, Boolean isOn, Set<Integer> selectedProgramsIds) {
		this.id = id;
		this.isOn = isOn;
		this.selectedProgramsIds = selectedProgramsIds;
	}
	
	public OutputRtDto(Timestamp lastTime,int id, Boolean isOn, Set<Integer> selectedProgramsIds) {
		this.id = id;
		this.isOn = isOn;
		this.lastTime = lastTime;
		this.selectedProgramsIds = selectedProgramsIds;
	}
	
	public Timestamp getLastTime() {
		return lastTime;
	}

	public void setLastTime(Timestamp lastTime) {
		this.lastTime = lastTime;
	}

	public OutputRtDto() {}
	
	@Override
	public String toString() {
		return "OutputRtDto [id=" + id + ", isOn=" + isOn + ", selectedProgramsIds=" + selectedProgramsIds + "]";
	}
	
}
	
