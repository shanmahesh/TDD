package com.ComputeDiscount.api;

import java.util.Date;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ComputeDiscountAPI {
	
	@GetMapping("/")
	public String hello() {
		return "hello ComputeDiscount";
	}
	
	
	@GetMapping("/age")
	public String computeAge(@RequestParam String date ) {
		//int x = new Date().getYear();
		
		
		//int y = date.getYear();
		
		//return String.valueOf(x - y) ;
		return date;
	}
	
	
	@GetMapping("/getDisc/{x}")
	public String getDisc(@PathVariable Integer x ) {
		int disc = 0; 
		
		if(x <=0)
			disc = 0;
		else if(x <= 20)
			disc = 20;
		else if(x > 20 && x <= 40)
			disc = 40;
		else if(x > 40 && x <= 60)
			disc = 60;
		else  if(x > 60 && x <= 100)
			disc = 100;
		else 
			disc = 0;
		
		System.out.println(" Discount " + disc);
		
		return String.valueOf(disc) ;
	}

}
