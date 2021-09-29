package com.example.jpapersistence.common.entity.NumberId;

import com.example.jpapersistence.common.enums.Gender;

import javax.persistence.*;

@Entity
@Table(name = "author_algorithm")
public class Author_Algorithm {
    @Id
    private Long   id;

    private int    age;
    private String name;
    @Enumerated(EnumType.STRING)
    private Gender genre;

    public void setId(Long id) {
        this.id = id;
    }
}
