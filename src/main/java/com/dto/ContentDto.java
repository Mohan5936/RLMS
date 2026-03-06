package com.dto;

import org.hibernate.validator.constraints.URL;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ContentDto {
	
	private long id;
	
	@NotBlank(message = "Module title is required")
	private String moduleTitle;
	@NotBlank(message = "Module description is required")
    private String moduleDescription;
    
    @JsonProperty("isAccessFree")
    private boolean isAccessFree;
    
    @NotBlank(message = "Module link is required")
    @URL(message = "Please provide a valid URL")
    private String link;
    
    @NotBlank(message = "Module title is required")
    private Long courseId;
    
    public ContentDto() {}

	public void setId(long id) {
		// TODO Auto-generated method stub
		this.id=id;
	}
    
}
