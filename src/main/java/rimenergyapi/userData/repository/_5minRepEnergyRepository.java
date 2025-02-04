package rimenergyapi.userData.repository;

import java.sql.Timestamp;
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
public class _5minRepEnergyRepository {
	@Autowired
	@Qualifier("tenantEntityManager")
	private EntityManager em;

	@Value("${energydivise}")
	String energydivise;
	
	public List<RecordDto> findAll5MinRepEnergyByZoneRange(int[] zonesIds, Timestamp startDate, Timestamp endDate, String timeBack) {
		String timeSelectColumn = " rep.time as time";
		String timeWhereClause = " AND rep.time BETWEEN '" + startDate + "' AND '" + endDate+"'";
		if(timeBack != null) {
			timeSelectColumn = timecolumnExpression(timeBack, timeSelectColumn);
			timeWhereClause = timeWhereExpression(timeBack, timeWhereClause, startDate, endDate);
		}
		String query = "SELECT i.zone_id as groupId, sum(rep.act_energy_tot / " + energydivise + " ) as sumAct," + timeSelectColumn
				+ " FROM  rep_energy_5min rep"
				+ " INNER JOIN groups_phase g_ph ON g_ph.phase_id = rep.phase_id"
				+ " INNER JOIN groups g ON g.id = g_ph.groups_id and g.type=\"General\""
				+ " INNER JOIN installation i ON i.id = g.installation_id" 
				+ " WHERE i.zone_id IN (" + Ints.join(", ", zonesIds) + ")" 
				+ timeWhereClause
				+ " GROUP BY  i.zone_id, rep.time";
		return em.createNativeQuery(query).unwrap(SQLQuery.class)
				.setResultTransformer(Transformers.aliasToBean(RecordDto.class)).getResultList();
	}
	
	public List<RecordDto> findAll5MinRepEnergyByInstallationRange(int[] installationsIds, Timestamp startDate, Timestamp endDate, String timeBack) {
		String timeSelectColumn = " rep.time as time";
		String timeWhereClause = " AND rep.time BETWEEN '" + startDate + "' AND '" + endDate+"'";
		if(timeBack != null) {
			timeSelectColumn = timecolumnExpression(timeBack, timeSelectColumn);
			timeWhereClause = timeWhereExpression(timeBack, timeWhereClause, startDate, endDate);
		}
		String query = "SELECT g.installation_id as groupId, sum(rep.act_energy_tot / " + energydivise + ") as sumAct," + timeSelectColumn
				+ " FROM  rep_energy_5min rep"
				+ " INNER JOIN groups_phase g_ph ON g_ph.phase_id = rep.phase_id"
				+ " INNER JOIN groups g ON g.id = g_ph.groups_id and g.type=\"General\""
				+ " WHERE g.installation_id IN (" + Ints.join(", ", installationsIds) + ")" 
				+ timeWhereClause
				+ " GROUP BY  g.installation_id, rep.time";
		return em.createNativeQuery(query).unwrap(SQLQuery.class)
				.setResultTransformer(Transformers.aliasToBean(RecordDto.class)).getResultList();
	}
	
	public List<RecordDto> findAll5MinRepEnergyByGroupRange(int[] groupsIds, Timestamp startDate, Timestamp endDate, String timeBack) {
		String timeSelectColumn = " rep.time as time";
		String timeWhereClause = " AND rep.time BETWEEN '" + startDate + "' AND '" + endDate+"'";
		if(timeBack != null) {
			timeSelectColumn = timecolumnExpression(timeBack, timeSelectColumn);
			timeWhereClause = timeWhereExpression(timeBack, timeWhereClause, startDate, endDate);
		}
		String query = "SELECT g_ph.groups_id as groupId, sum(rep.act_energy_tot /" + energydivise + ") as sumAct," + timeSelectColumn
				+ " FROM  rep_energy_5min rep"
				+ " INNER JOIN groups_phase g_ph ON g_ph.phase_id = rep.phase_id"
				+ " INNER JOIN groups g ON g.id = g_ph.groups_id and g.type=\"General\""
				+ " WHERE g_ph.groups_id IN (" + Ints.join(", ", groupsIds) + ")" 
				+ timeWhereClause
				+ " GROUP BY  g_ph.groups_id, rep.time";
		return em.createNativeQuery(query).unwrap(SQLQuery.class)
				.setResultTransformer(Transformers.aliasToBean(RecordDto.class)).getResultList();
	}
	
