package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Entity.User;

@Repository
public interface userRepository extends JpaRepository<User, Long> {

	public User findByFirstName(String name);
	
	public User findByUserNumber(long number);
	
}
