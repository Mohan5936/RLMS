package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Entity.Course;
import com.dto.CourseDto;
import com.service.CourseService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/course")
public class CourseController {

	@Autowired
	private CourseService serv;
	
	@PostMapping("/addCourse")
	public ResponseEntity<CourseDto> createCourse(@Valid @RequestBody CourseDto courseDto) {
        CourseDto savedCourse = serv.saveCourse(courseDto);
        return new ResponseEntity<>(savedCourse, HttpStatus.CREATED);
    }
	
	@GetMapping("/{id}")
    public ResponseEntity<CourseDto> getCourseById(@PathVariable long id) {
        CourseDto courseDto = serv.getCourseById(id);
        return ResponseEntity.ok(courseDto);
    }

}
