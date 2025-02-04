package rimenergyapi.userData.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rimenergyapi.repository.DeviceRepository;
import rimenergyapi.userData.service.DeviceService;

@Service
public class DeviceServiceImpl implements DeviceService{
	
	@Autowired
	private DeviceRepository deviceRepo;

	@Override
	public Map<String, List<Integer>> getAllDisconnectedDevice() {
		Map<String, List<Integer>> result = new HashMap<String, List<Integer>>();
		List<Integer> list = new ArrayList<Integer>();
		list.add(8);
		list.add(5);
		//	result.put("ENERGY", deviceRepo.getAllDisconnectedEnergyDeviceIds());
		result.put("FROID", /*list);//*/ deviceRepo.getAllDisconnectedFroidDeviceIds());
		//result.put("", deviceRepo.getAllDisconnectedTmpDeviceIds());
		return result;
	}

}
