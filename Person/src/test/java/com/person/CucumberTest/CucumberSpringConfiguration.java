package com.person.CucumberTest;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.stereotype.Component;

import com.person.PersonApplication;
import com.person.service.PersonService;

import io.cucumber.spring.CucumberContextConfiguration;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

import javax.annotation.PostConstruct;




@CucumberContextConfiguration
 @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT,
			classes = com.person.PersonApplication.class)
@AutoConfigureMockMvc
public class CucumberSpringConfiguration {


	@MockBean
	PersonService personService;
	
}
