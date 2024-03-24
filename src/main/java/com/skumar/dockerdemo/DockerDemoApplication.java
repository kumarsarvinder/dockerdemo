package com.skumar.dockerdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class DockerDemoApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(DockerDemoApplication.class);
	}

	@GetMapping("/greetings/{name}")
	public String getGreetings(@PathVariable String name){
		return "Hello "+ name + " Welcome Docker Application from Image";
	}


	public static void main(String[] args) {
		SpringApplication.run(DockerDemoApplication.class, args);
	}

}
