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
public class HourRepEnergyRepository {
	@Autowired
	@Qualifier("tenantEntityManager")
	private EntityManager em;
	
	@Value("${energydivise}")
	String energydivise;
	
	public List<RecordDto> findAllHourRepEnergyByZoneRange(int[] zonesIds, Timestamp startDate, Timestamp endDate, String timeBack) {
		String timeSelectColumn = " ANY_VALUE(rep.time) as time";
		String timeWhereClause = " AND rep.time BETWEEN '" + startDate + "' AND '" + endDate+"'";
		if(timeBack != null) {
			timeSelectColumn = timecolumnExpression(timeBack, timeSelectColumn);
			timeWhereClause = timeWhereExpression(timeBack, timeWhereClause, startDate, endDate);
		}
		String query = "SELECT i.zone_id as groupId, sum(rep.act_energy_tot / " + energydivise + ") as sumAct," + timeSelectColumn
				+ " FROM  rep_energy_hour rep"
				+ " INNER JOIN groups_phase g_ph ON g_ph.phase_id = rep.phase_id"
				+ " INNER JOIN groups g ON g.id = g_ph.groups_id  and g.type=\"General\""
				+ " INNER JOIN installation i ON i.id = g.installation_id" 
				+ " WHERE i.zone_id IN (" + Ints.join(", ", zonesIds) + ")" 
				+ timeWhereClause
				+ " GROUP BY  i.zone_id, rep.time";
		return em.createNativeQuery(query).unwrap(SQLQuery.class)
				.setResultTransformer(Transformers.aliasToBean(RecordDto.class)).getResultList();
	}
	
	public List<RecordDto> findAllHourRepEnergyByInstallationRange(int[] installationsIds, Timestamp startDate, Timestamp endDate, String timeBack) {
		String timeSelectColumn = " ANY_VALUE(rep.time) as time";
		String timeWhereClause = " AND rep.time BETWEEN '" + startDate + "' AND '" + endDate+"'";
		if(timeBack != null) {
			timeSelectColumn = timecolumnExpression(timeBack, timeSelectColumn);
			timeWhereClause = timeWhereExpression(timeBack, timeWhereClause, startDate, endDate);
		}
		String query = "SELECT g.installation_id as groupId, sum(rep.act_energy_tot / " + energydivise +") as sumAct," + timeSelectColumn
				+ " FROM  rep_energy_hour rep"
				+ " INNER JOIN groups_phase g_ph ON g_ph.phase_id = rep.phase_id"
				+ " INNER JOIN groups g ON g.id = g_ph.groups_id  and g.type=\"General\""
				+ " WHERE g.installation_id IN (" + Ints.join(", ", installationsIds) + ")" 
				+ timeWhereClause
				+ " GROUP BY  g.installation_id, rep.time";
		return em.createNativeQuery(query).unwrap(SQLQuery.class)
				.setResultTransformer(Transformers.aliasToBean(RecordDto.class)).getResultList();
	}
	
	public List<RecordDto> findAllHourRepEnergyByGroupRange(int[] groupsIds, Timestamp startDate, Timestamp endDate, String timeBack) {
		String timeSelectColumn = " ANY_VALUE(rep.time) as time";
		String timeWhereClause = " AND rep.time BETWEEN '" + startDate + "' AND '" + endDate+"'";
		if(timeBack != null) {
			timeSelectColumn = timecolumnExpression(timeBack, timeSelectColumn);
			timeWhereClause = timeWhereExpression(timeBack, timeWhereClause, startDate, endDate);
		}
		String query = "SELECT g_ph.groups_id as groupId, sum(rep.act_energy_tot / " + energydivise + ") as sumAct," + timeSelectColumn
				+ " FROM  rep_energy_hour rep"
				+ " INNER JOIN groups_phase g_ph ON g_ph.phase_id = rep.phase_id"
				+ " INNER JOIN groups g ON g.id = g_ph.groups_id  and g.type=\"General\""
				+ " WHERE g_ph.groups_id IN (" + Ints.join(", ", groupsIds) + ")" 
				+ timeWhereClause
				+ " GROUP BY  g_ph.groups_id, rep.time";
		return em.createNativeQuery(query).unwrap(SQLQuery.class)
				.setResultTransformer(Transformers.aliasToBean(RecordDto.class)).getResultList();
	}
	
