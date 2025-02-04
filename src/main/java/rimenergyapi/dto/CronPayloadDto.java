package rimenergyapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class CronPayloadDto {

	private DaysDto days;
//	private HourSection from;
//	private HourSection to;

	private boolean night;
	private boolean morning;

	public DaysDto getDays() {
		return days;
	}

	public void setDays(DaysDto days) {
		this.days = days;
	}

//	public HourSection getFrom() {
//		return from;
//	}
//
//	public void setFrom(HourSection from) {
//		this.from = from;
//	}
//
//	public HourSection getTo() {
//		return to;
//	}
//
//	public void setTo(HourSection to) {
//		this.to = to;
//	}

	public boolean isNight() {
		return night;
	}

	public void setNight(boolean night) {
		this.night = night;
	}

	public boolean isMorning() {
		return morning;
	}

	public void setMorning(boolean morning) {
		this.morning = morning;
	}

	@JsonIgnore
	public boolean always() {
		return this.morning && this.night;
	}

}
