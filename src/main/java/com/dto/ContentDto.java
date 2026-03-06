package com.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ContentDto {

	private String moduleTitle;
    private String moduleDescription;
    @JsonProperty("isAccessFree")
    private boolean isAccessFree;
    private String link;
    
    // We only need the ID to link it to a course
    private Long courseId;
    public ContentDto() {}
    
}
