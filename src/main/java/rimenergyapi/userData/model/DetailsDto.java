package rimenergyapi.userData.model;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

public class DetailsDto {

	private Map<Timestamp,Map<String,Double>> valeurs;
	
	
	public DetailsDto() {
		super();
		valeurs=new HashMap<>();
	}


	public Map<Timestamp, Map<String, Double>> getValeurs() {
		return valeurs;
	}


	public void setValeurs(Map<Timestamp, Map<String, Double>> valeurs) {
		this.valeurs = valeurs;
	}
	
}
