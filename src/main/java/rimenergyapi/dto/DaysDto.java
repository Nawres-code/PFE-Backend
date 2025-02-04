package rimenergyapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class DaysDto {

	private boolean monday;
	private boolean thesday;
	private boolean wednesday;
	private boolean thursday;
	private boolean friday;
	private boolean saturday;
	private boolean sunday;

	public DaysDto() {
		this.monday = false;
		this.thesday = false;
		this.wednesday = false;
		this.thursday = false;
		this.friday = false;
		this.saturday = false;
		this.sunday = false;
	}

	public boolean isMonday() {
		return monday;
	}

	public void setMonday(boolean monday) {
		this.monday = monday;
	}

	public boolean isThesday() {
		return thesday;
	}

	public void setThesday(boolean thesday) {
		this.thesday = thesday;
	}

	public boolean isWednesday() {
		return wednesday;
	}

	public void setWednesday(boolean wednesday) {
		this.wednesday = wednesday;
	}

	public boolean isThursday() {
		return thursday;
	}

	public void setThursday(boolean thursday) {
		this.thursday = thursday;
	}

	public boolean isFriday() {
		return friday;
	}

	public void setFriday(boolean friday) {
		this.friday = friday;
	}

	public boolean isSaturday() {
		return saturday;
	}

	public void setSaturday(boolean saturday) {
		this.saturday = saturday;
	}

	public boolean isSunday() {
		return sunday;
	}

	public void setSunday(boolean sunday) {
		this.sunday = sunday;
	}

	@JsonIgnore
	public void loadAllDays() {
		this.monday = true;
		this.thesday = true;
		this.wednesday = true;
		this.thursday = true;
		this.friday = true;
		this.saturday = true;
		this.sunday = true;
	}

	@JsonIgnore
	public boolean isAllDaysChecked() {
		return this.monday && this.thesday && this.wednesday && this.thursday && this.friday && this.saturday
				&& this.sunday;
	}

	@JsonIgnore
	public void loadSpecificDays(String[] days) {
		for (int i = 0; i < days.length; i++) {
			switch (days[i]) {
			case "0":
				this.monday = true;
				break;
			case "1":
				this.thesday = true;
				break;
			case "2":
				this.wednesday = true;
				break;
			case "3":
				this.thursday = true;
				break;
			case "4":
				this.friday = true;
				break;
			case "5":
				this.saturday = true;
				break;
			case "6":
				this.sunday = true;
				break;

			default:
				break;
			}
		}

	}

	@JsonIgnore
	public String getDaysSectionRegEx() {
		StringBuilder regEx = new StringBuilder();
		regEx.append("[");
		if (this.monday) {
			regEx.append("0,");
		}
		if (this.thesday) {
			regEx.append("1,");
		}
		if (this.wednesday) {
			regEx.append("2,");
		}
		if (this.thursday) {
			regEx.append("3,");
		}
		if (this.friday) {
			regEx.append("4,");
		}
		if (this.saturday) {
			regEx.append("5,");
		}
		if (this.sunday) {
			regEx.append("6,");
		}
		regEx.setLength(regEx.length() - 1);
		regEx.append("]");
		return regEx.toString();
	}
}
