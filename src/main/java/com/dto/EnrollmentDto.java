package com.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class EnrollmentDto {
    private Long id; // Matched wrapper class
    
    private Long studentId;
    private String studentName;
    
    private Long courseId;
    private String courseName;
    private String courseImageUrl; 
    
    private LocalDateTime enrollmentDate;
    private int progressPercentage;
    
    // ADDED: Status so the frontend can display "Completed" badges
    private String status; 
}