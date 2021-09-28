package com.example.jpapersistence.common.entity.NumberId;

import java.util.UUID;

import com.example.jpapersistence.common.BitConverter;

//@Entity
public class Comment {
    //@Id
    private Long   id = GuidToInt64();

    private String title;

    private String content;

    private static long GuidToInt64() {
        byte[] bytes = UUID.randomUUID().toString().getBytes();
        return BitConverter.toInt64(bytes, 0);
    }
}
