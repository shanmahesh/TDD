package com.person.CucumberTest;

import java.util.Map;

import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.event.ContextClosedEvent;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;


public class WireMockInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

	  @Override
	  public void initialize(ConfigurableApplicationContext applicationContext) {

	    WireMockServer wireMockServer =
	      new WireMockServer(new WireMockConfiguration().port(8081)
	    		  //new WireMockConfiguration().dynamicPort()
	    		  );

	    
	    
	    wireMockServer.start();

	    applicationContext.addApplicationListener(applicationEvent -> {
	      if (applicationEvent instanceof ContextClosedEvent) {
	        wireMockServer.stop();
	      }
	    });

	    applicationContext.getBeanFactory()
	      .registerSingleton("wireMockServer", wireMockServer);

	    System.out.print(" GGGGGGGGGGGGG " + wireMockServer.baseUrl());
	    
	    TestPropertyValues
	      .of(Map.of("todo_base_url", wireMockServer.baseUrl()))
	      .applyTo(applicationContext);

	  }
	}
