package com.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Entity.Content;
import com.Entity.Progress;
import com.Entity.User;
import com.repository.ProgressRepository;

@Service
public class ProgressService {

	@Autowired
    private ProgressRepository progressRepository;

    public Progress markAsComplete(Long userId, Long lessonId) {
        // Create new Progress object
        Progress progress = new Progress();

        // Link User
        User user = new User();
        user.setId(userId);
        progress.setUser(user);

        // Link Lesson
        Content lesson = new Content();
        lesson.setId(lessonId);
        progress.setLesson(lesson);

        // Set status
        progress.setCompleted(true);
        progress.setCompletionDate(LocalDateTime.now());

        return progressRepository.save(progress);
    }
}
