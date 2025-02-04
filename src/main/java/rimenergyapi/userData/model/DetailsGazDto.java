package rimenergyapi.userData.model;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

public class DetailsGazDto {
private Map<Timestamp,Map<String,Integer>> valeurs;
	
	
	public DetailsGazDto() {
		super();
		valeurs=new HashMap<>();
		
	}


	public Map<Timestamp, Map<String, Integer>> getValeurs() {
		return valeurs;
	}


	public void setValeurs(Map<Timestamp, Map<String, Integer>> valeurs) {
		this.valeurs = valeurs;
	}
	
}
