package rimenergyapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import rimenergyapi.dto.GroupsInfoDto;
import rimenergyapi.service.GroupService;

@RestController
@RequestMapping("/groups")
@CrossOrigin
@Api(value = "groups")
public class GroupController {

	@Autowired
	private GroupService groupService;
	
	@RequestMapping(value = "/group/{id}/{name}", method = RequestMethod.GET)
	public void renameGroup(@PathVariable("id") int id, @PathVariable("name") String name) {
		 groupService.renameGroup(id, name);
	}
		
	@RequestMapping(value="/{tenantId}", method = RequestMethod.GET)
	public @ResponseBody List<GroupsInfoDto> getAllByTenantId(@PathVariable("tenantId") int tenantId) {
		return groupService.getAllByTenantId(tenantId);
	}
	
	@RequestMapping(value = "/group/{id}", method = RequestMethod.PUT)
	public GroupsInfoDto updateGroup(@RequestBody GroupsInfoDto groupDto, @PathVariable("id") int id) {
		groupDto.setId(id);
		return groupService.updateGroup(groupDto);
	}
	
	@RequestMapping(value = "/group/{id}", method = RequestMethod.DELETE)
	public boolean deleteGroup(@PathVariable("id") int id) {
		return groupService.deleteGroup(id);
	}
	
	@RequestMapping(value = "/group", method = RequestMethod.POST)
	public GroupsInfoDto addGroup(@RequestBody GroupsInfoDto groupsInfoDto) {
		return groupService.addGroup(groupsInfoDto);
	}
}
