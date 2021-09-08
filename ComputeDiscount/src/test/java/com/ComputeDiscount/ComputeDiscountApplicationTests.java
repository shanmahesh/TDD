package com.ComputeDiscount;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import org.hamcrest.Matchers;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class ComputeDiscountApplicationTests {

	
	@Autowired
	MockMvc mockMvc;
	
	@Test
	void contextLoads() {
	}

	@ParameterizedTest
	@CsvSource({
		"0,0,0,0",
		"2,1,20,20",
		"22,21,40,40",
		"42,41,60,60",
		"62,61,100,100",
		"102,101,1000,0"
	})
	public void shouldReturnDiscount(int age,int r1,int r2, String d) throws Exception {
		
		System.out.println(age);
		
		mockMvc.perform(get("/getDisc/{age}", age)).andExpect(content().string(d));
		
	}
	
}
