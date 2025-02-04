package rimenergyapi.entity.Type;

import java.util.Optional;

public enum IOType {
	GAZ("GAZ"), EAU("EAU") , CALORIFIQUE("CALORIFIQUE");
	
	private String value;

	private IOType(String state) {
		this.value = state;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public static Optional<IOType> fromValue(String value) {
		try {
			
			switch (value) {
				case "GAZ":
					return Optional.of(GAZ);
				case "EAU":
					return Optional.of(EAU);
				case "CALORIFIQUE":
					return Optional.of(CALORIFIQUE);
			}
			
		} catch (Exception e) { }
		
		return Optional.ofNullable(null);
	}
}
