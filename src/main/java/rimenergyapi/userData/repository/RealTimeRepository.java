package rimenergyapi.userData.repository;

import java.io.PrintStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.BigDecimalType;
import org.hibernate.type.BooleanType;
import org.hibernate.type.DoubleType;
import org.hibernate.type.IntegerType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.hibernate.type.TimestampType;
import org.hibernate.type.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import rimenergyapi.dto.GroupReportCellDto;
import rimenergyapi.dto.InstallationReportCellDto;
import rimenergyapi.dto.OutputRtDto;
import rimenergyapi.dto.RealTimePointDto;
import rimenergyapi.dto.SensorValuesDto;
import rimenergyapi.userData.model.CalTemperatureDto;
import rimenergyapi.userData.model.GazDto;
import rimenergyapi.userData.model.IoDataDto;
import rimenergyapi.userData.model.IoRtDto;
import rimenergyapi.userData.model.RtEnergyDto;
import rimenergyapi.userData.model.RtInputTuple;
import rimenergyapi.userData.model.RtOutputTuple;
import rimenergyapi.userData.model.RtPointTuple;
import rimenergyapi.userData.model.RtSondeTuple;
import rimenergyapi.userData.model.RtUserEnergyTuple;

@Repository
public class RealTimeRepository {

	@Autowired
	@Qualifier("tenantEntityManager")
	private EntityManager em;


	@Value("${energydivise}")
	String energydivise;

