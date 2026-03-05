package com.dto;

import lombok.Data;

@Data
public class CourseDto {

	private long id;
	private String courseName;
	private String description;
	private String instructorName;
	private float price;
	
}
