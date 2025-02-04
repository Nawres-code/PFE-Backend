package rimenergyapi.userData.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import rimenergyapi.repository.UserAlertRepositoryCustom;

@Repository
public class DeviceRepositoryImpl implements DeviceRepositoryCustom {
	@Autowired
	@Qualifier("tenantEntityManager")
	private EntityManager em;
	
	@Override
	public List<Integer> getAllDisconnectedTmpDeviceIds() {
		List<Integer> result = new ArrayList<Integer>();
		String sql = "SELECT  distinct s.device_id" + " FROM calc_temperature clc"
				+ " inner join sonde s on s.id = clc.id_sonde" + " inner join device d on d.id = s.device_id"
				+ " where timeDIFF(now(), clc.last_time)>= '02:00:00' and d.type like \"%temperature%\" ";
		try {
			result = em.createNativeQuery(sql).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<Integer> getAllDisconnectedFroidDeviceIds() {
		List<Integer> result = new ArrayList<Integer>();
		String sql = "select device_id" + " from calc_archive_froid" + " where status = 0;";
		try {
			result = em.createNativeQuery(sql).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<Integer> getAllDisconnectedEnergyDeviceIds() {
		List<Integer> result = new ArrayList<Integer>();
		String sql = "SELECT distinct ph.device_id" + " FROM calc_energy_5min clc"
				+ " inner join phase ph on ph.id = clc.phase_id" + " inner join device d on d.id = ph.device_id"
				+ " where timeDIFF(now(), clc.time)>= '02:00:00' and d.type=\"energy\"";
		try {
			result = em.createNativeQuery(sql).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
