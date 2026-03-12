package com.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter; // Swapped @Data for these

@Entity
@Table(name = "content")
@Getter
@Setter
public class Content {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Changed to wrapper Long
    
    private String moduleTitle;
    private String moduleDescription;
    
    // Removed @JsonProperty from the Entity layer
    private boolean isAccessFree;
    
    private String link; // This will store your video or file URL
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="course_id", nullable = false)
    private Course course;
}