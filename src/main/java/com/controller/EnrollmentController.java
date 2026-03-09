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

@RestController
@RequestMapping("/api/enroll")
public class EnrollmentController {

	private final EnrollmentService enrollmentService;

    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    // CHANGE 1: More descriptive URL for Enrolling
    @PostMapping("/course/{courseId}/student/{studentId}")
    public ResponseEntity<EnrollmentDto> enroll(@PathVariable long courseId, @PathVariable long studentId) {
        return new ResponseEntity<>(enrollmentService.enrollStudent(studentId, courseId), HttpStatus.CREATED);
    }

    // KEEP: Get student dashboard
    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<EnrollmentDto>> getMyCourses(@PathVariable long studentId) {
        return ResponseEntity.ok(enrollmentService.getStudentEnrollments(studentId));
    }

    // CHANGE 2: NEW Endpoint to update student progress
    // PUT http://localhost:8080/api/enrollments/course/1/student/1/progress/50
    @PutMapping("/course/{courseId}/student/{studentId}/progress/{percentage}")
    public ResponseEntity<EnrollmentDto> updateProgress(
            @PathVariable long courseId, 
            @PathVariable long studentId, 
            @PathVariable int percentage) {
        return ResponseEntity.ok(enrollmentService.updateProgress(studentId, courseId, percentage));
    }
    
    
}
