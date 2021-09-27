package com.example.jpapersistence.idstrategy;

import com.example.jpapersistence.common.entity.Author;
import com.example.jpapersistence.common.entity.Author_UUID;
import com.example.jpapersistence.common.entity.Author_UUID_Update;
import com.example.jpapersistence.common.measure.Measured;
import com.example.jpapersistence.common.repository.AuthorRepository;
import com.example.jpapersistence.common.repository.Author_UUID_Repository;
import com.example.jpapersistence.common.repository.Author_UUID_Update_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class IdCompare {
    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private Author_UUID_Repository author_uuid_repository;

    @Autowired
    private Author_UUID_Update_Repository author_uuid_update_repository;

    @Measured
    public void test() {
        for(int i=0;i<10000;i++){
            Author author =new Author();
            authorRepository.save(author);
        }
    }

    @Measured
    public void test1() {
        for(int i=0;i<10000;i++){
            Author_UUID author =new Author_UUID();
            author_uuid_repository.save(author);
        }
    }

    @Measured
    public void test2() {
        for(int i=0;i<10000;i++){
            Author_UUID_Update author =new Author_UUID_Update();
            author_uuid_update_repository.save(author);
        }
    }

}
