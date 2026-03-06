package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Entity.Content;
import com.Entity.Course;
import com.dto.ContentDto;
import com.repository.CourseRepository;
import com.repository.contentRepository;

import serviceIMPL.contentServiceImpl;

@Service
public class ContentService implements contentServiceImpl {

	@Autowired
	private contentRepository repos;
	@Autowired
	private CourseRepository crepo;
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
		 System.out.println(dto.getModuleTitle()+"  "+dto.getModuleDescription()+"  "+dto.getLink()+"  "+dto.getCourseId()+"  hiwno "+dto.isAccessFree());
	    // 1. Map simple fields
//		content.setCourse(repos.findById(dto.getCourseId()).orElseThrow());
	    content.setModuleTitle(dto.getModuleTitle());
	    content.setModuleDescription(dto.getModuleDescription());
	    content.setAccessFree(dto.isAccessFree());
	    content.setLink(dto.getLink());
	    Course course=crepo.findById(dto.getCourseId()).orElseThrow();
	    // 2. Map the Relationship (The Industry Way)
	    // We create a "Proxy" course object with just the ID
	    content.setCourse(course);
	    System.out.println(dto.getModuleTitle()+"  "+dto.getModuleDescription()+"  "+dto.getLink()+"  "+dto.getCourseId()+"  "+dto.isAccessFree());

	    return repos.save(content);
	}



	
	
}
