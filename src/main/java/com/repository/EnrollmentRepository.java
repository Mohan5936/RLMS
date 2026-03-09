package com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Entity.Enrollment;


@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

	
	// Find all courses for a specific student
    List<Enrollment> findByStudentId(long studentId);
    
    // Check if a student is already enrolled (so they don't join twice!)
    boolean existsByStudentIdAndCourseId(long studentId, long courseId);
}
