package com.example.jpapersistence.common.repository;

import com.example.jpapersistence.common.entity.Author_Algorithm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Author_Algorithm_Repository extends JpaRepository<Author_Algorithm, Long> {
}
