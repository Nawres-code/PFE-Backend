package rimenergyapi.repository;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import org.hibernate.SQLQuery;
import org.hibernate.criterion.Order;
import org.hibernate.transform.Transformers;
import org.hibernate.type.BooleanType;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StringType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import rimenergyapi.dto.UserAlertDTO;
import rimenergyapi.entity.DeviceEntity;
import rimenergyapi.entity.DeviceFroidEntity;
import rimenergyapi.entity.GroupsEntity;
import rimenergyapi.entity.IOEntity;
import rimenergyapi.entity.InputEntity;
import rimenergyapi.entity.PhaseEntity;
import rimenergyapi.entity.SensorEntity;
import rimenergyapi.entity.SondeEntity;
import rimenergyapi.entity.StationEntity;
import rimenergyapi.entity.UserAlertEntity;
import rimenergyapi.entity.Type.UserAlertType;

@Repository
public class UserAlertRepositoryImpl implements UserAlertRepositoryCustom {

	@Autowired
	@Qualifier("tricityEntityManager")
	private EntityManager em;

	@Override
	public void updateStatus(int id, boolean status) {
		em.createQuery("UPDATE UserAlertEntity u" + " SET u.isActive = :status" + " WHERE u.id = :id")
				.setParameter("status", status).setParameter("id", id).executeUpdate();
	}

	@Override
	public boolean isExist(int id) {
		return (em.createNativeQuery("SELECT EXISTS (SELECT * FROM useralert_1 u WHERE u.id = :id ) AS exist")
				.setParameter("id", id).getSingleResult() + "").equals("1");
	}
	
	@Override
	public List<UserAlertEntity> findAllByGroupRangeAndByType(List<Integer> groupsId,
			Optional<UserAlertType> userAlertType , int tenantId) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<UserAlertEntity> q = cb.createQuery(UserAlertEntity.class);

		Subquery<GroupsEntity> subquery = q.subquery(GroupsEntity.class);
		Root<GroupsEntity> group = subquery.from(GroupsEntity.class);
		subquery.select(group).where(cb.in(group.get("id")).value(groupsId));

		Root<UserAlertEntity> alerts = q.from(UserAlertEntity.class);
		q.select(alerts);
		if (userAlertType.isPresent()) {
			q.where(cb.in(alerts.get("group")).value(subquery), cb.equal(alerts.get("type"), userAlertType.get()), cb.equal(alerts.get("tenantId"), tenantId));
		}
		else {
			q.where(cb.in(alerts.get("group")).value(subquery), cb.equal(alerts.get("tenantId"), tenantId));
		}

		List<javax.persistence.criteria.Order> orders = new ArrayList<>();
		orders.add(cb.asc(alerts.get("group").get("name")));
		orders.add(cb.asc(alerts.get("group").get("installation").get("name")));
		orders.add(cb.asc(alerts.get("group").get("installation").get("zones").get("name")));
		orders.add(cb.asc(alerts.get("type")));
		q.orderBy(orders);
		
