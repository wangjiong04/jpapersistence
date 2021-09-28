package com.example.jpapersistence.common.entity.NumberId;

import com.example.jpapersistence.common.BitConverter;
import com.example.jpapersistence.common.enums.Gender;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "author_uuid_update")
public class Author_UUID_Update {
    @Id
    private long id = GuidToInt64();

    private int age;
    private String name;
    private Gender genre;

    private static long GuidToInt64() {
        byte[] bytes = UUID.randomUUID().toString().getBytes();
        return BitConverter.toInt64(bytes, 0);
    }

}
