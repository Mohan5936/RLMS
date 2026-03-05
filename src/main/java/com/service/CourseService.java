package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Entity.Course;
import com.dto.CourseDto;
import com.repository.CourseRepository;

import serviceIMPL.CourseServiceImpl;

@Service
public class CourseService implements CourseServiceImpl{

	@Autowired
	private CourseRepository repos;
	
//	public Course addCourse(Course course) {
//		return repos.save(course);
//	}
	
//	public Course getOne(Long id) {
//		return repos.findById(id).orElseThrow();
//	}
	
	public List<Course> getAllCourses(){
		return repos.findAll();
	}

	@Override
	public Course saveCourse(CourseDto course) {
		// TODO Auto-generated method stub
		Course cou=new Course();
		cou.setCourseName(course.getCourseName());
		cou.setDescription(course.getDescription());
		cou.setId(course.getId());
		cou.setInstructorName(course.getInstructorName());
		cou.setPrice(course.getPrice());
		return repos.save(cou);
	}

	@Override
	public Course getById(long id) {
		// TODO Auto-generated method stub
		return repos.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
	}
}
