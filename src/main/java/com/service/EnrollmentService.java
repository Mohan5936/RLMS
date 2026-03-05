package com.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Entity.Course;
import com.Entity.Enrollment;
import com.Entity.User;
import com.dto.EnrollmentDto;
import com.repository.EnrollmentRepository;

import serviceIMPL.EnrollServiceImpl;

@Service
public class EnrollmentService implements EnrollServiceImpl {

	@Autowired
	private EnrollmentRepository repository;
	
	@Override
	public Enrollment enrollUser(EnrollmentDto dto) {
		Enrollment enrollment = new Enrollment();

        // Industry Mapping: Using Proxy Objects for IDs
        User user = new User();
        user.setId(dto.getUserId());
        enrollment.setUser(user);

        Course course = new Course();
        course.setId(dto.getCourseId());
        enrollment.setCourse(course);

        // Date is handled by your entity default value, 
        // but you can also set it explicitly here:
        enrollment.setEnrollmentDate(LocalDateTime.now());

        return repository.save(enrollment);
	}

	@Override
	public List<Enrollment> getEnrollmentsByUserId(Long userId) {
        return repository.findByUserId(userId);
    }

	@Override
	public List<Enrollment> getEnrollmentsByCourseId(Long courseId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