	public List<RecordDto> findAllHourRepEnergyByZoneRange(int[] zonesIds, Timestamp startDate, Timestamp endDate, String timeBack) {
		String timeSelectColumn = " rep.time as time";
		String timeWhereClause = " AND rep.time BETWEEN '" + startDate + "' AND '" + endDate+"'";
		if(timeBack != null) {
			timeSelectColumn = timecolumnExpression(timeBack, timeSelectColumn);
			timeWhereClause = timeWhereExpression(timeBack, timeWhereClause, startDate, endDate);
		}
		String query = "SELECT i.zone_id as groupId, sum(rep.act_energy_tot / " +energydivise+ ") as sumAct," + timeSelectColumn
				+ " FROM  rep_energy_5min rep"
				+ " INNER JOIN groups_phase g_ph ON g_ph.phase_id = rep.phase_id"
				+ " INNER JOIN groups g ON g.id = g_ph.groups_id and g.type=\"General\""
				+ " INNER JOIN installation i ON i.id = g.installation_id" 
				+ " WHERE i.zone_id IN (" + Ints.join(", ", zonesIds) + ")" 
				+ timeWhereClause
				+ " GROUP BY  i.zone_id, hour(rep.time)";
		return em.createNativeQuery(query).unwrap(SQLQuery.class)
				.setResultTransformer(Transformers.aliasToBean(RecordDto.class)).getResultList();
	}
	
	public List<RecordDto> findAllHourRepEnergyByInstallationRange(int[] installationsIds, Timestamp startDate, Timestamp endDate, String timeBack) {
		String timeSelectColumn = " rep.time as time";
		String timeWhereClause = " AND rep.time BETWEEN '" + startDate + "' AND '" + endDate+"'";
		if(timeBack != null) {
			timeSelectColumn = timecolumnExpression(timeBack, timeSelectColumn);
			timeWhereClause = timeWhereExpression(timeBack, timeWhereClause, startDate, endDate);
		}
		String query = "SELECT g.installation_id as groupId, sum(rep.act_energy_tot / " + energydivise +" ) as sumAct," + timeSelectColumn
				+ " FROM  rep_energy_5min rep"
				+ " INNER JOIN groups_phase g_ph ON g_ph.phase_id = rep.phase_id"
				+ " INNER JOIN groups g ON g.id = g_ph.groups_id and g.type=\"General\""
				+ " WHERE g.installation_id IN (" + Ints.join(", ", installationsIds) + ")" 
				+ timeWhereClause
				+ " GROUP BY  g.installation_id, hour(rep.time)";
		return em.createNativeQuery(query).unwrap(SQLQuery.class)
				.setResultTransformer(Transformers.aliasToBean(RecordDto.class)).getResultList();
	}
	
	public List<RecordDto> findAllHourRepEnergyByGroupRange(int[] groupsIds, Timestamp startDate, Timestamp endDate, String timeBack) {
		String timeSelectColumn = " rep.time as time";
		String timeWhereClause = " AND rep.time BETWEEN '" + startDate + "' AND '" + endDate+"'";
		if(timeBack != null) {
			timeSelectColumn = timecolumnExpression(timeBack, timeSelectColumn);
			timeWhereClause = timeWhereExpression(timeBack, timeWhereClause, startDate, endDate);
		}
		String query = "SELECT g_ph.groups_id as groupId, sum(rep.act_energy_tot / " + energydivise + ") as sumAct," + timeSelectColumn
				+ " FROM  rep_energy_5min rep"
				+ " INNER JOIN groups_phase g_ph ON g_ph.phase_id = rep.phase_id"
				+ " INNER JOIN groups g ON g.id = g_ph.groups_id and g.type=\"General\""
				+ " WHERE g_ph.groups_id IN (" + Ints.join(", ", groupsIds) + ")" 
				+ timeWhereClause
				+ " GROUP BY  g_ph.groups_id, hour(rep.time)";
		return em.createNativeQuery(query).unwrap(SQLQuery.class)
				.setResultTransformer(Transformers.aliasToBean(RecordDto.class)).getResultList();
	}
	
