package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Entity.Progress;
import com.dto.ProgressDto;
import com.service.ProgressService;

@RestController
@RequestMapping("/api/progress")
public class ProgressController {

	@Autowired
    private ProgressService progressService;

    // Call this as: POST http://localhost:8080/api/progress/mark?userId=1&lessonId=5
    @PostMapping("/mark")
    public ResponseEntity<Progress> markComplete(@RequestBody ProgressDto dto) {
        Progress result = progressService.markAsComplete(dto);
        return ResponseEntity.ok(result);
    }
}
