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
	public CourseDto saveCourse(CourseDto course) {
		// TODO Auto-generated method stub
		Course cou=new Course();
		cou.setCourseName(course.getCourseName());
		cou.setDescription(course.getDescription());
		cou.setId(course.getId());
		cou.setInstructorName(course.getInstructorName());
		cou.setPrice(course.getPrice());
		Course scourse=repos.save(cou);
		course.setId(scourse.getId());
		return course;
	}

	@Override
    public CourseDto getCourseById(long id) {
        Course course = repos.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found")); // We will handle this in Step 2

        CourseDto dto = new CourseDto();
        dto.setId(course.getId());
        dto.setCourseName(course.getCourseName());
        dto.setDescription(course.getDescription());
        dto.setInstructorName(course.getInstructorName());
        dto.setPrice(course.getPrice());
        return dto;
    }
}
