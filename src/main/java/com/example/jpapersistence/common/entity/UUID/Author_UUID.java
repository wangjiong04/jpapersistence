package com.example.jpapersistence.common.entity.UUID;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Persistable;

import com.example.jpapersistence.common.enums.Gender;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;

@NamedEntityGraph(name = "author_uuid_join", attributeNodes = { @NamedAttributeNode(value = "books", subgraph = "book-comments"), },

        subgraphs = { @NamedSubgraph(name = "book-comments", attributeNodes = { @NamedAttributeNode("comments") }) })
@Entity
@Table(name = "author_uuid")
public class Author_UUID implements Persistable<String> {
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

    @Override
    public String getId() {
        return id;
    }

    @Override
    public boolean isNew() {
        return true;
    }
}
