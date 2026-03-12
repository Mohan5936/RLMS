package com.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dto.ContentDto;
import com.serviceIMPL.contentServiceImpl; // FIXED: Imported the interface

import jakarta.validation.Valid; // FIXED: Imported the Valid annotation

@RestController
@RequestMapping("/api/content")
public class ContentController {

    private final contentServiceImpl contentService; // FIXED: Injecting the interface

    public ContentController(contentServiceImpl contentService) {
        this.contentService = contentService;
    }

    // Endpoint to upload/add a new module to a course
    @PostMapping
    // FIXED: Added @Valid to trigger your DTO constraints
    public ResponseEntity<ContentDto> addContent(@Valid @RequestBody ContentDto contentDto) {
        ContentDto savedContent = contentService.addContentToCourse(contentDto);
        return new ResponseEntity<>(savedContent, HttpStatus.CREATED);
    }

    // Endpoint to get all videos/modules for a specific course
    @GetMapping("/course/{courseId}")
    // FIXED: Changed 'long' to 'Long'
    public ResponseEntity<List<ContentDto>> getContentByCourse(@PathVariable Long courseId) {
        return ResponseEntity.ok(contentService.getContentByCourse(courseId));
    }

    // Endpoint to get details of a specific module
    @GetMapping("/{id}")
    // FIXED: Changed 'long' to 'Long'
    public ResponseEntity<ContentDto> getContentById(@PathVariable Long id) {
        return ResponseEntity.ok(contentService.getContentById(id));
    }
}