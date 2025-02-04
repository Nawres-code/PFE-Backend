package rimenergyapi.entity.Type;

public enum InstallationType {

	provider("provider"), consumption("consumption");
	
	private String value;

	InstallationType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public static InstallationType fromValue(String x) {
		if (x == null) {
			return null;
		}
		switch (x) {
		case "provider":
			return provider;
		case "consumption":
			return consumption;
		}
		return null;
	}
}
