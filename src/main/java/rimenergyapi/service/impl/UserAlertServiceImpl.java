package rimenergyapi.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import rimenergyapi.dto.ArchiveAlertDto;
import rimenergyapi.dto.UserAlertDTO;
import rimenergyapi.dto.UserAlertFilterDto;
import rimenergyapi.entity.UserAlertEntity;
import rimenergyapi.entity.Type.UserAlertType;
import rimenergyapi.repository.AlertConfigurationRepository;
import rimenergyapi.repository.UserAlertRepository;
import rimenergyapi.service.UserAlertService;
import rimenergyapi.userData.repository.NotificationRepository;

@Service
public class UserAlertServiceImpl implements UserAlertService {

	@Autowired
	private UserAlertRepository userAlertRepo;
	
	@Autowired
	private AlertConfigurationRepository alertCfgRepo;
	
	@Autowired
	private CurrentTenantIdentifierResolver tenantIdentifier;
	
	@Autowired
	private NotificationRepository notifRepo;

	@Override
	@Transactional(transactionManager = "tricityTransactionManager")
	public void deleteById(int id) {
		userAlertRepo.deleteById(id);
	}
	@Override
	@Transactional(transactionManager = "tenantTransactionManager")
	public void deleteCalcAlertById(int id) {
		alertCfgRepo.removeCalcAlertById(id);
	}

	@Override
	@Transactional(transactionManager = "tricityTransactionManager")
	public void add(UserAlertEntity userAlert) {
		userAlert.getConfigs().forEach(cfg -> userAlert.addConfig(cfg));
		userAlertRepo.save(userAlert);
	}

	@Override
	@Transactional(transactionManager = "tricityTransactionManager")
	public void update(UserAlertEntity userAlert, int id) {
		if (userAlertRepo.isExist(id)) {
			userAlert.setId(id);
			userAlert.getConfigs().forEach(cfg -> userAlert.addConfig(cfg));
			userAlertRepo.save(userAlert);
		} else {
			// throw new Exception("Trying to update a nonexistent alert!");
		}
	}

	@Override
	@Transactional(transactionManager = "tricityTransactionManager")
	public void updateUserAlertStatus(int id, boolean status) {
		userAlertRepo.updateStatus(id, status);
	}

