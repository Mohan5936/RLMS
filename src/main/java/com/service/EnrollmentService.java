package com.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Entity.Course;
import com.Entity.Enrollment;
import com.Entity.User;
import com.repository.EnrollmentRepository;

@Service
public class EnrollmentService {

	@Autowired
	private EnrollmentRepository repository;
	
	public Enrollment enrollStudent(Long userId, Long courseId) {
        Enrollment enrollment = new Enrollment();

        // Attach User ID
        User user = new User();
        user.setId(userId);
        enrollment.setUser(user);

        // Attach Course ID
        Course course = new Course();
        course.setId(courseId);
        enrollment.setCourse(course);

        enrollment.setEnrollmentDate(LocalDateTime.now());

        return repository.save(enrollment);
    }
	
	
}
