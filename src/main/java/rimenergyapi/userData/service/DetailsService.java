package rimenergyapi.userData.service;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import rimenergyapi.userData.model.DetailsDto;
import rimenergyapi.userData.model.DetailsPointDto;
import rimenergyapi.userData.model.DetailsSensorsDto;
import rimenergyapi.userData.model.DetailsStationsDto;
import rimenergyapi.userData.model.DetailsTemperateurDto;
import rimenergyapi.userData.model.DetailsGazDto;

public interface DetailsService {

	public Map<Integer, DetailsDto> getHistoricalDetails(int[] phaseId, String[] vars, Timestamp startDate,
														 Timestamp endDate, String period);

	public Map<Integer, DetailsDto> getHistoricalDetailsByGroup(int[] phaseId, String[] vars,
																Timestamp startDate, Timestamp endDate, String period);

	public  Map<Double, DetailsTemperateurDto>  getHistoricalDetailsByTemperature (int[] inputIds, Double[] sondeIds, String[] vars,Timestamp startDate,
																				   Timestamp endDate, String period);

	public Map<Double, DetailsTemperateurDto> getHistoricalDetailsByTemperature30Minute (Double[] sondeIds, String[] vars,Timestamp startDate,
																						 Timestamp endDate );

	public Map<Integer, DetailsGazDto> getHistoricalDetailsByGaz (int[] gazIds, String[] vars,Timestamp startDate,
																  Timestamp endDate );

	public Map<Integer, DetailsPointDto> getHistoricalDetailsByPoint (int[] pointIds, int[] deviceIds, String[] vars,Timestamp startDate,
																	  Timestamp endDate );

	public Map<String, DetailsStationsDto> getHistoricalDetailsByStation(String[] stationIds,
																		 String[] vars, Timestamp startDate, Timestamp endDate);

	public Map<String, DetailsSensorsDto> getHistoricalDetailsBySensor(String[] sensorIds,
																	   String[] vars, Timestamp startDate, Timestamp endDate);

	public DetailsTemperateurDto getHistoricalDetailsByInputs(int[] inputIds, Timestamp startDate,
															  Timestamp endDate, String period);

	public Map<Double, DetailsTemperateurDto> getHistoricalAvgTemperatureByInstallation(int[] inputIds,
																						Double[] sondeIds, String[] vars, Timestamp startDate, Timestamp endDate, String period,
																						double installationId);

	public Map<Integer, DetailsDto> getIoHistoricalDetailsByDateRangeAndPeriod(int[] ids,
																			   Timestamp startDate, Timestamp endDate, String period);

}
