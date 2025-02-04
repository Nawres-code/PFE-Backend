package rimenergyapi.userData.repository;

import java.util.List;

public interface DeviceRepositoryCustom {
	List<Integer> getAllDisconnectedFroidDeviceIds();
	List<Integer> getAllDisconnectedEnergyDeviceIds();
	List<Integer> getAllDisconnectedTmpDeviceIds();
}
