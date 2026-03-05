package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Entity.Course;
import com.repository.CourseRepository;

@Service
public class CourseService {

	@Autowired
	private CourseRepository repos;
	
	public Course addCourse(Course course) {
		return repos.save(course);
	}
	
	public Course getOne(Long id) {
		return repos.findById(id).orElseThrow();
	}
	
	public List<Course> getAllCourses(){
		return repos.findAll();
	}
}