		return em.createQuery(q).getResultList();
			}

	@Override
	public List<UserAlertEntity> findAllByDeviceRangeAndByType(List<Integer> devicesId,
			Optional<UserAlertType> userAlertType, int tenantId) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<UserAlertEntity> q = cb.createQuery(UserAlertEntity.class);

		Subquery<DeviceEntity> subquery = q.subquery(DeviceEntity.class);
		Root<DeviceEntity> device = subquery.from(DeviceEntity.class);
		subquery.select(device).where(cb.in(device.get("id")).value(devicesId));

		Root<UserAlertEntity> alerts = q.from(UserAlertEntity.class);
		q.select(alerts);
		if (userAlertType.isPresent()) {
			q.where(cb.in(alerts.get("device")).value(subquery), cb.equal(alerts.get("type"), userAlertType.get()), cb.equal(alerts.get("tenantId"), tenantId));
		}
		else {
			q.where(cb.in(alerts.get("device")).value(subquery), cb.equal(alerts.get("tenantId"), tenantId));
		}
		List<javax.persistence.criteria.Order> orders = new ArrayList<>();
		orders.add(cb.asc(alerts.get("device").get("name")));
		orders.add(cb.asc(alerts.get("device").get("installation").get("name")));
		orders.add(cb.asc(alerts.get("device").get("installation").get("zones").get("name")));
		orders.add(cb.asc(alerts.get("type")));
		q.orderBy(orders);
		return em.createQuery(q).getResultList();
	}

	@Override
	public List<UserAlertEntity> findAllByDeviceFroidRangeAndByType(List<Integer> devicesId,
			Optional<UserAlertType> userAlertType, int tenantId) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<UserAlertEntity> q = cb.createQuery(UserAlertEntity.class);

		Subquery<DeviceFroidEntity> subquery = q.subquery(DeviceFroidEntity.class);
		Root<DeviceFroidEntity> deviceFroid = subquery.from(DeviceFroidEntity.class);
		subquery.select(deviceFroid).where(cb.in(deviceFroid.get("id")).value(devicesId));

		Root<UserAlertEntity> alerts = q.from(UserAlertEntity.class);
		q.select(alerts);
		
		if (userAlertType.isPresent()) {
			q.where(cb.in(alerts.get("deviceFroid")).value(subquery), cb.equal(alerts.get("type"), userAlertType.get()), cb.equal(alerts.get("tenantId"), tenantId));
		}
		else {
			q.where(cb.in(alerts.get("deviceFroid")).value(subquery), cb.equal(alerts.get("tenantId"), tenantId));
		}
		
		List<javax.persistence.criteria.Order> orders = new ArrayList<>();
		orders.add(cb.asc(alerts.get("deviceFroid").get("name")));
		orders.add(cb.asc(alerts.get("deviceFroid").get("installation").get("name")));
		orders.add(cb.asc(alerts.get("deviceFroid").get("installation").get("zones").get("name")));
		orders.add(cb.asc(alerts.get("type")));
		q.orderBy(orders);

		return em.createQuery(q).getResultList();
	}

	@Override
	public List<UserAlertEntity> findAllBySondeRangeAndByType(List<Double> sondesId,
			Optional<UserAlertType> userAlertType, int tenantId) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<UserAlertEntity> q = cb.createQuery(UserAlertEntity.class);

		Subquery<SondeEntity> subquery = q.subquery(SondeEntity.class);
		Root<SondeEntity> sonde = subquery.from(SondeEntity.class);
		subquery.select(sonde).where(cb.in(sonde.get("id")).value(sondesId));

		Root<UserAlertEntity> alerts = q.from(UserAlertEntity.class);
		q.select(alerts);
		
		if (userAlertType.isPresent()) {
			q.where(cb.in(alerts.get("sonde")).value(subquery), cb.equal(alerts.get("type"), userAlertType.get()), cb.equal(alerts.get("tenantId"), tenantId));
		}
		else {
			q.where(cb.in(alerts.get("sonde")).value(subquery), cb.equal(alerts.get("tenantId"), tenantId));
		}
		List<javax.persistence.criteria.Order> orders = new ArrayList<>();
		orders.add(cb.asc(alerts.get("sonde").get("name")));
		orders.add(cb.asc(alerts.get("sonde").get("installation").get("name")));
		orders.add(cb.asc(alerts.get("sonde").get("installation").get("zones").get("name")));
		orders.add(cb.asc(alerts.get("type")));
		q.orderBy(orders);
		return em.createQuery(q).getResultList();
	}

	@Override
	public List<UserAlertDTO> findAllByPointRangeAndByType(List<Integer> pointsId,
			Optional<UserAlertType> userAlertType, int tenantId) {
		// pointsId => { deviceId, pointId .... }
		StringBuffer buffer= new StringBuffer();
		for(int i= 0; i<pointsId.size()-1; i+=2) {
			if(i!=0) {
				buffer.append(" OR ");
			}
			buffer.append("(pt.id = "+pointsId.get(i+1)+" AND pt.device_id = "+pointsId.get(i)+")");
		}
		
	return 	em.createNativeQuery(
			"SELECT " + 
			"    a.id as id," + 
			"    concat(pt.device_id,'-',pt.id) as measureId," + 
			"    a.emails as email," + 
			"    a.is_active as isActive," + 
			"	pt.label as measureName," + 
			"    a.tenant_id," + 
			"	a.type as type," + 
			"    'POINT' as measureType" + 
			" FROM" + 
			"    useralert_1 a" + 
			"	JOIN point pt on pt.id=a.point_id and pt.device_id = a.point_device_froid_id" + 
			" WHERE" + 
			"	("+new String(buffer)+")" + 
			"	AND a.tenant_id = "+tenantId).unwrap(SQLQuery.class)
			.addScalar("id", new IntegerType())
			.addScalar("measureId", new StringType())
			.addScalar("measureName", new StringType())
			.addScalar("measureType", new StringType())
			.addScalar("type", new StringType())
			.addScalar("isActive", new BooleanType())
			.addScalar("email", new StringType())
			.setResultTransformer(Transformers.aliasToBean(UserAlertDTO.class)).getResultList();
			//return em.createQuery(q).getResultList();
	}
	
	@Override
	public List<UserAlertEntity> findAllByStationRangeAndByType(List<String> stationIds, Optional<UserAlertType> userAlertType,
			int tenantId) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<UserAlertEntity> q = cb.createQuery(UserAlertEntity.class);

		Subquery<StationEntity> subquery = q.subquery(StationEntity.class);
		Root<StationEntity> station = subquery.from(StationEntity.class);
		subquery.select(station).where(cb.in(station.get("id")).value(stationIds));

		Root<UserAlertEntity> alerts = q.from(UserAlertEntity.class);
		q.select(alerts);
		
		if (userAlertType.isPresent()) {
			q.where(cb.in(alerts.get("station")).value(subquery), cb.equal(alerts.get("type"), userAlertType.get()), cb.equal(alerts.get("tenantId"), tenantId));
		}
		else {
			q.where(cb.in(alerts.get("station")).value(subquery), cb.equal(alerts.get("tenantId"), tenantId));
		}

		List<javax.persistence.criteria.Order> orders = new ArrayList<>();
		orders.add(cb.asc(alerts.get("station").get("name")));
		orders.add(cb.asc(alerts.get("station").get("installation").get("name")));
		orders.add(cb.asc(alerts.get("station").get("installation").get("zones").get("name")));
		orders.add(cb.asc(alerts.get("type")));
		q.orderBy(orders);
		return em.createQuery(q).getResultList();
	}
	
	@Override
	public List<UserAlertEntity> findAllBySensorRangeAndByType(List<String> measureIds, Optional<UserAlertType> userAlertType, int tenantId){
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<UserAlertEntity> q = cb.createQuery(UserAlertEntity.class);
		
		Subquery<SensorEntity> subquery = q.subquery(SensorEntity.class);
		Root<SensorEntity> sensor = subquery.from(SensorEntity.class);
		subquery.select(sensor).where(cb.in(sensor.get("id")).value(measureIds));

		Root<UserAlertEntity> alerts = q.from(UserAlertEntity.class);
		q.select(alerts);
		
		if (userAlertType.isPresent()) {
			q.where(cb.in(alerts.get("sensor")).value(subquery), cb.equal(alerts.get("type"),
					userAlertType.get()), cb.equal(alerts.get("tenantId"), tenantId));
		}
		else {
			q.where(cb.in(alerts.get("sensor")).value(subquery), cb.equal(alerts.get("tenantId"), tenantId));
		}
		
		List<javax.persistence.criteria.Order> orders = new ArrayList<>();
		orders.add(cb.asc(alerts.get("sensor").get("name")));
		orders.add(cb.asc(alerts.get("type")));
		q.orderBy(orders);

		return em.createQuery(q).getResultList();
	}
	
	@Override
	public List<UserAlertEntity> findAllByStationSensorRangeAndByType(List<String> measureIds, List<String> fatherIds,
			Optional<UserAlertType> userAlertType, int tenantId){
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<UserAlertEntity> q = cb.createQuery(UserAlertEntity.class);

		Subquery<StationEntity> subquery = q.subquery(StationEntity.class);
		Root<StationEntity> station = subquery.from(StationEntity.class);
		subquery.select(station).where(cb.in(station.get("id")).value(fatherIds));
		
		Subquery<SensorEntity> subquery1 = q.subquery(SensorEntity.class);
		Root<SensorEntity> sensor = subquery1.from(SensorEntity.class);
		subquery1.select(sensor).where(cb.in(sensor.get("id")).value(measureIds));

		Root<UserAlertEntity> alerts = q.from(UserAlertEntity.class);
		q.select(alerts);
		
		if (userAlertType.isPresent()) {
			q.where(cb.in(alerts.get("station")).value(subquery), cb.in(alerts.get("sensor")).value(subquery1), 
					cb.equal(alerts.get("type"), userAlertType.get()), cb.equal(alerts.get("tenantId"), tenantId));
		}
		else {
			q.where(cb.in(alerts.get("station")).value(subquery), cb.in(alerts.get("sensor")).value(subquery1),
					cb.equal(alerts.get("tenantId"), tenantId));
		}
		List<javax.persistence.criteria.Order> orders = new ArrayList<>();
		orders.add(cb.asc(alerts.get("sensor").get("name")));
		orders.add(cb.asc(alerts.get("station").get("name")));
		orders.add(cb.asc(alerts.get("station").get("installation").get("name")));
		orders.add(cb.asc(alerts.get("station").get("installation").get("zones").get("name")));
		orders.add(cb.asc(alerts.get("type")));
		q.orderBy(orders);

		return em.createQuery(q).getResultList();
	}

	@Override
	public List<UserAlertEntity> findAllByInputRangeAndByType(List<Integer> inputsId,
			Optional<UserAlertType> userAlertType, int tenantId) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<UserAlertEntity> q = cb.createQuery(UserAlertEntity.class);

		Subquery<InputEntity> subquery = q.subquery(InputEntity.class);
		Root<InputEntity> input = subquery.from(InputEntity.class);
		subquery.select(input).where(cb.in(input.get("id")).value(inputsId));

		Root<UserAlertEntity> alerts = q.from(UserAlertEntity.class);
		q.select(alerts);
		if (userAlertType.isPresent()) {
			q.where(cb.in(alerts.get("input")).value(subquery), cb.equal(alerts.get("type"), userAlertType.get()), cb.equal(alerts.get("tenantId"), tenantId));
		}
		else {
			q.where(cb.in(alerts.get("input")).value(subquery), cb.equal(alerts.get("tenantId"), tenantId));
		}
		List<javax.persistence.criteria.Order> orders = new ArrayList<>();
		orders.add(cb.asc(alerts.get("input").get("name")));
		orders.add(cb.asc(alerts.get("input").get("installation").get("name")));
		orders.add(cb.asc(alerts.get("input").get("installation").get("zones").get("name")));
		orders.add(cb.asc(alerts.get("type")));
		q.orderBy(orders);
		return em.createQuery(q).getResultList();
	}
	
	@Override
	public List<UserAlertEntity> findAllByPhaseRangeAndByType(List<Integer> phasesId,
			Optional<UserAlertType> userAlertType, int tenantId) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<UserAlertEntity> q = cb.createQuery(UserAlertEntity.class);

		Subquery<PhaseEntity> subquery = q.subquery(PhaseEntity.class);
		Root<PhaseEntity> phase = subquery.from(PhaseEntity.class);
		subquery.select(phase).where(cb.in(phase.get("id")).value(phasesId));

		Root<UserAlertEntity> alerts = q.from(UserAlertEntity.class);
		q.select(alerts);
		if (userAlertType.isPresent()) {
			q.where(cb.in(alerts.get("phase")).value(subquery), cb.equal(alerts.get("type"), userAlertType.get()), cb.equal(alerts.get("tenantId"), tenantId));
		}
		else {
			q.where(cb.in(alerts.get("phase")).value(subquery), cb.equal(alerts.get("tenantId"), tenantId));
		}
		List<javax.persistence.criteria.Order> orders = new ArrayList<>();
		orders.add(cb.asc(alerts.get("phase").get("name")));
		orders.add(cb.asc(alerts.get("phase").get("device").get("installation").get("name")));
		orders.add(cb.asc(alerts.get("phase").get("device").get("installation").get("zones").get("name")));
		orders.add(cb.asc(alerts.get("type")));
		q.orderBy(orders);
		return em.createQuery(q).getResultList();
	}
	
	@Override
	public List<UserAlertEntity> findAllByIORangeAndByType(List<Integer> iosId, Optional<UserAlertType> userAlertType,
			int tenantId) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<UserAlertEntity> q = cb.createQuery(UserAlertEntity.class);

		Subquery<IOEntity> subquery = q.subquery(IOEntity.class);
		Root<IOEntity> io = subquery.from(IOEntity.class);
		subquery.select(io).where(cb.in(io.get("id")).value(iosId));

		Root<UserAlertEntity> alerts = q.from(UserAlertEntity.class);
		q.select(alerts);
		if (userAlertType.isPresent()) {
			q.where(cb.in(alerts.get("io")).value(subquery), cb.equal(alerts.get("type"), userAlertType.get()), cb.equal(alerts.get("tenantId"), tenantId));
		} else {
			q.where(cb.in(alerts.get("io")).value(subquery), cb.equal(alerts.get("tenantId"), tenantId));
		}
		List<javax.persistence.criteria.Order> orders = new ArrayList<>();
		orders.add(cb.asc(alerts.get("io").get("name")));
		orders.add(cb.asc(alerts.get("io").get("device").get("installation").get("name")));
		orders.add(cb.asc(alerts.get("io").get("device").get("installation").get("zones").get("name")));
		orders.add(cb.asc(alerts.get("type")));
		q.orderBy(orders);
		return em.createQuery(q).getResultList();
	}
}