package rimenergyapi.userData.service.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rimenergyapi.dto.OutputRtDto;
import rimenergyapi.dto.RealTimePointDto;
import rimenergyapi.dto.SensorValuesDto;
import rimenergyapi.entity.CategoryEntity;
import rimenergyapi.entity.GroupsEntity;
import rimenergyapi.entity.InstallationEntity;
import rimenergyapi.entity.ProviderInstallationEntity;
import rimenergyapi.entity.ZonesEntity;
import rimenergyapi.repository.CategoryRepository;
import rimenergyapi.repository.IoImpulseRepository;
import rimenergyapi.repository.ProgramOutputRepository;
import rimenergyapi.repository.ZonesRepository;
import rimenergyapi.userData.model.CalTemperatureDto;
import rimenergyapi.userData.model.CalcArchivePointDto;
import rimenergyapi.userData.model.CategoryRtDto;
import rimenergyapi.userData.model.DataRtDto;
import rimenergyapi.userData.model.GazDto;
import rimenergyapi.userData.model.GroupRtDto;
import rimenergyapi.userData.model.GroupRtMappedDto;
import rimenergyapi.userData.model.InputRtDto;
import rimenergyapi.userData.model.InstallationRtDto;
import rimenergyapi.userData.model.IoDataDto;
import rimenergyapi.userData.model.IoRtDto;
import rimenergyapi.userData.model.PhaseRtDto;
import rimenergyapi.userData.model.ProviderRtDto;
import rimenergyapi.userData.model.RtEnergyDto;
import rimenergyapi.userData.model.RtInputTuple;
import rimenergyapi.userData.model.RtOutputTuple;
import rimenergyapi.userData.model.RtPointTuple;
import rimenergyapi.userData.model.RtSondeTuple;
import rimenergyapi.userData.model.RtTricityDto;
import rimenergyapi.userData.model.RtUserEnergyTuple;
import rimenergyapi.userData.model.ZoneRtDto;
import rimenergyapi.userData.repository.RealTimeRepository;
import rimenergyapi.userData.service.RealTimeService;

@Service
public class RealTimeServiceImpl implements RealTimeService {

	@Autowired
	RealTimeRepository realTimeRepository;

	@Autowired
	private ProgramOutputRepository programOutputRepo;

	@Autowired
	private CurrentTenantIdentifierResolver tenantIdentifier;

	@Autowired
	private ZonesRepository zonesRepository;
	
	@Autowired
	private CategoryRepository categoryRepo;
	
	@Autowired
	private IoImpulseRepository ioImpulseRepository;

	@Override
	public List<Object[]> getAllEnergyCategorie() {
		return realTimeRepository.findAllRtEnergyCategorie();
	}

	@Override
	public List<Object[]> getAllEnergyInstallation() {
		return realTimeRepository.findAllRtEnergyInstallation();
	}

	@Override
	public List<Object[]> getAllEnergyZone() {
		return realTimeRepository.findAllRtEnergyZone();
	}

