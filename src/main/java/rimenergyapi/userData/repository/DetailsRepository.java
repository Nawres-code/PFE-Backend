package rimenergyapi.userData.repository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.BigDecimalType;
import org.hibernate.type.IntegerType;
import org.hibernate.type.TimestampType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import rimenergyapi.dto.InputDataDto;
import rimenergyapi.userData.model.DetailsDto;
import rimenergyapi.userData.model.IoRtDto;
import rimenergyapi.userData.model.IoDataDto;

@Repository
public class DetailsRepository {

	@Autowired
	@Qualifier("tenantEntityManager")
	private EntityManager em;

	// *** phase ****//
	public List<Object[]>  findAllDataForVar(int[] phaseIds, List<String> vars, Timestamp startDate,Timestamp endDate) {
		List<Object[]> detailsDtos = new ArrayList<Object[]>();
		String phaseidsString="";
		for (int i=0;i<phaseIds.length;i++){
			if(i!=phaseIds.length-1)
				phaseidsString=phaseidsString+phaseIds[i]+',';
			else
				phaseidsString=phaseidsString+phaseIds[i];
		};
		String query = " SELECT phase_id, time , " + String.join(",", vars)
				+ " FROM archive "
				+ " where phase_id in (" +phaseidsString+ ")"
				+ " and time between \"" + startDate + "\" and \"" + endDate+"\"";
		try {
			System.out.println(query);
			List resultList = em.createNativeQuery(query).getResultList();
			detailsDtos = resultList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return detailsDtos;
	}
	public List<Object[]>  findAll5minDataForVar(int[] phaseIds, String vars, Timestamp startDate,Timestamp endDate) {
		List<Object[]> detailsDtos = new ArrayList<Object[]>();
		String phaseidsString="";
		for (int i=0;i<phaseIds.length;i++){
			if(i!=phaseIds.length-1)
				phaseidsString=phaseidsString+phaseIds[i]+',';
			else
				phaseidsString=phaseidsString+phaseIds[i];
		};

		String query = " SELECT phase_id, "
				+ " timestamp(replace(REPLACE(date_format(time, concat('%Y-%m-%d %H:', floor((minute(time)+5)/5) * 5,':00')), '60', \"00\"), ':5:',':05:')) AS time, "
				+ vars
				+ " FROM archive "
				+ " where phase_id in (" + phaseidsString + ")"
				+ " and time between \"" + startDate + "\" and \"" + endDate+"\""
				+ " group by phase_id, replace(REPLACE(date_format(time, concat('%Y-%m-%d %H:', floor((minute(time)+5)/5) * 5,':00')), '60', \"00\"), ':5:',':05:')"
				+ " order by time  asc";
		try {
			System.out.println(query);
			List resultList = em.createNativeQuery(query).getResultList();
			detailsDtos = resultList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return detailsDtos;
	}
	public List<Object[]>  findAllHourDataForVar(int[] phaseIds, String vars, Timestamp startDate, Timestamp endDate) {
		List<Object[]> detailsDtos = new ArrayList<Object[]>();
		String phaseidsString="";
		for (int i=0;i<phaseIds.length;i++){
			if(i!=phaseIds.length-1)
				phaseidsString=phaseidsString+phaseIds[i]+',';
			else
				phaseidsString=phaseidsString+phaseIds[i];
		};
		String query = " SELECT phase_id, date_add(timestamp(date_format(time, '%Y-%m-%d %H:00:00')), interval 1 hour) AS time, " + vars
				+ " FROM archive "
				+ " where phase_id in (" + phaseidsString + ")"
				+ " and time between \"" + startDate + "\" and \"" + endDate+"\""
				+ " group by phase_id, date_format(time, '%Y-%m-%d %H:00:00')"
				+ " order by time  asc";
		try {
			System.out.println(query);
			List resultList = em.createNativeQuery(query).getResultList();
			detailsDtos = resultList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return detailsDtos;
	}
	public List<Object[]>  findAllDayDataForVar(int[] phaseIds, String vars, Timestamp startDate, Timestamp endDate) {
		List<Object[]> detailsDtos = new ArrayList<Object[]>();
		String phaseidsString="";
		for (int i=0;i<phaseIds.length;i++){
			if(i!=phaseIds.length-1)
				phaseidsString=phaseidsString+phaseIds[i]+',';
			else
				phaseidsString=phaseidsString+phaseIds[i];
		};
		String query = " SELECT phase_id, timestamp(date_format(time, '%Y-%m-%d 00:00:00')) as time , " + vars
				+ " FROM archive "
				+ " where phase_id in (" +  phaseidsString + ")"
				+ " and time between \"" + startDate + "\" and \"" + endDate+"\""
				+ " group by phase_id, date_format(time, '%Y-%m-%d 00:00:00')"
				+ " order by time asc";
		try {
			System.out.println(query);
			List resultList = em.createNativeQuery(query).getResultList();
			detailsDtos = resultList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return detailsDtos;
	}
	public List<Object[]> findAllMonthDataForVar(int[] phaseIds, String vars, Timestamp startDate, Timestamp endDate) {
		List<Object[]> detailsDtos = new ArrayList<Object[]>();
		String phaseidsString="";
		for (int i=0;i<phaseIds.length;i++){
			if(i!=phaseIds.length-1)
				phaseidsString=phaseidsString+phaseIds[i]+',';
			else
				phaseidsString=phaseidsString+phaseIds[i];
		};
		String query = " SELECT phase_id, timestamp(date_format(time, '%Y-%m-01 00:00:00')) as time , " + vars
				+ " FROM archive "
				+ " where phase_id in (" +  phaseidsString + ")"
				+ " and time between \"" + startDate + "\" and \"" + endDate+"\""
				+ " group by phase_id, date_format(time, '%Y-%m-01 00:00:00')"
				+ " order by time asc";
		try {
			System.out.println(query);
			List resultList = em.createNativeQuery(query).getResultList();
			detailsDtos = resultList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return detailsDtos;
	}


	// *** groups ****//
	public List<Object[]> findAllDataForVarByGroup(int[] phaseIds, List<String> vars, Timestamp startDate,
												   Timestamp endDate) {
		List<Object[]> detailsDtos = new ArrayList<Object[]>();
		String phaseidsString="";
		boolean sum = false;
		for (int i=0;i<phaseIds.length;i++){
			if(i!=phaseIds.length-1)
				phaseidsString=phaseidsString+phaseIds[i]+',';
			else
				phaseidsString=phaseidsString+phaseIds[i];
		};
		String varsSumString="";
		for (int i=0;i<vars.size();i++){
			if(vars.get(i).contains("energy")||vars.get(i).contains("power")||vars.get(i).contains("current"))
				if(i!=vars.size()-1)
					varsSumString=varsSumString+"SUM("+vars.get(i)+") ,";
				else
					varsSumString=varsSumString+"SUM("+vars.get(i)+") ";
			else
			if(i!=vars.size()-1)
				varsSumString=varsSumString+"any_value("+vars.get(i)+") ,";
			else
				varsSumString=varsSumString+"any_value("+vars.get(i)+") ";
		};
		String query = " SELECT gp.groups_id, time , " + varsSumString
				+ " FROM archive a, groups_phase gp"
				+ " where a.phase_id=gp.phase_id and a.phase_id in (" +phaseidsString+ ")"
				+ " and time between \"" + startDate + "\" and \"" + endDate+"\""
				+ "  group by gp.groups_id,time "
				+ " order by time;";

		try {
			System.out.println(query);
			List resultList = em.createNativeQuery(query).getResultList();
			detailsDtos = resultList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return detailsDtos;
	}
	public List<Object[]> findAll5minDataForVarByGroup(int[] phaseIds, List<String> vars, Timestamp startDate,
													   Timestamp endDate) {
		List<Object[]> detailsDtos = new ArrayList<Object[]>();
		String phaseidsString="";
		for (int i=0;i<phaseIds.length;i++){
			if(i!=phaseIds.length-1)
				phaseidsString=phaseidsString+phaseIds[i]+',';
			else
				phaseidsString=phaseidsString+phaseIds[i];
		};
		String archVarsSumString="";
		String varsSumString="";
		boolean sum = false;
		for (int i = 0; i < vars.size(); i++) {
			if (vars.get(i).contains("energy") || vars.get(i).contains("power") || vars.get(i).contains("current")) {
				sum = true;
				if (i != vars.size() - 1) {
					archVarsSumString = archVarsSumString + "any_value(arch_." + vars.get(i) + "), ";
					varsSumString = varsSumString + "SUM(" + vars.get(i) + ") as "+ vars.get(i) +", ";
				} else {
					archVarsSumString = archVarsSumString + "any_value(arch_." + vars.get(i) + ")";
					varsSumString = varsSumString + "SUM(" + vars.get(i) + ") as "+ vars.get(i) +" ";
				}
			} else {
				if (i != vars.size() - 1) {
					archVarsSumString = archVarsSumString + "any_value( arch_." + vars.get(i) + "), ";
					varsSumString = varsSumString + "any_value(" + vars.get(i) + ") as "+ vars.get(i) +", ";
				} else {
					archVarsSumString = archVarsSumString + "any_value(arch_." + vars.get(i) + ")";
					varsSumString = varsSumString + "any_value(" + vars.get(i) + ")  as "+ vars.get(i) +" ";
				}
			}
		}
		String query = null;
		if(!sum) {
			query = " SELECT gp.groups_id, "
					+ "timestamp(replace(REPLACE(date_format(time, concat('%Y-%m-%d %H:', floor((minute(time)+5)/5) * 5,':00')), '60', \"00\"), ':5:',':05:')) AS time, "
					+ varsSumString
					+ " FROM archive a, groups_phase gp"
					+ " where a.phase_id=gp.phase_id and a.phase_id in (" +phaseidsString+ ")"
					+ " and time between \"" + startDate + "\" and \"" + endDate+"\""
					+ " group by gp.groups_id, "
					+ " replace(REPLACE(date_format(time, concat('%Y-%m-%d %H:', floor((minute(time)+5)/5) * 5,':00')), '60', \"00\"), ':5:',':05:')"
					+ " order by time asc";
		} else {
			query  = "select arch_.group_id as g, "
					+ "timestamp( replace(REPLACE(date_format(time, concat('%Y-%m-%d %H:', floor((minute(arch_.time)+5)/5) * 5,':00')), '60', \"00\"), ':5:',':05:')) AS time ,"
					+ " " + archVarsSumString
					+ " from (SELECT gp.groups_id as group_id, time, " + varsSumString
					+ " FROM"
					+ "    archive a,"
					+ "    groups_phase gp"
					+ " WHERE"
					+ "    a.phase_id = gp.phase_id"
					+ "        AND a.phase_id IN ( " +phaseidsString+ " )"
					+ "        AND time BETWEEN \"" + startDate + "\" and \"" + endDate+"\""
					+ " GROUP BY gp.groups_id , time ) As arch_"
					+ " group by arch_.group_id, date_format(time, concat('%Y-%m-%d %H:', floor((minute(arch_.time)+5)/5) * 5,':00'))"
					+ " order by time asc;";
		}
		try {
			System.out.println(query);
			List resultList = em.createNativeQuery(query).getResultList();
			detailsDtos = resultList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return detailsDtos;
	}
	public List<Object[]> findAllHourDataForVarByGroup(int[] phaseIds, List<String> vars, Timestamp startDate,
													   Timestamp endDate) {

		List<Object[]> detailsDtos = new ArrayList<Object[]>();
		String phaseidsString="";
		for (int i=0;i<phaseIds.length;i++){
			if(i!=phaseIds.length-1)
				phaseidsString=phaseidsString+phaseIds[i]+',';
			else
				phaseidsString=phaseidsString+phaseIds[i];
		};
		String archVarsSumString="";
		String varsSumString="";
		boolean sum = false;
		for (int i = 0; i < vars.size(); i++) {
			if (vars.get(i).contains("energy") || vars.get(i).contains("power") || vars.get(i).contains("current")) {
				sum = true;
				if (i != vars.size() - 1) {
					archVarsSumString = archVarsSumString + "any_value(arch_." + vars.get(i) + "), ";
					varsSumString = varsSumString + "SUM(" + vars.get(i) + ") as "+ vars.get(i) +", ";
				} else {
					archVarsSumString = archVarsSumString + "any_value(arch_." + vars.get(i) + ")";
					varsSumString = varsSumString + "SUM(" + vars.get(i) + ") as "+ vars.get(i) ;
				}
			} else {
				if (i != vars.size() - 1) {
					archVarsSumString = archVarsSumString + "any_value( arch_." + vars.get(i) + "), ";
					varsSumString = varsSumString + "any_value(" + vars.get(i) + ") as "+ vars.get(i) +", ";
				} else {
					archVarsSumString = archVarsSumString + "any_value(arch_." + vars.get(i) + ")";
					varsSumString = varsSumString + "any_value(" + vars.get(i) + ")  as "+ vars.get(i);
				}
			}
		}
		String query = null;
		if(!sum) {
			query = " SELECT gp.groups_id, "
					+  "date_add(timestamp(date_format(time, '%Y-%m-%d %H:00:00')), interval 1 hour) AS time, "
					+ varsSumString
					+ " FROM archive a, groups_phase gp"
					+ " where a.phase_id=gp.phase_id and a.phase_id in (" +phaseidsString+ ")"
					+ " and time between \"" + startDate + "\" and \"" + endDate+"\""
					+ " group by gp.groups_id, "
					+ "date_format(time, '%Y-%m-%d %H:00:00')"
					+ " order by time asc;";
		} else {
			query  = "select arch_.group_id as g, "
					+ "date_add(timestamp(date_format(arch_.time, '%Y-%m-%d %H:00:00')), interval 1 hour) as time, "
					+ " " + archVarsSumString
					+ " from (SELECT gp.groups_id as group_id, time, " + varsSumString
					+ " FROM"
					+ "    archive a,"
					+ "    groups_phase gp"
					+ " WHERE"
					+ "    a.phase_id = gp.phase_id"
					+ "        AND a.phase_id IN ( " +phaseidsString+ " )"
					+ "        AND time BETWEEN \"" + startDate + "\" and \"" + endDate+"\""
					+ " GROUP BY gp.groups_id , time ) As arch_"
					+ " group by arch_.group_id, date_format(arch_.time, '%Y-%m-%d %H:00:00')"
					+ " order by time asc;";
		}
		try {
			System.out.println(query);
			List resultList = em.createNativeQuery(query).getResultList();
			detailsDtos = resultList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return detailsDtos;
	}
	public List<Object[]> findAllDayDataForVarByGroup(int[] phaseIds, List<String> vars, Timestamp startDate,
													  Timestamp endDate) {
		List<Object[]> detailsDtos = new ArrayList<Object[]>();
		String phaseidsString="";
		for (int i=0;i<phaseIds.length;i++){
			if(i!=phaseIds.length-1)
				phaseidsString=phaseidsString+phaseIds[i]+',';
			else
				phaseidsString=phaseidsString+phaseIds[i];
		};
		String archVarsSumString="";
		String varsSumString="";
		boolean sum = false;
		for (int i = 0; i < vars.size(); i++) {
			if (vars.get(i).contains("energy") || vars.get(i).contains("power") || vars.get(i).contains("current")) {
				sum = true;
				if (i != vars.size() - 1) {
					archVarsSumString = archVarsSumString + "any_value(arch_." + vars.get(i) + "), ";
					varsSumString = varsSumString + "SUM(" + vars.get(i) + ") as "+ vars.get(i) +", ";
				} else {
					archVarsSumString = archVarsSumString + "any_value(arch_." + vars.get(i) + ")";
					varsSumString = varsSumString + "SUM(" + vars.get(i) + ") as "+ vars.get(i) ;
				}
			} else {
				if (i != vars.size() - 1) {
					archVarsSumString = archVarsSumString + "any_value( arch_." + vars.get(i) + "), ";
					varsSumString = varsSumString + "any_value(" + vars.get(i) + ") as "+ vars.get(i) +", ";
				} else {
					archVarsSumString = archVarsSumString + "any_value(arch_." + vars.get(i) + ")";
					varsSumString = varsSumString + "any_value(" + vars.get(i) + ")  as "+ vars.get(i);
				}
			}
		}
		String query = null;
		if(!sum) {
			query = " SELECT gp.groups_id, "
					+ " timestamp(date_format(time, '%Y-%m-%d 00:00:00')) as time,  "
					+ varsSumString
					+ " FROM archive a, groups_phase gp"
					+ " where a.phase_id=gp.phase_id and a.phase_id in (" +phaseidsString+ ")"
					+ " and time between \"" + startDate + "\" and \"" + endDate+"\""
					+ " group by gp.groups_id, "
					+ "date_format(time, '%Y-%m-%d 00:00:00')"
					+ " order by time asc;";
		} else {
			query  = "select arch_.group_id as g, "
					+ " timestamp(date_format(arch_.time, '%Y-%m-%d 00:00:00')) as time,"
					+ " " + archVarsSumString
					+ " from (SELECT gp.groups_id as group_id, time, " + varsSumString
					+ " FROM"
					+ "    archive a,"
					+ "    groups_phase gp"
					+ " WHERE"
					+ "    a.phase_id = gp.phase_id"
					+ "        AND a.phase_id IN ( " +phaseidsString+ " )"
					+ "        AND time BETWEEN \"" + startDate + "\" and \"" + endDate+"\""
					+ " GROUP BY gp.groups_id , time ) As arch_"
					+ " group by arch_.group_id, date_format(arch_.time, '%Y-%m-%d 00:00:00')"
					+ " order by time asc;";
		}
		try {
			System.out.println(query);
			List resultList = em.createNativeQuery(query).getResultList();
			detailsDtos = resultList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return detailsDtos;
	}
	public List<Object[]> findAllMonthDataForVarByGroup(int[] phaseIds, List<String> vars, Timestamp startDate,
														Timestamp endDate) {
		List<Object[]> detailsDtos = new ArrayList<Object[]>();
		String phaseidsString="";
		for (int i=0;i<phaseIds.length;i++){
			if(i!=phaseIds.length-1)
				phaseidsString=phaseidsString+phaseIds[i]+',';
			else
				phaseidsString=phaseidsString+phaseIds[i];
		};
		String archVarsSumString="";
		String varsSumString="";
		boolean sum = false;
		for (int i = 0; i < vars.size(); i++) {
			if (vars.get(i).contains("energy") || vars.get(i).contains("power") || vars.get(i).contains("current")) {
				sum = true;
				if (i != vars.size() - 1) {
					archVarsSumString = archVarsSumString + "any_value(arch_." + vars.get(i) + "), ";
					varsSumString = varsSumString + "SUM(" + vars.get(i) + ") as "+ vars.get(i) +", ";
				} else {
					archVarsSumString = archVarsSumString + "any_value(arch_." + vars.get(i) + ")";
					varsSumString = varsSumString + "SUM(" + vars.get(i) + ") as "+ vars.get(i);
				}
			} else {
				if (i != vars.size() - 1) {
					archVarsSumString = archVarsSumString + "any_value( arch_." + vars.get(i) + "), ";
					varsSumString = varsSumString + "any_value(" + vars.get(i) + ") as "+ vars.get(i) +", ";
				} else {
					archVarsSumString = archVarsSumString + "any_value(arch_." + vars.get(i) + ")";
					varsSumString = varsSumString + "any_value(" + vars.get(i) + ")  as "+ vars.get(i);
				}
			}
		}
		String query = null;
		if(!sum) {
			query = " SELECT gp.groups_id, "
					+ " timestamp(date_format(time, '%Y-%m-01 00:00:00')) as time,"
					+ varsSumString
					+ " FROM archive a, groups_phase gp"
					+ " where a.phase_id=gp.phase_id and a.phase_id in (" +phaseidsString+ ")"
					+ " and time between \"" + startDate + "\" and \"" + endDate+"\""
					+ " group by gp.groups_id, "
					+ "date_format(time, '%Y-%m-01 00:00:00')"
					+ " order by time asc;";
		} else {
			query  = "select arch_.group_id as g, "
					+ "timestamp(date_format(arch_.time, '%Y-%m-01 00:00:00')) as time, "
					+ " " + archVarsSumString
					+ " from (SELECT gp.groups_id as group_id, time, " + varsSumString
					+ " FROM"
					+ "    archive a,"
					+ "    groups_phase gp"
					+ " WHERE"
					+ "    a.phase_id = gp.phase_id"
					+ "        AND a.phase_id IN ( " +phaseidsString+ " )"
					+ "        AND time BETWEEN \"" + startDate + "\" and \"" + endDate+"\""
					+ " GROUP BY gp.groups_id , time ) As arch_"
					+ " group by arch_.group_id, date_format(arch_.time, '%Y-%m-01 00:00:00')"
					+ " order by time asc;";
		}
		try {
			System.out.println(query);
			List resultList = em.createNativeQuery(query).getResultList();
			detailsDtos = resultList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return detailsDtos;
	}


	// *** sonde  and inputs****//
	public List<Object[]> findAllDataForVarArchivTemperature1min(Double[] sondeIds, String var,
																 Timestamp startDate, Timestamp endDate) {
		List<Object[]> detailsDtos = new ArrayList<Object[]>();
		String sondeIdsString="";
		for (int i=0;i<sondeIds.length;i++){
			if(i!=sondeIds.length-1)
				sondeIdsString=sondeIdsString+sondeIds[i]+',';
			else
				sondeIdsString=sondeIdsString+sondeIds[i];
		};
		String query = " SELECT sonde_id, time , " + var
				+ " FROM archive_temperature "
				+ " where sonde_id in (" +sondeIdsString+ ")"
				+ " and time between \"" + startDate + "\" and \"" + endDate+"\" order by time asc";
		try {
			System.out.println(query);
			List resultList = em.createNativeQuery(query).getResultList();
			detailsDtos = resultList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return detailsDtos;
	}
	public List<Object[]>  findAllDataForVarArchivTemperature5min(Double[] sondeIds, String var, Timestamp startDate,Timestamp endDate) {
		List<Object[]> detailsDtos = new ArrayList<Object[]>();
		String sondeIdsString="";
		for (int i=0;i<sondeIds.length;i++){
			if(i!=sondeIds.length-1)
				sondeIdsString=sondeIdsString+sondeIds[i]+',';
			else
				sondeIdsString=sondeIdsString+sondeIds[i];
		};
		String query = " SELECT sonde_id, ANY_VALUE(FROM_UNIXTIME(ROUND(UNIX_TIMESTAMP(time) / 300)*300) )AS time, " + var
				+ " FROM archive_temperature "
				+ " where sonde_id in (" +sondeIdsString+ ")"
				+ " and time between \"" + startDate + "\" and \"" + endDate+"\" "
				+ " group by sonde_id, ROUND(UNIX_TIMESTAMP(time) / 300)"
				+ " order by ROUND(UNIX_TIMESTAMP(time) / 300)  asc";
		try {
			System.out.println(query);
			List resultList = em.createNativeQuery(query).getResultList();
			detailsDtos = resultList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return detailsDtos;
	}
	public List<Object[]>  findAllDataForVarArchivTemperatureHour(Double[] sondeIds, String var, Timestamp startDate,Timestamp endDate) {
		List<Object[]> detailsDtos = new ArrayList<Object[]>();
		String sondeIdsString="";
		for (int i=0;i<sondeIds.length;i++){
			if(i!=sondeIds.length-1)
				sondeIdsString=sondeIdsString+sondeIds[i]+',';
			else
				sondeIdsString=sondeIdsString+sondeIds[i];
		};
		String query = " SELECT sonde_id, ANY_VALUE(FROM_UNIXTIME(ROUND(UNIX_TIMESTAMP(time) / 3600)*3600) )AS time, " + var
				+ " FROM archive_temperature "
				+ " where sonde_id in (" +sondeIdsString+ ")"
				+ " and time between \"" + startDate + "\" and \"" + endDate+"\" "
				+ " group by sonde_id, ROUND(UNIX_TIMESTAMP(time) / 3600)"
				+ " order by ROUND(UNIX_TIMESTAMP(time) / 3600)  asc";
		try {
			System.out.println(query);
			List resultList = em.createNativeQuery(query).getResultList();
			detailsDtos = resultList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return detailsDtos;
	}
	public List<Object[]>  findAllDataForVarArchivTemperatureDay(Double[] sondeIds, String var, Timestamp startDate,Timestamp endDate) {
		List<Object[]> detailsDtos = new ArrayList<Object[]>();
		String sondeIdsString="";
		for (int i=0;i<sondeIds.length;i++){
			if(i!=sondeIds.length-1)
				sondeIdsString=sondeIdsString+sondeIds[i]+',';
			else
				sondeIdsString=sondeIdsString+sondeIds[i];
		};
		String query = " SELECT sonde_id, timestamp(concat(date(time),' ','00:00')) as time , " + var
				+ " FROM archive_temperature "
				+ " where sonde_id in (" +sondeIdsString+ ")"
				+ " and time between \"" + startDate + "\" and \"" + endDate+"\" "
				+ " group by sonde_id, timestamp(concat(date(time),' ','00:00'))"
				+ " order by time asc";
		try {
			System.out.println(query);
			List resultList = em.createNativeQuery(query).getResultList();
			detailsDtos = resultList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return detailsDtos;
	}
	public List<Object[]>  findAllDataForVarArchivTemperatureMonth(Double[] sondeIds, String var, Timestamp startDate,Timestamp endDate) {
		List<Object[]> detailsDtos = new ArrayList<Object[]>();
		String sondeIdsString="";
		for (int i=0;i<sondeIds.length;i++){
			if(i!=sondeIds.length-1)
				sondeIdsString=sondeIdsString+sondeIds[i]+',';
			else
				sondeIdsString=sondeIdsString+sondeIds[i];
		};
		String query =  " SELECT sonde_id, timestamp(concat(year(time),'-',month(time),'-01 00:00')) as time , " + var
				+ " FROM archive_temperature "
				+ " where sonde_id in (" +sondeIdsString+ ")"
				+ " and time between \"" + startDate + "\" and \"" + endDate+"\" "
				+ " group by sonde_id, timestamp(concat(year(time),'-',month(time),'-01 00:00'))"
				+ " order by time asc";
		try {
			System.out.println(query);
			List resultList = em.createNativeQuery(query).getResultList();
			detailsDtos = resultList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return detailsDtos;
	}
	public List<InputDataDto> findAllDataForVarArchivInputRaw(int[] inputIds, Timestamp startDate, Timestamp endDate) {
		List<InputDataDto> detailsDtos = null;
		String strInputIds = Arrays.toString(inputIds).replaceAll("\\[", "").replaceAll("\\]", "");
		String query = "SELECT id_input as id, time,  value FROM archive_input"
				+ " where id_input in (" + strInputIds + ")"
				+ " and time between \"" + startDate + "\" and \"" + endDate+"\" order by time asc";
		try {
			// System.out.println(query);
			List resultList = em.createNativeQuery(query).unwrap(SQLQuery.class)
					.setResultTransformer(Transformers.aliasToBean(InputDataDto.class)).getResultList();
			em.clear();
			detailsDtos = resultList;
		} catch (Exception e) {
			// e.printStackTrace();
		}
		return detailsDtos;
	}
	public List<Object[]>  findAllDataForVarArchivTemperature30Minute(Double[] sondeIds, String var, Timestamp startDate,Timestamp endDate) {
		List<Object[]> detailsDtos = new ArrayList<Object[]>();
		String sondeIdsString="";
		for (int i=0;i<sondeIds.length;i++){
			if(i!=sondeIds.length-1)
				sondeIdsString=sondeIdsString+sondeIds[i]+',';
			else
				sondeIdsString=sondeIdsString+sondeIds[i];
		};
		String varString[]=var.split(",");
		String anvVar="";
		for (int i=0;i<varString.length;i++) {
			if(i!=varString.length-1)
				anvVar+="ANY_VALUE("+varString[i]+"),";
			else
				anvVar+="ANY_VALUE("+varString[i]+")";
		}
		String query = " SELECT sonde_id,  DATE_ADD(DATE(TIME),INTERVAL floor((TIME_TO_SEC(time)/(30*60)))*30 MINUTE) as time_15,"
				+ 	anvVar
				+ " FROM archive_temperature "
				+ " where sonde_id in (" +sondeIdsString+ ")"
				+ " and temperature is not null "
				+ " and time between \"" + startDate + "\" and \"" + endDate+"\" "
				+ " group by time_15 "
				+ "order by time_15 asc";
		try {
			System.out.println(query);
			List<Object[]> resultList = em.createNativeQuery(query).getResultList();
			detailsDtos = resultList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return detailsDtos;
	}

	//avg temp

	public List<Object[]> findAllDataForAvgVarArchivTemperature1min(Double[] sondeIds, String var,
																	Timestamp startDate, Timestamp endDate) {
		List<Object[]> detailsDtos = new ArrayList<Object[]>();
		String sondeIdsString="";
		for (int i=0;i<sondeIds.length;i++){
			if(i!=sondeIds.length-1)
				sondeIdsString=sondeIdsString+sondeIds[i]+',';
			else
				sondeIdsString=sondeIdsString+sondeIds[i];
		};
		String query = " SELECT time , avg(temperature), avg(humidity), max(battery_percentage) "
				+ " FROM archive_temperature "
				+ " where sonde_id in (" +sondeIdsString+ ")"
				+ " and time between \"" + startDate + "\" and \"" + endDate+"\""
				+ " group by time"
				+ " order by time asc";
		try {
			System.out.println(query);
			List resultList = em.createNativeQuery(query).getResultList();
			detailsDtos = resultList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return detailsDtos;
	}

	public List<Object[]>  findAllDataForAvgVarArchivTemperature5min(Double[] sondeIds, String var, Timestamp startDate,Timestamp endDate) {
		List<Object[]> detailsDtos = new ArrayList<Object[]>();
		String sondeIdsString="";
		for (int i=0;i<sondeIds.length;i++){
			if(i!=sondeIds.length-1)
				sondeIdsString=sondeIdsString+sondeIds[i]+',';
			else
				sondeIdsString=sondeIdsString+sondeIds[i];
		};
		String query = " SELECT ANY_VALUE(FROM_UNIXTIME(ROUND(UNIX_TIMESTAMP(time) / 300)*300) )AS time, avg(temperature), avg(humidity), max(battery_percentage)"
				+ " FROM archive_temperature "
				+ " where sonde_id in (" +sondeIdsString+ ")"
				+ " and time between \"" + startDate + "\" and \"" + endDate+"\" "
				+ " group by ROUND(UNIX_TIMESTAMP(time) / 300)"
				+ " order by ROUND(UNIX_TIMESTAMP(time) / 300)  asc";
		try {
			System.out.println(query);
			List resultList = em.createNativeQuery(query).getResultList();
			detailsDtos = resultList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return detailsDtos;
	}
	public List<Object[]>  findAllDataForAvgVarArchivTemperatureHour(Double[] sondeIds, String var, Timestamp startDate,Timestamp endDate) {
		List<Object[]> detailsDtos = new ArrayList<Object[]>();
		String sondeIdsString="";
		for (int i=0;i<sondeIds.length;i++){
			if(i!=sondeIds.length-1)
				sondeIdsString=sondeIdsString+sondeIds[i]+',';
			else
				sondeIdsString=sondeIdsString+sondeIds[i];
		};
		String query = " SELECT  ANY_VALUE(FROM_UNIXTIME(ROUND(UNIX_TIMESTAMP(time) / 3600)*3600) )AS time, avg(temperature), avg(humidity), max(battery_percentage)"
				+ " FROM archive_temperature "
				+ " where sonde_id in (" +sondeIdsString+ ")"
				+ " and time between \"" + startDate + "\" and \"" + endDate+"\" "
				+ " group by ROUND(UNIX_TIMESTAMP(time) / 3600)"
				+ " order by ROUND(UNIX_TIMESTAMP(time) / 3600)  asc";
		try {
			System.out.println(query);
			List resultList = em.createNativeQuery(query).getResultList();
			detailsDtos = resultList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return detailsDtos;
	}
	public List<Object[]>  findAllDataForAvgVarArchivTemperatureDay(Double[] sondeIds, String var, Timestamp startDate,Timestamp endDate) {
		List<Object[]> detailsDtos = new ArrayList<Object[]>();
		String sondeIdsString="";
		for (int i=0;i<sondeIds.length;i++){
			if(i!=sondeIds.length-1)
				sondeIdsString=sondeIdsString+sondeIds[i]+',';
			else
				sondeIdsString=sondeIdsString+sondeIds[i];
		};
		String query = " SELECT  timestamp(concat(date(time),' ','00:00')) as time , avg(temperature), avg(humidity), max(battery_percentage)"
				+ " FROM archive_temperature "
				+ " where sonde_id in (" +sondeIdsString+ ")"
				+ " and time between \"" + startDate + "\" and \"" + endDate+"\" "
				+ " group by timestamp(concat(date(time),' ','00:00'))"
				+ " order by time asc";
		try {
			System.out.println(query);
			List resultList = em.createNativeQuery(query).getResultList();
			detailsDtos = resultList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return detailsDtos;
	}
	public List<Object[]>  findAllDataForAvgVarArchivTemperatureMonth(Double[] sondeIds, String var, Timestamp startDate,Timestamp endDate) {
		List<Object[]> detailsDtos = new ArrayList<Object[]>();
		String sondeIdsString="";
		for (int i=0;i<sondeIds.length;i++){
			if(i!=sondeIds.length-1)
				sondeIdsString=sondeIdsString+sondeIds[i]+',';
			else
				sondeIdsString=sondeIdsString+sondeIds[i];
		};
		String query =  " SELECT timestamp(concat(year(time),'-',month(time),'-01 00:00')) as time, avg(temperature), avg(humidity), max(battery_percentage)"
				+ " FROM archive_temperature "
				+ " where sonde_id in (" +sondeIdsString+ ")"
				+ " and time between \"" + startDate + "\" and \"" + endDate+"\" "
				+ " group by timestamp(concat(year(time),'-',month(time),'-01 00:00'))"
				+ " order by time asc";
		try {
			System.out.println(query);
			List resultList = em.createNativeQuery(query).getResultList();
			detailsDtos = resultList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return detailsDtos;
	}

	// *** GAZ  ***//
	public List<Object[]>  findAllDataForVarArchivGaz(int[] gazIds, String[] var, Timestamp startDate,Timestamp endDate) {
		List<Object[]> detailsDtos = new ArrayList<Object[]>();
		String gazIdsString="";
		for (int i=0;i<gazIds.length;i++){
			if(i!=gazIds.length-1)
				gazIdsString=gazIdsString+gazIds[i]+',';
			else
				gazIdsString=gazIdsString+gazIds[i];
		};
		String query = " SELECT g.id, a.time , " + var[0]
				+ " FROM archive_io a, gaz g "
				+ " where g.device_id = a.id_device and g.id in (" +gazIdsString+ ")"
				+ " and time between \"" + startDate + "\" and \"" + endDate+"\" order by time asc";
		try {
			System.out.println(query);
			List resultList = em.createNativeQuery(query).getResultList();
			detailsDtos = resultList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return detailsDtos;
	}

	// *** points  ***//
	public List<Object[]>  findAllDataForVarArchivTPoint(int[] pointIds, int [] deviceIds,  String var, Timestamp startDate,Timestamp endDate) {
		List<Object[]> detailsDtos = new ArrayList<Object[]>();
		String pointIdsString="";
		for (int i=0;i<pointIds.length;i++){
			if(i!=pointIds.length-1)
				pointIdsString=pointIdsString+pointIds[i]+',';
			else
				pointIdsString=pointIdsString+pointIds[i];
		};
		String deviceIdsString="";
		for (int i=0;i<deviceIds.length;i++){
			if(i!=deviceIds.length-1)
				deviceIdsString=deviceIdsString+deviceIds[i]+',';
			else
				deviceIdsString=deviceIdsString+deviceIds[i];
		};
		String query = "SELECT time, point_id, device_id, " + var
				+ " FROM archive_froid "
				+ " where point_id in (" +pointIdsString+ ") and  device_id in (" + deviceIdsString + ")"
				+ " and time between \"" + startDate + "\" and \"" + endDate+ "\" order by time asc";
		try {
			System.out.println(query);
			List resultList = em.createNativeQuery(query).getResultList();
			detailsDtos = resultList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return detailsDtos;
	}

	// *** Metos sensor & station  ***//
	public List<Object[]>  findAllDataForVarArchivMetos(String[] sensorIds, String var, Timestamp startDate,Timestamp endDate) {
		List<Object[]> detailsDtos = new ArrayList<Object[]>();
		String sensorIdsString="";
		for (int i=0;i<sensorIds.length;i++){
			if(i!=sensorIds.length-1)
				sensorIdsString=sensorIdsString+sensorIds[i]+',';
			else
				sensorIdsString=sensorIdsString+sensorIds[i];
		};
		String query = " SELECT sensor_id, time , " + var
				+ " FROM archive_metos "
				+ " where sensor_id in (" +sensorIdsString+ ")"
				+ " and time between \"" + startDate + "\" and \"" + endDate+"\" order by time asc";
		try {
			System.out.println(query);
			List resultList = em.createNativeQuery(query).getResultList();
			detailsDtos = resultList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return detailsDtos;
	}

	public List<Object[]>  findAllDataForVarArchivMetosStation(String[] stationIds, String var, Timestamp startDate,Timestamp endDate) {
		List<Object[]> detailsDtos = new ArrayList<Object[]>();
		String stationIdsString="";
		for (int i=0;i<stationIds.length;i++){
			if(i!=stationIds.length-1)
				stationIdsString= '\"'+stationIdsString +stationIds[i] + '\"' +',';
			else
				stationIdsString='\"'+stationIdsString +  stationIds[i] + '\"';
		};
		String query = " SELECT station_id, time , " + var
				+ " FROM archive_metos "
				+ " where station_id in (" +stationIdsString+ ")"
				+ " and time between \"" + startDate + "\" and \"" + endDate+"\" order by time asc";
		try {
			System.out.println(query);
			List resultList = em.createNativeQuery(query).getResultList();
			detailsDtos = resultList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return detailsDtos;
	}

	public List<Object[]> findAllDataForVarArchivIo(int[] ids,
													Timestamp startDate, Timestamp endDate, String period, String[] var){
		String fromQuery = "";

		switch (period.toLowerCase()) {
			case "hours":
				fromQuery = "rep_io_hour r";
				break;
			case "day":
				fromQuery = "rep_io_day r";
				break;
			case "month":
				fromQuery = "rep_io_month r ";
				break;
			case "row":
				fromQuery = "archive_io r";
				break;

			default: // day
				fromQuery = "rep_io_day r ";
				break;
		}

		List<Object[]> detailsDtos = new ArrayList<Object[]>();
		String gazIdsString="";
		for (int i=0;i<ids.length;i++){
			if(i!=ids.length-1)
				gazIdsString=gazIdsString+ids[i]+',';
			else
				gazIdsString=gazIdsString+ids[i];
		};
		if (period != "Row"){

				String query = " SELECT r.io_id, r.time , r.value * t.impulse_value as  value,"
						+ " r.global_count * t.impulse_value as impulse_value"
						+ " FROM " + fromQuery
						+ " join io_table t on t.id = r.io_id "
						+ " where r.io_id in (" +gazIdsString+ ")"
						+ " and r.time between \"" + startDate + "\" and \"" + endDate+"\" order by r.time asc";
		try {
			System.out.println(query);
			List resultList = em.createNativeQuery(query).getResultList();
			detailsDtos = resultList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		} else {
		String query = " SELECT r.io_id, r.time , r.value * t.impulse_value as  value,"
				+ " r.global_count * t.impulse_value as impulse_value"
				+ " FROM " + fromQuery
				+ " join io_table t on t.id = r.io_id "
				+ " where r.io_id in (" +gazIdsString+ ")"
				+ " and r.time between \"" + startDate + "\" and \"" + endDate+"\" order by r.time asc";
		try {
			System.out.println(query);
			List resultList = em.createNativeQuery(query).getResultList();
			detailsDtos = resultList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		}
		return detailsDtos;
	}
}
