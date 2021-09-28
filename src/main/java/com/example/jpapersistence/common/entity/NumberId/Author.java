package com.example.jpapersistence.common.entity.NumberId;

import java.util.List;

import com.example.jpapersistence.common.enums.Gender;

//@Entity
//@SequenceGenerator(name = "author_id_seq", sequenceName = "author_id_seq", allocationSize = 1)
public class Author {
    //@Id
    //@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "author_id_seq")
    private Long       id;

    private int        age;
    private String     name;
    private Gender     genre;

    private List<Book> books;
}
