package com.example.jpapersistence.common.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "author_algorithm")
public class Author_Algorithm {
    @Id
    private Long id;

    private int age;
    private String name;
    private String genre;

    public void setId(Long id) {
        this.id = id;
    }
}
