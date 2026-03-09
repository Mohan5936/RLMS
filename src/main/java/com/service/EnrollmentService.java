package com.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.Entity.Course;
import com.Entity.Enrollment;
import com.Entity.User;
import com.dto.EnrollmentDto;
import com.repository.CourseRepository;
import com.repository.EnrollmentRepository;
import com.repository.userRepository;
import com.serviceIMPL.EnrollServiceImpl;

@Service
public class EnrollmentService implements EnrollServiceImpl {
	
	private final EnrollmentRepository enrollmentRepo;
    private final userRepository userRepo;
    private final CourseRepository courseRepo;

    // Constructor Injection
    public EnrollmentService(EnrollmentRepository enrollmentRepo, 
                                 userRepository userRepo, 
                                 CourseRepository courseRepo) {
        this.enrollmentRepo = enrollmentRepo;
        this.userRepo = userRepo;
        this.courseRepo = courseRepo;
    }

    @Override
    public EnrollmentDto enrollStudent(long studentId, long courseId) {
        // 1. Check if the relationship already exists
        if (enrollmentRepo.existsByStudentIdAndCourseId(studentId, courseId)) {
            throw new RuntimeException("Error: You are already enrolled in this course.");
        }

        // 2. Fetch the Student (User) and the Course
        User student = userRepo.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found with ID: " + studentId));
        Course course = courseRepo.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found with ID: " + courseId));

        // 3. Create the enrollment record
        Enrollment enrollment = new Enrollment();
        enrollment.setStudent(student);
        enrollment.setCourse(course);
        // Default progress is 0, date is handled by @PrePersist in Entity
        
        Enrollment savedEnrollment = enrollmentRepo.save(enrollment);
        
        return mapToDto(savedEnrollment);
    }

    @Override
    public List<EnrollmentDto> getStudentEnrollments(long studentId) {
        return enrollmentRepo.findByStudentId(studentId)
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<EnrollmentDto> getCourseEnrollments(long courseId) {
        // You would add findByCourseId to your repository for this
        return enrollmentRepo.findAll().stream()
                .filter(e -> e.getCourse().getId() == courseId)
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public EnrollmentDto updateProgress(long studentId, long courseId, int newProgress) {
        // A simple way to update progress via the service
        List<Enrollment> enrollments = enrollmentRepo.findByStudentId(studentId);
        Enrollment enrollment = enrollments.stream()
                .filter(e -> e.getCourse().getId() == courseId)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Enrollment record not found"));

        enrollment.setProgressPercentage(newProgress);
        return mapToDto(enrollmentRepo.save(enrollment));
    }

    // --- The Core Mapping Logic ---
    private EnrollmentDto mapToDto(Enrollment enrollment) {
        EnrollmentDto dto = new EnrollmentDto();
        dto.setId(enrollment.getId());
        dto.setEnrollmentDate(enrollment.getEnrollmentDate());
        dto.setProgressPercentage(enrollment.getProgressPercentage());

        // Map Student Details
        dto.setStudentId(enrollment.getStudent().getId());
        dto.setStudentName(enrollment.getStudent().getFirstname() + " " + enrollment.getStudent().getLastname());

        // Map Course Details (Including the Thumbnail URL)
        dto.setCourseId(enrollment.getCourse().getId());
        dto.setCourseName(enrollment.getCourse().getCourseName());
        dto.setCourseImageUrl(enrollment.getCourse().getImageUrl()); 

        return dto;
    }
}
