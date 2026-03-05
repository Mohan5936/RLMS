package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Entity.Content;

@Repository
public interface contentRepository extends JpaRepository<Content, Long> {

}
