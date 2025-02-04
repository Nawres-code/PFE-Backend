package rimenergyapi.dto;

import rimenergyapi.entity.Type.ReportFormat;

public class ReportDto {

	private DateIntervalDto dateInterval;
	private ReportFormat format;
	private String type;
	private int timeDiff;
	private ReportPayloadDto reportDto;

	public DateIntervalDto getDateInterval() {
		return dateInterval;
	}

	public void setDateInterval(DateIntervalDto dateInterval) {
		this.dateInterval = dateInterval;
	}

	public ReportFormat getFormat() {
		return format;
	}

	public void setFormat(ReportFormat format) {
		this.format = format;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getTimeDiff() {
		return timeDiff;
	}

	public void setTimeDiff(int timeDiff) {
		this.timeDiff = timeDiff;
	}

	public ReportPayloadDto getReportDto() {
		return reportDto;
	}

	public void setReportDto(ReportPayloadDto reportDto) {
		this.reportDto = reportDto;
	}

}
