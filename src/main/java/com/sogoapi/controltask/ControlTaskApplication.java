package com.sogoapi.controltask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ControlTaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(ControlTaskApplication.class, args);
	}

	@GetMapping("/")
	public String status(){
		return "Olá Mundo!";
	}

}
