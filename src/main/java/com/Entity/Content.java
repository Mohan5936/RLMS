package com.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "content")
@Data
public class Content {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    private String moduleTitle;
    private String moduleDescription;
    
    @JsonProperty("isAccessFree")
    private boolean isAccessFree;
    
    private String link; // This will store your video or file URL
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="course_id", nullable = false)
    private Course course;
	
}
