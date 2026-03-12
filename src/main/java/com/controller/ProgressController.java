package com.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dto.ProgressDto;
import com.serviceIMPL.ProgressServiceImpl; // FIXED: Imported the interface

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/progress")
public class ProgressController {

    private final ProgressServiceImpl progressService; // FIXED: Changed to interface and made final

    // FIXED: Added Constructor Injection (Best Practice)
    public ProgressController(ProgressServiceImpl progressService) {
        this.progressService = progressService;
    }

    @PostMapping("/update")
    public ResponseEntity<ProgressDto> updateProgress(@Valid @RequestBody ProgressDto progressDto) {
        ProgressDto updatedProgress = progressService.updateProgress(progressDto);
        return ResponseEntity.ok(updatedProgress);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ProgressDto>> getUserProgress(@PathVariable Long userId) {
        List<ProgressDto> progressList = progressService.getUserProgress(userId);
        return ResponseEntity.ok(progressList);
    }
}