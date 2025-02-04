package rimenergyapi.userData.repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.DoubleType;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StringType;
import org.hibernate.type.TimestampType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import rimenergyapi.dto.SensorValuesDto;

@Repository
public class ArchiveMetosRepository {
	@Autowired
	@Qualifier("tenantEntityManager")
	private EntityManager em;

	public List<SensorValuesDto> findSensorsByTimeRangeAndStationId(String stationId, List<String> sensorIds, Timestamp from, Timestamp to) {
		String sql = "SELECT  s.installation_id as installationId, time as lastTime, sensor_id as sensorId, "
				+ " rt.station_id as stationId, avg_value as avg, min_value as min, max_value as max, "
				+ " sum_value as sum, last_value as last, time_value as time"
				 + " FROM archive_metos rt " 
				 + " left join installation_station s on s.station_id = rt.station_id"
				 + " where rt.station_id = ? and  rt.time >= ?"
				 + " and sensor_id in ( '" + sensorIds.stream().collect(Collectors.joining("','")) + "' )";
		List<SensorValuesDto> result = null;
		try {
			javax.persistence.Query nativeQuery  = em.createNativeQuery(sql)
					.setParameter(1, stationId)
					.setParameter(2, from);
			if( to != null) {
				sql+= " and rt.time <= ? ;";
				nativeQuery = em.createNativeQuery(sql)
						.setParameter(1, stationId)
						.setParameter(2, from)
						.setParameter(3, to);
			}
			SQLQuery	sqlQuery = nativeQuery.unwrap(SQLQuery.class);
			Query query = sqlQuery.addScalar("installationId",new IntegerType())
					.addScalar("lastTime", new TimestampType()).addScalar("stationId", new StringType())
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
	
}
