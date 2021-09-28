package com.example.jpapersistence.common.entity.UUID;

import com.example.jpapersistence.common.enums.Gender;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "author_uuid")
public class Author_UUID {
    @Id
    private String id = UUID.randomUUID().toString();

    private int age;
    private String name;
    private Gender genre;
}
