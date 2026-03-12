package com.service; 

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Entity.Course;
import com.Entity.Enrollment;
import com.Entity.User;
import com.Exception.ResourceNotFoundException;
import com.dto.EnrollmentDto;
import com.repository.CourseRepository;
import com.repository.EnrollmentRepository;
import com.repository.ProgressRepository;
import com.repository.contentRepository;
import com.repository.userRepository;
import com.serviceIMPL.EnrollServiceImpl; 

@Service
public class EnrollmentService implements EnrollServiceImpl {
    
    private final EnrollmentRepository enrollmentRepo;
    private final userRepository userRepo;
    private final CourseRepository courseRepo;
    private final contentRepository contentRepo;   
    private final ProgressRepository progressRepo; 
    private final EmailService emailService; // 1. Inject the EmailService

    public EnrollmentService(EnrollmentRepository enrollmentRepo, 
                                 userRepository userRepo, 
                                 CourseRepository courseRepo,
                                 contentRepository contentRepo,
                                 ProgressRepository progressRepo,
                                 EmailService emailService) { // 2. Add to constructor
        this.enrollmentRepo = enrollmentRepo;
        this.userRepo = userRepo;
        this.courseRepo = courseRepo;
        this.contentRepo = contentRepo;
        this.progressRepo = progressRepo;
        this.emailService = emailService;
    }

    @Override
    @Transactional
    public EnrollmentDto enrollStudent(Long studentId, Long courseId) {
        if (enrollmentRepo.existsByStudentIdAndCourseId(studentId, courseId)) {
            throw new IllegalStateException("Error: You are already enrolled in this course.");
        }

        User student = userRepo.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with ID: " + studentId));
        Course course = courseRepo.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with ID: " + courseId));

        Enrollment enrollment = new Enrollment();
        enrollment.setStudent(student);
        enrollment.setCourse(course);
        enrollment.setStatus("IN_PROGRESS"); // Default status
        
        Enrollment savedEnrollment = enrollmentRepo.save(enrollment);

        // 3. Trigger Welcome Email for the Course
        emailService.sendEmail(
            student.getEmail(),
            "Welcome to " + course.getCourseName(),
            "Hi " + student.getFirstname() + ",\n\nYou have successfully enrolled in " + course.getCourseName() + ". Happy learning!"
        );
        
        return mapToDto(savedEnrollment);
    }

    // ... getStudentEnrollments and getCourseEnrollments remain the same ...

    @Override
    @Transactional
    public void recalculateCourseProgress(Long studentId, Long courseId) {
        long totalLessons = contentRepo.countByCourseId(courseId);
        if (totalLessons == 0) return; 

        long completedLessons = progressRepo.countByUserIdAndLesson_Course_IdAndIsCompletedTrue(studentId, courseId);
        int progressPercentage = (int) Math.round(((double) completedLessons / totalLessons) * 100);

        Enrollment enrollment = enrollmentRepo.findByStudentIdAndCourseId(studentId, courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Enrollment record not found"));

        // Check if the course was just completed in this call
        boolean alreadyCompleted = "COMPLETED".equals(enrollment.getStatus());

        enrollment.setProgressPercentage(progressPercentage);

        if (progressPercentage >= 100) {
            enrollment.setStatus("COMPLETED");
            
            // 4. Trigger Completion Email (only if it wasn't already completed)
            if (!alreadyCompleted) {
                emailService.sendEmail(
                    enrollment.getStudent().getEmail(),
                    "Congratulations! Course Completed",
                    "Hi " + enrollment.getStudent().getFirstname() + ",\n\nYou've finished 100% of " + enrollment.getCourse().getCourseName() + "! Keep up the great work."
                
                );
                System.out.println("Email Sent Successfully "+enrollment.getStudent().getFirstname()+" with course of "+enrollment.getCourse().getCourseName()+" to email address "+enrollment.getStudent().getEmail());
            }
        } else {
            enrollment.setStatus("IN_PROGRESS");
        }

        enrollmentRepo.save(enrollment);
    }

    @Override
    public List<EnrollmentDto> getStudentEnrollments(Long studentId) {
        return enrollmentRepo.findByStudentId(studentId)
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<EnrollmentDto> getCourseEnrollments(Long courseId) {
        return enrollmentRepo.findByCourseId(courseId).stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public EnrollmentDto updateProgress(Long studentId, Long courseId, int newProgress) {
        Enrollment enrollment = enrollmentRepo.findByStudentIdAndCourseId(studentId, courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Enrollment record not found"));

        enrollment.setProgressPercentage(newProgress);
        return mapToDto(enrollmentRepo.save(enrollment));
    }

    private EnrollmentDto mapToDto(Enrollment enrollment) {
        EnrollmentDto dto = new EnrollmentDto();
        dto.setId(enrollment.getId());
        dto.setEnrollmentDate(enrollment.getEnrollmentDate());
        dto.setProgressPercentage(enrollment.getProgressPercentage());
        dto.setStatus(enrollment.getStatus());
        dto.setStudentId(enrollment.getStudent().getId());
        dto.setStudentName(enrollment.getStudent().getFirstname() + " " + enrollment.getStudent().getLastname());
        dto.setCourseId(enrollment.getCourse().getId());
        dto.setCourseName(enrollment.getCourse().getCourseName());
        dto.setCourseImageUrl(enrollment.getCourse().getImageUrl()); 
        return dto;
    }
}