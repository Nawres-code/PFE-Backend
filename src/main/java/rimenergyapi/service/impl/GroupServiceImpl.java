package rimenergyapi.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rimenergyapi.dto.GroupsInfoDto;
import rimenergyapi.entity.GroupsEntity;
import rimenergyapi.entity.PhaseEntity;
import rimenergyapi.repository.GroupRepository;
import rimenergyapi.service.GroupService;
import rimenergyapi.service.InstallationService;
import rimenergyapi.utils.GenericUtil;

@Service
public class GroupServiceImpl implements GroupService {

	@Autowired
	private GroupRepository groupRepo;
	
	@Autowired
	private InstallationService installationService;
	
	@Autowired
	protected ModelMapper mapper;
	
	
	@Transactional
	@Override
	public void renameGroup(int id, String name) {
		try {
			Optional<GroupsEntity> entityOpt = groupRepo.findById(id);
			if(entityOpt.isPresent()) {
				GroupsEntity entityUpdate = entityOpt.get();
				entityUpdate.setName(name);
				groupRepo.saveAndFlush(entityUpdate);
			}
		} catch (Exception e) { }
	}


	@Override
	public List<GroupsInfoDto> getAllByTenantId(int tenantId) {
		try {
			return GenericUtil.map(mapper, groupRepo.findByTenantId(tenantId), GroupsInfoDto.class ) ;
		} catch (Exception e) {	}
	
		return null;
	}


	@Override
	@Transactional
	public GroupsInfoDto updateGroup(GroupsInfoDto groupDto) {
		try {
			Optional<GroupsEntity> entityOpt = groupRepo.findById(groupDto.getId());
			if(entityOpt.isPresent()) {
				return addGroup(groupDto);
			}
		} catch (Exception e) { }
		
	return null;
	}


	@Override
	@Transactional
	public boolean deleteGroup(int id) {
		try {
			
			GroupsEntity entity = groupRepo.findById(id).get();
			// parent
			try {
				GroupsEntity parent = groupRepo.findById(entity.getParent().getId()).get();
				parent.getChildren().remove(entity);
			} catch (Exception e) { }
			entity.setParent(null);
			
			// phases
			for (PhaseEntity phase : entity.getPhases()) {
			    entity.removePhase(phase);
			}
			
			groupRepo.delete(entity);
			return true;
		} catch (Exception e) { 
			e.printStackTrace();
		}
		return false;
	}


	@Override
	@Transactional
	public GroupsInfoDto addGroup(GroupsInfoDto groupsInfoDto) {
		try {
			GroupsEntity group =  mapper.map(groupsInfoDto, GroupsEntity.class); 
			GroupsEntity parent = groupsInfoDto.getParentId() == 0 ? null : groupRepo.getOne(groupsInfoDto.getParentId());
			group.setParent(parent);
			group.setInstallation(installationService.getInstallationById(groupsInfoDto.getInstallationId()));
			group = groupRepo.save(group);
			groupsInfoDto = mapper.map(group, GroupsInfoDto.class);
			groupsInfoDto.setDeviceId(group.getPhases().stream().findFirst().get().getDevice().getId());
			groupsInfoDto.setParentId(group.getParent().getId());
			return groupsInfoDto;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
