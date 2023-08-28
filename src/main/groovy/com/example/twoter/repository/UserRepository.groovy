package com.example.twoter.repository

import com.example.twoter.model.User
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query

interface UserRepository extends MongoRepository<User, String> {
    @Query("{'username': ?0, 'password': ?1}")
    User getByUsernameAndPassword(String username, String password);
}
