package com.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

	@GetMapping("/")
	public String message() {
		System.out.println("Hello Erripukaaaa");
		return "Connection Established";
	}
}
