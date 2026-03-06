package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Entity.Content;
import com.Entity.Course;

@Repository
public interface contentRepository extends JpaRepository<Content, Long> {

//	public Course getByCourseId(long courseId);
}