	@Override
	public DataRtDto getAllEnergy(boolean isSingleInstallation) {

		DataRtDto dataRtDto = new DataRtDto();
		Map<Integer, Map<Integer,InstallationRtDto>> providerData = new HashMap<Integer, Map<Integer,InstallationRtDto>>();
		try {
			
//##############################
// ########### ENERGY ###########
//##############################
			try {		
				List<RtUserEnergyTuple> energyTuples = realTimeRepository.findAllRtEnergy();
				List<CategoryEntity> categories = categoryRepo.getAllConsumptionCtegory(Integer.parseInt(tenantIdentifier.resolveCurrentTenantIdentifier())); 
				dataRtDto.seteAct(energyTuples.stream().filter(t->t.getGroupType().equalsIgnoreCase("general")).mapToDouble(t->t.getActEnergyDay()).sum());
				dataRtDto.seteReact(energyTuples.stream().filter(t->t.getGroupType().equalsIgnoreCase("general")).mapToDouble(t->t.getReactEnergyDay()).sum());
				dataRtDto.seteApp(energyTuples.stream().filter(t->t.getGroupType().equalsIgnoreCase("general")).mapToDouble(t->t.getAppEnergyDay()).sum());
				dataRtDto.seteFund(energyTuples.stream().filter(t->t.getGroupType().equalsIgnoreCase("general")).mapToDouble(t->t.getFundEnergyDay()).sum());
				dataRtDto.setiPower(energyTuples.stream().filter(t->t.getGroupType().equalsIgnoreCase("general")).mapToDouble(t->t.getpInst()).sum());
				
				// eActPerCat 
				Map<Integer, Set<RtUserEnergyTuple>> byCat = energyTuples.stream().collect(Collectors.groupingBy(RtUserEnergyTuple::getCategoryId, Collectors.toSet()));
				byCat.entrySet().stream().forEach(e ->
					dataRtDto.geteActPerCat().compute(e.getKey(), (k,v)->
						new CategoryRtDto(e.getValue().stream().mapToDouble(t->t.getActEnergyDay()).sum(), e.getValue().stream().mapToDouble(t->t.getpInst()).sum()))
				);
				
				//ZonesRtDto
				Map<Integer, Set<RtUserEnergyTuple>> byZone = energyTuples.stream().collect(Collectors.groupingBy(RtUserEnergyTuple::getZoneId, Collectors.toSet()));
				byZone.entrySet().stream().forEach(e -> {
					// zone data
					dataRtDto.getZonesRtDto().compute(e.getKey(),(zk,zv)->
							new ZoneRtDto(e.getValue().stream().filter(t->t.getGroupType().equalsIgnoreCase("general")).mapToLong(c-> c.getTime().getTime()).max().orElse(0),
									e.getValue().stream().filter(t->t.getGroupType().equalsIgnoreCase("general")).mapToDouble(t->t.getActEnergyDay()).sum(), e.getValue().stream().mapToDouble(t->t.getReactEnergyDay()).sum(),
									e.getValue().stream().filter(t->t.getGroupType().equalsIgnoreCase("general")).mapToDouble(t->t.getAppEnergyDay()).sum(), e.getValue().stream().mapToDouble(t->t.getFundEnergyDay()).sum()
									, e.getValue().stream().filter(t->t.getGroupType().equalsIgnoreCase("general")).mapToDouble(t->t.getpInst()).sum()));
					// zone cat
					Map<Integer, Set<RtUserEnergyTuple>> zbyCat = e.getValue().stream().collect(Collectors.groupingBy(RtUserEnergyTuple::getCategoryId, Collectors.toSet()));
					zbyCat.entrySet().stream().forEach(ezCat ->
						dataRtDto.getZonesRtDto().get(e.getKey()).geteActPerCat().compute(ezCat.getKey(), (ck,cv)-> 
							new CategoryRtDto(ezCat.getValue().stream().mapToDouble(t->t.getActEnergyDay()).sum(), ezCat.getValue().stream().mapToDouble(t->t.getpInst()).sum()))
					);
					// installationRtDto
					Map<Integer, Set<RtUserEnergyTuple>> byInst = e.getValue().stream().collect(Collectors.groupingBy(RtUserEnergyTuple::getInstallationId, Collectors.toSet()));
					byInst.entrySet().stream().forEach(eInst -> { 	
						dataRtDto.getZonesRtDto().get(e.getKey()).getInstallationsRtDto().compute(eInst.getKey(),(instk, instv) ->
							new InstallationRtDto(eInst.getValue().stream().filter(t->t.getGroupType().equalsIgnoreCase("general")).mapToLong(c-> c.getTime().getTime()).max().orElse(0),
									eInst.getValue().stream().filter(t->t.getGroupType().equalsIgnoreCase("general")).mapToDouble(t->t.getActEnergyDay()).sum(), eInst.getValue().stream().mapToDouble(t->t.getReactEnergyDay()).sum(),
									eInst.getValue().stream().filter(t->t.getGroupType().equalsIgnoreCase("general")).mapToDouble(t->t.getAppEnergyDay()).sum(), eInst.getValue().stream().mapToDouble(t->t.getFundEnergyDay()).sum()
									, eInst.getValue().stream().filter(t->t.getGroupType().equalsIgnoreCase("general")).mapToDouble(t->t.getpInst()).sum()));					
						// inst cat
						Map<Integer, Set<RtUserEnergyTuple>> instbyCat = eInst.getValue().stream().collect(Collectors.groupingBy(RtUserEnergyTuple::getCategoryId, Collectors.toSet()));
						instbyCat.entrySet().stream().forEach(ezCat ->
							dataRtDto.getZonesRtDto().get(e.getKey())
							.getInstallationsRtDto().get(eInst.getKey())
							.geteActPerCat().compute(ezCat.getKey(), (ck,cv)-> 
								new CategoryRtDto(ezCat.getValue().stream().mapToDouble(t->t.getActEnergyDay()).sum(), ezCat.getValue().stream().mapToDouble(t->t.getpInst()).sum()))
						);
						//
						// groupsRtDto 
						Map<Integer, Set<RtUserEnergyTuple>> byGroup = eInst.getValue().stream().collect(Collectors.groupingBy(RtUserEnergyTuple::getGroupId, Collectors.toSet()));
						byGroup.entrySet().stream().forEach(eGroup -> { 	
							dataRtDto.getZonesRtDto().get(e.getKey()).getInstallationsRtDto().get(eInst.getKey())
							.getGroupsRtDto().compute(eGroup.getKey(),(instk, instv) ->
								new GroupRtDto(eGroup.getValue().stream().mapToLong(c-> c.getTime().getTime()).max().orElse(0),
										eGroup.getValue().stream().mapToDouble(t->t.getActEnergyDay()).sum(), eGroup.getValue().stream().mapToDouble(t->t.getReactEnergyDay()).sum(),
										eGroup.getValue().stream().mapToDouble(t->t.getAppEnergyDay()).sum(), eGroup.getValue().stream().mapToDouble(t->t.getFundEnergyDay()).sum()
										, eGroup.getValue().stream().mapToDouble(t->t.getpInst()).sum()));
							// group cat 
							Map<Integer, Set<RtUserEnergyTuple>> groupbyCat = eGroup.getValue().stream().collect(Collectors.groupingBy(RtUserEnergyTuple::getCategoryId, Collectors.toSet()));
							groupbyCat.entrySet().stream().forEach(egCat ->
								dataRtDto.getZonesRtDto().get(e.getKey())
								.getInstallationsRtDto().get(eInst.getKey())
								.getGroupsRtDto().get(eGroup.getKey())
								.geteActPerCat().put(egCat.getKey(), egCat.getValue().stream().mapToDouble(RtUserEnergyTuple::getAppEnergyDay).sum()));
								//new CategoryRtDto(egCat.getValue().stream().mapToDouble(t->t.getActEnergyDay()).sum(), egCat.getValue().stream().mapToDouble(t->t.getpInst()).sum()))
							// phaseRtDto
							eGroup.getValue().forEach(ph -> {
								dataRtDto.getZonesRtDto().get(e.getKey()).getInstallationsRtDto().get(eInst.getKey())
								.getGroupsRtDto().get(eGroup.getKey()).getPhasesRtDto()
								.compute(ph.getPhaseId(),(k,v)-> new PhaseRtDto(ph.getTime(), ph.getActEnergyDay(),
										ph.getReactEnergyDay(), ph.getAppEnergyDay(), ph.getFundEnergyDay(), ph.getvInst(), 
										ph.getcInst(), ph.getpInst(), ph.getPhInst()));
							});
						});
						
					});
				});
	
				//********* W Autre
				
				try {
					int idCatGeneral = categories.stream().filter(c->c.getType().equalsIgnoreCase("general") || c.getName().equalsIgnoreCase("general")).findFirst().orElse(null).getId();
					int idCatAutres = categories.stream().filter(c->c.getType().equalsIgnoreCase("autres")).findFirst().orElse(null).getId();
					double wAutre = 
					(dataRtDto.geteActPerCat().entrySet().stream().filter(ent->ent.getKey() == idCatGeneral).mapToDouble(ent-> ent.getValue().geteAct()).sum())
					-
					(dataRtDto.geteActPerCat().entrySet().stream().filter(ent->ent.getKey() != idCatGeneral).mapToDouble(ent-> ent.getValue().geteAct()).sum());
					double pAutre = 
							(dataRtDto.geteActPerCat().entrySet().stream().filter(ent->ent.getKey() == idCatGeneral).mapToDouble(ent-> ent.getValue().getiPower()).sum())
							-
							(dataRtDto.geteActPerCat().entrySet().stream().filter(ent->ent.getKey() != idCatGeneral).mapToDouble(ent-> ent.getValue().getiPower()).sum());
					dataRtDto.geteActPerCat().compute(idCatAutres, (l,m)->new CategoryRtDto(wAutre, pAutre));
					
					// by zone 
					energyTuples.stream().map(z->z.getZoneId()).collect(Collectors.toSet()).forEach(zId-> {
						double wAutreZ = 
								(dataRtDto.getZonesRtDto().get(zId).geteActPerCat().entrySet().stream().filter(ent->ent.getKey() == idCatGeneral).mapToDouble(ent-> ent.getValue().geteAct()).sum())
									-
								(dataRtDto.getZonesRtDto().get(zId).geteActPerCat().entrySet().stream().filter(ent->ent.getKey() != idCatGeneral).mapToDouble(ent-> ent.getValue().geteAct()).sum());
					    double pAutreZ = 
								(dataRtDto.getZonesRtDto().get(zId).geteActPerCat().entrySet().stream().filter(ent->ent.getKey() == idCatGeneral).mapToDouble(ent-> ent.getValue().getiPower()).sum())
									-
								(dataRtDto.getZonesRtDto().get(zId).geteActPerCat().entrySet().stream().filter(ent->ent.getKey() != idCatGeneral).mapToDouble(ent-> ent.getValue().getiPower()).sum());
						dataRtDto.getZonesRtDto().get(zId).geteActPerCat().compute(idCatAutres, (zl, zm) -> new CategoryRtDto(wAutreZ, pAutreZ));
					
					// by installation 
						energyTuples.stream().filter(t-> t.getZoneId() == zId)
						.map(z -> z.getInstallationId()).collect(Collectors.toSet()).forEach(iId-> {
							double wAutreI = 
									(dataRtDto.getZonesRtDto().get(zId).getInstallationsRtDto().get(iId).geteActPerCat().entrySet().stream().filter(ent->ent.getKey() == idCatGeneral).mapToDouble(ent-> ent.getValue().geteAct()).sum())
										-
									(dataRtDto.getZonesRtDto().get(zId).getInstallationsRtDto().get(iId).geteActPerCat().entrySet().stream().filter(ent->ent.getKey() != idCatGeneral).mapToDouble(ent-> ent.getValue().geteAct()).sum());
						    double pAutreI = 
									(dataRtDto.getZonesRtDto().get(zId).getInstallationsRtDto().get(iId).geteActPerCat().entrySet().stream().filter(ent->ent.getKey() == idCatGeneral).mapToDouble(ent-> ent.getValue().getiPower()).sum())
										-
									(dataRtDto.getZonesRtDto().get(zId).getInstallationsRtDto().get(iId).geteActPerCat().entrySet().stream().filter(ent->ent.getKey() != idCatGeneral).mapToDouble(ent-> ent.getValue().getiPower()).sum());
							dataRtDto.getZonesRtDto().get(zId).getInstallationsRtDto().get(iId).geteActPerCat().compute(idCatAutres, (zl, zm) -> new CategoryRtDto(wAutreI, pAutreI));
						// by group	
							energyTuples.stream().filter(t-> t.getInstallationId() == iId)
							.map(z -> z.getGroupId()).collect(Collectors.toSet()).forEach(gId-> {
								try {
									double wAutreG = 
											(dataRtDto.getZonesRtDto().get(zId).getInstallationsRtDto().get(iId).getGroupsRtDto().get(gId).geteActPerCat().entrySet().stream().filter(ent->ent.getKey() == idCatGeneral).mapToDouble(ent-> ent.getValue()).sum())
												-
											(dataRtDto.getZonesRtDto().get(zId).getInstallationsRtDto().get(iId).getGroupsRtDto().get(gId).geteActPerCat().entrySet().stream().filter(ent->ent.getKey() != idCatGeneral).mapToDouble(ent-> ent.getValue()).sum());
									dataRtDto.getZonesRtDto().get(zId).getInstallationsRtDto().get(iId).getGroupsRtDto().get(gId).geteActPerCat().compute(idCatAutres, (zl, zm) -> wAutreG);
								} catch (Exception e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							});
						});
					});
				} catch (Exception ex) { ex.printStackTrace(); }
				
				 // provider 
				try {
					int idCatGeneral = categories.stream().filter(c->c.getType().equalsIgnoreCase("general") || c.getName().equalsIgnoreCase("general")).findFirst().orElse(null).getId();
					int idCatAutres = categories.stream().filter(c->c.getType().equalsIgnoreCase("autres")).findFirst().orElse(null).getId();
					List<RtUserEnergyTuple> providerTuples = realTimeRepository.findAllRtProvider();
					//ZonesRtDto
					byZone = providerTuples.stream().collect(Collectors.groupingBy(RtUserEnergyTuple::getZoneId, Collectors.toSet()));
					byZone.entrySet().stream().forEach(e -> {
						providerData.put(e.getKey(), new HashMap<Integer,InstallationRtDto>());
						// installationRtDto
						Map<Integer, Set<RtUserEnergyTuple>> byInst = e.getValue().stream().collect(Collectors.groupingBy(RtUserEnergyTuple::getInstallationId, Collectors.toSet()));
						byInst.entrySet().stream().forEach(eInst -> { 	
						
							providerData.get(e.getKey()).compute(eInst.getKey(),(instk, instv) ->
								new InstallationRtDto(eInst.getValue().stream().filter(t->t.getGroupType().equalsIgnoreCase("general")).mapToLong(c-> c.getTime().getTime()).max().orElse(0),
										eInst.getValue().stream().filter(t->t.getGroupType().equalsIgnoreCase("general")).mapToDouble(t->t.getActEnergyDay()).sum(), eInst.getValue().stream().mapToDouble(t->t.getReactEnergyDay()).sum(),
										eInst.getValue().stream().filter(t->t.getGroupType().equalsIgnoreCase("general")).mapToDouble(t->t.getAppEnergyDay()).sum(), eInst.getValue().stream().mapToDouble(t->t.getFundEnergyDay()).sum()
										, eInst.getValue().stream().filter(t->t.getGroupType().equalsIgnoreCase("general")).mapToDouble(t->t.getpInst()).sum()));					
							// inst cat
							Map<Integer, Set<RtUserEnergyTuple>> instbyCat = eInst.getValue().stream().collect(Collectors.groupingBy(RtUserEnergyTuple::getCategoryId, Collectors.toSet()));
							instbyCat.entrySet().stream().forEach(ezCat ->
								providerData.get(e.getKey()).get(eInst.getKey())
								.geteActPerCat().compute(ezCat.getKey(), (ck,cv)-> 
									new CategoryRtDto(ezCat.getValue().stream().mapToDouble(t->t.getActEnergyDay()).sum(), ezCat.getValue().stream().mapToDouble(t->t.getpInst()).sum()))
							);
							// groupsRtDto 
							Map<Integer, Set<RtUserEnergyTuple>> byGroup = eInst.getValue().stream().collect(Collectors.groupingBy(RtUserEnergyTuple::getGroupId, Collectors.toSet()));
							byGroup.entrySet().stream().forEach(eGroup -> { 	
								providerData.get(e.getKey()).get(eInst.getKey())
								.getGroupsRtDto().compute(eGroup.getKey(),(instk, instv) ->
									new GroupRtDto(eGroup.getValue().stream().mapToLong(c-> c.getTime().getTime()).max().orElse(0),
											eGroup.getValue().stream().mapToDouble(t->t.getActEnergyDay()).sum(), eGroup.getValue().stream().mapToDouble(t->t.getReactEnergyDay()).sum(),
											eGroup.getValue().stream().mapToDouble(t->t.getAppEnergyDay()).sum(), eGroup.getValue().stream().mapToDouble(t->t.getFundEnergyDay()).sum()
											, eGroup.getValue().stream().mapToDouble(t->t.getpInst()).sum()));
								// group cat 
								Map<Integer, Set<RtUserEnergyTuple>> groupbyCat = eGroup.getValue().stream().collect(Collectors.groupingBy(RtUserEnergyTuple::getCategoryId, Collectors.toSet()));
								groupbyCat.entrySet().stream().forEach(egCat ->
								providerData.get(e.getKey()).get(eInst.getKey())
									.getGroupsRtDto().get(eGroup.getKey())
									.geteActPerCat().put(egCat.getKey(), egCat.getValue().stream().mapToDouble(RtUserEnergyTuple::getAppEnergyDay).sum()));
									//new CategoryRtDto(egCat.getValue().stream().mapToDouble(t->t.getActEnergyDay()).sum(), egCat.getValue().stream().mapToDouble(t->t.getpInst()).sum()))
								// phaseRtDto
								eGroup.getValue().forEach(ph -> {
									providerData.get(e.getKey()).get(eInst.getKey())
									.getGroupsRtDto().get(eGroup.getKey()).getPhasesRtDto()
									.compute(ph.getPhaseId(),(k,v)-> new PhaseRtDto(ph.getTime(), ph.getActEnergyDay(),
											ph.getReactEnergyDay(), ph.getAppEnergyDay(), ph.getFundEnergyDay(), ph.getvInst(), 
											ph.getcInst(), ph.getpInst(), ph.getPhInst()));
								});
							});
							double wAutreI = 
									(providerData.get(e.getKey()).get(eInst.getKey()).geteActPerCat().entrySet().stream().filter(ent->ent.getKey() == idCatGeneral).mapToDouble(ent-> ent.getValue().geteAct()).sum())
										-
									(providerData.get(e.getKey()).get(eInst.getKey()).geteActPerCat().entrySet().stream().filter(ent->ent.getKey() != idCatGeneral).mapToDouble(ent-> ent.getValue().geteAct()).sum());
						    double pAutreI = 
									(providerData.get(e.getKey()).get(eInst.getKey()).geteActPerCat().entrySet().stream().filter(ent->ent.getKey() == idCatGeneral).mapToDouble(ent-> ent.getValue().getiPower()).sum())
										-
									(providerData.get(e.getKey()).get(eInst.getKey()).geteActPerCat().entrySet().stream().filter(ent->ent.getKey() != idCatGeneral).mapToDouble(ent-> ent.getValue().getiPower()).sum());
							
						    providerData.get(e.getKey()).get(eInst.getKey()).geteActPerCat().compute(idCatAutres, (zl, zm) -> new CategoryRtDto(wAutreI, pAutreI));
						});
					});
					
					providerData.entrySet().forEach(entry -> {
						// entry.getKey();
						entry.getValue().entrySet().forEach(instEntry -> {
							dataRtDto.getZonesRtDto().get(entry.getKey()).getInstallationsRtDto()
							.get(instEntry.getKey()-1).setProvider(new ProviderRtDto(instEntry.getValue()));
						});
					});
				
				} catch (Exception e2) { }
	
				// last_day_W
				List<Object[]> lastDayW = realTimeRepository.getLastDayW();
				Map<Integer, Double> lastDayInstallationsEnegies = new HashMap<>();
				for(Object[] row : lastDayW) {
					lastDayInstallationsEnegies.put((int)row[0], (double)row[1]);
				}
				dataRtDto.setLastDayInstallationsEnegies(lastDayInstallationsEnegies);
				
			} catch (Exception e) {}
//##############################
// ########### INPUT ###########
//##############################
			
			try {
				List<RtInputTuple> inputTuples = realTimeRepository.findAllRtInput();

				Map<Integer, Map<Integer, Map<Integer, InputRtDto>>> inputsGroupped = inputTuples.stream()
						.collect(Collectors.groupingBy(RtInputTuple::getZoneId, 
									Collectors.groupingBy(RtInputTuple::getInstallationId,
										Collectors.toMap(RtInputTuple::getInputId, InputRtDto::new))));
				
				inputsGroupped.entrySet().forEach(inputEntryZone -> {
					dataRtDto.getZonesRtDto().compute(inputEntryZone.getKey(), 
							(zoneKey, zoneRtDto) -> {
								if(zoneRtDto == null) {
									return new ZoneRtDto(new Timestamp(new Date().getTime()));
								}
								return zoneRtDto;
							});
						inputEntryZone.getValue().entrySet().forEach(inputEntryInstallation -> {
							dataRtDto.getZonesRtDto().get(inputEntryZone.getKey()).getInstallationsRtDto().compute(
								inputEntryInstallation.getKey(),
								(k, v) -> {
									if(v == null) {
										return new InstallationRtDto
												(inputEntryInstallation.getValue().values()
														.stream().mapToLong(c-> c.getLastTime().getTime()).max().orElse(0), 0d, 0d, 0d, 0d, 0d, 
														inputEntryInstallation.getValue(), new HashMap<>(), new HashMap<>(), false);
									}  v.setInputsRtDto(new HashMap<>(inputEntryInstallation.getValue()));
									 return v;
								} );
					});
				});
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
//##############################
// ########### SONDE ###########
//##############################

			try {
				List<RtSondeTuple> sondeTuples = realTimeRepository.findAllRtSonde();
				Map<Integer, Map<Integer, Map<Double, CalTemperatureDto>>> sondesGroupped = sondeTuples.stream()
						.collect(Collectors.groupingBy(RtSondeTuple::getZoneId, 
									Collectors.groupingBy(RtSondeTuple::getInstallationId,
										Collectors.toMap(RtSondeTuple::getSondeId, CalTemperatureDto::new))));
				
				sondesGroupped.entrySet().forEach(sondeEntryZone -> {
					dataRtDto.getZonesRtDto().compute(sondeEntryZone.getKey(), 
							(zoneKey, zoneRtDto) -> {
								if(zoneRtDto == null) {
									return new ZoneRtDto(new Timestamp(new Date().getTime()));
								}
								return zoneRtDto;
							});
					sondeEntryZone.getValue().entrySet().forEach(sondeEntryInstallation -> {
							dataRtDto.getZonesRtDto().get(sondeEntryZone.getKey()).getInstallationsRtDto().compute(
									sondeEntryInstallation.getKey(),
								(k, v) -> {
									// start avg 	
										if(sondeEntryInstallation.getValue().keySet().contains(sondeEntryInstallation.getKey() * 1.0)) {
											Map<Double, CalTemperatureDto> allSonde = sondeEntryInstallation.getValue().entrySet()
											.stream()
											.filter(e -> !e.getKey().equals(sondeEntryInstallation.getKey() * 1.0))
											.collect(Collectors.toMap(e -> e.getKey(), e->e.getValue()));
											allSonde.values().stream().mapToInt(CalTemperatureDto::getLastHumidity).average().orElse(0);
											sondeEntryInstallation.getValue().compute(sondeEntryInstallation.getKey() * 1.0, 
													(kAvg,vAvg)-> {
														vAvg.setLastTemperatue((int) allSonde.values().stream().mapToInt(CalTemperatureDto::getLastTemperatue).average().orElse(0));
														vAvg.setLastHumidity((int) allSonde.values().stream().mapToInt(CalTemperatureDto::getLastHumidity).average().orElse(0));
														vAvg.setLastTime(new Timestamp(allSonde.values().stream().mapToLong(s->s.getLastTime().getTime()).max().orElse(0)));
														return vAvg;
											});
										}
									// end avg
									if(v == null) {
										return new InstallationRtDto
												(sondeEntryInstallation.getValue().values()
														.stream().mapToLong(c-> c.getLastTime().getTime()).max().orElse(0), 0d, 0d, 0d, 0d, 0d, 
														new HashMap<>(), sondeEntryInstallation.getValue(), new HashMap<>(), false);
									}  v.setSondesRtDto(new HashMap<>(sondeEntryInstallation.getValue()));
									 return v;
								} );
					});
				});
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
//##############################
// ########### OUTPUT ###########
//##############################	
			
			try {
				List<RtOutputTuple> outputTuples = realTimeRepository.findAllRtOutput();

				Map<Integer, Map<Integer, List<RtOutputTuple>>> outputsGroupped = outputTuples.stream()
						.collect(Collectors.groupingBy(RtOutputTuple::getZoneId, 
									Collectors.groupingBy(RtOutputTuple::getInstallationId,/*
										Collectors.groupingBy(RtOutputTuple::getOutputId, */Collectors.toList())));
				
				outputsGroupped.entrySet().forEach(outputEntryZone -> {
					dataRtDto.getZonesRtDto().compute(outputEntryZone.getKey(), 
							(zoneKey, zoneRtDto) -> {
								if(zoneRtDto == null) {
									return new ZoneRtDto(new Timestamp(new Date().getTime()));
								}
								return zoneRtDto;
							});
					
					outputEntryZone.getValue().entrySet().forEach(outputEntryInstallation -> {
							dataRtDto.getZonesRtDto().get(outputEntryZone.getKey()).getInstallationsRtDto().compute(
									outputEntryInstallation.getKey(),
								(k, v) -> {
									Map<Integer, OutputRtDto> Dtos = outputEntryInstallation.getValue().stream()
									.collect(Collectors.groupingBy(RtOutputTuple::getOutputId))
									.entrySet().stream()
									.collect(Collectors.toMap(e-> e.getKey(),
											e->{return new OutputRtDto(e.getValue().get(0).getLastTime(),
													e.getValue().get(0).getOutputId(),
													e.getValue().get(0).isOn(),
													e.getValue().stream().map(RtOutputTuple::getProgramsId).collect(Collectors.toSet()));  
											}));
									
									if(v == null) {
										return new InstallationRtDto(outputEntryInstallation.getValue().stream()
												.mapToLong(x-> x.getLastTime().getTime()).max().orElse(0), 0d, 0d, 0d, 0d, 0d, 
												new HashMap<>(), new HashMap<>(), Dtos, outputEntryInstallation.getValue().get(0).isOn());
									}  
									 v.setOutputsRt(new HashMap<>(Dtos));
									 return v;
								});
					});
				});
			} catch (Exception e) {
				e.printStackTrace();
			}
			
//##############################
// ########### POINTS ###########
//##############################
			try {
			// pointRtDto
			List<RtPointTuple> pointTuples = realTimeRepository.findAllRtEnergyPoint();
			Map<Integer, Map<Integer, List<RtPointTuple>>> pointsGroupped = pointTuples.stream()
					.collect(Collectors.groupingBy(RtPointTuple::getZoneId, 
								Collectors.groupingBy(RtPointTuple::getInstallationId,/*
									Collectors.groupingBy(RtOutputTuple::getOutputId, */Collectors.toList())));
			pointsGroupped.entrySet().forEach(pointEntryZone -> {
				dataRtDto.getZonesRtDto().compute(pointEntryZone.getKey(), 
						(zoneKey, zoneRtDto) -> {
							if(zoneRtDto == null) {
								return new ZoneRtDto(new Timestamp(new Date().getTime()));
							}
							return zoneRtDto;
						});
				
				pointEntryZone.getValue().entrySet().forEach(pointEntryInstallation -> {
					dataRtDto.getZonesRtDto().get(pointEntryZone.getKey()).getInstallationsRtDto().compute(
							pointEntryInstallation.getKey(),
						(k, v) -> { 
							Map<Integer, CalcArchivePointDto> Dtos = pointEntryInstallation.getValue().stream()
									.collect(Collectors.groupingBy(RtPointTuple::getPointId))
									.entrySet().stream()
									.collect(Collectors.toMap(e-> e.getValue().get(0).getPointId()+ e.getValue().get(0).getDeviceId() *1000,
											e->{return new CalcArchivePointDto(e.getValue().get(0).getPointId(), e.getValue().get(0).getDeviceId(),
													e.getValue().get(0).getValue(), e.getValue().get(0).getSetpointValue(),  e.getValue().get(0).getLastTime());  
											}));
							if(v == null) {
								return new InstallationRtDto(pointEntryInstallation.getValue().stream()
										.mapToLong(x-> x.getLastTime().getTime()).max().orElse(0), 0d, 0d, 0d, 0d, 0d, Dtos);
							}  
							 v.setPointRtDto(new HashMap<>(Dtos));
							 return v;
						});
				});
			});
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} catch (Exception ee) {
			ee.printStackTrace();
		}

		return dataRtDto;
	}

	@Override
	public List<RealTimePointDto> findRTPointByDevice(int deviceId) {
		return realTimeRepository.findRTPointByDevice(deviceId);
	}

	InstallationEntity findInstallationById(List<ZonesEntity> zones, int id) {
		for (ZonesEntity zone_i : zones) {
			for (InstallationEntity inst_i : zone_i.getInstallations()) {
				if (inst_i.getId() == id)
					return inst_i;
			}
		}
		return null;
	}

	@Override
	public DataRtDto getAllRecapEnergy(Timestamp startTime, Timestamp endTime) {
		List<ZonesEntity> zones = zonesRepository
				.getZoneByTenantId(Integer.parseInt(tenantIdentifier.resolveCurrentTenantIdentifier()));
		List<Object[]> zonedata = realTimeRepository.findAllRecapEnergyZone(startTime, endTime);
		DataRtDto dataRtDto = new DataRtDto();
		for (Object[] rtEnergyZoneDto : zonedata) {
			RtEnergyDto zoneDataCast = new RtEnergyDto((int) rtEnergyZoneDto[0], (Timestamp) rtEnergyZoneDto[1],
					(Double) rtEnergyZoneDto[2], 0, 0, 0);
			ZoneRtDto zoneDto = new ZoneRtDto(zoneDataCast.getTime(), zoneDataCast.getActEnergyDay(), 0, 0, 0);
			List<Object[]> installationsData = realTimeRepository
					.findAllRecapEnergyInstallationByZone(zoneDataCast.getId(), startTime, endTime);
			for (Object[] installationData : installationsData) {
				RtEnergyDto installationDataCast = new RtEnergyDto((int) installationData[0],
						(Timestamp) installationData[1], (Double) installationData[2], 0, 0, 0);
				InstallationRtDto installationDto = new InstallationRtDto(installationDataCast.getTime(),
						installationDataCast.getActEnergyDay(), 0, 0, 0);
				List<Object[]> groupsData = realTimeRepository
						.findAllRecapEnergyGroupsByInstallation(installationDataCast.getId(), startTime, endTime);
				// create group for others
				for (Object[] groupData : groupsData) {
					RtEnergyDto groupDataCast = new RtEnergyDto((int) groupData[0], (Timestamp) groupData[1],
							(Double) groupData[2], 0, 0, 0);
					GroupRtDto groupDto = new GroupRtDto(groupDataCast.getTime(), groupDataCast.getActEnergyDay(), 0, 0,
							0);
					List<Object[]> phasesData = realTimeRepository
							.findAllRecoveryEnergyphasesByGroup(groupDataCast.getId(), startTime, endTime);
					for (Object[] phaseData : phasesData) {
						RtEnergyDto phaseDataCast = new RtEnergyDto((int) phaseData[0], (Timestamp) phaseData[1],
								(Double) phaseData[2], 0, 0, 0);
						PhaseRtDto phaseDto = new PhaseRtDto(phaseDataCast.getTime(), phaseDataCast.getActEnergyDay(),
								0, 0, 0);
						groupDto.getPhasesRtDto().put(phaseDataCast.getId(), phaseDto);
					}
					installationDto.getGroupsRtDto().put(groupDataCast.getId(), groupDto);
				}
				InstallationEntity installation = findInstallationById(zones, installationDataCast.getId());
				if (!(installation.getName().equals("General")))
					dataRtDto.seteAct(dataRtDto.geteAct() + installationDto.geteAct());
				zoneDto.getInstallationsRtDto().put(installationDataCast.getId(), installationDto);
			}
			dataRtDto.getZonesRtDto().put(zoneDataCast.getId(), zoneDto);
		}

		return dataRtDto;
	}

	@Override
	public List<GazDto> getAllEnergyGaz() {
		// TODO Auto-generated method stub
		return realTimeRepository.findAllRtEnergyGazByInstallation();

	}

	@Override
	public List<GazDto> getAllEnergyGazDate(Timestamp startTime, Timestamp endTime) {
		// TODO Auto-generated method stub
		return realTimeRepository.findAllRtEnergyGazByInstallationDate(startTime, endTime);

	}

	private ProviderRtDto getAllEnergyProvider(ProviderInstallationEntity providerEntity) {

		ProviderRtDto providerDto = null;

		try {

			Object[] installationData = realTimeRepository.findAllRtEnergyInstallationById(providerEntity.getId());

			Object[] installationTricityData = realTimeRepository.findAllRttTricityInstallationById(providerEntity.getId());
			RtEnergyDto installationDataCast = new RtEnergyDto((int) installationData[0],
					(Timestamp) installationData[1], (Double) installationData[2], 0, 0, 0);

			ProviderRtDto providerDto_ = new ProviderRtDto(installationDataCast.getTime(),
					installationDataCast.getActEnergyDay(), 0, 0, 0);

			if (installationTricityData != null) {
				providerDto_.setiPower((Double) installationTricityData[2]);
			}

			// energy groups
			List<Object[]> groupsData = realTimeRepository
					.findAllRtEnergyGroupsByInstallation(installationDataCast.getId());
			// tricity groups
			List<Object[]> groupsTricityData = realTimeRepository
					.findAllRtTricityGroupsByInstallation(installationDataCast.getId());
			// create group for others
			// Lets seek for generals

			/***************************
			 * GROUPS DATA
			 **********************************************/
			// From Father to Son
			for (Object[] groupData : groupsData) {
				RtEnergyDto groupDataCast = new RtEnergyDto((int) groupData[0], (Timestamp) groupData[1],
						(Double) groupData[2], (Double) groupData[3], (Double) groupData[4], (Double) groupData[5],
						(boolean) (groupData[7].toString().contains("General")));
				GroupRtDto groupDto = new GroupRtDto(groupDataCast.getTime(), groupDataCast.getActEnergyDay(),
						groupDataCast.getReactEnergyDay(), groupDataCast.getFundEnergyDay(),
						groupDataCast.getAppEnergyDay());

				for (Object[] groupTricityData : groupsTricityData) {
					if ((int) groupTricityData[0] == (int) groupData[0]) {
						groupDto.setiPower((Double) groupTricityData[2]);
					}
				}
				List<Object[]> phasesData = realTimeRepository.findAllRtEnergyphasesByGroup(groupDataCast.getId());

				for (Object[] phaseData : phasesData) {
					RtEnergyDto phaseDataCast = new RtEnergyDto((int) phaseData[0], (Timestamp) phaseData[1],
							(Double) phaseData[2], 0, 0, 0);
					PhaseRtDto phaseDto = new PhaseRtDto(phaseDataCast.getTime(), phaseDataCast.getActEnergyDay(), 0, 0,
							0);
					groupDto.getPhasesRtDto().put(phaseDataCast.getId(), phaseDto);
				}
				providerDto_.getGroupsRtDto().put(groupDataCast.getId(), groupDto);

				List<Object[]> phasesTricityData = realTimeRepository
						.findAllRtTricityphasesByGroup(groupDataCast.getId());
				for (Object[] phaseTricityData : phasesTricityData) {
					RtTricityDto phasesTricityDataCast = new RtTricityDto((int) phaseTricityData[0],
							(Timestamp) phaseTricityData[1], (double) phaseTricityData[2], (double) phaseTricityData[3],
							(double) phaseTricityData[4], (double) phaseTricityData[5], (double) phaseTricityData[6]);
					/*
					 * PhaseRtDto phaseDto=new PhaseRtDto(phasesTricityDataCast.getTime(), 0, 0, 0,
					 * 0, phasesTricityDataCast.getCmoy(), phasesTricityDataCast.getVmoy(),
					 * phasesTricityDataCast.getPmoy(), phasesTricityDataCast.getPhmoy())
					 */

					providerDto_.getGroupsRtDto().get(groupDataCast.getId()).getPhasesRtDto()
							.get((int) phaseTricityData[0]).setVmoy((double) phaseTricityData[2]);
					providerDto_.getGroupsRtDto().get(groupDataCast.getId()).getPhasesRtDto()
							.get((int) phaseTricityData[0]).setCmoy((double) phaseTricityData[3]);
					providerDto_.getGroupsRtDto().get(groupDataCast.getId()).getPhasesRtDto()
							.get((int) phaseTricityData[0]).setPmoy((double) phaseTricityData[4]);
					providerDto_.getGroupsRtDto().get(groupDataCast.getId()).getPhasesRtDto()
							.get((int) phaseTricityData[0]).setPhmoy((double) phaseTricityData[5]);

				}
				// end details trt

			}
			// GroupRtDto otherGroupDto =new GroupRtDto(null, 0, 0, 0, 0);

			for (GroupsEntity group : providerEntity.getGroupses()) {
				if (group.getType().equals("General")) {
					providerDto_.getGroupsRtDto().put(group.getId(), providerDto_.getGroupsRtDto().get(group.getId()));
					providerDto_.getGroupsRtDto().get(group.getId());

					// Percat

					GroupRtDto groupDto = providerDto_.getGroupsRtDto().get(group.getId());

					if (!providerDto_.geteActPerCat().containsKey(group.getCategoryId()))
						providerDto_.geteActPerCat().put(group.getCategoryId(), new CategoryRtDto(0, 0));
					providerDto_.geteActPerCat().get(group.getCategoryId()).seteAct(
							providerDto_.geteActPerCat().get(group.getCategoryId()).geteAct() + groupDto.geteAct());
					providerDto_.geteActPerCat().get(group.getCategoryId()).setiPower(
							providerDto_.geteActPerCat().get(group.getCategoryId()).getiPower() + groupDto.getiPower());
					/*
					 * if (!zoneDto.geteActPerCat().containsKey(group.getCategoryId()))
					 * zoneDto.geteActPerCat().put(group.getCategoryId(), new CategoryRtDto(0, 0));
					 * zoneDto.geteActPerCat().get(group.getCategoryId())
					 * .seteAct(zoneDto.geteActPerCat().get(group.getCategoryId()).geteAct() +
					 * groupDto.geteAct()); zoneDto.geteActPerCat().get(group.getCategoryId())
					 * .setiPower(zoneDto.geteActPerCat().get(group.getCategoryId()).getiPower() +
					 * groupDto.getiPower());
					 * 
					 * if (!dataRtDto.geteActPerCat().containsKey(group.getCategoryId()))
					 * dataRtDto.geteActPerCat().put(group.getCategoryId(), new CategoryRtDto(0,
					 * 0)); dataRtDto.geteActPerCat().get(group.getCategoryId())
					 * .seteAct(dataRtDto.geteActPerCat().get(group.getCategoryId()).geteAct() +
					 * groupDto.geteAct()); dataRtDto.geteActPerCat().get(group.getCategoryId())
					 * .setiPower(dataRtDto.geteActPerCat().get(group.getCategoryId()).getiPower() +
					 * groupDto.getiPower());
					 */

					providerDto_.geteActPerCat().get(4)
							.seteAct(providerDto_.geteActPerCat().get(4).geteAct() + groupDto.geteAct());
					providerDto_.geteActPerCat().get(4)
							.setiPower(providerDto_.geteActPerCat().get(4).getiPower() + groupDto.getiPower());
					/*
					 * if (!isSingleInstallation) { zoneDto.geteActPerCat().get(4)
					 * .seteAct(zoneDto.geteActPerCat().get(4).geteAct() + groupDto.geteAct());
					 * zoneDto.geteActPerCat().get(4).setiPower(
					 * zoneDto.geteActPerCat().get(4).getiPower() + groupDto.getiPower());
					 * 
					 * dataRtDto.geteActPerCat().get(4)
					 * .seteAct(dataRtDto.geteActPerCat().get(4).geteAct() + groupDto.geteAct());
					 * dataRtDto.geteActPerCat().get(4).setiPower(
					 * dataRtDto.geteActPerCat().get(4).getiPower() + groupDto.getiPower()); }
					 */
				}

			}

			Iterator<Integer> itInstDto = providerDto_.getGroupsRtDto().keySet().iterator();
			while (itInstDto.hasNext()) {
				int key = itInstDto.next();
				// GroupRtMappedDto groupMapped = providerDto_.getGroupsRtDto().get(key);
				GroupsEntity selectedGroup = providerEntity.getGroupById(key);

				selectedGroup.getChildren().stream().forEach(child1 -> {
					GroupRtDto rtDto = providerDto_.getGroupsRtDto().get(child1.getId());
					if (rtDto != null) {
						GroupRtMappedDto groupMapped2 = new GroupRtMappedDto(rtDto);
						child1.getChildren().stream().forEach(child2 -> {
							GroupRtDto rtDto2 = providerDto_.getGroupsRtDto().get(child2.getId());
							if (rtDto2 != null)
								groupMapped2.getGroupsRtMappedDto().put(child2.getId(), new GroupRtMappedDto(rtDto2));
						});
						// groupMapped.getGroupsRtMappedDto().put(child1.getId(), groupMapped2);

						// per cat
						if (!providerDto_.geteActPerCat().containsKey(child1.getCategoryId()))
							providerDto_.geteActPerCat().put(child1.getCategoryId(), new CategoryRtDto(0, 0));
						providerDto_.geteActPerCat().get(child1.getCategoryId()).seteAct(
								providerDto_.geteActPerCat().get(child1.getCategoryId()).geteAct() + rtDto.geteAct());
						providerDto_.geteActPerCat().get(child1.getCategoryId())
								.setiPower(providerDto_.geteActPerCat().get(child1.getCategoryId()).getiPower()
										+ rtDto.getiPower());

						/*
						 * if (!zoneDto.geteActPerCat().containsKey(child1.getCategoryId()))
						 * zoneDto.geteActPerCat().put(child1.getCategoryId(), new CategoryRtDto(0, 0));
						 * zoneDto.geteActPerCat().get(child1.getCategoryId())
						 * .seteAct(zoneDto.geteActPerCat().get(child1.getCategoryId()).geteAct() +
						 * rtDto.geteAct()); zoneDto.geteActPerCat().get(child1.getCategoryId())
						 * .setiPower(zoneDto.geteActPerCat().get(child1.getCategoryId()).getiPower() +
						 * rtDto.getiPower());
						 * 
						 * if (!dataRtDto.geteActPerCat().containsKey(child1.getCategoryId()))
						 * dataRtDto.geteActPerCat().put(child1.getCategoryId(), new CategoryRtDto(0,
						 * 0)); dataRtDto.geteActPerCat().get(child1.getCategoryId())
						 * .seteAct(dataRtDto.geteActPerCat().get(child1.getCategoryId()).geteAct() +
						 * rtDto.geteAct()); dataRtDto.geteActPerCat().get(child1.getCategoryId())
						 * .setiPower(dataRtDto.geteActPerCat().get(child1.getCategoryId()).getiPower()
						 * + rtDto.getiPower());
						 */

						providerDto_.geteActPerCat().get(4)
								.seteAct(providerDto_.geteActPerCat().get(4).geteAct() - rtDto.geteAct());
						providerDto_.geteActPerCat().get(4)
								.setiPower(providerDto_.geteActPerCat().get(4).getiPower() - rtDto.getiPower());
						/*
						 * zoneDto.geteActPerCat().get(4)
						 * .seteAct(zoneDto.geteActPerCat().get(4).geteAct() - rtDto.geteAct());
						 * zoneDto.geteActPerCat().get(4)
						 * .setiPower(zoneDto.geteActPerCat().get(4).getiPower() - rtDto.getiPower());
						 * dataRtDto.geteActPerCat().get(4)
						 * .seteAct(dataRtDto.geteActPerCat().get(4).geteAct() - rtDto.geteAct());
						 * dataRtDto.geteActPerCat().get(4).setiPower(
						 * dataRtDto.geteActPerCat().get(4).getiPower() - rtDto.getiPower());
						 */
					} else {
						if (!providerDto_.geteActPerCat().containsKey(child1.getCategoryId()))
							providerDto_.geteActPerCat().put(child1.getCategoryId(), new CategoryRtDto(0, 0));
					}
				});

			}
			providerDto = providerDto_;
		} catch (Exception e) {
			providerDto = new ProviderRtDto(new Timestamp(0), 0, 0, 0, 0);
		}

		return providerDto;
	}

	@Override
	public Map<Integer, Map<String, List<SensorValuesDto>>> getAllRtSensors() {
		Map<Integer, Map<String, List<SensorValuesDto>>> rtMap = null;
		try {
			List<SensorValuesDto> rtSensors = realTimeRepository.findAllRtSensors();
			rtMap = rtSensors.stream().collect(Collectors.groupingBy(SensorValuesDto::getInstallationId, HashMap::new,
					Collectors.groupingBy(SensorValuesDto::getStationId, Collectors.toList())));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rtMap;
	}
	
	@Override
	public Map<Integer, IoRtDto> getAllRtIo(String period) {
		List<IoRtDto>  rtList = realTimeRepository.findAllRtIo(period);
		Map<Integer, IoRtDto> hash  = new HashMap<>();
		try {
			for (IoRtDto ioRtDto : rtList) {
				hash.put(ioRtDto.getIoId(), ioRtDto);
			}
		} catch (Exception e) { }
		return hash;
	}
}