	@Override
	public List<UserAlertEntity> findAllByMeasureId(UserAlertFilterDto filter) {
		List<UserAlertEntity> list = new ArrayList<UserAlertEntity>();
		List<Integer> ids = null;
		filter.setMeasureType( filter.getMeasureType().startsWith("IO_")? "IO_IMPULSE" : filter.getMeasureType());
		if (filter.getMeasureIds().size() > 0) {
			switch (filter.getMeasureType()) {
			case "GROUP":
				ids = filter.getMeasureIds().stream()
						.mapToInt(Integer::parseInt)
						.boxed().collect(Collectors.toList());
				list = userAlertRepo.findAllByGroupRangeAndByType(ids, UserAlertType.fromValue(filter.getUserAlertType()),
						Integer.parseInt(tenantIdentifier.resolveCurrentTenantIdentifier()));
				break;
			case "DEVICE_FROID":
				ids = filter.getMeasureIds().stream()
						.mapToInt(Integer::parseInt)
						.boxed().collect(Collectors.toList());
				list = userAlertRepo.findAllByDeviceFroidRangeAndByType(ids, UserAlertType.fromValue(filter.getUserAlertType()),
						Integer.parseInt(tenantIdentifier.resolveCurrentTenantIdentifier()));
				break;
			case "DEVICE":
				ids = filter.getMeasureIds().stream()
						.mapToInt(Integer::parseInt)
						.boxed().collect(Collectors.toList());
				list = userAlertRepo.findAllByDeviceRangeAndByType(ids, UserAlertType.fromValue(filter.getUserAlertType()),
						Integer.parseInt(tenantIdentifier.resolveCurrentTenantIdentifier()));
				break;
			case "SONDE":
				List<Double> sondeIds = filter.getMeasureIds()
											.stream()
											.map(id->Double.valueOf(id))
											.collect(Collectors.toList());
				list = userAlertRepo.findAllBySondeRangeAndByType(sondeIds,UserAlertType.fromValue(filter.getUserAlertType()),
						Integer.parseInt(tenantIdentifier.resolveCurrentTenantIdentifier()));
				break;
			case "STATION":
				List<String> stationIds = filter.getMeasureIds()
											.stream()
											.collect(Collectors.toList());
				list = userAlertRepo.findAllByStationRangeAndByType(stationIds, UserAlertType.fromValue(filter.getUserAlertType()),
						Integer.parseInt(tenantIdentifier.resolveCurrentTenantIdentifier()));
				break;
			case "SENSOR":
				list = userAlertRepo.findAllBySensorRangeAndByType(filter.getMeasureIds(), UserAlertType.fromValue(filter.getUserAlertType()),
						Integer.parseInt(tenantIdentifier.resolveCurrentTenantIdentifier()));
				break;
			case "STATION_SENSOR":
				list = userAlertRepo.findAllByStationSensorRangeAndByType(filter.getMeasureIds(), filter.getFatherIds(), UserAlertType.fromValue(filter.getUserAlertType()),
						Integer.parseInt(tenantIdentifier.resolveCurrentTenantIdentifier()));
				break;
			case "INPUT":
				ids = filter.getMeasureIds().stream()
				.mapToInt(Integer::parseInt)
				.boxed().collect(Collectors.toList());
				list = userAlertRepo.findAllByInputRangeAndByType(ids, UserAlertType.fromValue(filter.getUserAlertType()),
						Integer.parseInt(tenantIdentifier.resolveCurrentTenantIdentifier()));
				break;
			case "PHASE":
				ids = filter.getMeasureIds().stream()
				.mapToInt(Integer::parseInt)
				.boxed().collect(Collectors.toList());
				list = userAlertRepo.findAllByPhaseRangeAndByType(ids, UserAlertType.fromValue(filter.getUserAlertType()),
						Integer.parseInt(tenantIdentifier.resolveCurrentTenantIdentifier()));
				break;
			case "IO_IMPULSE":
				ids = filter.getMeasureIds().stream()
				.mapToInt(Integer::parseInt)
				.boxed().collect(Collectors.toList());
				list = userAlertRepo.findAllByIORangeAndByType(ids, UserAlertType.fromValue(filter.getUserAlertType()),
						Integer.parseInt(tenantIdentifier.resolveCurrentTenantIdentifier()));
				break;
			}
			
		}
		return list;
	}
	
	@Override
	public List<UserAlertDTO> findAllByPointRanges(List<Integer> pointsIds, String userAlertType ) {
		List<UserAlertDTO> list = new ArrayList<UserAlertDTO>();
		list = userAlertRepo.findAllByPointRangeAndByType(pointsIds, UserAlertType.fromValue(userAlertType), Integer.parseInt(tenantIdentifier.resolveCurrentTenantIdentifier()));
		list.forEach(alert-> alert.setConfigs(alertCfgRepo.getAllUserAlertId(alert.getId())));
		return list;
	}
	
	@Override
	public List<UserAlertEntity> findAllByTenantId(int idUser) {
		List<Order> orders = new ArrayList<Order>();
		orders.add(new Order(Sort.Direction.ASC, "sonde.name"));
		orders.add(new Order(Sort.Direction.ASC, "sonde.installation.name"));
		orders.add(new Order(Sort.Direction.ASC, "sonde.installation.zones.name"));
		
		orders.add(new Order(Sort.Direction.ASC, "group.name"));
		orders.add(new Order(Sort.Direction.ASC, "group.installation.name"));
		orders.add(new Order(Sort.Direction.ASC, "group.installation.zones.name"));
		
		orders.add(new Order(Sort.Direction.ASC, "phase.name"));
		orders.add(new Order(Sort.Direction.ASC, "phase.device.installation.name"));
		orders.add(new Order(Sort.Direction.ASC, "phase.device.installation.zones.name"));
		
		orders.add(new Order(Sort.Direction.ASC, "io.type"));
		orders.add(new Order(Sort.Direction.ASC, "io.name"));
		orders.add(new Order(Sort.Direction.ASC, "io.device.installation.name"));
		orders.add(new Order(Sort.Direction.ASC, "io.device.installation.zones.name"));
		
		orders.add(new Order(Sort.Direction.ASC, "type"));

		return userAlertRepo.findAllByTenantId(idUser, Sort.by(orders));
	}
	
	@Override
	public List<ArchiveAlertDto> getAllArchive(int idUser) {
		List<ArchiveAlertDto> result = null;
		try {
			result = notifRepo.getUserAlert(365);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
}
