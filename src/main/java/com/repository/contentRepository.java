package com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Entity.Content;

@Repository
public interface contentRepository extends JpaRepository<Content, Long> {

//	public Course getByCourseId(long courseId);
	List<Content> findByCourseId(Long courseId);
	// Counts how many lessons belong to a course
	long countByCourseId(Long courseId);
}
