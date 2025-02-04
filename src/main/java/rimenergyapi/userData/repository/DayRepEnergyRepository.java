package rimenergyapi.userData.repository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.google.common.primitives.Ints;

import rimenergyapi.userData.model.RecordDto;
@Repository
public class DayRepEnergyRepository {
	@Autowired
	@Qualifier("tenantEntityManager")
	private EntityManager em;
	
	@Value("${energydivise}")
	String energydivise;
	
	public List<RecordDto> findAllDayRepEnergyByZone( String grouping, boolean dayBefore) {
		String query = "";
		String whereClause = "";
		
		switch(grouping) {
			case "thisday":
				if(!dayBefore) {
					whereClause =  " WHERE rep.time >= date(now()) and rep.time < date_add( date(now()),interval 1 day)";
					query = "SELECT i.zone_id as groupId, sum(rep.act_energy_tot / " + energydivise + ") as sumAct, ANY_VALUE(rep.time) as time" 
							+ " FROM  rep_energy_day rep"
							+ " INNER JOIN groups_phase g_ph ON g_ph.phase_id = rep.phase_id"
							+ " INNER JOIN groups g ON g.id = g_ph.groups_id  and g.type=\"General\""
							+ " INNER JOIN installation i ON i.id = g.installation_id and i.nature like 'consumption'" 
							+ whereClause
							+ " GROUP BY  i.zone_id";
				} else {
					whereClause = " WHERE rep.time >= date_add( date(now()),interval -1 day) and rep.time <  CURRENT_timestamp - INTERVAL 1 DAY";	
					query = "SELECT i.zone_id as groupId, sum(rep.act_energy_tot / " + energydivise + ") as sumAct, ANY_VALUE(rep.time) as time" 
							+ " FROM  rep_energy_hour rep"
							+ " INNER JOIN groups_phase g_ph ON g_ph.phase_id = rep.phase_id"
							+ " INNER JOIN groups g ON g.id = g_ph.groups_id  and g.type=\"General\""
							+ " INNER JOIN installation i ON i.id = g.installation_id and i.nature like 'consumption'" 
							+ whereClause
							+ " GROUP BY  i.zone_id";
				}
			break;
			case "lastday":
				if(!dayBefore) {
					whereClause = " WHERE rep.time >= date_add( date(now()),interval -1 day) and rep.time < CURRENT_timestamp - INTERVAL 1 DAY ";
					query = "SELECT i.zone_id as groupId, sum(rep.act_energy_tot / " + energydivise + ") as sumAct, ANY_VALUE(rep.time) as time" 
							+ " FROM  rep_energy_5min rep"
							+ " INNER JOIN groups_phase g_ph ON g_ph.phase_id = rep.phase_id"
							+ " INNER JOIN groups g ON g.id = g_ph.groups_id  and g.type=\"General\""
							+ " INNER JOIN installation i ON i.id = g.installation_id and i.nature like 'consumption'" 
							+ whereClause
							+ " GROUP BY  i.zone_id";
				} else {
					whereClause = " WHERE rep.time >= date_add( date(now()),interval -2 day) and rep.time < CURRENT_timestamp - INTERVAL 2 DAY ";
					query = "SELECT i.zone_id as groupId, sum(rep.act_energy_tot / " + energydivise + ") as sumAct, ANY_VALUE(rep.time) as time" 
							+ " FROM  rep_energy_5min rep"
							+ " INNER JOIN groups_phase g_ph ON g_ph.phase_id = rep.phase_id"
							+ " INNER JOIN groups g ON g.id = g_ph.groups_id  and g.type=\"General\""
							+ " INNER JOIN installation i ON i.id = g.installation_id and i.nature like 'consumption'" 
							+ whereClause
							+ " GROUP BY  i.zone_id";
				}
				
			break;
		}
		try {
			return em.createNativeQuery(query).unwrap(SQLQuery.class)
					.setResultTransformer(Transformers.aliasToBean(RecordDto.class)).getResultList();
		} catch (Exception e) { 
			return new ArrayList<RecordDto>();
		}
		
	}
	
