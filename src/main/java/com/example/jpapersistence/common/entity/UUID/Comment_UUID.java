package com.example.jpapersistence.common.entity.UUID;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity(name = "comment_uuid")
public class Comment_UUID {
    @Id
    private String    id = UUID.randomUUID().toString();

    private String    title;

    private String    content;

    private Integer   grade;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    @JsonBackReference("comments")
    private Book_UUID book;

    public void setBook(Book_UUID book) {
        this.book = book;
    }
}
