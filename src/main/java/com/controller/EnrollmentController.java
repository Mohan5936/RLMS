package com.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dto.EnrollmentDto;
import com.service.EnrollmentService;
import com.serviceIMPL.EnrollServiceImpl; // FIXED: Imported the interface

@RestController
@RequestMapping("/api/enroll")
public class EnrollmentController {

    private final EnrollmentService enrollmentService; // FIXED: Injecting the interface

    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    // Enrolling a student in a course
    @PostMapping("/course/{courseId}/student/{studentId}")
    // FIXED: Changed 'long' to 'Long'
    public ResponseEntity<EnrollmentDto> enroll(@PathVariable Long courseId, @PathVariable Long studentId) {
        return new ResponseEntity<>(enrollmentService.enrollStudent(studentId, courseId), HttpStatus.CREATED);
    }

    // Get student dashboard (courses they are taking)
    @GetMapping("/student/{studentId}")
    // FIXED: Changed 'long' to 'Long'
    public ResponseEntity<List<EnrollmentDto>> getMyCourses(@PathVariable Long studentId) {
        return ResponseEntity.ok(enrollmentService.getStudentEnrollments(studentId));
    }

    // ADDED: Get instructor dashboard (students enrolled in a specific course)
    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<EnrollmentDto>> getCourseEnrollments(@PathVariable Long courseId) {
        return ResponseEntity.ok(enrollmentService.getCourseEnrollments(courseId));
    }

    // Endpoint to update student progress manually (Good for admins/testing)
    // PUT http://localhost:8080/api/enroll/course/1/student/1/progress/50
    @PutMapping("/course/{courseId}/student/{studentId}/progress/{percentage}")
    public ResponseEntity<EnrollmentDto> updateProgress(
            @PathVariable Long courseId, 
            @PathVariable Long studentId, 
            @PathVariable int percentage) { // 'int' is fine here for percentage!
        return ResponseEntity.ok(enrollmentService.updateProgress(studentId, courseId, percentage));
    }
}