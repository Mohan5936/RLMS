package com.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Entity.Content;
import com.Entity.Progress;
import com.Entity.User;
import com.Exception.ResourceNotFoundException;
import com.dto.ProgressDto;
import com.repository.ProgressRepository;
import com.repository.contentRepository;
import com.repository.userRepository;
import com.serviceIMPL.ProgressServiceImpl;

@Service
public class ProgressService implements ProgressServiceImpl {

	@Autowired
    private ProgressRepository progressRepository;

    @Autowired
    private userRepository userRepository;

    @Autowired
    private contentRepository contentRepository;

    @Override
    public ProgressDto updateProgress(ProgressDto progressDto) {
        User user = userRepository.findById(progressDto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + progressDto.getUserId()));

        Content lesson = contentRepository.findById(progressDto.getLessonId())
                .orElseThrow(() -> new ResourceNotFoundException("Lesson not found with id: " + progressDto.getLessonId()));

        Optional<Progress> existingProgress = progressRepository.findByUserIdAndLessonId(user.getId(), lesson.getId());
        
        Progress progress = existingProgress.orElse(new Progress());
        progress.setUser(user);
        progress.setLesson(lesson); // Fixed method call
        progress.setCompleted(progressDto.isCompleted());
        
        // Auto-set completion date if marked as completed
        if (progressDto.isCompleted() && progress.getCompletionDate() == null) {
            progress.setCompletionDate(LocalDateTime.now());
        } else if (!progressDto.isCompleted()) {
            progress.setCompletionDate(null);
        }

        Progress savedProgress = progressRepository.save(progress);

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
            dto.setLessonId(progress.getLesson().getId()); // Fixed method call
            dto.setCompleted(progress.isCompleted());
            dto.setCompletionDate(progress.getCompletionDate());
            return dto;
        }).collect(Collectors.toList());
    }
}
