package com.dto;

import org.hibernate.validator.constraints.URL;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ContentDto {
	
	private long id;
    private String moduleTitle;
    private String moduleDescription;
    
    @JsonProperty("isAccessFree")
    private boolean isAccessFree;
    
    private String link;
    private long courseId; // To link it to the specific course
    
}
