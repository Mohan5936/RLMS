package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Entity.User;
import com.repository.userRepository;

@Service
public class userService {

	@Autowired
	public userRepository repository;
	
	public User add(User user) {
		return repository.save(user);
	}
	
	public List<User> getAll(){
		return repository.findAll();
	}

	public void delet() {
		// TODO Auto-generated method stub
		repository.deleteAll();
	}
	
//	public User getByName(String name) {
//		return repository.findByfirst_name(name);
//	}
//	
//	public User getByNumber(long number) {
//		return repository.findByUser_number(number);
//	}
}
