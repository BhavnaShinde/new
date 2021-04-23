package com.cg.fsd4.kanban_board.services;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.cg.fsd4.kanban_board.entity.ProjectEntity;
import com.cg.fsd4.kanban_board.exception.ResourceNotFoundException;
import com.cg.fsd4.kanban_board.repo.ProjectRepo;

@ExtendWith(SpringExtension.class)
public class UserServiceImplIntegrationTest {
	@TestConfiguration
	static class userServiceImplTestContextConfiguration {
		@Bean
		public UserService standardService() {
			return new UserServiceImpl();
		}
	}

	@Autowired
	private UserService standardService;
	@MockBean
	private ProjectRepo standardRepository;

	@BeforeEach
	public void setUp() {
		ProjectEntity std1 = new ProjectEntity("Standard_1");
		std1.setProjectId(11);

		ProjectEntity std2 = new ProjectEntity("Standard_2");
		ProjectEntity std3 = new ProjectEntity("Standard_3");

		List<ProjectEntity> stds = Arrays.asList(std1, std2, std3);

		Mockito.when(standardRepository.findById(std1.getProjectId())).thenReturn((Optional.of(std1)));
		Mockito.when(standardRepository.findAll()).thenReturn(stds);
		Mockito.when(standardRepository.findById((int) 11)).thenReturn(Optional.empty());
	}

	@Test
	public void whenValidId_thenProjectShouldBeFound() throws ResourceNotFoundException {
		List<ProjectEntity> fromDb = standardService.getProjectDetails();
		assertThat(fromDb.get(1).getProjectName());

		verifyFindByIdIsCalledOnce();
	}

	private void verifyFindByIdIsCalledOnce() {
		// TODO Auto-generated method stub
		Mockito.verify(standardRepository, VerificationModeFactory.times(1)).findAll();
		Mockito.reset(standardRepository);
	}

	@Test
	public void whenInValidId_thenProjectShouldNotBeFound() throws ResourceNotFoundException {
		ProjectEntity fromDb = standardService.getProById(1);
		verifyFindByIdIsCalledOnce();
		assertThat(fromDb).isNull();
	}

	@Test
	public void given3Project_whenGetAll_thenReturn3Records() throws ResourceNotFoundException {
		ProjectEntity std1 = new ProjectEntity("Standard_1");
		ProjectEntity std2 = new ProjectEntity("Standard_2");
		ProjectEntity std3 = new ProjectEntity("Standard_3");

		List<ProjectEntity> allStandards = standardService.getProjectDetails();
		verifyFindAllStandardsIsCalledOnce();
		assertThat(allStandards).hasSize(3).extracting(ProjectEntity::getTeamMemberList)
				.contains(std1.getTeamMemberList(), std2.getTeamMemberList(), std3.getTeamMemberList());
	}

	private void verifyFindAllStandardsIsCalledOnce() {
		// TODO Auto-generated method stub
		Mockito.verify(standardRepository, VerificationModeFactory.times(1)).findAll();
		Mockito.reset(standardRepository);
	}

}
