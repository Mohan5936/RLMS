package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Entity.Enrollment;
import com.service.EnrollmentService;

@RestController
@RequestMapping("/api/enroll")
public class EnrollmentController {

	@Autowired
    private EnrollmentService enrollmentService;

    // POST http://localhost:8080/api/enrollment/join?userId=1&courseId=10
    @PostMapping("/join")
    public Enrollment joinCourse(@RequestParam Long userId, @RequestParam Long courseId) {
        return enrollmentService.enrollStudent(userId, courseId);
    }
}
