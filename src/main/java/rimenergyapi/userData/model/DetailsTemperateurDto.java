package rimenergyapi.userData.model;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class DetailsTemperateurDto {

	private TreeMap<Timestamp, Map<String, Integer>> valeurs;

	public DetailsTemperateurDto() {
		super();
		valeurs = new TreeMap<>();

	}
	
	public DetailsTemperateurDto(TreeMap<Timestamp, Map<String, Integer>> map) {
		valeurs = new TreeMap<Timestamp, Map<String, Integer>>(map);
	}

	public TreeMap<Timestamp, Map<String, Integer>> getValeurs() {
		return valeurs;
	}

	public void setValeurs(TreeMap<Timestamp, Map<String, Integer>> valeurs) {
		this.valeurs = valeurs;
	}

}
