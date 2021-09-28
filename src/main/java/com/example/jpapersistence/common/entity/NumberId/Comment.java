package com.example.jpapersistence.common.entity.NumberId;

import java.util.UUID;

import com.example.jpapersistence.common.BitConverter;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
public class Comment {
    @Id
    private Long    id = GuidToInt64();

    private String  title;

    private String  content;

    private Integer grade;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    @JsonBackReference("comments")
    private Book    book;

    private static long GuidToInt64() {
        byte[] bytes = UUID.randomUUID().toString().getBytes();
        return BitConverter.toInt64(bytes, 0);
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
