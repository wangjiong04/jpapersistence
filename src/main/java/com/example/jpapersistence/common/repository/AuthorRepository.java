package com.example.jpapersistence.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.jpapersistence.common.entity.NumberId.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
}