package com.serviceIMPL;

import java.util.List;

import com.dto.EnrollmentDto;

public interface EnrollServiceImpl {
    
    // Enroll a student in a specific course
    EnrollmentDto enrollStudent(Long studentId, Long courseId); // Changed to Long
    
    // Get all enrollments for a specific student (for their dashboard)
    List<EnrollmentDto> getStudentEnrollments(Long studentId); // Changed to Long
    
    // Get all students for a specific course (for the instructor's view)
    List<EnrollmentDto> getCourseEnrollments(Long courseId); // Changed to Long
    
    // Update progress (e.g., student finished a module)
    EnrollmentDto updateProgress(Long studentId, Long courseId, int newProgress); // Changed to Long
    
    public void recalculateCourseProgress(Long studentId, Long courseId); // Changed to Long
    
    
}