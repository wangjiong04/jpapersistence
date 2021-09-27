package com.example.jpapersistence.common.entity;

import javax.persistence.*;
import java.util.UUID;

@Entity
@SequenceGenerator(name = "author_id_seq", sequenceName = "author_id_seq", allocationSize = 1)
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "author_id_seq")
    private Long id;

    private int age;
    private String name;
    private String genre;
}
