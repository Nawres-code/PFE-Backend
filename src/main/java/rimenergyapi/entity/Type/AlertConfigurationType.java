package rimenergyapi.entity.Type;

import java.util.Optional;

public enum AlertConfigurationType {
	TIME ("TIME"), WEEK_DAYS("WEEK_DAYS"), DATE("DATE"), ACTIVE_W_DAY("ACTIVE_W_DAY"), POWER_INST("POWER _INST")
	, DISCONNECTION("DISCONNECTION"), VARIATION("VARIATION"), TEMPERATURE_THRESHOLD ("TEMPERATURE_THRESHOLD"), VALUE("value");

    private String value ;

    private AlertConfigurationType(String state){
        this.value = state;
    }

    public String getValue(){
        return value;
    }

    public void setValue(String value){
        this.value = value;
    }

    public static Optional<AlertConfigurationType> fromValue(String x){
        if(x == null){
        	return Optional.ofNullable(null);
        }
        switch (x){
    	case "TIME":
			return Optional.of(TIME);
		case "WEEK_DAYS":
			return Optional.of(WEEK_DAYS);
		case "DATE":
			return Optional.of(DATE);
		case "ACTIVE_W_DAY":
			return Optional.of(ACTIVE_W_DAY);
		case "POWER_INST":
			return Optional.of(POWER_INST);
		case "DISCONNECTION":
			return Optional.of(DISCONNECTION);
		case "VARIATION":
			return Optional.of(VARIATION);
		case "TEMPERATURE_THRESHOLD":
			return Optional.of(TEMPERATURE_THRESHOLD);
		case "value":
			return Optional.of(VALUE);
		}
		return Optional.ofNullable(null);
    }
}
