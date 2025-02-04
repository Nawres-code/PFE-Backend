package rimenergyapi.userData.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rimenergyapi.dto.ArchiveAlertDto;
import rimenergyapi.userData.repository.NotificationRepository;
import rimenergyapi.userData.service.NotificationService;

@Service
public class NotificationServiceImpl  implements NotificationService{
	@Autowired
	private NotificationRepository notifRepo;
	
	@Override
	public List<String> getFiredAlarmPointList() {
		return notifRepo.getFiredAlarmPointList();
	}
	
	@Override
	public List<ArchiveAlertDto> getUserAlert(int days) {
		return notifRepo.getUserAlert(days);
	}
}
