package com.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.Entity.Content;
import com.Entity.Course;
import com.dto.ContentDto;
import com.repository.CourseRepository;
import com.repository.contentRepository;
import com.serviceIMPL.contentServiceImpl;

@Service
public class ContentService implements contentServiceImpl {

	private final contentRepository contentRepository;
    private final CourseRepository courseRepository;

    public ContentService(contentRepository contentRepository, CourseRepository courseRepository) {
        this.contentRepository = contentRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public ContentDto addContentToCourse(ContentDto dto) {
        // 1. Verify the course exists before attaching content
        Course course = courseRepository.findById(dto.getCourseId())
                .orElseThrow(() -> new RuntimeException("Course not found with ID: " + dto.getCourseId()));

        // 2. Map DTO to Entity
        Content content = new Content();
        content.setModuleTitle(dto.getModuleTitle());
        content.setModuleDescription(dto.getModuleDescription());
        content.setAccessFree(dto.isAccessFree());
        content.setLink(dto.getLink());
        content.setCourse(course); // The Foreign Key link

        // 3. Save to PostgreSQL
        Content savedContent = contentRepository.save(content);

        // 4. Return the result back as a DTO
        return mapToDto(savedContent);
    }

    @Override
    public List<ContentDto> getContentByCourse(long courseId) {
        return contentRepository.findByCourseId(courseId)
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ContentDto getContentById(long id) {
        Content content = contentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Content not found with ID: " + id));
        return mapToDto(content);
    }

    // Helper Method: Entity -> DTO
    private ContentDto mapToDto(Content content) {
        ContentDto dto = new ContentDto();
        dto.setId(content.getId());
        dto.setModuleTitle(content.getModuleTitle());
        dto.setModuleDescription(content.getModuleDescription());
        dto.setAccessFree(content.isAccessFree());
        dto.setLink(content.getLink());
        dto.setCourseId(content.getCourse().getId());
        return dto;
    }	
}
