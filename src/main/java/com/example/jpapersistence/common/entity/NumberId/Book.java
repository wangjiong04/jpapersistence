package com.example.jpapersistence.common.entity.NumberId;

import java.util.List;
import java.util.UUID;

import com.example.jpapersistence.common.BitConverter;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;

@Entity
public class Book {
    @Id
    private Long          id = GuidToInt64();

    private String        title;
    private int           price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    @JsonBackReference("books")
    private Author        author;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "book", fetch = FetchType.LAZY)
    @JsonManagedReference("comments")
    private List<Comment> comments;

    private static long GuidToInt64() {
        byte[] bytes = UUID.randomUUID().toString().getBytes();
        return BitConverter.toInt64(bytes, 0);
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}