	public List<RecordDto> findAllDayRepEnergyByZoneRange(int[] zonesIds, Timestamp startDate, Timestamp endDate, String timeBack) {
		String timeSelectColumn = " ANY_VALUE(rep.time) as time";
		String timeWhereClause = " AND rep.time BETWEEN '" + startDate + "' AND '" + endDate+"'";
		if(timeBack != null) {
			timeSelectColumn = timecolumnExpression(timeBack, timeSelectColumn);
			timeWhereClause = timeWhereExpression(timeBack, timeWhereClause, startDate, endDate);
		}
		String query = "SELECT i.zone_id as groupId, sum(rep.act_energy_tot / " + energydivise + ") as sumAct," + timeSelectColumn
				+ " FROM  rep_energy_hour rep"
				+ " INNER JOIN groups_phase g_ph ON g_ph.phase_id = rep.phase_id"
				+ " INNER JOIN groups g ON g.id = g_ph.groups_id  and g.type=\"General\""
				+ " INNER JOIN installation i ON i.id = g.installation_id" 
				+ " WHERE i.zone_id IN (" + Ints.join(", ", zonesIds) + ")" 
				+ timeWhereClause
				+ " GROUP BY  i.zone_id, day(rep.time)";
		return em.createNativeQuery(query).unwrap(SQLQuery.class)
				.setResultTransformer(Transformers.aliasToBean(RecordDto.class)).getResultList();
	}
	
	public List<RecordDto> findAllDayRepEnergyByInstallationRange(int[] installationsIds, Timestamp startDate, Timestamp endDate, String timeBack) {
		String timeSelectColumn = " ANY_VALUE(rep.time) as time";
		String timeWhereClause = " AND rep.time BETWEEN '" + startDate + "' AND '" + endDate+"'";
		if(timeBack != null) {
			timeSelectColumn = timecolumnExpression(timeBack, timeSelectColumn);
			timeWhereClause = timeWhereExpression(timeBack, timeWhereClause, startDate, endDate);
		}
		String query = "SELECT g.installation_id as groupId, sum(rep.act_energy_tot / " + energydivise + ") as sumAct," + timeSelectColumn
				+ " FROM  rep_energy_hour rep"
				+ " INNER JOIN groups_phase g_ph ON g_ph.phase_id = rep.phase_id"
				+ " INNER JOIN groups g ON g.id = g_ph.groups_id  and g.type=\"General\""
				+ " WHERE g.installation_id IN (" + Ints.join(", ", installationsIds) + ")" 
				+ timeWhereClause
				+ " GROUP BY  g.installation_id, day(rep.time)";
		return em.createNativeQuery(query).unwrap(SQLQuery.class)
				.setResultTransformer(Transformers.aliasToBean(RecordDto.class)).getResultList();
	}
	
	public List<RecordDto> findAllDayRepEnergyByGroupRange(int[] groupsIds, Timestamp startDate, Timestamp endDate, String timeBack) {
		String timeSelectColumn = " ANY_VALUE(rep.time) as time";
		String timeWhereClause = " AND rep.time BETWEEN '" + startDate + "' AND '" + endDate+"'";
		if(timeBack != null) {
			timeSelectColumn = timecolumnExpression(timeBack, timeSelectColumn);
			timeWhereClause = timeWhereExpression(timeBack, timeWhereClause, startDate, endDate);
		}
		String query = "SELECT g_ph.groups_id as groupId, sum(rep.act_energy_tot / " + energydivise + ") as sumAct," + timeSelectColumn
				+ " FROM  rep_energy_hour rep"
				+ " INNER JOIN groups_phase g_ph ON g_ph.phase_id = rep.phase_id"
				+ " INNER JOIN groups g ON g.id = g_ph.groups_id  and g.type=\"General\""
				+ " WHERE g_ph.groups_id IN (" + Ints.join(", ", groupsIds) + ")" 
				+ timeWhereClause
				+ " GROUP BY  g_ph.groups_id, day(rep.time)";
		return em.createNativeQuery(query).unwrap(SQLQuery.class)
				.setResultTransformer(Transformers.aliasToBean(RecordDto.class)).getResultList();
	}
	
	public List<RecordDto> findAllHourRepEnergyByZone(Timestamp startDate, Timestamp endDate, String timeBack) {
		String timeSelectColumn = " ANY_VALUE(rep.time) as time";
		String timeWhereClause = " AND rep.time BETWEEN '" + startDate + "' AND '" + endDate+"'";
		if(timeBack != null) {
			timeSelectColumn = timecolumnExpression(timeBack, timeSelectColumn);
			timeWhereClause = timeWhereExpression(timeBack, timeWhereClause, startDate, endDate);
		}
		String query = "SELECT i.zone_id as groupId, sum(rep.act_energy_tot / " + energydivise + ") as sumAct," + timeSelectColumn
				+ " FROM  rep_energy_hour rep"
				+ " INNER JOIN groups_phase g_ph ON g_ph.phase_id = rep.phase_id"
				+ " INNER JOIN groups g ON g.id = g_ph.groups_id  and g.type=\"General\""
				+ " INNER JOIN installation i ON i.id = g.installation_id"
				+ timeWhereClause
				+ " GROUP BY  i.zone_id, rep.time";
		return em.createNativeQuery(query).unwrap(SQLQuery.class)
				.setResultTransformer(Transformers.aliasToBean(RecordDto.class)).getResultList();
	}
	
