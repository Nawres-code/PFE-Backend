package rimenergyapi.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;

import org.hibernate.SQLQuery;
import org.hibernate.internal.TypeLocatorImpl;
import org.hibernate.transform.Transformers;
import org.hibernate.type.EnumType;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StringType;
import org.hibernate.type.Type;
import org.hibernate.type.TypeResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import rimenergyapi.dto.AlertConfigurationInfoDTO;

@Repository
public class AlertConfigurationRepository{
	@Autowired
	@Qualifier("tricityEntityManager")
	private EntityManager em;
	
	@Autowired
	@Qualifier("tenantEntityManager")
	private EntityManager em_1;
	
	public Set<AlertConfigurationInfoDTO> getAllUserAlertId(int userAlertId){
		Properties params = new Properties();
		params.put("enumClass", "rimenergyapi.entity.Type.AlertConfigurationType");
		params.put("type", "12"); /*type 12 instructs to use the String representation of enum value*/
		//If you are using Hibernate 5.x then try:
		params.put("useNamed", true);
		Type myEnumType = new TypeLocatorImpl(new TypeResolver()).custom(EnumType.class, params);
		List<AlertConfigurationInfoDTO> list =new ArrayList<AlertConfigurationInfoDTO>();
		list=em.createNativeQuery("SELECT id," + 
				"    operator," + 
				"	value1," + 
				"    value2," + 
				"    type" + 
				" FROM" + 
				"    alertconfiguration_1" + 
				" WHERE alert_id="+userAlertId).unwrap(SQLQuery.class)
		.addScalar("type", myEnumType)
		.addScalar("id", new IntegerType())
		.addScalar("operator", new StringType())
		.addScalar("value1", new StringType())
		.addScalar("value2", new StringType())
		.setResultTransformer(Transformers.aliasToBean(AlertConfigurationInfoDTO.class))
		.getResultList();
		return list.stream().collect(Collectors.toSet());
	}
	
	public void removeCalcAlertById(int calcAlertId) {
		em_1.createNativeQuery("delete from calc_alert where alert_id = "+calcAlertId).executeUpdate();
	}
}
