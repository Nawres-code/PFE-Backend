package rimenergyapi.dto;

import java.util.List;

import rimenergyapi.entity.Type.ReportType;

public class ReportPayloadDto {
	
	private ReportType type;
	private List<Integer> idsRange;
	
	public ReportPayloadDto() {
		super();
	}
	
	public ReportPayloadDto(ReportType type, List<Integer> idsRange) {
		super();
		this.setType(type);
		this.idsRange = idsRange;
	}
	
	public List<Integer> getIdsRange() {
		return idsRange;
	}
	
	public void setIdsRange(List<Integer> idsRange) {
		this.idsRange = idsRange;
	}

	public ReportType getType() {
		return type;
	}

	public void setType(ReportType type) {
		this.type = type;
	}
	
}
