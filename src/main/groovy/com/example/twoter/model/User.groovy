package com.example.twoter.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.DBRef
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "users")
class User {
    @Id
    String id
    String username
    String email
    String password

    @DBRef
    List<Post> posts

    @DBRef
    List<Like> likes

    List<String> subscribers
}
