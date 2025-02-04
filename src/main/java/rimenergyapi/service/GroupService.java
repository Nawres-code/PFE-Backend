package rimenergyapi.service;

import java.util.List;

import rimenergyapi.dto.GroupsInfoDto;

public interface GroupService {

	void renameGroup(int id, String name);

	List<GroupsInfoDto> getAllByTenantId(int tenantId);

	GroupsInfoDto updateGroup(GroupsInfoDto groupDto);

	boolean deleteGroup(int id);

	GroupsInfoDto addGroup(GroupsInfoDto groupsInfoDto);
	
}
