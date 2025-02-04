package rimenergyapi.repository;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import rimenergyapi.dto.UserAlertDTO;
import rimenergyapi.entity.UserAlertEntity;
import rimenergyapi.entity.Type.UserAlertType;

public interface UserAlertRepositoryCustom {
	
	void updateStatus(int id, boolean status);
	
	boolean isExist(int id);
	
	List<UserAlertEntity> findAllByGroupRangeAndByType(List<Integer> groupsId,
			Optional<UserAlertType> userAlertType, int tenantId);
	
	List<UserAlertEntity> findAllByDeviceRangeAndByType(List<Integer> devicesId,
			Optional<UserAlertType> userAlertType, int tenantId);
	
	List<UserAlertEntity> findAllByDeviceFroidRangeAndByType(List<Integer> devicesId,
			Optional<UserAlertType> userAlertType, int tenantId);
	
	List<UserAlertDTO> findAllByPointRangeAndByType(List<Integer> pointsId,
			Optional<UserAlertType> userAlertType, int tenantId);
	
	List<UserAlertEntity> findAllBySondeRangeAndByType(List<Double> sondesId,
			Optional<UserAlertType> userAlertType, int tenantId);
	
	List<UserAlertEntity> findAllByStationRangeAndByType(List<String> stationIds, Optional<UserAlertType> userAlertType,
			int parseInt);
	
	List<UserAlertEntity> findAllByStationSensorRangeAndByType(List<String> measureIds, List<String> fatherIds,
			Optional<UserAlertType> userAlertType, int tenantId);
	
	List<UserAlertEntity> findAllBySensorRangeAndByType(List<String> measureIds, Optional<UserAlertType> userAlertType, int tenantId);
	
	List<UserAlertEntity> findAllByInputRangeAndByType(List<Integer> inputsId,
			Optional<UserAlertType> userAlertType, int tenantId);

	List<UserAlertEntity> findAllByPhaseRangeAndByType(List<Integer> inputsId,
			Optional<UserAlertType> userAlertType, int tenantId);
	
	List<UserAlertEntity> findAllByIORangeAndByType(List<Integer> iosId,
			Optional<UserAlertType> userAlertType, int tenantId);
}
