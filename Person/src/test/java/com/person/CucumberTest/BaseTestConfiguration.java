package com.person.CucumberTest;

import static io.restassured.RestAssured.given;

import javax.annotation.PostConstruct;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.stereotype.Component;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;



public class BaseTestConfiguration {

	 @LocalServerPort
     int port;
  
  
  @PostConstruct
  private void init() {
	  
	  
	  setPort(port);
  }
  
  
  public static RequestSpecification getRequest() {
	  
	  System.out.println(" PORT XX - " + RestAssured.port);
	  
	  return given();
	  
  }
  
  public static void setPort(int port) {
	  RestAssured.port = port;
	  
	 System.out.println(" PORT XX - " + port);
  }
  
	
}
