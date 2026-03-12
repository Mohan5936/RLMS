package com.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class CourseDto {

    private Long id; // Changed to wrapper class
    
    @NotBlank(message = "Course name cannot be empty")
    private String courseName;
    
    @NotBlank(message = "Course description is required")
    private String description;
    
    @PositiveOrZero(message = "Price cannot be negative")
    private float price;
    
    private String imageUrl;
    
    // For input: we need to know WHO is creating it
    private Long instructorId; // Changed to wrapper class
    
    // For output: we show the name for convenience
    private String instructorName;
}