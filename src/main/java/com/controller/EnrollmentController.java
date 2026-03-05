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

import com.Entity.Enrollment;
import com.dto.EnrollmentDto;
import com.service.EnrollmentService;

@RestController
@RequestMapping("/api/enroll")
public class EnrollmentController {

	@Autowired
    private EnrollmentService enrollmentService;

    // POST http://localhost:8080/api/enrollment/join?userId=1&courseId=10
	@PostMapping("/join")
    public ResponseEntity<Enrollment> enroll(@RequestBody EnrollmentDto dto) {
        // We use 201 Created for new database records
        Enrollment savedEnrollment = enrollmentService.enrollUser(dto);
        return new ResponseEntity<>(savedEnrollment, HttpStatus.CREATED);
    }

    // GET: http://localhost:8080/api/enrollments/user/1
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Enrollment>> getMyCourses(@PathVariable Long userId) {
        return ResponseEntity.ok(enrollmentService.getEnrollmentsByUserId(userId));
    }
}
