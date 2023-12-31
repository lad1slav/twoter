package com.example.twoter.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

import java.time.LocalDate

@Document(collection = "comments")
class Comment {
    @Id
    String id
    String data
    LocalDate date
    String postId
}
