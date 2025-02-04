package rimenergyapi.service;

import java.util.List;

import rimenergyapi.dto.ArchiveAlertDto;
import rimenergyapi.dto.UserAlertDTO;
import rimenergyapi.dto.UserAlertFilterDto;
import rimenergyapi.entity.UserAlertEntity;

public interface UserAlertService {
	void deleteById(int id);
	void add(UserAlertEntity userAlert);
	void update(UserAlertEntity userAlert, int id);
	void updateUserAlertStatus(int id, boolean status);
	List<UserAlertEntity> findAllByMeasureId(UserAlertFilterDto filter);
	List<UserAlertDTO> findAllByPointRanges(List<Integer> pointsIds, String userAlertType );
	void deleteCalcAlertById(int id);
	List<UserAlertEntity> findAllByTenantId(int idUser);
	List<ArchiveAlertDto> getAllArchive(int idUser);
}
	