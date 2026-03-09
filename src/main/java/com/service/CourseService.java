package com.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.Entity.Course;
import com.Entity.User;
import com.dto.CourseDto;
import com.repository.CourseRepository;
import com.repository.userRepository;
import com.serviceIMPL.CourseServiceImpl;

@Service
public class CourseService implements CourseServiceImpl{

	private final CourseRepository courseRepository;
    private final userRepository userRepository;

    public CourseService(CourseRepository courseRepository, userRepository userRepository) {
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
    }

    @Override
    public CourseDto createCourse(CourseDto courseDto) {
        // 1. Find the User who is the instructor
        User instructor = userRepository.findById(courseDto.getInstructorId())
                .orElseThrow(() -> new RuntimeException("Instructor not found with ID: " + courseDto.getInstructorId()));

        // 2. Map DTO to Entity
        Course course = new Course();
        course.setCourseName(courseDto.getCourseName());
        course.setDescription(courseDto.getDescription());
        course.setPrice(courseDto.getPrice());
        course.setImageUrl(courseDto.getImageUrl());
        course.setInstructor(instructor); // Setting the relationship!

        // 3. Save to DB
        Course savedCourse = courseRepository.save(course);

        // 4. Return DTO
        return mapToDto(savedCourse);
    }

    @Override
    public List<CourseDto> getAllCourses() {
        return courseRepository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public CourseDto getCourseById(long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));
        return mapToDto(course);
    }

    private CourseDto mapToDto(Course course) {
        CourseDto dto = new CourseDto();
        dto.setId(course.getId());
        dto.setCourseName(course.getCourseName());
        dto.setDescription(course.getDescription());
        dto.setPrice(course.getPrice());
        dto.setImageUrl(course.getImageUrl());
        dto.setInstructorId(course.getInstructor().getId());
        // Getting the name from the User object
        dto.setInstructorName(course.getInstructor().getFirstname() + " " + course.getInstructor().getLastname());
        return dto;
    }
}
