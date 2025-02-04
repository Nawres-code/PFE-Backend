package rimenergyapi.userData.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import rimenergyapi.dto.ArchiveAlertDto;
import rimenergyapi.dto.GroupReportCellDto;

@Repository
public class NotificationRepository {
	@Autowired
	@Qualifier("tenantEntityManager")
	private EntityManager em;

	public List<String> getFiredAlarmPointList(){
		List<String> result = new ArrayList<String>();
		try {
			return em.createNativeQuery("SELECT concat(df.label,' - ', pt.label) "
					+ " FROM calc_archive_point clc_pt " 
					+ " inner join point pt on pt.id = clc_pt.point_id and pt.device_id = clc_pt.device_id " 
					+ " inner join device_froid df on df.id = pt.device_id " 
					+ " where pt.type = \"alarm\" and clc_pt.value = 1; ").getResultList();
		}catch(Exception e) {
			return result;
		}
	}
	
	public List<ArchiveAlertDto> getUserAlert(int days) {
		List<ArchiveAlertDto> result = new ArrayList<ArchiveAlertDto>();
		try {
			return em.createNativeQuery("SELECT alert_id as id, time as time, calculated_value as calcVal,"
					+ " infracted_value as infractedVal, infracted_operator as infractedOperator,"
					+ " measure_type as measureType, alert_type as alertType, measure_id as measureId,"
					+ "	zone_id as zoneId,"
					+ "	installation_id as installationId,"
					+ "	father_id as fatherId" 
					+ " FROM archive_alert"
					+ " where time > date(DATE_SUB(now(), INTERVAL "+days+" DAY)) "
					+ " order by time desc;")
					.unwrap(SQLQuery.class)
					.setResultTransformer(Transformers.aliasToBean(ArchiveAlertDto.class))
					.getResultList();
		} catch(Exception e) {
			return result;
		}
	}
}
