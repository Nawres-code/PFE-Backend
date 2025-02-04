package rimenergyapi.userData.service;

import java.util.List;

import rimenergyapi.dto.ArchiveAlertDto;

public interface NotificationService {
	List<String> getFiredAlarmPointList();

	List<ArchiveAlertDto> getUserAlert(int days);
}