	public List<RecordDto> findAll5MinRepEnergyByZone(Timestamp startDate, Timestamp endDate, String timeBack) {
		String timeSelectColumn = " rep.time as time";
		String timeWhereClause = " AND rep.time BETWEEN '" + startDate + "' AND '" + endDate+"'";
		if(timeBack != null) {
			timeSelectColumn = timecolumnExpression(timeBack, timeSelectColumn);
			timeWhereClause = timeWhereExpression(timeBack, timeWhereClause, startDate, endDate);
		}
		String query = "SELECT i.zone_id as groupId, sum(rep.act_energy_tot / " + energydivise + ") as sumAct," + timeSelectColumn
				+ " FROM  rep_energy_5min rep"
				+ " INNER JOIN groups_phase g_ph ON g_ph.phase_id = rep.phase_id"
				+ " INNER JOIN groups g ON g.id = g_ph.groups_id and g.type=\"General\""
				+ " INNER JOIN installation i ON i.id = g.installation_id"
				+ timeWhereClause
				+ " GROUP BY  i.zone_id, rep.time";
		return em.createNativeQuery(query).unwrap(SQLQuery.class)
				.setResultTransformer(Transformers.aliasToBean(RecordDto.class)).getResultList();
	}
	
	public List<RecordDto> findAll5MinRepEnergyByInstallation(Timestamp startDate, Timestamp endDate, String timeBack) {
		String timeSelectColumn = " rep.time as time";
		String timeWhereClause = " AND rep.time BETWEEN '" + startDate + "' AND '" + endDate+"'";
		if(timeBack != null) {
			timeSelectColumn = timecolumnExpression(timeBack, timeSelectColumn);
			timeWhereClause = timeWhereExpression(timeBack, timeWhereClause, startDate, endDate);
		}
		String query = "SELECT g.installation_id as groupId, sum(rep.act_energy_tot / " +energydivise + ") as sumAct," + timeSelectColumn
				+ " FROM  rep_energy_5min rep"
				+ " INNER JOIN groups_phase g_ph ON g_ph.phase_id = rep.phase_id"
				+ " INNER JOIN groups g ON g.id = g_ph.groups_id and g.type=\"General\""
				+ timeWhereClause
				+ " GROUP BY  g.installation_id, rep.time";
		return em.createNativeQuery(query).unwrap(SQLQuery.class)
				.setResultTransformer(Transformers.aliasToBean(RecordDto.class)).getResultList();
	}
	
	public List<RecordDto> findAll5MinRepEnergyByGroup(Timestamp startDate, Timestamp endDate, String timeBack) {
		String timeSelectColumn = " rep.time as time";
		String timeWhereClause = " AND rep.time BETWEEN '" + startDate + "' AND '" + endDate+"'";
		if(timeBack != null) {
			timeSelectColumn = timecolumnExpression(timeBack, timeSelectColumn);
			timeWhereClause = timeWhereExpression(timeBack, timeWhereClause, startDate, endDate);
		}
		String query = "SELECT g_ph.groups_id as groupId, sum(rep.act_energy_tot / " + energydivise +" ) as sumAct," + timeSelectColumn
				+ " FROM  rep_energy_5min rep"
				+ " INNER JOIN groups_phase g_ph ON g_ph.phase_id = rep.phase_id"
				+ " INNER JOIN groups g ON g.id = g_ph.groups_id and g.type=\"General\""
				+ timeWhereClause
				+ " GROUP BY  g_ph.groups_id, rep.time";
		return em.createNativeQuery(query).unwrap(SQLQuery.class)
				.setResultTransformer(Transformers.aliasToBean(RecordDto.class)).getResultList();
	}
	
