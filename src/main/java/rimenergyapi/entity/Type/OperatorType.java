package rimenergyapi.entity.Type;

import java.util.Optional;

public enum OperatorType {
	MIN(">="), MAX("<="), BETWEEN("Between"), NOT_BETWEEN("Not between"), IN("IN"), OUT("OUT"), EQUAL("="),
	NOT_EQUAL("!=");

	private String value;

	private OperatorType(String state) {
		this.value = state;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public static Optional<OperatorType> fromValue(String x) {
		if (x == null) {
			return null;
		}
		switch (x) {
		case ">=":
			return Optional.of(MIN);
		case "<=":
			return Optional.of(MAX);
		case "Between":
			return Optional.of(BETWEEN);
		case "Not between":
			return Optional.of(NOT_BETWEEN);
		case "IN":
			return Optional.of(IN);
		case "OUT":
			return Optional.of(OUT);
		case "=":
			return Optional.of(EQUAL);
		case "!=":
			return Optional.of(NOT_EQUAL);
		}
		return null;
	}

}
