package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Entity.Content;
import com.dto.ContentDto;
import com.service.ContentService;

@RestController
@RequestMapping("/content")
public class ContentController {

	@Autowired
	private ContentService serv;
	
	@PostMapping("/addContent")
	public Content add(@RequestBody ContentDto content) {
		return serv.saveContent(content);
	}
	
	@GetMapping("/getContent/{id})")
	public Content getById(@PathVariable long id) {
		return serv.getById(id);
	}
	
	@GetMapping("/getAllContent")
	public List<Content> getAll(){
		return serv.getAllcontent();
	}
}
