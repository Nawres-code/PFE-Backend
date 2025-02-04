package rimenergyapi.userData.service.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import rimenergyapi.dto.InputDataDto;
import rimenergyapi.repository.InstallationRepository;
import rimenergyapi.userData.model.DetailsDto;
import rimenergyapi.userData.model.DetailsPointDto;
import rimenergyapi.userData.model.DetailsSensorsDto;
import rimenergyapi.userData.model.DetailsStationsDto;
import rimenergyapi.userData.model.DetailsTemperateurDto;
import rimenergyapi.userData.model.DetailsGazDto;
import rimenergyapi.userData.repository.DetailsRepository;
import rimenergyapi.userData.service.DetailsService;

@Service
public class DetailsServiceImpl implements DetailsService {

	@Autowired
	DetailsRepository detailsRepository;

	@Autowired
	InstallationRepository installationRepo;

	@Value("${system.db.version}")
	int dbVersion;

	@Autowired
	private CurrentTenantIdentifierResolver tenantIdentifier;



	@Override
	public Map<Integer, DetailsDto> getHistoricalDetails(int[] phaseId, String[] vars, Timestamp startDate,
														 Timestamp endDate, String period) {

		Map<Integer, DetailsDto> detailsDtos = new HashMap<Integer, DetailsDto>();
		List<Object[]> details = null;
		List<String> varslist = new ArrayList<String>(Arrays.asList(vars));
		if (varslist.contains("power_max")) {
			if (!varslist.contains("current_max"))
				varslist.add("current_max");
			if (!varslist.contains("voltage_inst"))
				varslist.add("voltage_inst");
		}
		if (varslist.contains("power_min")) {
			if (!varslist.contains("current_min"))
				varslist.add("current_min");
			if (!varslist.contains("voltage_inst"))
				varslist.add("voltage_inst");
		}
		if (varslist.contains("power_moy")) {
			if (!varslist.contains("energy_act"))
				varslist.add("energy_act");
		}
		if (varslist.contains("power_inst")) {
//			if (!varslist.contains("current_inst"))
//				varslist.add("current_inst");
//			if (!varslist.contains("voltage_inst"))
//				varslist.add("voltage_inst");

			if(dbVersion<2) {
				varslist.remove("power_inst");
			}
		}

		/* if (varslist.contains("power_fact")) {
			varslist= Arrays.asList("energy_act", "energy_app");
		}*/

		switch (period.toLowerCase()) {
			case "1min":
				details = detailsRepository.findAllDataForVar(phaseId, varslist, startDate, endDate);
				break;
			case "5min":
				details = detailsRepository.findAll5minDataForVar(phaseId,"any_value(" + String.join("), any_value(", varslist) + ")",startDate, endDate);
				break;
			case "hours":
				details = detailsRepository.findAllHourDataForVar(phaseId,"any_value(" + String.join("), any_value(", varslist) + ")",startDate, endDate);
				break;
			case "day":
				details = detailsRepository.findAllDayDataForVar(phaseId,"any_value(" + String.join("), any_value(", varslist) + ")",startDate, endDate);
				break;
			case "month":
				details = detailsRepository.findAllMonthDataForVar(phaseId,"any_value(" + String.join("), any_value(", varslist) + ")",startDate, endDate);
				break;
			default:
				return  new HashMap<Integer, DetailsDto>();
		}

		for (Object[] detail : details) {
			if (!detailsDtos.containsKey((Integer) detail[0]))
				detailsDtos.put((Integer) detail[0], new DetailsDto());
			for (int i = 0; i < varslist.size(); i++) {
				if (!detailsDtos.get((Integer) detail[0]).getValeurs().containsKey((Timestamp) detail[1]))
					detailsDtos.get((Integer) detail[0]).getValeurs().put((Timestamp) detail[1], new HashMap<>());
				detailsDtos.get((Integer) detail[0]).getValeurs().get((Timestamp) detail[1]).put(varslist.get(i),
						(double) detail[2 + i]);
			}
		}
		return detailsDtos;
	}

