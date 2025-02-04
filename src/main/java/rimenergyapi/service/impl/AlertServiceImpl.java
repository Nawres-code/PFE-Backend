package rimenergyapi.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rimenergyapi.dto.AlertInfoDto;
import rimenergyapi.dto.UserAlertDTO;
import rimenergyapi.entity.AlertEntity;
import rimenergyapi.entity.UserAlertEntity;
import rimenergyapi.entity.Type.AlertConfigurationType;
import rimenergyapi.entity.Type.AlertType;
import rimenergyapi.repository.AlertConfigurationRepository;
import rimenergyapi.service.UserAlertService;
import rimenergyapi.utils.Utils;
import rimenergyapi.exception.ResourceNotFoundException;

@Service
public class AlertServiceImpl {

//	@Override
//	public AlertInfoDto getAlertByNameAndDeviceId(AlertType name, long deviceId) {
//		AlertEntity alert = this.alertRepository.findByNameAndDeviceId(name, deviceId);
//
//		if (alert != null) {
//			AlertInfoDto alertInfoDto = this.mapper.map(alert, AlertInfoDto.class);
//			alertInfoDto.getAlertConfigurations().forEach(config -> {
//				if (config.getAlertConfigurationType().equals(AlertConfigurationType.ACTIVE_W_DAY)) {
//					config.setPayload(Utils.getCronPayloadFromRegEx(config.getValue1()));
//				}
//			});
//			return alertInfoDto;
//		}
//		throw new ResourceNotFoundException("alert" + name.toString() + "' not found");
//	}
//
//	@Override
//	@Transactional
//	public AlertInfoDto updatetAlert(AlertUpdateDTO payload) {
//		List<Long> devvices = new ArrayList<Long>();
//		AlertInfoDto alertInfoDto = null;
//		return null;
//	}

}