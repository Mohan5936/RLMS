package com.serviceIMPL;

import java.util.List;

import com.Entity.Content;
import com.dto.ContentDto;

public interface contentServiceImpl {

	// Create new module/video
    ContentDto addContentToCourse(ContentDto contentDto);
    
    // Get all modules for a specific course
    List<ContentDto> getContentByCourse(long courseId);
    
    // Get a single module detail
    ContentDto getContentById(long id);

}
