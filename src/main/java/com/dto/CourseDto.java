package com.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class CourseDto {

	private long id;
    private String courseName;
    private String description;
    private float price;
    private String imageUrl;
    
    // For input: we need to know WHO is creating it
    private long instructorId; 
    
    // For output: we show the name for convenience
    private String instructorName;
}
