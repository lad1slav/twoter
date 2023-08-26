package com.example.twoter.model

import com.example.twoter.repository.CommentRepository
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.DBRef
import org.springframework.data.mongodb.core.mapping.Document

import java.time.LocalDate

@Document(collection = "posts")
class Post {
    @Id
    String id;
    String data;
    LocalDate date;
    String userId;

    @DBRef
    List<Comment> comments
}
