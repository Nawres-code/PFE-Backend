package rimenergyapi.entity.Type;

import java.util.Optional;

public enum UserAlertType {
	POWER("POWER"), ENERGY("ENERGY"), DISCONNECTION("DISCONNECTION"), POWERFACTORY("POWERFACTORY"), AMPERAGE("AMPERAGE")
	, VOLTAGE("VOLTAGE"), VARIATION ("VARIATION"), TEMPERATURE_THRESHOLD ("TEMPERATURE_THRESHOLD"), SIMPLE("simple"), SIMPLE_VAL("simple_val")
	, DEPHASAGE("DEPHASAGE"), GAZ("GAZ"), EAU("EAU"), CALORIFIQUE("CALORIFIQUE");

	private String value;

	private UserAlertType(String state) {
		this.value = state;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public static Optional<UserAlertType> fromValue(String value) {
		if (value == null) {
			return Optional.ofNullable(null);
		}
		switch (value) {
		case "POWER":
			return Optional.of(POWER);
		case "ENERGY":
			return Optional.of(ENERGY);
		case "DISCONNECTION":
			return Optional.of(DISCONNECTION);
		case "POWERFACTORY":
			return Optional.of(POWERFACTORY);
		case "AMPERAGE":
			return Optional.of(AMPERAGE);
		case "VOLTAGE":
			return Optional.of(VOLTAGE);
		case "VARIATION":
			return Optional.of(VARIATION);
		case "TEMPERATURE_THRESHOLD":
			return Optional.of(TEMPERATURE_THRESHOLD);
		case "simple":
			return Optional.of(SIMPLE);
		case "SIMPLE_VAL":
			return Optional.of(SIMPLE_VAL);
		case "DEPHASAGE":
			return Optional.of(DEPHASAGE);
		case "GAZ":
			return Optional.of(GAZ);
		case "EAU":
			return Optional.of(EAU);
		case "CALORIFIQUE":
				return Optional.of(CALORIFIQUE);
		
		}
		
		return Optional.ofNullable(null);
	}

}
