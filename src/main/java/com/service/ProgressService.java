package com.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Entity.Content;
import com.Entity.Progress;
import com.Entity.User;
import com.dto.ProgressDto;
import com.repository.ProgressRepository;

import serviceIMPL.ProgressServiceImpl;

@Service
public class ProgressService implements ProgressServiceImpl {

	@Autowired
    private ProgressRepository progressRepository;

	@Override
	public Progress markAsComplete(ProgressDto dto) {
		// 1. Check if progress already exists to prevent duplicates
        return progressRepository.findByUserIdAndLessonId(dto.getUserId(), dto.getLessonId())
            .orElseGet(() -> {
                // 2. If not found, create new Progress record
                Progress progress = new Progress();
                
                // Set User Proxy
                User user = new User();
                user.setId(dto.getUserId());
                progress.setUser(user);

                // Set Lesson Proxy (Using your Content entity)
                Content lesson = new Content();
                lesson.setId(dto.getLessonId());
                progress.setLesson(lesson);

                progress.setCompleted(true);
                progress.setCompletionDate(LocalDateTime.now());
                
                return progressRepository.save(progress);
            });
	}
}