	@Override
	public Map<Integer, DetailsDto> getHistoricalDetailsByGroup(int[] phaseId, String[] vars, Timestamp startDate,
																Timestamp endDate, String period) {
		Map<Integer, DetailsDto> detailsDtos = new HashMap<Integer, DetailsDto>();
		List<Object[]> details = null;
		List<String> varslist = new ArrayList<String>(Arrays.asList(vars));

		if (varslist.contains("power_max")) {
			if (!varslist.contains("current_max"))
				varslist.add("current_max");
			if (!varslist.contains("voltage_inst"))
				varslist.add("voltage_inst");
		}
		if (varslist.contains("power_min")) {
			if (!varslist.contains("current_min"))
				varslist.add("current_min");
			if (!varslist.contains("voltage_inst"))
				varslist.add("voltage_inst");
		}
		if (varslist.contains("power_moy")) {
			if (!varslist.contains("energy_act"))
				varslist.add("energy_act");
		}

		if (varslist.contains("power_inst")) {
//			if (!varslist.contains("current_inst"))
//				varslist.add("current_inst");
//			if (!varslist.contains("voltage_inst"))
//				varslist.add("voltage_inst");

			if(dbVersion<2) {
				varslist.remove("power_inst");
			}
		}

		switch (period.toLowerCase()) {
			case "1min":
				details = detailsRepository.findAllDataForVarByGroup(phaseId, varslist, startDate, endDate);
				break;
			case "5min":
				details = detailsRepository.findAll5minDataForVarByGroup(phaseId, varslist, startDate, endDate);
				break;
			case "hours":
				details = detailsRepository.findAllHourDataForVarByGroup(phaseId, varslist, startDate, endDate);
				break;
			case "day":
				details = detailsRepository.findAllDayDataForVarByGroup(phaseId, varslist, startDate, endDate);
				break;
			case "month":
				details = detailsRepository.findAllMonthDataForVarByGroup(phaseId, varslist, startDate, endDate);
				break;
			default:
				return  new HashMap<Integer, DetailsDto>();
		}

		for (Object[] detail : details) {
			if (!detailsDtos.containsKey((Integer) detail[0]))
				detailsDtos.put((Integer) detail[0], new DetailsDto());
			for (int i = 0; i < varslist.size(); i++) {
				if (!detailsDtos.get((Integer) detail[0]).getValeurs().containsKey((Timestamp) detail[1]))
					detailsDtos.get((Integer) detail[0]).getValeurs().put((Timestamp) detail[1], new HashMap<>());
				detailsDtos.get((Integer) detail[0]).getValeurs().get((Timestamp) detail[1]).put(varslist.get(i),
						Math.abs((double) detail[2 + i]));
			}
		}
		return detailsDtos;
	}

