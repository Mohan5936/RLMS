package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Entity.Course;
import com.dto.CourseDto;
import com.service.CourseService;

@RestController
@RequestMapping("/course")
public class CourseController {

	@Autowired
	private CourseService serv;
	
	@PostMapping("/addCourse")
	public Course add(@RequestBody CourseDto course) {
		return serv.saveCourse(course);
	}
	
	@GetMapping("/getCourse/{id}")
	public Course get(@PathVariable Long id) {
		return serv.getById(id);
	}
	
	
	@GetMapping("/getAllCourses")
	public List<Course> getAllC(){
		return serv.getAllCourses();
	}
}
