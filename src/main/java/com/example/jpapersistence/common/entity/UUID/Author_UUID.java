package com.example.jpapersistence.common.entity.UUID;

import java.util.List;
import java.util.UUID;

import com.example.jpapersistence.common.enums.Gender;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;

@Entity
@Table(name = "author_uuid")
public class Author_UUID {
    @Id
    private String          id = UUID.randomUUID().toString();

    private int             age;
    private String          name;
    @Enumerated(EnumType.STRING)
    private Gender          genre;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "author", fetch = FetchType.LAZY)
    @JsonManagedReference("books")
    private List<Book_UUID> books;

    public List<Book_UUID> getBooks() {
        return books;
    }
}
