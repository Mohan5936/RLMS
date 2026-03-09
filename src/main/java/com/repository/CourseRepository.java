package com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Entity.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

	public Course findByCourseName(String name);
	List<Course> findByInstructorId(long instructorId);
}