	public List<RecordDto> findAllHourRepEnergyByInstallation(Timestamp startDate, Timestamp endDate, String timeBack) {
		String timeSelectColumn = " ANY_VALUE(rep.time) as time";
		String timeWhereClause = " AND rep.time BETWEEN '" + startDate + "' AND '" + endDate+"'";
		if(timeBack != null) {
			timeSelectColumn = timecolumnExpression(timeBack, timeSelectColumn);
			timeWhereClause = timeWhereExpression(timeBack, timeWhereClause, startDate, endDate);
		}
		String query = "SELECT g.installation_id as groupId, sum(rep.act_energy_tot / " + energydivise + ") as sumAct," + timeSelectColumn
				+ " FROM  rep_energy_hour rep"
				+ " INNER JOIN groups_phase g_ph ON g_ph.phase_id = rep.phase_id"
				+ " INNER JOIN groups g ON g.id = g_ph.groups_id  and g.type=\"General\""
				+ timeWhereClause
				+ " GROUP BY  g.installation_id, rep.time";
		return em.createNativeQuery(query).unwrap(SQLQuery.class)
				.setResultTransformer(Transformers.aliasToBean(RecordDto.class)).getResultList();
	}
	
	public List<RecordDto> findAllHourRepEnergyByGroup(Timestamp startDate, Timestamp endDate, String timeBack) {
		String timeSelectColumn = " ANY_VALUE(rep.time) as time";
		String timeWhereClause = " AND rep.time BETWEEN '" + startDate + "' AND '" + endDate+"'";
		if(timeBack != null) {
			timeSelectColumn = timecolumnExpression(timeBack, timeSelectColumn);
			timeWhereClause = timeWhereExpression(timeBack, timeWhereClause, startDate, endDate);
		}
		String query = "SELECT g_ph.groups_id as groupId, sum(rep.act_energy_tot / " + energydivise + ") as sumAct," + timeSelectColumn
				+ " FROM  rep_energy_hour rep"
				+ " INNER JOIN groups_phase g_ph ON g_ph.phase_id = rep.phase_id"
				+ " INNER JOIN groups g ON g.id = g_ph.groups_id  and g.type=\"General\""
				+ timeWhereClause
				+ " GROUP BY  g_ph.groups_id, rep.time";
		return em.createNativeQuery(query).unwrap(SQLQuery.class)
				.setResultTransformer(Transformers.aliasToBean(RecordDto.class)).getResultList();
	}
	
	public List<RecordDto> findAllDayRepEnergyByZone(Timestamp startDate, Timestamp endDate, String timeBack) {
		String timeSelectColumn = " rep.time as time";
		String timeWhereClause = " AND rep.time BETWEEN '" + startDate + "' AND '" + endDate+"'";
		if(timeBack != null) {
			timeSelectColumn = timecolumnExpression(timeBack, timeSelectColumn);
			timeWhereClause = timeWhereExpression(timeBack, timeWhereClause, startDate, endDate);
		}
		String query = "SELECT i.zone_id as groupId, sum(rep.act_energy_tot / " + energydivise + ") as sumAct," + timeSelectColumn
				+ " FROM  rep_energy_hour rep"
				+ " INNER JOIN groups_phase g_ph ON g_ph.phase_id = rep.phase_id"
				+ " INNER JOIN groups g ON g.id = g_ph.groups_id  and g.type=\"General\""
				+ " INNER JOIN installation i ON i.id = g.installation_id" 
				+ timeWhereClause
				+ " GROUP BY  i.zone_id, day(rep.time)";
		return em.createNativeQuery(query).unwrap(SQLQuery.class)
				.setResultTransformer(Transformers.aliasToBean(RecordDto.class)).getResultList();
	}
	
	public List<RecordDto> findAllDayRepEnergyByInstallation(Timestamp startDate, Timestamp endDate, String timeBack) {
		String timeSelectColumn = " ANY_VALUE(rep.time) as time";
		String timeWhereClause = " AND rep.time BETWEEN '" + startDate + "' AND '" + endDate+"'";
		if(timeBack != null) {
			timeSelectColumn = timecolumnExpression(timeBack, timeSelectColumn);
			timeWhereClause = timeWhereExpression(timeBack, timeWhereClause, startDate, endDate);
		}
		String query = "SELECT g.installation_id as groupId, sum(rep.act_energy_tot / " +energydivise + ") as sumAct," + timeSelectColumn
				+ " FROM  rep_energy_hour rep"
				+ " INNER JOIN groups_phase g_ph ON g_ph.phase_id = rep.phase_id"
				+ " INNER JOIN groups g ON g.id = g_ph.groups_id  and g.type=\"General\""
				+ " JOIN installation i ON i.id = g.installation_id and i.nature=\"consumption\""
				+ timeWhereClause
				+ " GROUP BY  g.installation_id, day(rep.time)";
		return em.createNativeQuery(query).unwrap(SQLQuery.class)
				.setResultTransformer(Transformers.aliasToBean(RecordDto.class)).getResultList();
	}
	
