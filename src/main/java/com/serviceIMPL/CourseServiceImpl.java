package com.serviceIMPL;

import java.util.List;

import com.dto.CourseDto;

public interface CourseServiceImpl {

	CourseDto createCourse(CourseDto courseDto);
    List<CourseDto> getAllCourses();
    CourseDto getCourseById(long id);
}
