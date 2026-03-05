package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Entity.User;
import com.service.userService;

@RestController
@RequestMapping("/user")
public class userController {

	@Autowired
	public userService service;
	
	@PostMapping("/add")
	public User addUser(@RequestBody User user) {
		return service.add(user);
	}
	
	@GetMapping("/getall")
	public List<User> getAllUser(){
		return service.getAll();
	}
	
	@DeleteMapping("/deleteAll")
	public void del() {
		service.delet();
	}
	
//	@GetMapping("/getbyname/{name}")
//	public User getByName(String name) {
//		return service.getByName(name);
//	}
//	
//	@GetMapping("/getbynumber/{number}")
//	public User getByNumber(long number) {
//		return service.getByNumber(number);
//	}
}
