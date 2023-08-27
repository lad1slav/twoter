package com.example.twoter.repository

import com.example.twoter.model.Like
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query

public interface LikeRepository extends MongoRepository<Like, String> {
}
