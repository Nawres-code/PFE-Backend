package rimenergyapi.repository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.hibernate.transform.Transformers;
import org.hibernate.type.BooleanType;
import org.hibernate.type.DateType;
import org.hibernate.type.DoubleType;
import org.hibernate.type.IntegerType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.hibernate.type.TimestampType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.google.common.primitives.Ints;

import rimenergyapi.CurrentTenantIdentifierResolverImpl;
import rimenergyapi.dto.AnnualReportDto;
import rimenergyapi.dto.GroupReportCellDto;
import rimenergyapi.dto.InstallationReportCellDto;
import rimenergyapi.model.AnnualEnergyReport;

@Repository
public class ReportRepository {
	@Autowired
	private CurrentTenantIdentifierResolver tenantIdentifier;
	
	@Autowired
	@Qualifier("tenantEntityManager")
	private EntityManager em;
	
	@Value("${energydivise}")
	private String energydivise;
	
	
	public List<GroupReportCellDto> findHourlyEnergyByInstallation(List<Integer> range , Timestamp startDate, Timestamp endDate){
		List<GroupReportCellDto> result=new ArrayList<>();
		String sql ="SELECT g.name, g.type, rt_h.time as time, sum(rt_h.act_energy_tot) as value" + 
				" FROM CC_"+ tenantIdentifier.resolveCurrentTenantIdentifier()+".rep_energy_hour rt_h" + 
				" INNER JOIN tricity.groups_phase g_ph ON g_ph.phase_id = rt_h.phase_id" + 
				" INNER JOIN tricity.`groups` g ON g.id = g_ph.groups_id" + 
				" WHERE g.installation_id ="+ range.get(0)+
				" AND rt_h.time BETWEEN '"+ startDate+ "' AND '"+ endDate +"' "+
				" GROUP BY g.name, rt_h.time, g.type"+
				" ORDER BY rt_h.time, g.name;";
		try {
			SQLQuery sqlQuery = em.createNativeQuery(sql).unwrap(SQLQuery.class);
		    Query query = sqlQuery.setResultTransformer(Transformers.aliasToBean(GroupReportCellDto.class));
		    result= query.getResultList();
 		   em.clear();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public List<GroupReportCellDto> findDailyEnergyByInstallation(List<Integer> range , Timestamp startDate, Timestamp endDate, boolean isOpen){
		String notStr;
		notStr= isOpen? "": "NOT ";
		List<GroupReportCellDto> result= new ArrayList<>();
		String sql ="SELECT g.name, g.type, timestamp(date(rt_h.time)) as time, sum(rt_h.act_energy_tot) as value" +  
				" FROM CC_"+ tenantIdentifier.resolveCurrentTenantIdentifier()+".rep_energy_hour rt_h" + 
				" INNER JOIN tricity.groups_phase g_ph ON g_ph.phase_id = rt_h.phase_id" + 
				" INNER JOIN tricity.`groups` g ON g.id = g_ph.groups_id" + 
				" WHERE g.installation_id ="+ range.get(0)+
				" AND rt_h.time BETWEEN '"+ startDate+ "' AND '"+ endDate +"' "+
				" AND hour(rt_h.time) "+notStr+" BETWEEN 9 and 21"+
				" GROUP BY g.name, timestamp(date(rt_h.time)), g.type"+
				" ORDER BY timestamp(date(rt_h.time)), g.name;";
		try {
			System.out.println("query, "+isOpen+": "+ sql);
			SQLQuery sqlQuery = em.createNativeQuery(sql).unwrap(SQLQuery.class);
		    result = sqlQuery
		    		.setResultTransformer(Transformers.aliasToBean(GroupReportCellDto.class))
		    		.getResultList();
 		   em.clear();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public List<InstallationReportCellDto> findEnergyByInstallationRange(List<Integer> range, boolean isOpen, Timestamp startDate, Timestamp endDate){
		List<InstallationReportCellDto> result = new ArrayList<>();
		String thresholdLbl="threshold_day";
		String notStr="";
		if(!isOpen)
		{
			thresholdLbl="threshold_night";
			notStr="NOT ";
		}
		int[] array=range.stream().mapToInt(Integer::intValue).toArray();
		String sql="SELECT any_value(g.installation_id) as idInstallation, any_value(i.code) as code, any_value(i.name) as magasin, "+isOpen+" as isOpen " + 
				", any_value(g."+thresholdLbl+" * TIMESTAMPDIFF(day, '"+startDate+"', '"+endDate+"')"+" ) as threshold, g.name" + 
				", g.type, sum(rt_h.act_energy_tot) as value" + 
				" FROM CC_"+ tenantIdentifier.resolveCurrentTenantIdentifier()+".rep_energy_hour rt_h" + 
				" INNER JOIN tricity.groups_phase g_ph ON g_ph.phase_id = rt_h.phase_id" + 
				" INNER JOIN tricity.`groups` g ON g.id = g_ph.groups_id" + 
				" INNER JOIN tricity.installation i ON i.id = g.installation_id" + 
				" WHERE hour(rt_h.time) "+notStr+"BETWEEN 9 and 21 and  i.zone_id in (" + Ints.join(",", array) + ")" +
				" AND rt_h.time BETWEEN '"+ startDate+ "' AND '"+ endDate +"'"+
				" GROUP BY code, g.name, g.type" + 
				" ORDER BY  code, g.name;";
		try {
			SQLQuery sqlQuery = em.createNativeQuery(sql).unwrap(SQLQuery.class);
		    Query query = sqlQuery.addScalar("idInstallation", new LongType())
		    					.addScalar("code", new LongType())
		    					.addScalar("magasin", new StringType())
		    					.addScalar("threshold", new LongType())
		    					.addScalar("name", new StringType())
		    					.addScalar("type", new StringType())
		    					.addScalar("value", new DoubleType())
		    					.addScalar("isOpen", new BooleanType())
		    		.setResultTransformer(Transformers.aliasToBean(InstallationReportCellDto.class));
		    result= query.getResultList();
		   em.clear();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public List<GroupReportCellDto> findEnergyByGroupRange(List<Integer> range , Timestamp startDate, Timestamp endDate){
		List<GroupReportCellDto> result=new ArrayList<>();
		int[] array=range.stream().mapToInt(Integer::intValue).toArray();
		//timestamp(date(rt_h.time)) as time
		String sql ="SELECT g.name, g.type, rt_h.time as time, sum(rt_h.act_energy_tot) as value" + 
				" FROM CC_"+ tenantIdentifier.resolveCurrentTenantIdentifier()+".rep_energy_hour rt_h" + 
				" INNER JOIN tricity.groups_phase g_ph ON g_ph.phase_id = rt_h.phase_id" + 
				" INNER JOIN tricity.`groups` g ON g.id = g_ph.groups_id" + 
				" WHERE g.id in ("+  Ints.join(",", array)+")" +
				" AND rt_h.time BETWEEN "+ startDate+ " AND "+ endDate +
				" GROUP BY g.name, rt_h.time, g.type"+
				" ORDER BY rt_h.time, g.name;";
		try {
			SQLQuery sqlQuery = em.createNativeQuery(sql).unwrap(SQLQuery.class);
		    Query query = sqlQuery.setResultTransformer(Transformers.aliasToBean(GroupReportCellDto.class));
		    result= query.getResultList();
 		   em.clear();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public List<String> findGroupsLabels(List<Integer>idRange) {
	//	int[] idArray=idRange.parallelStream().mapToInt(Integer::valueOf).toArray();
		return em.createNativeQuery("Select name from `groups` where installation_id = "+ idRange.get(0)+ /*in ("+Ints.join(",", idArray)+)*/ " and type <> 'General' order by name;").getResultList();
	}
	
	public Object[] findInstallationNameAndCodeById(int id) {
		return (Object[]) em.createNativeQuery("SELECT name, code, zone_id FROM tricity.installation WHERE id= "+id+" ;").getResultList().get(0);
	}
	
	public String findAnnualGeneralEnergyPerHour(Timestamp startDate, Timestamp endDate) {
		String sql = " ( SELECT  concat(\"hours_\",Hour(rep.time))  as timeRange, sum(rep.act_energy_tot)/ 1000 as energy, date(rep.time) as date" 
		      + " FROM rep_energy_5min rep"
		      +	" INNER JOIN groups_phase g_ph ON g_ph.phase_id = rep.phase_id" 
			  +	" INNER JOIN `groups` g ON g.id = g_ph.groups_id" 
			  + " where g.type = 'General' and rep.time >= '"+startDate+"' and rep.time < '"+endDate+"'"  
			  + " group by Hour(rep.time), concat(MONTHNAME(rep.time) ,' ', YEAR(rep.time))" 
			  + " order by rep.time ) ";
		return sql;
	}
	
	public String findAnnualGeneralEnergyPerHourChargN1(Timestamp startDate, Timestamp endDate) {
		String sql = " ( SELECT  concat(\"hours1_\",Hour(rep.time))  as timeRange, sum(rep.act_energy_tot)/ 1000 as energy, date(rep.time) as date" 
		      + " FROM rep_energy_5min rep"
		      +	" INNER JOIN groups_phase g_ph ON g_ph.phase_id = rep.phase_id" 
			  +	" INNER JOIN `groups` g ON g.id = g_ph.groups_id" 
			  + " where (g.name = 'Chambre negative 1' or g.name = 'Chambre negative 2') and rep.time >= '"+startDate+"' and rep.time < '"+endDate+"'"  
			  + " group by Hour(rep.time), concat(MONTHNAME(rep.time) ,' ', YEAR(rep.time))" 
			  + " order by rep.time ) ";
		return sql;
	}
	
	public String findAnnualGeneralEnergyPerHourChargN2(Timestamp startDate, Timestamp endDate) {
		String sql = " ( SELECT  concat(\"hours2_\",Hour(rep.time))  as timeRange, sum(rep.act_energy_tot)/ 1000 as energy, date(rep.time) as date" 
		      + " FROM rep_energy_5min rep"
		      +	" INNER JOIN groups_phase g_ph ON g_ph.phase_id = rep.phase_id" 
			  +	" INNER JOIN `groups` g ON g.id = g_ph.groups_id" 
			  + " where g.name = 'Chambre negative 2' and rep.time >= '"+startDate+"' and rep.time < '"+endDate+"'"  
			  + " group by Hour(rep.time), concat(MONTHNAME(rep.time) ,' ', YEAR(rep.time))" 
			  + " order by rep.time ) ";
		return sql;
	}
	
	
	public String findAnnualGeneralEnergyPerMonthByTimeRange(String timeRange, String startTime, String endTime, Timestamp startDate, Timestamp endDate) {
		String sql = " ( SELECT '"+timeRange+"' as timeRange, sum(rep.act_energy_tot)/ 1000 as energy, date(rep.time) as date" 
		      + " FROM rep_energy_5min rep"
		      +	" INNER JOIN groups_phase g_ph ON g_ph.phase_id = rep.phase_id" 
			  +	" INNER JOIN `groups` g ON g.id = g_ph.groups_id" 
			  + " where g.type = 'General'  and rep.time >= '"+startDate+"' and rep.time < '"+endDate+"'"  
			  + " and time(rep.time) >= time('"+ startTime +":00') and time(rep.time) < time('"+ endTime +":00')"
			  + " group by concat(MONTHNAME(rep.time) ,' ', YEAR(rep.time))" 
			  + " order by rep.time ) ";
		return sql;
	}
	
	public String findAnnualGeneralEnergyPerMonthByHourRange(String timeRange, String startHour, String endHour, Timestamp startDate, Timestamp endDate) {
		String sql = " ( SELECT '"+timeRange+"' as timeRange, sum(rep.act_energy_tot)/ 1000 as energy, date(rep.time) as date" 
					      + " FROM rep_energy_hour rep"
					      +	" INNER JOIN groups_phase g_ph ON g_ph.phase_id = rep.phase_id" 
						  +	" INNER JOIN `groups` g ON g.id = g_ph.groups_id" 
						  + " where g.type = 'General'  and rep.time >= '"+startDate+"' and rep.time < '"+endDate+"'"  
						  + " and time(rep.time) >= time('"+ startHour +":00') and time(rep.time) < time('"+ endHour +":00')"
						  + " group by concat(MONTHNAME(rep.time) ,' ', YEAR(rep.time))" 
						  + " order by rep.time ) ";
		return sql;
	}
	
	public String findAnnualGeneralEnergyPerMonthByMidnightTimeRange(String timeRange, String startTime, String endTime, Timestamp startDate, Timestamp endDate) {
		String sql = " ( SELECT '"+timeRange+"' as timeRange, sum(rep.act_energy_tot)/ 1000 as energy, date(rep.time) as date" 
		      + " FROM rep_energy_5min rep"
		      +	" INNER JOIN groups_phase g_ph ON g_ph.phase_id = rep.phase_id" 
			  +	" INNER JOIN `groups` g ON g.id = g_ph.groups_id" 
			  + " where g.type = 'General'  and rep.time >= '"+startDate+"' and rep.time < '"+endDate+"'" 
			  + " and ( time(rep.time) >= time('"+ startTime +":00')"
			  	+ " or ( time(rep.time) < time('"+ endTime +":00') ) )"
			  	+ " group by concat(MONTHNAME(rep.time) ,' ', YEAR(rep.time))" 
				  + " order by rep.time ) ";
		return sql;
	}
	
	
	
	public String findAnnualGeneralEnergyPerMonthByMidnightHourRange(String timeRange, String startHour, String endHour, Timestamp startDate, Timestamp endDate) {
		String sql = " ( SELECT '"+timeRange+"' as timeRange, sum(rep.act_energy_tot)/ 1000 as energy, date(rep.time) as date" 
					      + " FROM rep_energy_hour rep"
					      +	" INNER JOIN groups_phase g_ph ON g_ph.phase_id = rep.phase_id" 
						  +	" INNER JOIN `groups` g ON g.id = g_ph.groups_id" 
						  + " where g.type = 'General'  and rep.time >= '"+startDate+"' and rep.time < '"+endDate+"'" 
						  + " and ( time(rep.time) >= time('"+ startHour +":00') "
						  		+ " or ( time(rep.time) < time('"+ endHour +":00')) ) "
						  + " group by concat(MONTHNAME(rep.time) ,' ', YEAR(rep.time))" 
						  + " order by rep.time ) ";
		return sql;
	}
	 
	public List<AnnualReportDto> findAnnualGeneralEnergyPerMonthByHourRange(Timestamp startDate, Timestamp endDate) {
		List<AnnualReportDto> result = null; 
		List<String> timeRanges = Arrays.asList("6:30 - 8:30", "8:30 - 13:30", "13:30 - 19" );
		List<String> hourRanges = Arrays.asList("7 - 18", "18 - 21", "19 - 22" );
		
		String sql ="";
		String[] start_end;
		
		for(String range : timeRanges) {
			if(!sql.isEmpty()) {
				sql+= " UNION ";
			}
			start_end = range.split(" - ");
			sql += findAnnualGeneralEnergyPerMonthByTimeRange(range, start_end[0], start_end[1], startDate, endDate);
		}
		
		for(String range : hourRanges) {
			if(!sql.isEmpty()) {
				sql+= " UNION ";
			}
			start_end = range.split(" - ");
			sql += findAnnualGeneralEnergyPerMonthByHourRange(range, start_end[0], start_end[1], startDate, endDate);
		}
		
		sql += " UNION " +findAnnualGeneralEnergyPerMonthByMidnightTimeRange("22 - 06:30" , "22","06:30", startDate, endDate)
		+ " UNION " + findAnnualGeneralEnergyPerMonthByMidnightHourRange( "21 - 7", "21","7", startDate, endDate);
		
		sql += " UNION " +findAnnualGeneralEnergyPerHour(startDate, endDate);
		
		sql += " UNION " +findAnnualGeneralEnergyPerHourChargN1(startDate, endDate);
		
		//sql += " UNION " +findAnnualGeneralEnergyPerHourChargN2(startDate, endDate);

		 System.out.println("sql****: "+ sql);
		try {
			SQLQuery sqlQuery = em.createNativeQuery(sql).unwrap(SQLQuery.class);
		    Query query = sqlQuery
		    		.addScalar("timeRange", new StringType())
					.addScalar("energy", new DoubleType())
					.addScalar("date", new TimestampType())
		    		.setResultTransformer(Transformers.aliasToBean(AnnualReportDto.class));
		    result= query.getResultList();
		    em.clear();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public List<AnnualReportDto> findAnnualGeneralEnergyPerMonthByInstallation (Timestamp startDate, Timestamp endDate) {
		List<AnnualReportDto> result = null; 
		String sql = "SELECT concat(z.name, ' - ',i.name) as timeRange, sum(rep.act_energy_tot)/ 1000 as energy, date(concat(year(rep.time),'-', month(rep.time),'-',1)) as date" 
				+ " FROM rep_energy_hour rep" 
				+ " join phase ph on ph.id = rep.phase_id" 
				+ " join device d on ph.device_id = d.id" 
				+ " join installation i on  i.id = d.installation_id" 
				+ " join zones z on z.id_zone = i.zone_id" 
				+ " where rep.time >= '" + startDate + "' and rep.time < '" + endDate + "'" 
				+ " group by concat(MONTHNAME(rep.time) ,' ', YEAR(rep.time)), i.name" 
				+ " order by rep.time , i.name";
		try {
			SQLQuery sqlQuery = em.createNativeQuery(sql).unwrap(SQLQuery.class);
		    Query query = sqlQuery
		    		.addScalar("timeRange", new StringType())
					.addScalar("energy", new DoubleType())
					.addScalar("date", new TimestampType())
		    		.setResultTransformer(Transformers.aliasToBean(AnnualReportDto.class));
		    result= query.getResultList();
		    em.clear();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	
}