	public List<RecordDto> findAllDayRepEnergyByGroup(Timestamp startDate, Timestamp endDate, String timeBack) {
		String timeSelectColumn = " ANY_VALUE(rep.time) as time";
		String timeWhereClause = " AND rep.time BETWEEN '" + startDate + "' AND '" + endDate+"'";
		if(timeBack != null) {
			timeSelectColumn = timecolumnExpression(timeBack, timeSelectColumn);
			timeWhereClause = timeWhereExpression(timeBack, timeWhereClause, startDate, endDate);
		}
		String query = "SELECT g_ph.groups_id as groupId, sum(rep.act_energy_tot / "+ energydivise + " ) as sumAct," + timeSelectColumn
				+ " FROM  rep_energy_hour rep"
				+ " INNER JOIN groups_phase g_ph ON g_ph.phase_id = rep.phase_id"
				+ " INNER JOIN groups g ON g.id = g_ph.groups_id  and g.type=\"General\""
				+ " JOIN installation i ON i.id = g.installation_id and i.nature=\"consumption\""
				+ timeWhereClause
				+ " GROUP BY  g_ph.groups_id, day(rep.time)";
		return em.createNativeQuery(query).unwrap(SQLQuery.class)
				.setResultTransformer(Transformers.aliasToBean(RecordDto.class)).getResultList();
	}
	
	public List<RecordDto> findAllHourRepEnergyByZone( String grouping, boolean dayBefore) {
		String query= "";
		String whereClause = "";
		switch(grouping) {
			case "lasthour":
				if(dayBefore) {
					whereClause = " WHERE rep.time >= date_add(date_format(now(),'%Y-%m-%d %H:00:00'), interval -1 day) and "
									+ " rep.time < DATE_ADD(CURRENT_TIMESTAMP, INTERVAL - 1 DAY) ";
					query = "SELECT i.zone_id as groupId, sum(rep.act_energy_tot / " + energydivise + ") as sumAct, ANY_VALUE(rep.time) as time" 
							+ " FROM  rep_energy_5min rep"
							+ " INNER JOIN groups_phase g_ph ON g_ph.phase_id = rep.phase_id"
							+ " INNER JOIN groups g ON g.id = g_ph.groups_id  and g.type=\"General\""
							+ " INNER JOIN installation i ON i.id = g.installation_id and i.nature like 'consumption'" 
							+ whereClause
							+ " GROUP BY  i.zone_id";
				} else {
					whereClause = " WHERE rep.time >= date_format(now(),'%Y-%m-%d %H:00:00') and rep.time < now()";
					query = "SELECT i.zone_id as groupId, sum(rep.act_energy_tot / " + energydivise + ") as sumAct, ANY_VALUE(rep.time) as time" 
							+ " FROM  rep_energy_hour rep"
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
	
	public List<RecordDto> findAllHourRepEnergyByInstallation( String grouping, boolean dayBefore) {
		String query="";
		String whereClause = "";
		switch(grouping) {
			case "lasthour":
				if(dayBefore) {
					whereClause = " WHERE rep.time >= date_add(date_format(now(),'%Y-%m-%d %H:00:00'), interval -1 day)"
							+ " and rep.time <  DATE_ADD(CURRENT_TIMESTAMP, INTERVAL - 1 DAY)";	
					query = "SELECT g.installation_id as groupId, sum(rep.act_energy_tot / " + energydivise + ") as sumAct, ANY_VALUE(rep.time) as time" 
							+ " FROM  rep_energy_5min rep"
							+ " INNER JOIN groups_phase g_ph ON g_ph.phase_id = rep.phase_id"
							+ " INNER JOIN groups g ON g.id = g_ph.groups_id  and g.type=\"General\""
							+ " INNER JOIN installation i ON i.id = g.installation_id and i.nature like 'consumption'" 
							+ whereClause
							+ " GROUP BY  g.installation_id";
				} else {
					whereClause = " WHERE rep.time >= date_format(now(),'%Y-%m-%d %H:00:00') and rep.time < now()";
					query = "SELECT g.installation_id as groupId, sum(rep.act_energy_tot / " + energydivise + ") as sumAct, ANY_VALUE(rep.time) as time" 
							+ " FROM  rep_energy_hour rep"
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
