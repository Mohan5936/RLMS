package com.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Entity.Progress;


@Repository
public interface ProgressRepository extends JpaRepository<Progress, Long> {

	Optional<Progress> findByUserIdAndLessonId(Long userId, Long lessonId);

}
