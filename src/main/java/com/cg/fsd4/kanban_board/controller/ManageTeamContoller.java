package com.cg.fsd4.kanban_board.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.fsd4.kanban_board.entity.ProjectEntity;
import com.cg.fsd4.kanban_board.entity.TeamLeadEntity;
import com.cg.fsd4.kanban_board.entity.TeamMemberEntity;
import com.cg.fsd4.kanban_board.entity.UserEntity;
import com.cg.fsd4.kanban_board.exception.TeamMemberNotFoundException;
import com.cg.fsd4.kanban_board.response.KanbanBoardResponse;
import com.cg.fsd4.kanban_board.services.ManageTeamService;

@RequestMapping(path = "/api/kanban_board")
@RestController

@ComponentScan(basePackages = {"com.cg.fsd4.kanban_board.controller"})
@CrossOrigin("*")
public class ManageTeamContoller {
	

	@Autowired
	private ManageTeamService manageTeamService;

	@PostMapping(path = "/createUser", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public KanbanBoardResponse createUserAccount(@RequestBody UserEntity userEntity) {
		
		UserEntity user = manageTeamService.createUserAccount(userEntity);
		KanbanBoardResponse kanbanBoardResponse = new KanbanBoardResponse();

		if (user == null) {
			kanbanBoardResponse.setMessage("User Account not created");
		} else {
			kanbanBoardResponse.setMessage("User Account created succesfully");
		}
		return kanbanBoardResponse;
	}

//	@PostMapping(path = "/addProject", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//	public KanbanBoardResponse addProject(@RequestBody ProjectEntity projectEntity) {
//
//		ProjectEntity project = manageTeamService.addProject(projectEntity);
//		KanbanBoardResponse kanbanBoardResponse = new KanbanBoardResponse();
//
//		if (project == null) {
//			kanbanBoardResponse.setMessage("Project not added");
//		} else {
//			kanbanBoardResponse.setMessage("Project added succesfully");
//		}
//
//		return kanbanBoardResponse;
//	}

	@PostMapping(path = "/addteamlead", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public KanbanBoardResponse addTeamLead(@RequestBody TeamLeadEntity teamLeadEntity) {
		int count = 0;
		List<TeamLeadEntity> teamLeadList = manageTeamService.getTeamLead();

		KanbanBoardResponse kanbanBoardResponse = new KanbanBoardResponse();
		for (TeamLeadEntity onebyone : teamLeadList) {
			if (onebyone.getProjectEntity().getProjectId() == teamLeadEntity.getProjectEntity().getProjectId()) {
				count++;
			}
		}
		if (count == 0) {
			TeamLeadEntity teamLead = manageTeamService.addTeamLead(teamLeadEntity);
			kanbanBoardResponse.setMessage("Team lead details added");
			return kanbanBoardResponse;
		}
		kanbanBoardResponse.setMessage("Fail team lead already added");
		return kanbanBoardResponse;
	}

	@PostMapping(path = "/addteammember", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public KanbanBoardResponse addTeamMember(@RequestBody TeamMemberEntity teamMemberEntity) {
		TeamMemberEntity teamLead = manageTeamService.addTeamMember(teamMemberEntity);
		KanbanBoardResponse kanbanBoardResponse = new KanbanBoardResponse();
		if (teamLead == null) {
			kanbanBoardResponse.setMessage("Team member not added");
		} else {

			kanbanBoardResponse.setMessage("Team member added succesfully");

		}

		return kanbanBoardResponse;
	}

	@GetMapping(path = "/getmembers", produces = MediaType.APPLICATION_JSON_VALUE)
	public KanbanBoardResponse getTeamMembers(int projectId) {

		List<TeamMemberEntity> teamMembers = manageTeamService.getTeamMembers();
		KanbanBoardResponse kanbanBoardResponse = new KanbanBoardResponse();
		TeamMemberNotFoundException exception = new TeamMemberNotFoundException();
		List<TeamMemberEntity> list = new ArrayList<TeamMemberEntity>();
		try {
			for (TeamMemberEntity onebyone : teamMembers) {
				if (onebyone.getProjectEntity().getProjectId() == projectId) {
					list.add(onebyone);
				}
			}
		} catch (Exception e) {
			kanbanBoardResponse.setMessage(e.getMessage());
		}

		kanbanBoardResponse.setMembersList(list);
		return kanbanBoardResponse;
	}

	@GetMapping(path = "/getlead",produces = MediaType.APPLICATION_JSON_VALUE)
	public KanbanBoardResponse getTeamLead(int projectId) {

		List<TeamLeadEntity> teamLead = manageTeamService.getTeamLead();
		KanbanBoardResponse kanbanBoardResponse = new KanbanBoardResponse();
		List<TeamLeadEntity> lead = new ArrayList<TeamLeadEntity>();
		try {
			for (TeamLeadEntity onebyone : teamLead) {
				if (onebyone.getProjectEntity().getProjectId() == projectId) {
					kanbanBoardResponse.setTeamLeadEntity(onebyone);
					lead.add(onebyone);
					kanbanBoardResponse.setTeamLeadList(lead);
					return kanbanBoardResponse;
				}
			}
		} catch (Exception e) {
			kanbanBoardResponse.setMessage(e.getMessage());
		}
		return kanbanBoardResponse;

	}

	@DeleteMapping(path = "/deleteteamlead",produces = MediaType.APPLICATION_JSON_VALUE)
	public boolean deleteTeamLead(int teamLeaderId) {
		System.out.println(teamLeaderId);
//		KanbanBoardResponse kanbanBoardResponse = new KanbanBoardResponse();
		boolean isDeleted = false;
//		try {
			TeamLeadEntity teamLead = manageTeamService.deleteTeamLead(teamLeaderId);

			isDeleted = manageTeamService.deleteLead(teamLead);
//		} catch (Exception e) {
//			kanbanBoardResponse.setMessage("Team lead not deleted");
//			return kanbanBoardResponse;
//		}
//
//		if (isDeleted) {
//			kanbanBoardResponse.setMessage("Team lead deleted succesfully");
//		} else {
//			kanbanBoardResponse.setMessage("Team lead not deleted");
//		}
//		return kanbanBoardResponse;
		return isDeleted;
	}

	@DeleteMapping(path = "/deleteteammember",produces = MediaType.APPLICATION_JSON_VALUE)
	public boolean deleteTeamMember(int teamMemberId) throws TeamMemberNotFoundException {
		boolean isDeleted = false;
//		KanbanBoardResponse kanbanBoardResponse = new KanbanBoardResponse();

//		try {
			TeamMemberEntity teamLead = manageTeamService.deleteTeamMember(teamMemberId);

			isDeleted = manageTeamService.deleteMember(teamLead);


		return isDeleted;
	}
}