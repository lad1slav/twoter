package com.example.twoter.repository

import com.example.twoter.model.Comment
import org.springframework.data.mongodb.repository.MongoRepository

public interface CommentRepository extends MongoRepository<Comment, String> {

}
