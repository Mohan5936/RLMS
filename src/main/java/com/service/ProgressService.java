package com.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; // Swapped to Spring's Transactional

import com.Entity.Content;
import com.Entity.Progress;
import com.Entity.User;
import com.Exception.ResourceNotFoundException; // Upgraded Exception
import com.dto.ProgressDto;
import com.repository.ProgressRepository;
import com.repository.contentRepository;
import com.repository.userRepository;
import com.serviceIMPL.EnrollServiceImpl; // Injecting the interface!
import com.serviceIMPL.ProgressServiceImpl;

@Service
public class ProgressService implements ProgressServiceImpl {

    // ADDED 'final' TO ALL OF THESE
    private final ProgressRepository progressRepository;
    private final userRepository userRepository;
    private final contentRepository contentRepository;
    private final EnrollServiceImpl enrollmentService; 

    // Constructor Injection
    public ProgressService(ProgressRepository progressRepository, 
                               userRepository userRepository, 
                               contentRepository contentRepository,
                               EnrollServiceImpl enrollmentService) {
        this.progressRepository = progressRepository;
        this.userRepository = userRepository;
        this.contentRepository = contentRepository;
        this.enrollmentService = enrollmentService;
    }

    @Override
    @Transactional
    public ProgressDto updateProgress(ProgressDto progressDto) {
        // 1. Fetch the User and the Content (Lesson) using custom 404 Exceptions
        User user = userRepository.findById(progressDto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + progressDto.getUserId()));

        Content lesson = contentRepository.findById(progressDto.getLessonId())
                .orElseThrow(() -> new ResourceNotFoundException("Lesson not found with id: " + progressDto.getLessonId()));

        // 2. Find existing progress or create a new one
        Optional<Progress> existingProgress = progressRepository.findByUserIdAndLessonId(user.getId(), lesson.getId());
        Progress progress = existingProgress.orElse(new Progress());
        
        progress.setUser(user);
        progress.setLesson(lesson);
        progress.setCompleted(progressDto.isCompleted());
        
        // 3. Auto-set or clear completion date
        if (progressDto.isCompleted() && progress.getCompletionDate() == null) {
            progress.setCompletionDate(LocalDateTime.now());
        } else if (!progressDto.isCompleted()) {
            progress.setCompletionDate(null);
        }

        // 4. Save the lesson progress
        Progress savedProgress = progressRepository.save(progress);

        // ---------------------------------------------------------
        // 5. THE MAGIC HAPPENS HERE: Trigger the math calculation!
        Long courseId = lesson.getCourse().getId(); 
        enrollmentService.recalculateCourseProgress(user.getId(), courseId);
        // ---------------------------------------------------------

        // 6. Map back to DTO and return
        progressDto.setId(savedProgress.getId());
        progressDto.setCompletionDate(savedProgress.getCompletionDate());
        return progressDto;
    }

    @Override
    public List<ProgressDto> getUserProgress(Long userId) {
        return progressRepository.findByUserId(userId).stream().map(progress -> {
            ProgressDto dto = new ProgressDto();
            dto.setId(progress.getId());
            dto.setUserId(progress.getUser().getId());
            dto.setLessonId(progress.getLesson().getId()); 
            dto.setCompleted(progress.isCompleted());
            dto.setCompletionDate(progress.getCompletionDate());
            return dto;
        }).collect(Collectors.toList());
    }
}