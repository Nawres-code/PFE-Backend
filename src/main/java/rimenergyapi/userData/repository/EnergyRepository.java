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
import rimenergyapi.userData.model.RtEnergyDto;

@Repository
public class EnergyRepository {
	@Autowired
	@Qualifier("tenantEntityManager")
	private EntityManager em;

	@Value("${energydivise}")
	String energydivise;

	public List<RtEnergyDto> findListRtEnergy(long installationIds) {
		List<RtEnergyDto> acttEnergy = new ArrayList<RtEnergyDto>();
		String query = "SELECT g.id as idGroup, tr.time as date, it.id as idInstallation, sum(act_energy_day /"  + energydivise + " )"
				+ " as actEngery FROM rt_energy tr, groups_phase gp, installation it, groups g "
				+ " where tr.phase_id = gp.phase_id and gp.groups_id = g.id and g.installation_id = it.id and it.id = "
				+ installationIds + " group by g.id, tr.time, it.id ";
		try {
			acttEnergy = em.createNativeQuery(query).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return acttEnergy;
	}

	public List<RtEnergyDto> findAllRTtEnergy() {
		List<RtEnergyDto> rtEnergy = new ArrayList<RtEnergyDto>();
		String query = "SELECT * FROM rt_energy";
		try {
			rtEnergy = em.createNativeQuery(query).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rtEnergy;
	}

	public List<Object[]> finAllRepEnergy5min(int[] groupIds, Timestamp startDate, Timestamp endDate) {
		List<Object[]> records = new ArrayList<Object[]>();
		String query = "SELECT gp.groups_id as groupId , sum(act_energy_tot / " + energydivise + " ) as sumAct , rp.time as time FROM rep_energy_5min rp,groups_phase gp "
				+ " where rp.time BETWEEN '" + startDate + "' AND '" + endDate + "' and gp.phase_id = rp.phase_id "
				+ " and rp.phase_id in (select phase_id from groups_phase gp where gp.groups_id in ("
				+ Ints.join(",", groupIds) + " )) group by gp.groups_id,rp.time ";

		try {
			records = em.createNativeQuery(query).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return records;
	}

	public List<Object[]> finAllRepEnergyDay(int[] groupIds, Timestamp startDate, Timestamp endDate) {
		List<Object[]> records = new ArrayList<Object[]>();
		String query = "SELECT gp.groups_id as groupId , sum(act_energy_tot / " + energydivise + " ) as sumAct, rp.time as time  FROM rep_energy_day rp,groups_phase gp "
				+ " where rp.time BETWEEN '" + startDate + "' AND '" + endDate + "' and gp.phase_id = rp.phase_id "
				+ " and rp.phase_id in (select phase_id from groups_phase gp where gp.groups_id in ("
				+ Ints.join(",", groupIds) + " )) and gp.groups_id in ( "
				+ Ints.join(",", groupIds) + " ) group by gp.groups_id,rp.time ";

		try {
			records = em.createNativeQuery(query).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return records;
	}

	public List<Object[]> finAllRepEnergyHours(int[] groupIds, Timestamp startDate, Timestamp endDate) {
		List<Object[]> records = new ArrayList<Object[]>();
		String query = "SELECT gp.groups_id as groupId ,sum(act_energy_tot / " + energydivise + " ) as sumAct , rp.time as time FROM rep_energy_hour rp,groups_phase gp "
				+ " where rp.time BETWEEN '" + startDate + "' AND '" + endDate + "' and gp.phase_id = rp.phase_id "
				+ " and rp.phase_id in (select phase_id from groups_phase gp where gp.groups_id in ("
				+ Ints.join(",", groupIds) + " )) and gp.groups_id in ("
				+ Ints.join(",", groupIds) + " ) group by gp.groups_id,rp.time ";

		try {
			records = em.createNativeQuery(query).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return records;
	}

	public List<Object[]> finAllRepEnergyMonth(int[] groupIds, Timestamp startDate, Timestamp endDate) {
		List<Object[]> records = new ArrayList<Object[]>();
		String query = "SELECT gp.groups_id as groupId ,sum(act_energy_tot / " 
				+ energydivise + " ) as sumAct, Any_value(rp.time) as time FROM rep_energy_day rp,groups_phase gp "
				+ " where rp.time BETWEEN '" + startDate + "' AND '" + endDate + "' and gp.phase_id = rp.phase_id "
				+ " and rp.phase_id in (select phase_id from groups_phase gp where gp.groups_id in ("
				+ Ints.join(",", groupIds) + " )) and gp.groups_id in ( " 
				+ Ints.join(",", groupIds) + " ) group by gp.groups_id, month(rp.time) ";
		try {
			records = em.createNativeQuery(query).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return records;
	}
	
	public List<Object[]> finAllRepEnergy5minByType(int[] groupIds, Timestamp startDate, Timestamp endDate, String type) {
		String wColumn = null;
		switch (type) {
		case "energy_react":
			wColumn = "react_energy_tot";
			break;
		case "energy_app":
			wColumn = "app_energy_tot";
			break;
		case "energy_fund":
			wColumn = "fund_energy_tot";
			break;
		default:
			wColumn = "act_energy_tot";
		}
		List<Object[]> records = new ArrayList<Object[]>();
		String query = "SELECT gp.groups_id as groupId , sum( " + wColumn + " / " + energydivise + " ) as sumAct , rp.time as time FROM rep_energy_5min rp,groups_phase gp "
				+ " where rp.time BETWEEN '" + startDate + "' AND '" + endDate + "' and gp.phase_id = rp.phase_id "
				+ " and rp.phase_id in (select phase_id from groups_phase gp where gp.groups_id in ("
				+ Ints.join(",", groupIds) + " )) group by gp.groups_id,rp.time order by time";

		try {
			records = em.createNativeQuery(query).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return records;
	}

	public List<Object[]> finAllRepEnergyRowByType(int[] groupIds, Timestamp startDate, Timestamp endDate, String type) {

		List<Object[]> records = new ArrayList<Object[]>();
		String query = "SELECT gp.groups_id as groupId , ar.energy_act as sumAct , ar.time as time FROM archive ar,groups_phase gp "
				+ " where ar.time BETWEEN '" + startDate + "' AND '" + endDate + "' and gp.phase_id = ar.phase_id "
				+ " and ar.phase_id in (select phase_id from groups_phase gp where gp.groups_id in ("
				+ Ints.join(",", groupIds) + " )) group by gp.groups_id,ar.time order by time";

		try {
			records = em.createNativeQuery(query).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return records;
	}

	public List<Object[]> finAllRepEnergyDayByType(int[] groupIds, Timestamp startDate, Timestamp endDate, String type) {
		List<Object[]> records = new ArrayList<Object[]>();
		String wColumn = null;
		switch (type) {
		case "energy_react":
			wColumn = "react_energy_tot";
			break;
		case "energy_app":
			wColumn = "app_energy_tot";
			break;
		case "energy_fund":
			wColumn = "fund_energy_tot";
			break;
		default:
			wColumn = "act_energy_tot";
		}
		String query = "SELECT gp.groups_id as groupId , sum( " + wColumn + "  / " + energydivise + " ) as sumAct, rp.time as time  FROM rep_energy_day rp,groups_phase gp "
				+ " where rp.time BETWEEN '" + startDate + "' AND '" + endDate + "' and gp.phase_id = rp.phase_id "
				+ " and rp.phase_id in (select phase_id from groups_phase gp where gp.groups_id in ("
				+ Ints.join(",", groupIds) + " )) and gp.groups_id in ( "
				+ Ints.join(",", groupIds) + " ) group by gp.groups_id,rp.time order by time";

		try {
			records = em.createNativeQuery(query).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return records;
	}

	public List<Object[]> finAllRepEnergyHoursByType(int[] groupIds, Timestamp startDate, Timestamp endDate, String type) {
		List<Object[]> records = new ArrayList<Object[]>();
		String wColumn = null;
		switch (type) {
		case "energy_react":
			wColumn = "react_energy_tot";
			break;
		case "energy_app":
			wColumn = "app_energy_tot";
			break;
		case "energy_fund":
			wColumn = "fund_energy_tot";
			break;
		default:
			wColumn = "act_energy_tot";
		}
		String query = "SELECT gp.groups_id as groupId ,sum( " + wColumn + "  / " + energydivise + " ) as sumAct , rp.time as time FROM rep_energy_hour rp,groups_phase gp "
				+ " where rp.time BETWEEN '" + startDate + "' AND '" + endDate + "' and gp.phase_id = rp.phase_id "
				+ " and rp.phase_id in (select phase_id from groups_phase gp where gp.groups_id in ("
				+ Ints.join(",", groupIds) + " )) and gp.groups_id in ("
				+ Ints.join(",", groupIds) + " ) group by gp.groups_id,rp.time order by time";

		try {
			records = em.createNativeQuery(query).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return records;
	}

	public List<Object[]> finAllRepEnergyMonthByType(int[] groupIds, Timestamp startDate, Timestamp endDate, String type) {
		List<Object[]> records = new ArrayList<Object[]>();
		String wColumn = null;
		switch (type) {
		case "energy_react":
			wColumn = "react_energy_tot";
			break;
		case "energy_app":
			wColumn = "app_energy_tot";
			break;
		case "energy_fund":
			wColumn = "fund_energy_tot";
			break;
		default:
			wColumn = "act_energy_tot";
		}
		String query = "SELECT gp.groups_id as groupId ,sum( " + wColumn + "  / " 
				+ energydivise + " ) as sumAct, Any_value(rp.time) as time FROM rep_energy_day rp,groups_phase gp "
				+ " where rp.time BETWEEN '" + startDate + "' AND '" + endDate + "' and gp.phase_id = rp.phase_id "
				+ " and rp.phase_id in (select phase_id from groups_phase gp where gp.groups_id in ("
				+ Ints.join(",", groupIds) + " )) and gp.groups_id in ( " 
				+ Ints.join(",", groupIds) + " ) group by gp.groups_id, month(rp.time) order by time";
		try {
			records = em.createNativeQuery(query).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return records;
	}

	public List<RecordDto> findAll5MinRepEnergyByZoneRange(int[] zonesIds, Timestamp startDate, Timestamp endDate,
			String timeBack) {
		String timeSelectColumn = " rep.time as time";
		String timeWhereClause = " AND rep.time BETWEEN '" + startDate + "' AND '" + endDate + "'";
		if (timeBack != null) {
			timeSelectColumn = timecolumnExpression(timeBack, timeSelectColumn);
			timeWhereClause = timeWhereExpression(timeBack, timeWhereClause, startDate, endDate);
		}
		String query = "SELECT i.zone_id as groupId, sum(rep.act_energy_tot / "+ energydivise + " ) as sumAct," + timeSelectColumn
				+ " FROM  rep_energy_5min rep" + " INNER JOIN groups_phase g_ph ON g_ph.phase_id = rep.phase_id"
				+ " INNER JOIN groups g ON g.id = g_ph.groups_id"
				+ " INNER JOIN installation i ON i.id = g.installation_id" + " WHERE i.zone_id IN ("
				+ Ints.join(", ", zonesIds) + ")" + timeWhereClause + " GROUP BY  i.zone_id, rep.time";
		return em.createNativeQuery(query).unwrap(SQLQuery.class)
				.setResultTransformer(Transformers.aliasToBean(RecordDto.class)).getResultList();
	}

	public List<RecordDto> findAllDayRepEnergyByZoneRange(int[] zonesIds, Timestamp startDate, Timestamp endDate,
			String timeBack) {
		String timeSelectColumn = " rep.time as time";
		String timeWhereClause = " AND rep.time BETWEEN '" + startDate + "' AND '" + endDate + "'";
		if (timeBack != null) {
			timeSelectColumn = timecolumnExpression(timeBack, timeSelectColumn);
			timeWhereClause = timeWhereExpression(timeBack, timeWhereClause, startDate, endDate);
		}
		String query = "SELECT i.zone_id as groupId, sum(rep.act_energy_tot / " + energydivise + " ) as sumAct," + timeSelectColumn
				+ " FROM  rep_energy_day rep" + " INNER JOIN groups_phase g_ph ON g_ph.phase_id = rep.phase_id"
				+ " INNER JOIN groups g ON g.id = g_ph.groups_id"
				+ " INNER JOIN installation i ON i.id = g.installation_id" + " WHERE i.zone_id IN ("
				+ Ints.join(", ", zonesIds) + ")" + timeWhereClause + " GROUP BY  i.zone_id, rep.time order by time";
		return em.createNativeQuery(query).unwrap(SQLQuery.class)
				.setResultTransformer(Transformers.aliasToBean(RecordDto.class)).getResultList();
	}

	public List<RecordDto> findAllHourRepEnergyByZoneRange(int[] zonesIds, Timestamp startDate, Timestamp endDate,
			String timeBack) {
		String timeSelectColumn = " rep.time as time";
		String timeWhereClause = " AND rep.time BETWEEN '" + startDate + "' AND '" + endDate + "'";
		if (timeBack != null) {
			timeSelectColumn = timecolumnExpression(timeBack, timeSelectColumn);
			timeWhereClause = timeWhereExpression(timeBack, timeWhereClause, startDate, endDate);
		}
		String query = "SELECT i.zone_id as groupId, sum(rep.act_energy_tot / " + energydivise + ") as sumAct," + timeSelectColumn
				+ " FROM  rep_energy_hour rep" + " INNER JOIN groups_phase g_ph ON g_ph.phase_id = rep.phase_id"
				+ " INNER JOIN groups g ON g.id = g_ph.groups_id"
				+ " INNER JOIN installation i ON i.id = g.installation_id" + " WHERE i.zone_id IN ("
				+ Ints.join(", ", zonesIds) + ")" + timeWhereClause + " GROUP BY  i.zone_id, rep.time order by time";
		return em.createNativeQuery(query).unwrap(SQLQuery.class)
				.setResultTransformer(Transformers.aliasToBean(RecordDto.class)).getResultList();
	}

	public List<RecordDto> findAll5MinRepEnergyByInstallationRange(int[] installationsIds, Timestamp startDate,
			Timestamp endDate, String timeBack) {
		String timeSelectColumn = " rep.time as time";
		String timeWhereClause = " AND rep.time BETWEEN '" + startDate + "' AND '" + endDate + "'";
		if (timeBack != null) {
			timeSelectColumn = timecolumnExpression(timeBack, timeSelectColumn);
			timeWhereClause = timeWhereExpression(timeBack, timeWhereClause, startDate, endDate);
		}
		String query = "SELECT g.installation_id as groupId, sum(rep.act_energy_tot / " + energydivise  + " ) as sumAct," + timeSelectColumn
				+ " FROM  rep_energy_5min rep" + " INNER JOIN groups_phase g_ph ON g_ph.phase_id = rep.phase_id"
				+ " INNER JOIN groups g ON g.id = g_ph.groups_id" + " WHERE g.installation_id IN ("
				+ Ints.join(", ", installationsIds) + ")" + timeWhereClause + " GROUP BY  g.installation_id, rep.time order by time";
		return em.createNativeQuery(query).unwrap(SQLQuery.class)
				.setResultTransformer(Transformers.aliasToBean(RecordDto.class)).getResultList();
	}

	public List<RecordDto> findAllDayRepEnergyByInstallationRange(int[] installationsIds, Timestamp startDate,
			Timestamp endDate, String timeBack) {
		String timeSelectColumn = " rep.time as time";
		String timeWhereClause = " AND rep.time BETWEEN '" + startDate + "' AND '" + endDate + "'";
		if (timeBack != null) {
			timeSelectColumn = timecolumnExpression(timeBack, timeSelectColumn);
			timeWhereClause = timeWhereExpression(timeBack, timeWhereClause, startDate, endDate);
		}
		String query = "SELECT g.installation_id as groupId, sum(rep.act_energy_tot / " + energydivise + ") as sumAct," + timeSelectColumn
				+ " FROM  rep_energy_day rep" + " INNER JOIN groups_phase g_ph ON g_ph.phase_id = rep.phase_id"
				+ " INNER JOIN groups g ON g.id = g_ph.groups_id" + " WHERE g.installation_id IN ("
				+ Ints.join(", ", installationsIds) + ")" + timeWhereClause + " GROUP BY  g.installation_id, rep.time order by time";
		return em.createNativeQuery(query).unwrap(SQLQuery.class)
				.setResultTransformer(Transformers.aliasToBean(RecordDto.class)).getResultList();
	}

	public List<RecordDto> findAllHourRepEnergyByInstallationRange(int[] installationsIds, Timestamp startDate,
			Timestamp endDate, String timeBack) {
		String timeSelectColumn = " rep.time as time";
		String timeWhereClause = " AND rep.time BETWEEN '" + startDate + "' AND '" + endDate + "'";
		if (timeBack != null) {
			timeSelectColumn = timecolumnExpression(timeBack, timeSelectColumn);
			timeWhereClause = timeWhereExpression(timeBack, timeWhereClause, startDate, endDate);
		}
		String query = "SELECT g.installation_id as groupId, sum(rep.act_energy_tot / " + energydivise + ") as sumAct," + timeSelectColumn
				+ " FROM  rep_energy_hour rep" + " INNER JOIN groups_phase g_ph ON g_ph.phase_id = rep.phase_id"
				+ " INNER JOIN groups g ON g.id = g_ph.groups_id" + " WHERE g.installation_id IN ("
				+ Ints.join(", ", installationsIds) + ")" + timeWhereClause + " GROUP BY  g.installation_id, rep.time order by time";
		return em.createNativeQuery(query).unwrap(SQLQuery.class)
				.setResultTransformer(Transformers.aliasToBean(RecordDto.class)).getResultList();
	}

	public List<RecordDto> findAll5MinRepEnergyByGroupRange(int[] groupsIds, Timestamp startDate, Timestamp endDate,
			String timeBack) {
		String timeSelectColumn = " rep.time as time";
		String timeWhereClause = " AND rep.time BETWEEN '" + startDate + "' AND '" + endDate + "'";
		if (timeBack != null) {
			timeSelectColumn = timecolumnExpression(timeBack, timeSelectColumn);
			timeWhereClause = timeWhereExpression(timeBack, timeWhereClause, startDate, endDate);
		}
		String query = "SELECT g_ph.groups_id as groupId, sum(rep.act_energy_tot / " + energydivise + ") as sumAct," + timeSelectColumn
				+ " FROM  rep_energy_5min rep" + " INNER JOIN groups_phase g_ph ON g_ph.phase_id = rep.phase_id"
				+ " INNER JOIN groups g ON g.id = g_ph.groups_id" + " WHERE g_ph.groups_id IN ("
				+ Ints.join(", ", groupsIds) + ")" + timeWhereClause + " GROUP BY  g_ph.groups_id, rep.time order by time";
		return em.createNativeQuery(query).unwrap(SQLQuery.class)
				.setResultTransformer(Transformers.aliasToBean(RecordDto.class)).getResultList();
	}

	public List<RecordDto> findAllDayRepEnergyByGroupRange(int[] groupsIds, Timestamp startDate, Timestamp endDate,
			String timeBack) {
		String timeSelectColumn = " rep.time as time";
		String timeWhereClause = " AND rep.time BETWEEN '" + startDate + "' AND '" + endDate + "'";
		if (timeBack != null) {
			timeSelectColumn = timecolumnExpression(timeBack, timeSelectColumn);
			timeWhereClause = timeWhereExpression(timeBack, timeWhereClause, startDate, endDate);
		}
		String query = "SELECT g_ph.groups_id as groupId, sum(rep.act_energy_tot / " + energydivise + ") as sumAct," + timeSelectColumn
				+ " FROM  rep_energy_day rep" + " INNER JOIN groups_phase g_ph ON g_ph.phase_id = rep.phase_id"
				+ " INNER JOIN groups g ON g.id = g_ph.groups_id" + " WHERE g_ph.groups_id IN ("
				+ Ints.join(", ", groupsIds) + ")" + timeWhereClause + " GROUP BY  g_ph.groups_id, rep.time order by time";
		return em.createNativeQuery(query).unwrap(SQLQuery.class)
				.setResultTransformer(Transformers.aliasToBean(RecordDto.class)).getResultList();
	}

	public List<RecordDto> findAllHourRepEnergyByGroupRange(int[] groupsIds, Timestamp startDate, Timestamp endDate,
			String timeBack) {
		String timeSelectColumn = " rep.time as time";
		String timeWhereClause = " AND rep.time BETWEEN '" + startDate + "' AND '" + endDate + "'";
		if (timeBack != null) {
			timeSelectColumn = timecolumnExpression(timeBack, timeSelectColumn);
			timeWhereClause = timeWhereExpression(timeBack, timeWhereClause, startDate, endDate);
		}
		String query = "SELECT g_ph.groups_id as groupId, sum(rep.act_energy_tot / " +energydivise+ ") as sumAct," + timeSelectColumn
				+ " FROM  rep_energy_hour rep" + " INNER JOIN groups_phase g_ph ON g_ph.phase_id = rep.phase_id"
				+ " INNER JOIN groups g ON g.id = g_ph.groups_id" + " WHERE g_ph.groups_id IN ("
				+ Ints.join(", ", groupsIds) + ")" + timeWhereClause + " GROUP BY  g_ph.groups_id, rep.time order by time";
		return em.createNativeQuery(query).unwrap(SQLQuery.class)
				.setResultTransformer(Transformers.aliasToBean(RecordDto.class)).getResultList();
	}

	private String timecolumnExpression(String timeBack, String timeSelectColumn) {
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

	private String timeWhereExpression(String timeBack, String timeWhereClause, Timestamp startDate,
			Timestamp endDate) {
		switch (timeBack) {
		case "day":
			return " AND rep.time BETWEEN DATE_ADD('" + startDate + "', INTERVAL -1 day) AND DATE_ADD('" + endDate
					+ "', INTERVAL -1 day)";
		case "week":
			return " AND rep.time BETWEEN DATE_ADD('" + startDate + "', INTERVAL -1 week) AND DATE_ADD('" + endDate
					+ "', INTERVAL -1 week)";
		case "month":
			return " AND rep.time BETWEEN DATE_ADD('" + startDate + "', INTERVAL -1 month) AND DATE_ADD('" + endDate
					+ "', INTERVAL -1 month)";
		}
		return timeWhereClause;
	}

}
