package com.dto;

import org.hibernate.validator.constraints.URL;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ContentDto {
    
    private Long id; // Changed to wrapper Long
    
    @NotBlank(message = "Module title is required")
    private String moduleTitle;
    
    @NotBlank(message = "Module description is required")
    private String moduleDescription;
    
    @JsonProperty("isAccessFree")
    private boolean isAccessFree;
    
    @NotBlank(message = "Content link is required")
    @URL(message = "Please provide a valid URL") // Added the URL validation!
    private String link;
    
    private Long courseId; // Changed to wrapper Long
}