package com.example.jpapersistence.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.jpapersistence.common.entity.UUID.Author_UUID;

@Repository
public interface Author_UUID_Repository extends JpaRepository<Author_UUID, String>,
                                        BaseRepository<Author_UUID, String> {

}
