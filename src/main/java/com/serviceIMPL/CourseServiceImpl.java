package com.serviceIMPL;

import java.util.List;

import com.dto.CourseDto;

public interface CourseServiceImpl {

    CourseDto createCourse(CourseDto courseDto);
    
    List<CourseDto> getAllCourses();
    
    // Changed 'long' to the wrapper class 'Long' to match the service class
    CourseDto getCourseById(Long id); 
}