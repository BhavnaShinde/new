package com.cg.fsd4.kanban_board.controller;

//import static org.junit.jupiter.api.Assertions.*;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import com.cg.fsd4.kanban_board.entity.ProjectEntity;
//import com.cg.fsd4.kanban_board.entity.TeamMemberEntity;
//import com.cg.fsd4.kanban_board.entity.UserEntity;
//import com.cg.fsd4.kanban_board.services.ManageTeamService;
//
//class ManageTeamContollerTest {
//
//	@Autowired
//	private ManageTeamService manageTeamService;
//
//	@Test
//	void testCreateUser() {
//		UserEntity entity = new UserEntity();
//
//		entity.setUserName("anil");
//		entity.setEmailId("anil@gmail.com");
//		entity.setPassword("anil");
//		entity.setOrganisation("Ka");
//		assertEquals("User Account created succesfully", manageTeamService.createUserAccount(entity));
//	}
//
//	@Test
//	void testAddTeamMember() {
//		TeamMemberEntity entity = new TeamMemberEntity();
//		entity.setEmployeeId(2343234);
//		entity.setTeamMemberName("chandu");
//		entity.setProjectPassword("chandu123");
//		entity.getProjectEntity().setProjectId(21);
//		;
//		assertEquals("Team member added succesfully", manageTeamService.addTeamMember(entity));
//	} 
//	
//	@Test
//	void testGetTeamMembers() {
//		assertNotNull(manageTeamService.getTeamMembers());
//	}
//
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//
//	@Test
//	void testAddTeamLead() {
//		fail("Not yet implemented");
//	}
//
//	
//	
//	@Test
//	void testGetTeamLead() {
//
//	}
//
//	@Test
//	void testDeleteTeamLead() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testDeleteTeamMember() {
//		fail("Not yet implemented");
//	}
//
//}
