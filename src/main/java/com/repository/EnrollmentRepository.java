package com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Entity.Enrollment;


@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
//	List<Enrollment> getEnrollmentsByUserId(Long userId);

	List<Enrollment> findByUserId(Long userId);


}
