package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Entity.Progress;
import com.service.ProgressService;

@RestController
@RequestMapping("/api/progress")
public class ProgressController {

	@Autowired
    private ProgressService progressService;

    // Call this as: POST http://localhost:8080/api/progress/mark?userId=1&lessonId=5
    @PostMapping("/mark")
    public Progress markLessonComplete(@RequestParam Long userId, @RequestParam Long lessonId) {
        return progressService.markAsComplete(userId, lessonId);
    }
}
