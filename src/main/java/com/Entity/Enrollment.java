package com.Entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter // Replaced @Data with @Getter and @Setter to prevent circular dependency bugs
@Table(name = "enrollments", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"student_id", "course_id"}) // Blocks duplicate enrollments
})
public class Enrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Changed primitive 'long' to wrapper 'Long' (best practice for IDs in JPA)

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

    // ADDED: Status to track if they finished the course
    private String status; 

    @PrePersist
    protected void onCreate() {
        this.enrollmentDate = LocalDateTime.now();
        if (this.status == null) {
            this.status = "IN_PROGRESS"; // Default status
        }
    }
}