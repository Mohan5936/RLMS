package com.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Entity.Course;
import com.Entity.Role; // Added to check permissions
import com.Entity.User;
import com.Exception.ResourceNotFoundException; // Upgraded exception
import com.dto.CourseDto;
import com.repository.CourseRepository;
import com.repository.userRepository;
import com.serviceIMPL.CourseServiceImpl;

@Service
public class CourseService implements CourseServiceImpl {

    private final CourseRepository courseRepository;
    private final userRepository userRepository;

    public CourseService(CourseRepository courseRepository, userRepository userRepository) {
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional // Ensures database safety
    public CourseDto createCourse(CourseDto courseDto) {
        // 1. Find the User who is the instructor using proper Exception
        User instructor = userRepository.findById(courseDto.getInstructorId())
                .orElseThrow(() -> new ResourceNotFoundException("Instructor not found with ID: " + courseDto.getInstructorId()));

        // --- NEW SECURE BUSINESS LOGIC ---
        // 2. Verify the user is actually allowed to teach
        if (instructor.getRole() != Role.INSTRUCTOR && instructor.getRole() != Role.ADMIN) {
            throw new IllegalStateException("Access Denied: Only instructors can create courses.");
        }

        // 3. Map DTO to Entity
        Course course = new Course();
        course.setCourseName(courseDto.getCourseName());
        course.setDescription(courseDto.getDescription());
        course.setPrice(courseDto.getPrice());
        course.setImageUrl(courseDto.getImageUrl());
        course.setInstructor(instructor); // Setting the relationship!

        // 4. Save to DB
        Course savedCourse = courseRepository.save(course);

        // 5. Return DTO
        return mapToDto(savedCourse);
    }

    @Override
    public List<CourseDto> getAllCourses() {
        return courseRepository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public CourseDto getCourseById(Long id) { // Changed 'long' to wrapper 'Long'
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with ID: " + id));
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