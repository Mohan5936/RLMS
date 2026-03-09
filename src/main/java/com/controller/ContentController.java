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
import com.service.ContentService;

@RestController
@RequestMapping("/api/content")
public class ContentController {

	private final ContentService contentService;

    public ContentController(ContentService contentService) {
        this.contentService = contentService;
    }

    // Endpoint to upload/add a new module to a course
    @PostMapping
    public ResponseEntity<ContentDto> addContent(@RequestBody ContentDto contentDto) {
        ContentDto savedContent = contentService.addContentToCourse(contentDto);
        return new ResponseEntity<>(savedContent, HttpStatus.CREATED);
    }

    // Endpoint to get all videos/modules for a specific course
    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<ContentDto>> getContentByCourse(@PathVariable long courseId) {
        return ResponseEntity.ok(contentService.getContentByCourse(courseId));
    }

    // Endpoint to get details of a specific module
    @GetMapping("/{id}")
    public ResponseEntity<ContentDto> getContentById(@PathVariable long id) {
        return ResponseEntity.ok(contentService.getContentById(id));
    }
}
