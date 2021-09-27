package com.example.jpapersistence.idstrategy;

import com.example.jpapersistence.common.SnowFlake;
import com.example.jpapersistence.common.entity.Author;
import com.example.jpapersistence.common.entity.Author_Algorithm;
import com.example.jpapersistence.common.entity.Author_UUID;
import com.example.jpapersistence.common.entity.Author_UUID_Update;
import com.example.jpapersistence.common.measure.Measured;
import com.example.jpapersistence.common.repository.AuthorRepository;
import com.example.jpapersistence.common.repository.Author_Algorithm_Repository;
import com.example.jpapersistence.common.repository.Author_UUID_Repository;
import com.example.jpapersistence.common.repository.Author_UUID_Update_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class IdCompare {
    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private Author_UUID_Repository author_uuid_repository;

    @Autowired
    private Author_UUID_Update_Repository author_uuid_update_repository;

    @Autowired
    private Author_Algorithm_Repository author_algorithm_repository;

    @Autowired
    private SnowFlake snowFlake;

    @Measured(message = "Sequence id:")
    public void test() {
        for (int i = 0; i < 10000; i++) {
            Author author = new Author();
            authorRepository.save(author);
        }
    }

    @Measured(message = "UUID:")
    public void test1() {
        for (int i = 0; i < 10000; i++) {
            Author_UUID author = new Author_UUID();
            author_uuid_repository.save(author);
        }
    }

    @Measured(message = "UUID to int64:")
    public void test2() {
        for (int i = 0; i < 10000; i++) {
            Author_UUID_Update author = new Author_UUID_Update();
            author_uuid_update_repository.save(author);
        }
    }

    @Measured(message = "Snow flake id:")
    public void test3() {
        for (int i = 0; i < 10000; i++) {
            Author_Algorithm author = new Author_Algorithm();
            author.setId(snowFlake.nextId());
            author_algorithm_repository.save(author);
        }
    }

}
