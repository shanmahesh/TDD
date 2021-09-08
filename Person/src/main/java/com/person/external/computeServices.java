package com.person.external;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@Component
public class computeServices implements compute {

	@Value("${computeservice.base.url}")
	String computeServiceBaeUrl;
	
	@Override
	public String computeByAge(int age) {
		// TODO Auto-generated method stub
		
		WebClient webClient = WebClient.create(computeServiceBaeUrl);
		
		Mono<String> disc = 
		webClient
		.get()
		.uri("/getDisc/{x}", age)
		.retrieve()
		.bodyToMono(String.class)
		;
		
		return disc.block();
	}

}