	public List<RecordDto> findAllDayRepEnergyByInstallation( String grouping, boolean dayBefore) {
		String query="";
		String whereClause = "";
		switch(grouping) {
			case "thisday":
				if(!dayBefore) {
					whereClause = " WHERE rep.time >= date(now()) and rep.time < date_add( date(now()),interval 1 day)";
					query = "SELECT g.installation_id as groupId, sum(rep.act_energy_tot / " + energydivise + ") as sumAct, ANY_VALUE(rep.time) as time" 
							+ " FROM  rep_energy_day rep"
							+ " INNER JOIN groups_phase g_ph ON g_ph.phase_id = rep.phase_id"
							+ " INNER JOIN groups g ON g.id = g_ph.groups_id  and g.type=\"General\""
							+ " INNER JOIN installation i ON i.id = g.installation_id and i.nature like 'consumption'" 
							+ whereClause
							+ " GROUP BY  g.installation_id";
				} else {
					whereClause = " WHERE rep.time >= date_add( date(now()),interval -1 day) and rep.time <  CURRENT_timestamp - INTERVAL 1 DAY";
					query = "SELECT g.installation_id as groupId, sum(rep.act_energy_tot / " + energydivise + ") as sumAct, ANY_VALUE(rep.time) as time" 
							+ " FROM  rep_energy_5min rep"
							+ " INNER JOIN groups_phase g_ph ON g_ph.phase_id = rep.phase_id"
							+ " INNER JOIN groups g ON g.id = g_ph.groups_id  and g.type=\"General\""
							+ " INNER JOIN installation i ON i.id = g.installation_id and i.nature like 'consumption'" 
							+ whereClause
							+ " GROUP BY  g.installation_id";
				}
				
			break;
			case "lastday":
				if(!dayBefore) {
					whereClause = " WHERE rep.time >= date_add( date(now()),interval -1 day) and rep.time < CURRENT_timestamp - INTERVAL 1 DAY ";
					query = "SELECT g.installation_id as groupId, sum(rep.act_energy_tot / " + energydivise + ") as sumAct, ANY_VALUE(rep.time) as time" 
							+ " FROM  rep_energy_5min rep"
							+ " INNER JOIN groups_phase g_ph ON g_ph.phase_id = rep.phase_id"
							+ " INNER JOIN groups g ON g.id = g_ph.groups_id  and g.type=\"General\""
							+ " INNER JOIN installation i ON i.id = g.installation_id and i.nature like 'consumption'" 
							+ whereClause
							+ " GROUP BY  g.installation_id";
				} else { 
					whereClause = " WHERE rep.time >= date_add( date(now()),interval -2 day) and rep.time < CURRENT_timestamp - INTERVAL 2 DAY ";	
					query = "SELECT g.installation_id as groupId, sum(rep.act_energy_tot / " + energydivise + ") as sumAct, ANY_VALUE(rep.time) as time" 
							+ " FROM  rep_energy_5min rep"
							+ " INNER JOIN groups_phase g_ph ON g_ph.phase_id = rep.phase_id"
							+ " INNER JOIN groups g ON g.id = g_ph.groups_id  and g.type=\"General\""
							+ " INNER JOIN installation i ON i.id = g.installation_id and i.nature like 'consumption'" 
							+ whereClause
							+ " GROUP BY  g.installation_id";
				}
			break;
		}
		
		
		try {
			return em.createNativeQuery(query).unwrap(SQLQuery.class)
					.setResultTransformer(Transformers.aliasToBean(RecordDto.class)).getResultList();
		} catch (Exception e) { 
			return new ArrayList<RecordDto>();
		}
	}
	
	private String timecolumnExpression(String timeBack, String timeSelectColumn)
	{
		switch (timeBack) {
		case "day":
			return "  DATE_ADD(ANY_VALUE(rep.time), INTERVAL -1 day) as time";
		case "week":
			return "  DATE_ADD(ANY_VALUE(rep.time), INTERVAL -1 week) as time";
		case "month":
			return "  DATE_ADD(ANY_VALUE(rep.time), INTERVAL -1 month) as time";
		}
		return timeSelectColumn;
	}
	
	private String timeWhereExpression(String timeBack, String timeWhereClause, Timestamp startDate, Timestamp endDate)
	{
		switch (timeBack) {
		case "day":
			return " AND rep.time BETWEEN DATE_ADD('" + startDate + "', INTERVAL -1 day) AND DATE_ADD('" + endDate+"', INTERVAL -1 day)";
		case "week":
			return " AND rep.time BETWEEN DATE_ADD('" + startDate + "', INTERVAL -1 week) AND DATE_ADD('" + endDate+"', INTERVAL -1 week)";
		case "month":
			return " AND rep.time BETWEEN DATE_ADD('" + startDate + "', INTERVAL -1 month) AND DATE_ADD('" + endDate+"', INTERVAL -1 month)";
		}
		return timeWhereClause;
	}
}
