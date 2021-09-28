package com.example.jpapersistence.common.entity.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity(name = "comment_uuid")
public class Comment_UUID {
    @Id
    private String id = UUID.randomUUID().toString();

    private String title;

    private String content;
}
