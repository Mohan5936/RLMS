package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Entity.Content;
import com.Entity.Course;
import com.dto.ContentDto;
import com.repository.contentRepository;

import serviceIMPL.contentServiceImpl;

@Service
public class ContentService implements contentServiceImpl {

	@Autowired
	private contentRepository repos;
	
//	public Content addContent(Content content) {
//		return repos.save(content);
//	}
	
	public Content getById(long id) {
		return repos.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
	}

	
	public List<Content> getAllcontent(){
		return repos.findAll();
	}

	@Override
	public Content saveContent(ContentDto dto) {
		Content content = new Content();
	    
	    // 1. Map simple fields
	    content.setModuleTitle(dto.getModuleTitle());
	    content.setModuleDescription(dto.getModuleDescription());
	    content.setAccessFree(dto.isAccessFree());
	    content.setLink(dto.getLink());

	    // 2. Map the Relationship (The Industry Way)
	    // We create a "Proxy" course object with just the ID
	    Course course = new Course();
	    course.setId(dto.getCourseId());
	    content.setCourse(course);

	    return repos.save(content);
	}
}
