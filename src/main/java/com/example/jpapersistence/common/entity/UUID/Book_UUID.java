package com.example.jpapersistence.common.entity.UUID;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Persistable;

import com.example.jpapersistence.common.enums.Category;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;

@Entity(name = "book_uuid")
public class Book_UUID implements Persistable<String> {
    @Id
    private String             id = UUID.randomUUID().toString();

    private String             title;
    private int                price;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    @JsonBackReference("books")
    private Author_UUID        author;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "book", fetch = FetchType.LAZY)
    @JsonManagedReference("comments")
    private List<Comment_UUID> comments;

    @Enumerated(EnumType.STRING)
    private Category           category;

    public List<Comment_UUID> getComments() {
        return comments;
    }

    public void setAuthor(Author_UUID author) {
        this.author = author;
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
