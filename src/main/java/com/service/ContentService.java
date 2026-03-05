package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Entity.Content;
import com.repository.contentRepository;

@Service
public class ContentService {

	@Autowired
	private contentRepository repos;
	
	public Content addContent(Content content) {
		return repos.save(content);
	}
	
	public Content getById(long id) {
		return repos.findById(id).orElseThrow();
	}
	
	public List<Content> getAllcontent(){
		return repos.findAll();
	}
}