	@Override
	public Map<Double, DetailsTemperateurDto> getHistoricalDetailsByTemperature(int[] inputIds, Double[] sondeIds,
																				String[] vars, Timestamp startDate, Timestamp endDate, String period) {
		Map<Double, DetailsTemperateurDto> detailsDtos = new HashMap<Double, DetailsTemperateurDto>();
		double installationId = -1;
		List<Object[]> details = null;
		installationId= installationRepo.getAllInstallationIds(Integer.parseInt(tenantIdentifier.resolveCurrentTenantIdentifier()))
				.stream().map(i-> i*1D)
				.filter(i-> Arrays.asList(sondeIds).contains(i))
				.findFirst().orElse(-1D);
		DetailsTemperateurDto inputsDetails = getHistoricalDetailsByInputs(inputIds, startDate, endDate, "raw");
		if (inputsDetails != null)
			detailsDtos.put(-1D, inputsDetails);

		switch (period.toLowerCase()) {
			case "1min":
				details = detailsRepository.findAllDataForVarArchivTemperature1min(sondeIds, String.join(",", vars),
						startDate, endDate);
				for (Object[] detail : details) {
					if (!detailsDtos.containsKey((Double) detail[0]))
						detailsDtos.put((Double) detail[0], new DetailsTemperateurDto());
					for (int i = 0; i < vars.length; i++) {
						if (!detailsDtos.get((Double) detail[0]).getValeurs().containsKey((Timestamp) detail[1]))
							detailsDtos.get((Double) detail[0]).getValeurs().put((Timestamp) detail[1], new HashMap<>());
						detailsDtos.get((Double) detail[0]).getValeurs().get((Timestamp) detail[1]).put(vars[i],
								((Integer) detail[2 + i]) == null ? null : ((Integer) detail[2 + i]));
					}
				}
				try {
					if (installationId != -1) {
						if(sondeIds.length>0) {
							inputIds = null;
						}
						Map<Double, DetailsTemperateurDto> avgDetailsDtos = getHistoricalAvgTemperatureByInstallation(inputIds,
								sondeIds, vars, startDate, endDate, "1min", installationId);
						Entry<Double, DetailsTemperateurDto> entry = avgDetailsDtos.entrySet().stream().findFirst()
								.orElse(null);
						detailsDtos.compute(entry.getKey(), (k, v) -> entry.getValue());
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
				return detailsDtos;

			case "5min":
				details = detailsRepository.findAllDataForVarArchivTemperature5min(sondeIds,
						"any_value(" + String.join("), any_value(", vars) + ")", startDate, endDate);
				break;
			case "hours":
				details = detailsRepository.findAllDataForVarArchivTemperatureHour(sondeIds,
						"any_value(" + String.join("), any_value(", vars) + ")", startDate, endDate);
				break;
			case "day":
				details = detailsRepository.findAllDataForVarArchivTemperatureDay(sondeIds,
						"any_value(" + String.join("), any_value(", vars) + ")", startDate, endDate);
				break;
			case "month":
				details = detailsRepository.findAllDataForVarArchivTemperatureMonth(sondeIds,
						"any_value(" + String.join("), any_value(", vars) + ")", startDate, endDate);
				break;
			default:
				return new HashMap<Double, DetailsTemperateurDto>();
		}

		for (Object[] detail : details) {
			if (!detailsDtos.containsKey((Double) detail[0]))
				detailsDtos.put((Double) detail[0], new DetailsTemperateurDto());
			for (int i = 0; i < vars.length; i++) {
				if (!detailsDtos.get((Double) detail[0]).getValeurs().containsKey((Timestamp) detail[1]))
					detailsDtos.get((Double) detail[0]).getValeurs().put((Timestamp) detail[1], new HashMap<>());
				detailsDtos.get((Double) detail[0]).getValeurs().get((Timestamp) detail[1]).put(vars[i],
						((BigInteger) detail[2 + i]) == null ? null : ((BigInteger) detail[2 + i]).intValue());
			}
		}
		try {
			if (installationId != -1) {
				if (sondeIds.length > 0) {
					inputIds = null;
				}
				Map<Double, DetailsTemperateurDto> avgDetailsDtos = getHistoricalAvgTemperatureByInstallation(inputIds,
						sondeIds, vars, startDate, endDate, period, installationId);
				Entry<Double, DetailsTemperateurDto> entry = avgDetailsDtos.entrySet().stream().findFirst()
						.orElse(null);
				detailsDtos.compute(entry.getKey(), (k, v) -> entry.getValue());
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		return detailsDtos;
	}

	@Override
	public Map<Double, DetailsTemperateurDto> getHistoricalDetailsByTemperature30Minute(Double[] sondeIds,
																						String[] vars, Timestamp startDate, Timestamp endDate) {
		Map<Double, DetailsTemperateurDto> detailsDtos = new HashMap<Double, DetailsTemperateurDto>();

		List<Object[]> details = null;

		details = detailsRepository.findAllDataForVarArchivTemperature30Minute(sondeIds, String.join(",", vars), startDate,
				endDate);

		for (Object[] detail : details) {
			if (!detailsDtos.containsKey((Double) detail[0]))
				detailsDtos.put((Double) detail[0], new DetailsTemperateurDto());
			for (int i = 0; i < vars.length; i++) {
				if (!detailsDtos.get((Double) detail[0]).getValeurs().containsKey((Timestamp) detail[1]))
					detailsDtos.get((Double) detail[0]).getValeurs().put((Timestamp) detail[1], new HashMap<>());
				detailsDtos.get((Double) detail[0]).getValeurs().get((Timestamp) detail[1]).put(vars[i],
						((BigInteger) detail[2 + i]).intValue() );
			}
		}
		return detailsDtos;
	}
	@Override
	public Map<Integer, DetailsPointDto> getHistoricalDetailsByPoint(int[] pointIds, int[] deviceIds, String[] vars,
																	 Timestamp startDate, Timestamp endDate) {
		Map<Integer, DetailsPointDto> detailsDtos = new HashMap<Integer, DetailsPointDto>();

		List<Object[]> details = null;

		details = detailsRepository.findAllDataForVarArchivTPoint(pointIds, deviceIds, String.join(",", vars),
				startDate, endDate);

		for (Object[] detail : details) {
			int uuid = (Integer) detail[1] + (Integer) detail[2] * 1000;
			if (!detailsDtos.containsKey(uuid))
				detailsDtos.put(uuid, new DetailsPointDto());
			for (int i = 0; i < vars.length; i++) {
				if (!detailsDtos.get(uuid).getValeurs().containsKey((Timestamp) detail[0]))
					detailsDtos.get(uuid).getValeurs().put((Timestamp) detail[0], new HashMap<>());
				detailsDtos.get(uuid).getValeurs().get((Timestamp) detail[0]).put(vars[i], (Double) detail[3 + i]);
			}
		}
		return detailsDtos;
	}

	@Override
	public Map<Integer, DetailsGazDto> getHistoricalDetailsByGaz(int[] gazIds, String[] vars, Timestamp startDate,
																 Timestamp endDate) {
		Map<Integer, DetailsGazDto> detailsGazDtos = new HashMap<Integer, DetailsGazDto>();
		List<Object[]> details = null;

		details = detailsRepository.findAllDataForVarArchivGaz(gazIds, vars, startDate, endDate);

		for (Object[] detail : details) {
			if (!detailsGazDtos.containsKey((Integer) detail[0]))
				detailsGazDtos.put((Integer) detail[0], new DetailsGazDto());
			for (int i = 0; i < vars.length; i++) {
				if (!detailsGazDtos.get((Integer) detail[0]).getValeurs().containsKey((Timestamp) detail[1]))
					detailsGazDtos.get((Integer) detail[0]).getValeurs().put((Timestamp) detail[1], new HashMap<>());
				detailsGazDtos.get((Integer) detail[0]).getValeurs().get((Timestamp) detail[1]).put(vars[i],
						(Integer) detail[2 + i]);
			}
		}
		return detailsGazDtos;
	}

	@Override
	public Map<String, DetailsSensorsDto> getHistoricalDetailsBySensor(String[] sensorIds,
																	   String[] vars, Timestamp startDate, Timestamp endDate) {
		Map<String, DetailsSensorsDto> detailsDtos = new HashMap<String, DetailsSensorsDto>();

		List<Object[]> details = null;

		details = detailsRepository.findAllDataForVarArchivMetos(sensorIds, String.join(",", vars), startDate,
				endDate);

		for (Object[] detail : details) {
			if (!detailsDtos.containsKey((String) detail[0]))
				detailsDtos.put((String) detail[0], new DetailsSensorsDto());
			for (int i = 0; i < vars.length; i++) {
				if (!detailsDtos.get((String) detail[0]).getValeurs().containsKey((Timestamp) detail[1]))
					detailsDtos.get((String) detail[0]).getValeurs().put((Timestamp) detail[1], new HashMap<>());
				detailsDtos.get((String) detail[0]).getValeurs().get((Timestamp) detail[1]).put(vars[i],
						(Double) detail[2 + i]);
			}
		}
		return detailsDtos;
	}

	@Override
	public Map<String, DetailsStationsDto> getHistoricalDetailsByStation(String[] stationIds, String[] vars,
																		 Timestamp startDate, Timestamp endDate) {
		Map<String, DetailsStationsDto> detailsDtos = new HashMap<String, DetailsStationsDto>();

		List<Object[]> details = null;

		details = detailsRepository.findAllDataForVarArchivMetosStation(stationIds, String.join(",", vars), startDate,
				endDate);

		for (Object[] detail : details) {
			if (!detailsDtos.containsKey((String) detail[0]))
				detailsDtos.put((String) detail[0], new DetailsStationsDto());
			for (int i = 0; i < vars.length; i++) {
				if (!detailsDtos.get((String) detail[0]).getValeurs().containsKey((Timestamp) detail[1]))
					detailsDtos.get((String) detail[0]).getValeurs().put((Timestamp) detail[1], new HashMap<>());
				detailsDtos.get((String) detail[0]).getValeurs().get((Timestamp) detail[1]).put(vars[i],
						((Double) detail[2 + i]) );
			}
		}
		return detailsDtos;
	}


	@Override
	public DetailsTemperateurDto getHistoricalDetailsByInputs(int[] inputIds, Timestamp startDate,
															  Timestamp endDate, String period) {
		List<InputDataDto> details = null;
		try {
			switch (period.toLowerCase()) {
				case "raw":
					details = detailsRepository.findAllDataForVarArchivInputRaw(inputIds, startDate, endDate);
					break;
				default:
					return  null;
			}
			Assert.notNull(details, ""); Assert.notEmpty(details, "");
			TreeMap<Timestamp, Map<String, Integer>> collect =
					details.stream().collect(
							Collectors.groupingBy(InputDataDto::getTime, TreeMap::new,Collectors.toMap(value -> value.getId()+"", value -> value.getValue()))
					);
			return new DetailsTemperateurDto(collect);
		} catch (Exception e) {
			return  null;
		}
	}

	@Override
	public Map<Double, DetailsTemperateurDto> getHistoricalAvgTemperatureByInstallation(int[] inputIds,
																						Double[] sondeIds, String[] vars, Timestamp startDate, Timestamp endDate, String period, double installationId) {
		// installationRepo
		Map<Double, DetailsTemperateurDto> detailsDtos = new HashMap<Double, DetailsTemperateurDto>();
		List<Object[]> details = null;

		DetailsTemperateurDto inputsDetails = getHistoricalDetailsByInputs(inputIds, startDate, endDate, "raw");
		if(inputsDetails != null)
			detailsDtos.put(-1D, inputsDetails);
		sondeIds = installationRepo.getAllSondeIdByInstallationId(installationId).toArray(sondeIds);
		switch (period.toLowerCase()) {
			case "1min":
				details = detailsRepository.findAllDataForAvgVarArchivTemperature1min(sondeIds,String.join(",", vars), startDate, endDate);
				for (Object[] detail : details) {
					if (!detailsDtos.containsKey(installationId))
						detailsDtos.put(installationId, new DetailsTemperateurDto());
					for (int i = 0; i < vars.length; i++) {
						if (!detailsDtos.get(installationId).getValeurs().containsKey((Timestamp) detail[0]))
							detailsDtos.get(installationId).getValeurs().put((Timestamp) detail[0], new HashMap<>());
						detailsDtos.get(installationId).getValeurs().get((Timestamp) detail[0]).put(vars[i],
								((BigDecimal) detail[1 + i])== null? null: ((BigDecimal) detail[1 + i]).intValue());
					}
				}
				return detailsDtos;

			case "5min":
				details = detailsRepository.findAllDataForAvgVarArchivTemperature5min(sondeIds,"any_value(" + String.join("), any_value(", vars) + ")", startDate,
						endDate);
				break;
			case "hours":
				details = detailsRepository.findAllDataForAvgVarArchivTemperatureHour(sondeIds,"any_value(" + String.join("), any_value(", vars) + ")", startDate,
						endDate);
				break;
			case "day":
				details = detailsRepository.findAllDataForAvgVarArchivTemperatureDay(sondeIds,"any_value(" + String.join("), any_value(", vars) + ")", startDate,
						endDate);
				break;
			case "month":
				details = detailsRepository.findAllDataForAvgVarArchivTemperatureMonth(sondeIds,"any_value(" + String.join("), any_value(", vars) + ")", startDate,
						endDate);
				break;
			default:
				return  new HashMap<Double, DetailsTemperateurDto>();
		}

		for (Object[] detail : details) {
			if (!detailsDtos.containsKey(installationId))
				detailsDtos.put(installationId, new DetailsTemperateurDto());
			for (int i = 0; i < vars.length; i++) {
				if (!detailsDtos.get(installationId).getValeurs().containsKey((Timestamp) detail[0]))
					detailsDtos.get(installationId).getValeurs().put((Timestamp) detail[0], new HashMap<>());
				detailsDtos.get(installationId).getValeurs().get((Timestamp) detail[0]).put(vars[i],
						((BigDecimal) detail[1 + i])== null? null: ((BigDecimal) detail[1 + i]).intValue());
			}
		}
		return detailsDtos;
	}



	@Override
	public Map<Integer, DetailsDto> getIoHistoricalDetailsByDateRangeAndPeriod(int[] ids,
																			   Timestamp startDate, Timestamp endDate, String period) {
		Map<Integer, DetailsDto> detailsDtos = new HashMap<Integer, DetailsDto>();
		String[] vars = {"value", "global_count"};
		List<Object[]> details = detailsRepository.findAllDataForVarArchivIo(ids, startDate, endDate, period, vars);
		for (Object[] detail : details) {
			if (!detailsDtos.containsKey((Integer) detail[0]))
				detailsDtos.put((Integer) detail[0], new DetailsDto());
			for (int i = 0; i < vars.length; i++) {
				if (!detailsDtos.get((Integer) detail[0]).getValeurs().containsKey((Timestamp) detail[1]))
					detailsDtos.get((Integer) detail[0]).getValeurs().put((Timestamp) detail[1], new HashMap<>());
				detailsDtos.get((Integer) detail[0]).getValeurs().get((Timestamp) detail[1]).put(vars[i],
						(Double) detail[2 + i]);
			}
		}
		return detailsDtos;
	}


}
