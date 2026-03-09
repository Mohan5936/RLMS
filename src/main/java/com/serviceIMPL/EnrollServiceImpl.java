package com.serviceIMPL;

import java.util.List;

import com.Entity.Enrollment;
import com.dto.EnrollmentDto;

public interface EnrollServiceImpl {
	
	// Enroll a student in a specific course
    EnrollmentDto enrollStudent(long studentId, long courseId);
    
    // Get all enrollments for a specific student (for their dashboard)
    List<EnrollmentDto> getStudentEnrollments(long studentId);
    
    // Get all students for a specific course (for the instructor's view)
    List<EnrollmentDto> getCourseEnrollments(long courseId);
    
    // Update progress (e.g., student finished a module)
    EnrollmentDto updateProgress(long studentId, long courseId, int newProgress);
}