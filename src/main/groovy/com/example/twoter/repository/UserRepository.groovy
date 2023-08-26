package com.example.twoter.repository

import com.example.twoter.model.User
import org.springframework.data.mongodb.repository.MongoRepository

interface UserRepository extends MongoRepository<User, String> {

}
