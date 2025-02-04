package rimenergyapi.userData.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import rimenergyapi.userData.model.RecordDto;

@Repository
public class RtStatisticalRepository {
	
	@Autowired
	@Qualifier("tenantEntityManager")
	private EntityManager em;
	
	@Value("${energydivise}")
	String energydivise;

	public List<RecordDto> findAllHourInstallationsRtStatistical(int daySub,int hourSub){
		List<RecordDto> rtStatistical = new ArrayList<RecordDto>();
		String query = "SELECT g.installation_id as groupId, sum(act_energy_tot / " + energydivise+ ") as sumAct, DATE_ADD(rep.time, INTERVAL "+daySub+" DAY) as time"
		+ " FROM rep_energy_hour rep, groups_phase gp ,groups g "
		+ " where gp.phase_id=rep.phase_id and gp.groups_id=g.id"
		+ " and hour(time) = hour(now())-"+hourSub
		+ " and Date(time)= Date(DATE_SUB(now(), INTERVAL "+daySub+" DAY)) " 
		+ " group by (g.installation_id) ";
		
		try {
			rtStatistical =  em.createNativeQuery(query).unwrap(SQLQuery.class)
					.setResultTransformer(Transformers.aliasToBean(RecordDto.class)).getResultList();
			em.clear();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rtStatistical;
	}
	
	public List<RecordDto> findAllDaysInstallationsRtStatistical(int daySub){
	List<RecordDto> rtStatistical = new ArrayList<RecordDto>();
		String query = " SELECT g.installation_id as groupId, sum(act_energy_tot /" +  energydivise + ") as sumAct, DATE_ADD(rep.time, INTERVAL "+daySub+" DAY) as time"
				+ " FROM rep_energy_hour rep, groups_phase gp , groups g " 
				+ " where gp.phase_id = rep.phase_id and gp.groups_id = g.id"
				+ " and Date(time)= Date(DATE_SUB(now(), INTERVAL "+daySub+" DAY))"
				+ " group by (g.installation_id) ";
		
		try {
			rtStatistical =  em.createNativeQuery(query).unwrap(SQLQuery.class)
					.setResultTransformer(Transformers.aliasToBean(RecordDto.class)).getResultList();
			em.clear();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rtStatistical;
	}

	
	public List<RecordDto> findAllThisDayInstallationsRtStatistical(int daySub){
	List<RecordDto> rtStatistical = new ArrayList<RecordDto>();
		String query = " SELECT g.installation_id as groupId, sum(act_energy_tot / "+  energydivise + " ) as sumAct, DATE_ADD(rep.time, INTERVAL "+daySub+" DAY) as time"
				+ " FROM rep_energy_hour rep, groups_phase gp ,groups g " 
				+ " where gp.phase_id=rep.phase_id and gp.groups_id=g.id " 
				+ " and Date(time)= Date(DATE_SUB(now(), INTERVAL "+daySub+" DAY))" 
				+ " and time(time) < time(now())"
				+ " group by (g.installation_id)";
		try {
			rtStatistical =  em.createNativeQuery(query).unwrap(SQLQuery.class)
					.setResultTransformer(Transformers.aliasToBean(RecordDto.class)).getResultList();
			em.clear();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rtStatistical;
	}
	
	/****zones*****/
	public List<RecordDto> findAllHourZonesRtStatistical(int daySub,int hourSub){
	List<RecordDto> rtStatistical = new ArrayList<RecordDto>();
		String query = "SELECT inst.zone_id as groupId, sum(act_energy_tot / " + energydivise +" ) as sumAct, DATE_ADD(rep.time, INTERVAL "+daySub+" DAY) as time"
				+ " FROM rep_energy_hour rep, groups_phase gp , groups g , installation inst " 
				+ " where gp.phase_id = rep.phase_id and gp.groups_id = g.id and g.installation_id = inst.id "
				+ " and hour(time) = hour(now())-"+hourSub
				+ " and Date(time) = Date(DATE_SUB(now(), INTERVAL "+daySub+" DAY)) " 
				+ " group by (inst.zone_id)";
		
		try {
			rtStatistical =  em.createNativeQuery(query).unwrap(SQLQuery.class)
					.setResultTransformer(Transformers.aliasToBean(RecordDto.class)).getResultList();
			em.clear();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rtStatistical;
	}
	
	public List<RecordDto> findAllDaysZonesRtStatistical(int daySub){
	List<RecordDto> rtStatistical = new ArrayList<RecordDto>();
		String query = " SELECT inst.zone_id as groupId, sum(act_energy_tot / " + energydivise + ") as sumAct, DATE_ADD(rep.time, INTERVAL "+daySub+" DAY) as time"
				+ " FROM rep_energy_hour rep, groups_phase gp ,groups g , installation inst " 
				+ " where gp.phase_id=rep.phase_id and gp.groups_id=g.id and g.installation_id = inst.id "
				+ " and Date(time)= Date(DATE_SUB(now(), INTERVAL "+daySub+" DAY)) " 
				+ " group by (inst.zone_id) ";
		
		try {
			rtStatistical =  em.createNativeQuery(query).unwrap(SQLQuery.class)
					.setResultTransformer(Transformers.aliasToBean(RecordDto.class)).getResultList();
			em.clear();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rtStatistical;
	}

	
	public List<RecordDto> findAllThisDayZonesRtStatistical(int daySub){
	List<RecordDto> rtStatistical = new ArrayList<RecordDto>();
		String query = " SELECT inst.zone_id as groupId, sum(act_energy_tot / " + energydivise + " ) as sumAct, DATE_ADD(rep.time, INTERVAL "+daySub+" DAY) as time"
				+ " FROM rep_energy_hour rep, groups_phase gp ,groups g, installation inst " 
				+ " where gp.phase_id=rep.phase_id and gp.groups_id=g.id and g.installation_id = inst.id" 
				+ " and Date(time)= Date(DATE_SUB(now(), INTERVAL "+daySub+" DAY))" 
				+ " and time(time)<time(now()) " 
				+ " group by (inst.zone_id)";
		
		try {
			rtStatistical =  em.createNativeQuery(query).unwrap(SQLQuery.class)
					.setResultTransformer(Transformers.aliasToBean(RecordDto.class)).getResultList();
			em.clear();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rtStatistical;
	}
		
}
