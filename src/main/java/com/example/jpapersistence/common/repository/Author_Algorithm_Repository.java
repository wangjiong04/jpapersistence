package com.example.jpapersistence.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.jpapersistence.common.entity.NumberId.Author_Algorithm;

@Repository
public interface Author_Algorithm_Repository extends JpaRepository<Author_Algorithm, Long> {
}
