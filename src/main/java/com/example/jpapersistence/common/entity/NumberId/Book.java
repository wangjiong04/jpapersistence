package com.example.jpapersistence.common.entity.NumberId;

import java.util.List;
import java.util.UUID;

import com.example.jpapersistence.common.BitConverter;

//@Entity
public class Book {
    //@Id
    private Long          id = GuidToInt64();

    private String        title;
    private int           price;

    private List<Comment> comments;

    private static long GuidToInt64() {
        byte[] bytes = UUID.randomUUID().toString().getBytes();
        return BitConverter.toInt64(bytes, 0);
    }

}
