package com.example.jpapersistence.common.entity.NumberId;

import java.util.UUID;

import com.example.jpapersistence.common.BitConverter;
import com.example.jpapersistence.common.enums.Gender;

import javax.persistence.*;

@Entity
@Table(name = "author_uuid_update")
public class Author_UUID_Update {
    @Id
    private long   id = GuidToInt64();

    private int    age;
    private String name;
    @Enumerated(EnumType.STRING)
    private Gender genre;

    private static long GuidToInt64() {
        byte[] bytes = UUID.randomUUID().toString().getBytes();
        return BitConverter.toInt64(bytes, 0);
    }

}
