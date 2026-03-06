package com.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class CourseDto {

	private long id;
	
	@NotBlank(message = "Course name is required")
	private String courseName;
	
	@NotBlank(message = "Course Description is required")
	private String description;
	
	@NotBlank(message = "Instructor name is required")
	private String instructorName;
	
	@NotBlank(message = "Price is required")
	@PositiveOrZero(message="Price cannot be negative")
	private float price;
}
