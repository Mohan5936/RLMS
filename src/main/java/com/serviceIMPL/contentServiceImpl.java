package com.serviceIMPL;

import java.util.List;

import com.dto.ContentDto;

public interface contentServiceImpl {

    // Create new module/video
    ContentDto addContentToCourse(ContentDto contentDto);
    
    // Get all modules for a specific course (Changed 'long' to 'Long')
    List<ContentDto> getContentByCourse(Long courseId);
    
    // Get a single module detail (Changed 'long' to 'Long')
    ContentDto getContentById(Long id);
}