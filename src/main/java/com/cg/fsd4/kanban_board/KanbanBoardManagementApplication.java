package com.cg.fsd4.kanban_board;

import java.beans.JavaBean;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


import org.springframework.context.annotation.Bean;


import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@SpringBootApplication
//@ComponentScan({"com.cg.fsd4.kanban_board"})

public class KanbanBoardManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(KanbanBoardManagementApplication.class, args);
	}

}