	public List<CalTemperatureDto> findAllRtEnergySondeByInstallation(int id) {
		List<CalTemperatureDto> rtEnergies = new ArrayList<CalTemperatureDto>();
		String query = "SELECT s.id as sondeId, ct.last_temperature as lastTemperatue, ct.last_humidity as lastHumidity"
				+ ", ct.last_time as lastTime, IFNULL(ct.battery_percentage, 0) as lastBattery, s.installation_id as installationId"
				+ " FROM sonde s, calc_temperature ct "
				+ "  where ct.id_sonde = s.id and s.installation_id = " + id;
		try {
			rtEnergies = em.createNativeQuery(query)
					.unwrap(SQLQuery.class)
					.addScalar("sondeId", new DoubleType())
					.addScalar("lastTemperatue", new IntegerType())
					.addScalar("lastHumidity", new IntegerType())
					.addScalar("lastTime", new TimestampType())
					.addScalar("lastBattery", new IntegerType())
					.setResultTransformer(Transformers.aliasToBean(CalTemperatureDto.class))
					.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rtEnergies;
	}

	public List<GazDto> findAllRtEnergyGazByInstallation() {
		List<GazDto> rtEnergies = new ArrayList<>();
		String sql = "SELECT g.id as gazId, sum(a.in1) as in1, max(a.time) as time "
				+ " FROM archive_io a, device d, gaz g,  installation it "
				+ " where a.id_device = d.id and d.id = g.device_id  and it.type= 'gaz'and it.id = g.installation_id"
				+ " and a.time > timestamp(current_date) and a.time < timestamp(DATE_ADD(current_date, interval 1 day)) ";

		try {
			SQLQuery sqlQuery = em.createNativeQuery(sql).unwrap(SQLQuery.class);
			Query query = sqlQuery.addScalar("gazId", new IntegerType()).addScalar("in1", new BigDecimalType())
					.addScalar("time", new TimestampType())
					.setResultTransformer(Transformers.aliasToBean(GazDto.class));
			rtEnergies = query.getResultList();
			em.clear();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rtEnergies;
	}

	public List<GazDto> findAllRtEnergyGazByInstallationDate(Timestamp startTime, Timestamp endTime) {
		List<GazDto> rtEnergies = new ArrayList<>();
		String sql = "SELECT g.id as gazId, sum(a.in1) as in1, max(a.time) as time "
				+ " FROM archive_io a, device d, gaz g,  installation it "
				+ " where a.id_device = d.id and d.id = g.device_id  and it.type= 'gaz'"
				+ " and a.time  BETWEEN " + startTime + " AND " + endTime + " and it.id = g.installation_id; ";

		try {
			SQLQuery sqlQuery = em.createNativeQuery(sql).unwrap(SQLQuery.class);
			Query query = sqlQuery.addScalar("gazId", new IntegerType()).addScalar("in1", new BigDecimalType())
					.addScalar("time", new TimestampType())
					.setResultTransformer(Transformers.aliasToBean(GazDto.class));
			rtEnergies = query.getResultList();
			em.clear();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rtEnergies;
	}
	
	public List<Object[]> findAllRtEnergyCategorie() {
		List<Object[]> rtEnergies = new ArrayList<Object[]>();
		String query = "SELECT g.category_id, max(tr.time), sum(act_energy_day) FROM rt_energy tr, groups_phase gp, `groups` g "
				+ " where tr.phase_id = gp.phase_id and gp.groups_id = g.id and tr.time > timestamp(current_date) "
				+ " group by g.category_id ";
		try {
			rtEnergies = em.createNativeQuery(query).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rtEnergies;
	}

		List<Object[]> rtEnergies = new ArrayList<Object[]>();
		public List<Object[]> findAllRtEnergyInstallation() {
		String query = "SELECT g.installation_id, max(tr.time), sum(act_energy_day) FROM rt_energy tr, groups_phase gp, `groups` g "
				+ " where tr.phase_id = gp.phase_id and gp.groups_id = g.id and g.type='General' and tr.time > timestamp(current_date) "
				+ " group by g.installation_id ";
		try {
			rtEnergies = em.createNativeQuery(query).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rtEnergies;
	}

	public List<Object[]> findAllRtEnergyInstallationByZone(int zoneId) {
		List<Object[]> rtEnergies = new ArrayList<Object[]>();
		String query = "SELECT g.installation_id, max(tr.time), sum(act_energy_day / " + energydivise
				+ ") FROM rt_energy tr, groups_phase gp, `groups` g, installation i "
				+ " where tr.phase_id = gp.phase_id and gp.groups_id = g.id and  g.type='General' and g.installation_id = i.id and tr.time > timestamp(current_date) and i.zone_id = "
				+ zoneId + " group by g.installation_id ";
		try {
			rtEnergies = em.createNativeQuery(query).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rtEnergies;
	}

	public List<Object[]> findAllRtEnergyInstallationByZoneSingleInstallation(int zoneId) {
		List<Object[]> rtEnergies = new ArrayList<Object[]>();
		String query = "SELECT g.installation_id, max(tr.time), sum(act_energy_day / " + energydivise
				+ ") FROM rt_energy tr, groups_phase gp, `groups` g, installation i "
				+ " where tr.phase_id = gp.phase_id and gp.groups_id = g.id and g.installation_id = i.id  and tr.time > timestamp(current_date) and i.zone_id = "
				+ zoneId + " group by g.installation_id ";
		try {
			rtEnergies = em.createNativeQuery(query).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rtEnergies;
	}

	public List<Object[]> findAllRtEnergyZone() {
		List<Object[]> rtEnergies = new ArrayList<Object[]>();
		String query = "SELECT it.zone_id as id, max(tr.time),  sum(act_energy_day / " + energydivise
				+ ") as actEnergyDay FROM rt_energy tr, groups_phase gp, `groups` g , installation it "
				+ " where tr.phase_id = gp.phase_id and gp.groups_id = g.id and  g.type='General'  and tr.time > timestamp(current_date) and g.installation_id = it.id "
				+ " group by it.zone_id";
		try {
			rtEnergies = em.createNativeQuery(query).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rtEnergies;
	}

	public List<Object[]> findAllRtEnergyGroupsByInstallation(int id) {
		List<Object[]> rtEnergies = new ArrayList<Object[]>();
		String query = "SELECT g.id as id, max(tr.time)"
				+ ",  sum(act_energy_day / " + energydivise	+ ") as actEnergyDay"
				+ ",  sum(react_energy_day / " + energydivise	+ ") as reactEnergyDay"
				+ ",  sum(fund_energy_day / " + energydivise	+ ") as fundEnergyDay"
				+ ",  sum(app_energy_day / " + energydivise	+ ") as appEnergyDay"
				+ ", g.category_id as category, g.type as type FROM rt_energy tr, groups_phase gp, `groups` g"
				+ " where tr.phase_id = gp.phase_id and gp.groups_id = g.id and tr.time > timestamp(current_date) and g.installation_id = "
				+ id + " group by g.id";
		try {
			rtEnergies = em.createNativeQuery(query).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rtEnergies;
	}

	public List<Object[]> findAllRtEnergyphasesByGroup(int id) {
		// TODO Auto-generated method stub
		List<Object[]> rtEnergies = new ArrayList<Object[]>();
		String query = "SELECT tr.phase_id as id, tr.time,  act_energy_day  / " + energydivise
				+ " as actEnergyDay FROM rt_energy tr, groups_phase gp"
				+ " where tr.phase_id = gp.phase_id  and tr.time > timestamp(current_date) and gp.groups_id = " + id
				+ "";
		try {
			rtEnergies = em.createNativeQuery(query).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rtEnergies;
	}

	public List<Object[]> findAllRtTricityphasesByGroup(int id) {
		// TODO Auto-generated method stub
		List<Object[]> rtEnergies = new ArrayList<Object[]>();
		String query = "SELECT tr.phase_id as id, tr.time, voltage_inst as vmoy, current_inst as cmoy, power_inst as pmoy, phase_inst as phmoy,power_inst  FROM rt_tricity tr, groups_phase gp"
				+ " where tr.phase_id = gp.phase_id  and tr.time > timestamp(current_date) and gp.groups_id = " + id
				+ "";
		try {
			rtEnergies = em.createNativeQuery(query).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rtEnergies;
	}

	public List<RealTimePointDto> findRTPointByDevice(int deviceId) {
		List<RealTimePointDto> result = new ArrayList<>();
		String sql = "SELECT c_pt.point_id as pointId, c_pt.value as pointValue, c_pt.setpoint_value as setpointValue, CONCAT(d_f.label,' - ',pt.label )as pointLbl"
				+ " FROM calc_archive_point c_pt" + " Inner join point pt on pt.id= c_pt.point_id"
				+ " Inner join device_froid d_f on d_f.id=c_pt.device_id" + " where d_f.id=" + deviceId + ";";
		try {
			SQLQuery sqlQuery = em.createNativeQuery(sql).unwrap(SQLQuery.class);
			Query query = sqlQuery.addScalar("pointId", new IntegerType()).addScalar("pointValue", new DoubleType())
					.addScalar("setpointValue", new DoubleType()).addScalar("pointLbl", new StringType())
					.setResultTransformer(Transformers.aliasToBean(RealTimePointDto.class));
			result = query.getResultList();
			em.clear();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public List<Object[]> findAllRtTricityZone() {
		List<Object[]> rtTricity = new ArrayList<Object[]>();
		String query = "SELECT it.zone_id as id, max(tr.time),  sum(abs(power_inst)) as powerDay FROM rt_tricity tr, groups_phase gp, `groups` g , installation it \n"
				+ "where tr.phase_id = gp.phase_id and gp.groups_id = g.id and  g.type='General'  and tr.time > timestamp(current_date) and g.installation_id = it.id \n"
				+ "group by it.zone_id";
		try {
			rtTricity = em.createNativeQuery(query).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rtTricity;
	}

	public List<Object[]> findAllRttTricityInstallationByZone(int zoneId) {
		List<Object[]> rttTricity = new ArrayList<Object[]>();
		String query = "SELECT g.installation_id, max(tr.time), sum(abs(power_inst)) FROM rt_tricity tr, groups_phase gp, `groups` g, installation i "
				+ " where tr.phase_id = gp.phase_id and gp.groups_id = g.id and  g.type='General' and g.installation_id = i.id and tr.time > timestamp(current_date) and i.zone_id = "
				+ zoneId + " group by g.installation_id ";
		try {
			rttTricity = em.createNativeQuery(query).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rttTricity;
	}

	public List<Object[]> findAllRtTricityGroupsByInstallation(int id) {
		List<Object[]> rttTricity = new ArrayList<Object[]>();
		String query = "SELECT g.id as id, max(tr.time),  sum(abs(power_inst)) as powerDay, g.category_id as category, g.type as type FROM rt_tricity tr,"
				+ " groups_phase gp, `groups` g"
				+ " where tr.phase_id = gp.phase_id and gp.groups_id = g.id and tr.time > timestamp(current_date) and g.installation_id = "
				+ id + " group by g.id";
		try {
			rttTricity = em.createNativeQuery(query).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rttTricity;
	}

	public List<Object[]> findAllRecapEnergyZone(Timestamp startTime, Timestamp endTime) {
		List<Object[]> rtRecoveryEnergies = new ArrayList<Object[]>();
		String query = "SELECT it.zone_id as id, max(tr.time), sum(act_energy_tot  / " + energydivise
				+ ") as actEnergyDay FROM rep_energy_hour tr, groups_phase gp, `groups` g , installation it "
				+ " where tr.phase_id = gp.phase_id and gp.groups_id = g.id and  g.type='General'  and tr.time  "
				+ " BETWEEN '" + startTime + "' AND '" + endTime + "' "
				+ " and g.installation_id = it.id  group by it.zone_id";
		try {
			rtRecoveryEnergies = em.createNativeQuery(query).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rtRecoveryEnergies;
	}

	public List<Object[]> findAllRecapEnergyInstallationByZone(int zoneId, Timestamp startTime, Timestamp endTime) {
		List<Object[]> rtRecoveryEnergies = new ArrayList<Object[]>();
		String query = "SELECT g.installation_id, max(tr.time), sum(act_energy_tot  / " + energydivise
				+ ") as actEnergyDay FROM rep_energy_hour tr, groups_phase gp, `groups` g, installation i "
				+ " where tr.phase_id = gp.phase_id and gp.groups_id = g.id  and g.installation_id = i.id and tr.time "
				+ " BETWEEN '" + startTime + "' AND '" + endTime + "' " + " and i.zone_id = " + zoneId
				+ " group by g.installation_id ";
		try {
			rtRecoveryEnergies = em.createNativeQuery(query).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rtRecoveryEnergies;
	}

	public List<Object[]> findAllRecapEnergyGroupsByInstallation(int id, Timestamp startTime, Timestamp endTime) {
		List<Object[]> rtRecoveryEnergies = new ArrayList<Object[]>();
		String query = "SELECT g.id as id, max(tr.time),  sum(act_energy_tot  / " + energydivise
				+ ") as actEnergyDay , g.type as type FROM rep_energy_hour tr, groups_phase gp, `groups` g"
				+ " where tr.phase_id = gp.phase_id and gp.groups_id = g.id and tr.time " + " BETWEEN '" + startTime
				+ "' AND '" + endTime + "' " + "and g.installation_id = " + id + " group by g.id";
		try {
			rtRecoveryEnergies = em.createNativeQuery(query).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rtRecoveryEnergies;
	}

	public List<Object[]> findAllRecoveryEnergyphasesByGroup(int id, Timestamp startTime, Timestamp endTime) {
		// TODO Auto-generated method stub
		List<Object[]> rtRecoveryEnergies = new ArrayList<Object[]>();
		String query = "SELECT tr.phase_id as id, tr.time,  act_energy_tot / " + energydivise
				+ " as recovery FROM rep_energy_hour tr, groups_phase gp"
				+ " where tr.phase_id = gp.phase_id  and tr.time " + " BETWEEN '" + startTime + "' AND '" + endTime
				+ "' " + " and gp.groups_id = " + id + "";
		try {
			rtRecoveryEnergies = em.createNativeQuery(query).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rtRecoveryEnergies;
	}

	public List<Object[]> findAllRtEnergyPointByInstallation(int id) {
		List<Object[]> rtEnergiesSondes = new ArrayList<Object[]>();
		/*
		 * String query
		 * =" SELECT p.id,  Any_value(ctp.value) as value, Any_value(ctp.setpoint_value) as setpointValue ,  Any_value(ctp.last_time), Any_value(p.device_id) as deviceId "
		 * + " FROM point p, calc_archive_point ctp , installation inst " +
		 * " where ctp.point_id = p.id and p.installation_id = " + id +
		 * " group by p.id,p.device_id ";
		 */
		String query = "select ctp.point_id ,Any_value(ctp.value) as value, Any_value(ctp.setpoint_value) as setpointValue "
				+ ", Any_value(ctp.last_time) as lastTime, Any_value(p.device_id) as deviceId "
				+ " from calc_archive_point ctp, point p "
				+ " where ctp.device_id = p.device_id and p.installation_id = " + id
				+ " group by ctp.point_id, p.device_id ";
		try {
			rtEnergiesSondes = em.createNativeQuery(query).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rtEnergiesSondes;
	}
	

	public List<OutputRtDto> findAllRtOutputByInstallationId(int installationId) {
		List<OutputRtDto> outputRtDtos = null;
		String sql = "SELECT o.id AS id, clc.last_state AS isOn" 
				+ " FROM output o" 
				+ " JOIN calc_output clc on clc.output_id = o.id"
				+ " WHERE o.installation_id = " + installationId;
		try {
			outputRtDtos = new ArrayList<OutputRtDto>();
			SQLQuery sqlQuery = em.createNativeQuery(sql).unwrap(SQLQuery.class);
		    Query query = sqlQuery.addScalar("id", new IntegerType())
		    					.addScalar("isOn", new BooleanType())
		    		.setResultTransformer(Transformers.aliasToBean(OutputRtDto.class));
		    outputRtDtos = query.getResultList();
		   em.clear();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return outputRtDtos;
	}

	public Object[] findAllRtEnergyInstallationById(int idInstallation) {
		Object[] rtEnergie =  null;
		String query = "SELECT g.installation_id, max(tr.time), sum(act_energy_day / " + energydivise
				+ ") FROM rt_energy tr, groups_phase gp, `groups` g, installation i "
				+ " where tr.phase_id = gp.phase_id and gp.groups_id = g.id and  g.type='General' and g.installation_id = i.id and tr.time > timestamp(current_date) and g.installation_id = "
				+ idInstallation;
		try {
			rtEnergie = (Object[]) em.createNativeQuery(query).getResultList().get(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rtEnergie;
	}
	public Object[] findAllRttTricityInstallationById(int idInstallation) {
		Object[] rttTricity = null;
		String query = "SELECT g.installation_id, max(tr.time), sum(abs(power_inst)) FROM rt_tricity tr, groups_phase gp, `groups` g, installation i "
				+ " where tr.phase_id = gp.phase_id and gp.groups_id = g.id and  g.type='General' and g.installation_id = i.id and tr.time > timestamp(current_date) and g.installation_id = "
				+ idInstallation;
		try {
			rttTricity = (Object[]) em.createNativeQuery(query).getResultList().get(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rttTricity;
	}
	
	public List<SensorValuesDto> findAllRtSensors(){
		String sql = "SELECT  s.installation_id as installationId, last_time as lastTime, sensor_id as sensorId, rt.station_id as stationId, avg_value as avg, min_value as min, max_value as max, sum_value as sum, last_value as last, time_value as time"
				 + " FROM rt_sensor rt " 
				 + " left join installation_station s on s.station_id = rt.station_id;";
		List<SensorValuesDto> result = null;
		try {
			SQLQuery sqlQuery = em.createNativeQuery(sql).unwrap(SQLQuery.class);
			Query query = sqlQuery.addScalar("installationId",new IntegerType()).addScalar("lastTime", new TimestampType()).addScalar("stationId", new StringType())
					.addScalar("sensorId", new StringType()).addScalar("avg", new DoubleType())
					.addScalar("min", new DoubleType()).addScalar("max", new DoubleType())
					.addScalar("sum", new DoubleType()).addScalar("last", new DoubleType())
					.addScalar("time", new DoubleType())
					.setResultTransformer(Transformers.aliasToBean(SensorValuesDto.class));
			result = query.getResultList();
			em.clear();
		} catch (Exception e) {
			 e.printStackTrace();
		}
		return result;
	}

	public List<Object[]> findAllRtEnergyInputsByInstallation(int installationId) {
		List<Object[]> rtEnergies = new ArrayList<Object[]>();
		String query = "SELECT  i.id, ct.last_value, ct.last_time "
						+ " FROM calc_input ct"
						+ " join input i on i.id = ct.id_input and  i.installation_id = "+ installationId ;
		try {
			rtEnergies = em.createNativeQuery(query).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rtEnergies;

	}
	
	//################################

	public List<Object[]> getLastDayW(){
		List<Object[]> lastDayEnergies = new ArrayList<Object[]>();
		String sql ="SELECT g.installation_id, sum(act_energy_tot)"
		+ " FROM rep_energy_day rep"
		+ " join groups_phase gph on gph.phase_id = rep.phase_id"
		+ " join `groups` g on g.id = gph.groups_id"
		+ " where g.type= 'General'"
		+ " and time = date(now()+ interval 1 Hour) - interval 1 day"
		+ " group by g.installation_id;";
		try {
			lastDayEnergies = em.createNativeQuery(sql).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lastDayEnergies;
	}
	
	public List<RtUserEnergyTuple> findAllRtEnergy(){
		List<RtUserEnergyTuple> RtEnergyTuple = null;
		String sql = " SELECT i.zone_id as zoneId, g.installation_id as installationId, gp.groups_id as groupId, rt.phase_id as phaseId"
				+ " , g.category_id as categoryId, g.type as groupType, IFNULL(g.father_id, 0) as groupFatherId"
				+ " , rt.time"
				+ " , act_energy_day  / " + energydivise + " as actEnergyDay"
				+ " , react_energy_day / " + energydivise + " as reactEnergyDay"
				+ " , fund_energy_day / " + energydivise + " as fundEnergyDay"
				+ " , app_energy_day / " + energydivise + " as appEnergyDay"
				+ " , voltage_inst as vInst, current_inst as cInst, power_inst as pInst, phase_inst as phInst"
				+ " FROM rt_energy rt, groups_phase gp, rt_tricity tr, installation i, `groups` g"
				+ " where rt.phase_id = tr.phase_id and rt.phase_id = gp.phase_id and gp.groups_id = g.id "
				+ " and g.installation_id = i.id and rt.time > timestamp(current_date)"
				+ " and i.nature like 'consumption'";
		try {
			RtEnergyTuple = new ArrayList<RtUserEnergyTuple>();
			SQLQuery sqlQuery = em.createNativeQuery(sql).unwrap(SQLQuery.class);
		    Query query = sqlQuery
		    		.addScalar("groupFatherId", new IntegerType())
		    		.addScalar("zoneId", new IntegerType())
		    		.addScalar("installationId", new IntegerType())
		    		.addScalar("groupId", new IntegerType())
		    		.addScalar("phaseId", new IntegerType())
		    		.addScalar("categoryId", new IntegerType())
		    		.addScalar("groupType", new StringType())
		    		.addScalar("groupFatherId", new IntegerType())
		    		.addScalar("time", new TimestampType())
		    		.addScalar("actEnergyDay", new DoubleType())
		    		.addScalar("reactEnergyDay", new DoubleType())
		    		.addScalar("fundEnergyDay", new DoubleType())
		    		.addScalar("appEnergyDay", new DoubleType())
		    		.addScalar("vInst", new DoubleType())
		    		.addScalar("cInst", new DoubleType())
		    		.addScalar("pInst", new DoubleType())
		    		.addScalar("phInst", new DoubleType())
		    		.setResultTransformer(Transformers.aliasToBean(RtUserEnergyTuple.class));
		    RtEnergyTuple = query.getResultList();
		   em.clear();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return RtEnergyTuple;
	}
	
	public List<RtUserEnergyTuple> findAllRtProvider(){
		List<RtUserEnergyTuple> RtEnergyTuple = null;
		String sql = " SELECT i.zone_id as zoneId, g.installation_id as installationId, gp.groups_id as groupId, rt.phase_id as phaseId"
				+ " , g.category_id as categoryId, g.type as groupType, IFNULL(g.father_id, 0) as groupFatherId"
				+ " , rt.time"
				+ " , act_energy_day  / " + energydivise + " as actEnergyDay"
				+ " , react_energy_day / " + energydivise + " as reactEnergyDay"
				+ " , fund_energy_day / " + energydivise + " as fundEnergyDay"
				+ " , app_energy_day / " + energydivise + " as appEnergyDay"
				+ " , voltage_inst as vInst, current_inst as cInst, power_inst as pInst, phase_inst as phInst"
				+ " FROM rt_energy rt, groups_phase gp, rt_tricity tr, installation i, `groups` g"
				+ " where rt.phase_id = tr.phase_id and rt.phase_id = gp.phase_id and gp.groups_id = g.id "
				+ " and g.installation_id = i.id and rt.time > timestamp(current_date)"
				+ " and i.nature like 'provider'";
		try {
			RtEnergyTuple = new ArrayList<RtUserEnergyTuple>();
			SQLQuery sqlQuery = em.createNativeQuery(sql).unwrap(SQLQuery.class);
		    Query query = sqlQuery
		    		.addScalar("groupFatherId", new IntegerType())
		    		.addScalar("zoneId", new IntegerType())
		    		.addScalar("installationId", new IntegerType())
		    		.addScalar("groupId", new IntegerType())
		    		.addScalar("phaseId", new IntegerType())
		    		.addScalar("categoryId", new IntegerType())
		    		.addScalar("groupType", new StringType())
		    		.addScalar("groupFatherId", new IntegerType())
		    		.addScalar("time", new TimestampType())
		    		.addScalar("actEnergyDay", new DoubleType())
		    		.addScalar("reactEnergyDay", new DoubleType())
		    		.addScalar("fundEnergyDay", new DoubleType())
		    		.addScalar("appEnergyDay", new DoubleType())
		    		.addScalar("vInst", new DoubleType())
		    		.addScalar("cInst", new DoubleType())
		    		.addScalar("pInst", new DoubleType())
		    		.addScalar("phInst", new DoubleType())
		    		.setResultTransformer(Transformers.aliasToBean(RtUserEnergyTuple.class));
		    RtEnergyTuple = query.getResultList();
		   em.clear();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return RtEnergyTuple;
	}
	
	public List<RtInputTuple> findAllRtInput() {
		List<RtInputTuple> rtEnergies = new ArrayList<RtInputTuple>();
		String query = 
				"SELECT it.zone_id as zoneId, it.id as installationId, i.id as inputId , ct.last_value as value, ct.last_time as time"
				+ " FROM calc_input ct"
				+ " join input i on i.id = ct.id_input"
				+ " join installation it on it.id = i.installation_id;";
		try {
			rtEnergies = em.createNativeQuery(query)
					.unwrap(SQLQuery.class)
		    		.setResultTransformer(Transformers.aliasToBean(RtInputTuple.class))
					.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rtEnergies;
	}
	
	public List<RtSondeTuple> findAllRtSonde() {
		List<RtSondeTuple> rtEnergies = new ArrayList<RtSondeTuple>();
		String query = "SELECT s.id as sondeId,  IFNULL( ct.last_temperature, 0) as lastTemperatue,  IFNULL( ct.last_humidity, 0) as lastHumidity"
				+ " , IFNULL( ct.last_time, '1970-01-01 00:00:00')  as lastTime, IFNULL(ct.battery_percentage, 0) as lastBattery, i.id as installationId,"
				+ " i.zone_id as zoneId"
				+ " FROM sonde s"
				+ " left join calc_temperature ct on ct.id_sonde = s.id"
				+ " join installation i on i.id = s.installation_id;";
		try {
			rtEnergies = em.createNativeQuery(query)
					.unwrap(SQLQuery.class)
					.addScalar("zoneId", new IntegerType())
					.addScalar("installationId", new IntegerType())
					.addScalar("sondeId", new DoubleType())
					.addScalar("lastTemperatue", new IntegerType())
					.addScalar("lastHumidity", new IntegerType())
					.addScalar("lastTime", new TimestampType())
					.addScalar("lastBattery", new IntegerType())
					.setResultTransformer(Transformers.aliasToBean(RtSondeTuple.class))
					.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rtEnergies;
	}
	
	public List<RtOutputTuple> findAllRtOutput() {
		List<RtOutputTuple> outputRtDtos = new ArrayList<RtOutputTuple>();
		String sql = "SELECT i.zone_id as zoneId, i.id as installationId, o.id AS outputId,"
				+ " clc.last_state AS isOn, po.program_id as programsId, last_checked_time as lastTime"
				+ " FROM output o"
				+ " left JOIN calc_output clc on clc.output_id = o.id"
				+ " left JOIN program_output po on po.output_id = o.id"
				+ " join installation i on i.id = o.installation_id;";
		try {
			outputRtDtos = new ArrayList<RtOutputTuple>();
			SQLQuery sqlQuery = em.createNativeQuery(sql).unwrap(SQLQuery.class);
		    Query query = sqlQuery.addScalar("outputId", new IntegerType())
		    					.addScalar("isOn", new BooleanType())
		    					.addScalar("zoneId", new IntegerType())
		    					.addScalar("installationId", new IntegerType())
		    					.addScalar("programsId", new IntegerType())
		    					.addScalar("lastTime", new TimestampType())
		    		.setResultTransformer(Transformers.aliasToBean(RtOutputTuple.class));
		    outputRtDtos = query.getResultList();
		   em.clear();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return outputRtDtos;
	}
	
	public List<RtPointTuple> findAllRtEnergyPoint() {
		List<RtPointTuple> rtEnergiesSondes = new ArrayList<RtPointTuple>();
		String query = "SELECT Any_value(i.zone_id) as zoneId, Any_value(i.id) as installationId,"
				+ " ctp.point_id as pointId,Any_value(ctp.value) as value, Any_value(ctp.setpoint_value) as setpointValue"
				+ ", Any_value(ctp.last_time) as lastTime, p.device_id as deviceId "
				+ " from calc_archive_point ctp, point p"
				+ " join installation i on i.id = p.installation_id "
				+ " where ctp.device_id = p.device_id"
				+ " group by ctp.point_id, p.device_id; ";
		try {
			rtEnergiesSondes = em.createNativeQuery(query).unwrap(SQLQuery.class)
					.addScalar("zoneId", new IntegerType())
					.addScalar("installationId", new IntegerType())
					.addScalar("pointId", new IntegerType())
					.addScalar("deviceId", new IntegerType())
					.addScalar("value", new DoubleType())
					.addScalar("setpointValue", new DoubleType())
					.addScalar("lastTime", new TimestampType())
		.setResultTransformer(Transformers.aliasToBean(RtPointTuple.class)).getResultList();
			em.clear();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rtEnergiesSondes;
	}

	public List<IoRtDto> findAllRtIo(String period) {
		String timeQuery = "", fromQuery = "last_day_io";
		List<IoRtDto> rtRows = new ArrayList<IoRtDto>();

		try {
			switch (period.toLowerCase()) {
			case "hour":
				timeQuery = " timestamp(date_format(NOW(),  '%Y-%m-%d %H:00:00')) "; fromQuery = "last_hour_io rt";
				break;
			case "week":
				timeQuery = " timestamp(DATE_ADD(CURDATE(), INTERVAL - WEEKDAY(CURDATE()) DAY)) "; fromQuery = "last_week_io rt";
				break;
			case "month":
				timeQuery = " timestamp(date_format(NOW(),  '%Y-%m-01 00:00:00')) "; fromQuery = "last_month_io rt";
				break;

			default: // day
				timeQuery = " timestamp(date(NOW())) "; fromQuery = "last_day_io rt";
				break;
			}
			
			String query = " SELECT rt.io_id AS ioId, sum(rt.value)* t.impulse_value AS value, max(rt.global_count)* t.impulse_value AS globalcount"
					+ " , " + timeQuery + " AS lastTime"
					+ " FROM " + fromQuery
					+ " join io_table t on t.id = rt.io_id "
					+ " GROUP BY rt.io_id;";
			rtRows = em.createNativeQuery(query).unwrap(SQLQuery.class)
					.addScalar("ioId", new IntegerType())
					.addScalar("value", new BigDecimalType())
					.addScalar("globalCount", new BigDecimalType())
					.addScalar("lastTime", new TimestampType())
					.setResultTransformer(Transformers.aliasToBean(IoRtDto.class))
					.getResultList();
			em.clear();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return rtRows;
	}
}
