package com.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class EnrollmentDto {
	private long id;
    private long studentId;
    private String studentName;
    private long courseId;
    private String courseName;
    private String courseImageUrl; // Added this for the UI
    private LocalDateTime enrollmentDate;
    private int progressPercentage;
}
