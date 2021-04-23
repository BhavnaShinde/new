package com.cg.fsd4.kanban_board.controller;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.BDDMockito;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.web.servlet.MockMvc;
//
//import com.cg.fsd4.kanban_board.KanbanBoardManagementApplication;
//import com.cg.fsd4.kanban_board.entity.ProjectEntity;
//import com.cg.fsd4.kanban_board.repo.UserRepo;
//import com.cg.fsd4.kanban_board.services.MapValidationErrorService;
//import com.cg.fsd4.kanban_board.services.UserService;
//import com.fasterxml.jackson.databind.ObjectMapper;
////@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = KanbanBoardManagementApplication.class)
//@ExtendWith(SpringExtension.class)
////@WebMvcTest(UserController.class)
//@WebMvcTest(KanbanBoardManagementApplication.class)
//public class UsercontrollerTesting {
//	@Autowired
//	MockMvc mockMvc;
//	
//	@MockBean
//	UserService service;
//	
//	@MockBean
//	
//	MapValidationErrorService mapValidationErrorService;
//	
//	private static ObjectMapper mapper = new ObjectMapper();
//	@Test
//	void test1_createNewProject() throws Exception{
//	ProjectEntity payment = new ProjectEntity(11,"2019-11-11","2018-11-12","eex","iuyy","gyufu",1);
//	BDDMockito.given(service.addProject(Mockito.any())).willReturn(payment);
//	mockMvc.perform(post("/api/kanban_board/addProject"))
//	.andDo(print())
//	.andExpect(status().isBadRequest());
//	}
//	
//	
//}

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.Arrays;
import java.util.List;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.cg.fsd4.kanban_board.KanbanBoardManagementApplication;
import com.cg.fsd4.kanban_board.entity.ProjectEntity;
import com.cg.fsd4.kanban_board.services.UserService;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment =WebEnvironment.RANDOM_PORT  ,classes = KanbanBoardManagementApplication.class)
//@AutoConfigureMockMvc
@WebMvcTest(UserController.class)
public class UsercontrollerTesting {
	@Autowired
	private MockMvc mvc;

	@MockBean
	private UserService service;

	@BeforeEach
	public void setUp() throws Exception {
	}
	@Test
	public void whenPostStd_thenCreatePro() throws Exception {
		ProjectEntity std1 = new ProjectEntity("Standard");

		given(service.addProject(Mockito.any())).willReturn(std1); 

		mvc.perform(post("/api/kanban_board/addProject").contentType(MediaType.APPLICATION_JSON)
				.content(JsonUtil.toJson(std1))).andExpect(status().isCreated())
				.andExpect(jsonPath("$.stdName", is("Standard_1")));

		verify(service, VerificationModeFactory.times(1)).addProject(Mockito.any());
		reset(service);
	}

	@Test
	public void givenStandards_whenGetStandards_thenReturnJsonArray() throws Exception {
		ProjectEntity std1 = new ProjectEntity("Standard_1");
		ProjectEntity std2 = new ProjectEntity("Standard_2");
		ProjectEntity std3 = new ProjectEntity("Standard_3");

		List<ProjectEntity> allStandards = Arrays.asList(std1, std2, std3);

		 given(service.getProjectDetails()).willReturn(allStandards); 

		mvc.perform(get("/api/kanban_board/getprojectdetails").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(3))).andExpect(jsonPath("$[0].stdName", is(std1.getProjectName())))
				.andExpect(jsonPath("$[1].stdName", is(std2 .getProjectName())))
				.andExpect(jsonPath("$[2].stdName", is(std3.getProjectName())));
		verify(service, VerificationModeFactory.times(1)).getProjectDetails();  
		reset(service);
	}

}