	public List<RecordDto> findAllHourRepEnergyByZone(Timestamp startDate, Timestamp endDate, String timeBack) {
		String timeSelectColumn = " rep.time as time";
		String timeWhereClause = " AND rep.time BETWEEN '" + startDate + "' AND '" + endDate+"'";
		if(timeBack != null) {
			timeSelectColumn = timecolumnExpression(timeBack, timeSelectColumn);
			timeWhereClause = timeWhereExpression(timeBack, timeWhereClause, startDate, endDate);
		}
		String query = "SELECT i.zone_id as groupId, sum(rep.act_energy_tot /" + energydivise  + " ) as sumAct," + timeSelectColumn
				+ " FROM  rep_energy_5min rep"
				+ " INNER JOIN groups_phase g_ph ON g_ph.phase_id = rep.phase_id"
				+ " INNER JOIN groups g ON g.id = g_ph.groups_id and g.type=\"General\""
				+ " INNER JOIN installation i ON i.id = g.installation_id"
				+ timeWhereClause
				+ " GROUP BY  i.zone_id, hour(rep.time)";
		return em.createNativeQuery(query).unwrap(SQLQuery.class)
				.setResultTransformer(Transformers.aliasToBean(RecordDto.class)).getResultList();
	}
	
	public List<RecordDto> findAllHourRepEnergyByInstallation(Timestamp startDate, Timestamp endDate, String timeBack) {
		String timeSelectColumn = " rep.time as time";
		String timeWhereClause = " AND rep.time BETWEEN '" + startDate + "' AND '" + endDate+"'";
		if(timeBack != null) {
			timeSelectColumn = timecolumnExpression(timeBack, timeSelectColumn);
			timeWhereClause = timeWhereExpression(timeBack, timeWhereClause, startDate, endDate);
		}
		String query = "SELECT g.installation_id as groupId, sum(rep.act_energy_tot /" + energydivise + ") as sumAct," + timeSelectColumn
				+ " FROM  rep_energy_5min rep"
				+ " INNER JOIN groups_phase g_ph ON g_ph.phase_id = rep.phase_id"
				+ " INNER JOIN groups g ON g.id = g_ph.groups_id and g.type=\"General\""
				+ timeWhereClause
				+ " GROUP BY  g.installation_id, hour(rep.time)";
		return em.createNativeQuery(query).unwrap(SQLQuery.class)
				.setResultTransformer(Transformers.aliasToBean(RecordDto.class)).getResultList();
	}
	
	public List<RecordDto> findAllHourRepEnergyByGroupRange( Timestamp startDate, Timestamp endDate, String timeBack) {
		String timeSelectColumn = " rep.time as time";
		String timeWhereClause = " AND rep.time BETWEEN '" + startDate + "' AND '" + endDate+"'";
		if(timeBack != null) {
			timeSelectColumn = timecolumnExpression(timeBack, timeSelectColumn);
			timeWhereClause = timeWhereExpression(timeBack, timeWhereClause, startDate, endDate);
		}
		String query = "SELECT g_ph.groups_id as groupId, sum(rep.act_energy_tot / " + energydivise  +") as sumAct," + timeSelectColumn
				+ " FROM  rep_energy_5min rep"
				+ " INNER JOIN groups_phase g_ph ON g_ph.phase_id = rep.phase_id"
				+ " INNER JOIN groups g ON g.id = g_ph.groups_id and g.type=\"General\""
				+ timeWhereClause
				+ " GROUP BY  g_ph.groups_id, hour(rep.time)";
		return em.createNativeQuery(query).unwrap(SQLQuery.class)
				.setResultTransformer(Transformers.aliasToBean(RecordDto.class)).getResultList();
	}
	
	
	private String timecolumnExpression(String timeBack, String timeSelectColumn)
	{
		switch (timeBack) {
		case "day":
			return "  DATE_ADD(rep.time, INTERVAL 1 day) as time";
		case "week":
			return "  DATE_ADD(rep.time, INTERVAL 1 week) as time";
		case "month":
			return "  DATE_ADD(rep.time, INTERVAL 1 month) as time";
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
