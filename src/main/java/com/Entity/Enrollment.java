package com.Entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import lombok.Data;

@Entity
@Data
public class Enrollment {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    // The Student who is enrolling
    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private User student;

    // The Course they are joining
    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    private LocalDateTime enrollmentDate;

    // Track progress (0 to 100)
    private int progressPercentage = 0;

    @PrePersist
    protected void onCreate() {
        this.enrollmentDate = LocalDateTime.now();
    }
	
}
