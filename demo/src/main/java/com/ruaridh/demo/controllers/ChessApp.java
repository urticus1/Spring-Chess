package com.ruaridh.demo.controllers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages={"com.ruaridh.demo"})
public class ChessApp {

	public static void main(String[] args) {
		SpringApplication.run(ChessApp.class, args);

	}

}
