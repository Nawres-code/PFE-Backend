package rimenergyapi.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import rimenergyapi.dto.ArchiveAlertDto;
import rimenergyapi.dto.UserAlertDTO;
import rimenergyapi.dto.UserAlertFilterDto;
import rimenergyapi.entity.DeviceEntity;
import rimenergyapi.entity.DeviceFroidEntity;
import rimenergyapi.entity.GroupsEntity;
import rimenergyapi.entity.IOEntity;
import rimenergyapi.entity.InputEntity;
import rimenergyapi.entity.PhaseEntity;
import rimenergyapi.entity.PointEntity;
import rimenergyapi.entity.SensorEntity;
import rimenergyapi.entity.SondeEntity;
import rimenergyapi.entity.StationEntity;
import rimenergyapi.entity.UserAlertEntity;
import rimenergyapi.service.UserAlertService;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/user-alerts-api")
@ApiIgnore
@Api(value = "User alerts", description = "User Alerts Management")
public class UserAlertController {
	
	@Autowired
	UserAlertService userAlertService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private CurrentTenantIdentifierResolver tenantIdentifier;

	@PostMapping("/alerts")
	@ResponseBody
	public List<UserAlertDTO> getAllByMesureRangeAndType(@RequestBody UserAlertFilterDto filter) {
		if(filter.getMeasureType() != null && filter.getMeasureType().equals("POINT")) {
			List<Integer> ids = filter.getMeasureIds().stream()
										.mapToInt(Integer::parseInt)
										.boxed().collect(Collectors.toList());
			return userAlertService.findAllByPointRanges(ids, filter.getUserAlertType());
		}
		List<UserAlertEntity> list = userAlertService.findAllByMeasureId(filter);
		return list.stream().map(this::convertToDto).collect(Collectors.toList());
	}


	@PostMapping("/{idUser}/alerts")
	@ResponseBody
	public List<UserAlertDTO> getAll(@PathVariable("idUser") int idUser, @RequestBody UserAlertFilterDto filter) {
		List<UserAlertEntity> list = new ArrayList<>();
		if(filter == null) {
			list = userAlertService.findAllByTenantId(idUser);
		} else if(filter.getMeasureType() != null && filter.getMeasureType().equals("POINT")) {
			List<Integer> ids = filter.getMeasureIds().stream()
					.mapToInt(Integer::parseInt)
					.boxed().collect(Collectors.toList());	
			return userAlertService.findAllByPointRanges(ids, filter.getUserAlertType());
		} else if(!filter.getMeasureIds().isEmpty()) {
			list = userAlertService.findAllByMeasureId(filter);
		} 
		
		if(filter.getMeasureIds().isEmpty()) {
			list = userAlertService.findAllByTenantId(idUser);
			List<UserAlertDTO> result = null;
			try {
				result = list.stream().map(this::convertToDto)
						.filter(a ->( filter.getInstallationId()!= null && a.getInstallationId().equalsIgnoreCase(filter.getInstallationId()+"")) || filter.getInstallationId()== null  )
						.filter(a -> filter.getZoneId()!= null && a.getZoneId().equalsIgnoreCase(filter.getZoneId()+"") || filter.getZoneId()== null)
						.filter(a-> filter.getMeasureType() != null && a.getMeasureType().equalsIgnoreCase(filter.getMeasureType()) ||  filter.getMeasureType() == null)
						.filter(a-> (filter.getUserAlertType() != null  && 
						!filter.getUserAlertType().equalsIgnoreCase("") && a.getType().equalsIgnoreCase(filter.getUserAlertType())
						|| (filter.getUserAlertType().equalsIgnoreCase(""))))
						.collect(Collectors.toList());
				/*result = result.stream().filter(a -> filter.getZoneId()!= null && a.getZoneId().equalsIgnoreCase(filter.getZoneId()+"") || filter.getZoneId()== null)
						.collect(Collectors.toList());
				result = result.stream().filter(a-> filter.getMeasureType() != null && a.getMeasureType().equalsIgnoreCase(filter.getMeasureType()) ||  filter.getMeasureType() == null)
						.collect(Collectors.toList());
				result = result.stream().filter(a-> (filter.getUserAlertType() != null  && 
						!filter.getUserAlertType().equalsIgnoreCase("") && a.getType().equalsIgnoreCase(filter.getUserAlertType())
						|| (filter.getUserAlertType().equalsIgnoreCase("")))).collect(Collectors.toList());*/
			} catch (Exception e) {
				e.printStackTrace();
			}
			return result;
		} 
		return list.stream().map(this::convertToDto).collect(Collectors.toList());
	}
	
