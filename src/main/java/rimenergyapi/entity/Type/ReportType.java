package rimenergyapi.entity.Type;

public enum ReportType {
	SINGLEINSTALLATION("single_installation"), COMPARASION("comparasion"), BYZONE("by_zone"), ISSAT_GROUP("ISSAT_GROUP"), ISSAT_GLOBAL("ISSAT_GLOBAL");
	
	private String value;

	private ReportType(String state) {
		this.value = state;
	}

	public static ReportType fromValue(String x) {
		if (x == null) {
			return null;
		}
		switch (x) {
		case "single_installation":
			return SINGLEINSTALLATION;
		case "comparasion":
			return COMPARASION;
		case "by_zone":
			return BYZONE;
		case"ISSAT_GROUP":
			return ISSAT_GROUP;
		case"ISSAT_GLOBAL":
			return ISSAT_GLOBAL;
		default:
			return null;
		}
	}
	
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
