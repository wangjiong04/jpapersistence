package com.example.jpapersistence.common.entity.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity(name = "book_uuid")
public class Book_UUID {
    @Id
    private String id = UUID.randomUUID().toString();

    private String title;
    private int price;

}