	@GetMapping("/{idUser}/alerts")
	@ResponseBody
	public List<UserAlertDTO> filterAll(@PathVariable("idUser") int idUser) {
		List<UserAlertEntity> list = userAlertService.findAllByTenantId(idUser);
		return list.stream().map(this::convertToDto).collect(Collectors.toList());
	}
	@PostMapping("/alerts/alert")
	public void createUserAlertEntity(@RequestBody UserAlertDTO userAlert) {
		userAlertService.add(convertToEntity(userAlert));
	}

	@DeleteMapping("/alerts/{id}")
	public void delete(@PathVariable("id") int id) {
		userAlertService.deleteById(id);
		userAlertService.deleteCalcAlertById(id);
	}

	@PutMapping("/alerts/{id}")
	public void update(@RequestBody UserAlertDTO userAlert, @PathVariable("id") int id) {
		userAlertService.update(convertToEntity(userAlert), id);
	}

	@PutMapping("/alerts/{id}/status")
	public void updateUserAlertStatus(@PathVariable("id") int id, @RequestBody boolean status) {
		userAlertService.updateUserAlertStatus(id, status);
	}
	
	//	mappers
	private UserAlertDTO convertToDto(UserAlertEntity alert) {
		
		UserAlertDTO alertDto = new UserAlertDTO();
		try {
			alertDto = modelMapper.map(alert, UserAlertDTO.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (alert.getDevice() != null) {
			alertDto.setMeasureId(alert.getDevice().getId() + "");
			alertDto.setMeasureName(alert.getDevice().getName());
			alertDto.setMeasureType("DEVICE");
			alertDto.setInstallationId(alert.getDevice().getInstallation().getId()+"");
			alertDto.setFatherId(alertDto.getInstallationId());
			alertDto.setZoneId(alert.getDevice().getInstallation().getZones().getIdZone()+"");
		} else if (alert.getDeviceFroid() != null && alert.getPoint()!= null) {
			alertDto.setMeasureId(alert.getPoint().getId()+"");
			alertDto.setMeasureName(alert.getPoint().getLabel());
			alertDto.setFatherId(alert.getPoint().getDevice().getId() + "");
			alertDto.setMeasureType("POINT");
			alertDto.setInstallationId(alert.getPoint().getInstallation().getId()+"");
			alertDto.setFatherId(alertDto.getInstallationId());
			alertDto.setZoneId(alert.getPoint().getInstallation().getZones().getIdZone()+"");
		} else if (alert.getStation() != null && alert.getSensor()!= null) {
			alertDto.setMeasureId(alert.getSensor().getId() + "");
			alertDto.setMeasureName(alert.getSensor().getName());
			alertDto.setFatherId(alert.getStation().getId());
			alertDto.setMeasureType("SENSOR");
			alertDto.setInstallationId(alert.getStation().getInstallation().getId()+"");
			alertDto.setZoneId(alert.getStation().getInstallation().getZones().getIdZone()+"");
		} else if (alert.getDeviceFroid() != null) {
			alertDto.setMeasureId(alert.getDeviceFroid().getId() + "");
			alertDto.setMeasureName(alert.getDeviceFroid().getName());
			alertDto.setMeasureType("DEVICE_FROID");
			alertDto.setInstallationId(alert.getDeviceFroid().getInstallation().getId()+"");
			alertDto.setFatherId(alertDto.getInstallationId());
			alertDto.setZoneId(alert.getDeviceFroid().getInstallation().getZones().getIdZone()+"");
		} else if (alert.getGroup() != null) {
			alertDto.setMeasureId(alert.getGroup().getId() + "");
			alertDto.setMeasureName(alert.getGroup().getName());
			alertDto.setMeasureType("GROUP");
			alertDto.setInstallationId(alert.getGroup().getInstallation().getId()+"");
			alertDto.setFatherId(alertDto.getInstallationId());
			alertDto.setZoneId(alert.getGroup().getInstallation().getZones().getIdZone()+"");
		}else if (alert.getSonde() != null) { 
			alertDto.setMeasureId(alert.getSonde().getId() + "");
			alertDto.setMeasureName(alert.getSonde().getName());
			alertDto.setMeasureType("SONDE");
			alertDto.setInstallationId(alert.getSonde().getInstallation().getId()+"");
			alertDto.setFatherId(alertDto.getInstallationId());
			alertDto.setZoneId(alert.getSonde().getInstallation().getZones().getIdZone()+"");
		}else if (alert.getInput() != null) { 
			alertDto.setMeasureId(alert.getInput().getId() + "");
			alertDto.setMeasureName(alert.getInput().getName());
			alertDto.setMeasureType("INPUT");
			alertDto.setInstallationId(alert.getInput().getInstallation().getId()+"");
			alertDto.setFatherId(alertDto.getInstallationId());
			alertDto.setZoneId(alert.getInput().getInstallation().getZones().getIdZone()+"");
		}else if (alert.getPhase() != null) { 
			alertDto.setMeasureId(alert.getPhase().getId() + "");
			alertDto.setMeasureName(alert.getPhase().getName());
			alertDto.setMeasureType("PHASE");
			alertDto.setInstallationId(alert.getPhase().getDevice().getInstallation().getId()+"");
			alertDto.setFatherId(alertDto.getInstallationId());
			alertDto.setZoneId(alert.getPhase().getDevice().getInstallation().getZones().getIdZone()+"");
		}else if (alert.getIo() != null) { 
			alertDto.setMeasureId(alert.getIo().getId() + "");
			alertDto.setMeasureName(alert.getIo().getName());
			alertDto.setMeasureType("IO_"+alert.getIo().getType().getValue());
			alertDto.setInstallationId(alert.getIo().getDevice().getInstallation().getId()+"");
			alertDto.setFatherId(alertDto.getInstallationId());
			alertDto.setZoneId(alert.getIo().getDevice().getInstallation().getZones().getIdZone()+"");
		}
	    return alertDto;
	}
	
	private UserAlertEntity convertToEntity(UserAlertDTO alertDto){
		UserAlertEntity alert = modelMapper.map(alertDto, UserAlertEntity.class);
		switch (alertDto.getMeasureType()) {
		case "DEVICE":
			DeviceEntity device = new DeviceEntity();
			device.setId(Integer.parseInt(alertDto.getMeasureId()));
			alert.setDevice(device);
			break;
		case "POINT":
			PointEntity point = new PointEntity();
			String[] ids = alertDto.getMeasureId().split("-");
			point.setId(Integer.parseInt(ids[1]));
			DeviceFroidEntity device_f= new DeviceFroidEntity();
			device_f.setId(Integer.parseInt(ids[0]));
			point.setDevice(device_f);
			alert.setPoint(point);
			break;
		case "DEVICE_FROID":
			DeviceFroidEntity deviceFroid = new DeviceFroidEntity();
			deviceFroid.setId(Integer.parseInt(alertDto.getMeasureId()));
			alert.setDeviceFroid(deviceFroid);
			break;
		case "GROUP":
			GroupsEntity group = new GroupsEntity();
			group.setId(Integer.parseInt(alertDto.getMeasureId()));
			alert.setGroup(group);
			break;
		case "SONDE":
			SondeEntity sonde = new SondeEntity();
			sonde.setId(new Double(alertDto.getMeasureId()));
			alert.setSonde(sonde);
			break;
		case "SENSOR":
			SensorEntity sensor = new SensorEntity();
			sensor.setId(alertDto.getMeasureId());
			StationEntity station = new StationEntity();
			station.setId(alertDto.getFatherId());
			alert.setSensor(sensor);
			alert.setStation(station);
			break;
		case "INPUT":
			InputEntity input = new InputEntity();
			input.setId(new Integer(alertDto.getMeasureId()));
			alert.setInput(input);
			break;
		case "PHASE":
			PhaseEntity phase = new PhaseEntity();
			phase.setId(new Integer(alertDto.getMeasureId()));
			alert.setPhase(phase);
			break;
		default:
			IOEntity io = new IOEntity();
			io.setId(new Integer(alertDto.getMeasureId()));
			alert.setIo(io);
			break;
		}
		alert.setTenantId(Integer.parseInt(tenantIdentifier.resolveCurrentTenantIdentifier()));
	    return alert;
	}
	
	@GetMapping("/{idUser}/archive")
	@ResponseBody
	public List<ArchiveAlertDto> getAllArchive(@PathVariable("idUser") int idUser) {
		List<ArchiveAlertDto> list = userAlertService.getAllArchive(idUser);
		return list;
	}
	
}
