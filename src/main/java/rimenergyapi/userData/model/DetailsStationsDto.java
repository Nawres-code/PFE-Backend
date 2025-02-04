package rimenergyapi.userData.model;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

public class DetailsStationsDto {
	private Map<Timestamp, Map<String, Double>> valeurs;

	public DetailsStationsDto() {
		super();
		valeurs = new HashMap<>();

	}

	public Map<Timestamp, Map<String, Double>> getValeurs() {
		return valeurs;
	}

	public void setValeurs(Map<Timestamp, Map<String, Double>> valeurs) {
		this.valeurs = valeurs;
	}
}
