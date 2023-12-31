package com.example.twoter.service

import com.example.twoter.model.User
import com.example.twoter.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService {

    private final UserRepository userRepository

    UserService(UserRepository userRepository) {
        this.userRepository = userRepository
    }

    User saveUser(User user) {
        userRepository.save(user)
    }

    User editUser(User newUser) {
        userRepository.save(newUser)
    }

    User deleteUser(String userId) {
        userRepository.deleteById(userId)
    }

    List<User> getAllUsers() {
        userRepository.findAll()
    }

    User getUserById(String id) {
        userRepository.findById(id).get()
    }

    User login(String username, String password) throws IllegalAccessError{
        User user = userRepository.getByUsernameAndPassword(username, password)
        if (user === null) {
            throw new IllegalAccessError("Incorrect username or password")
        }
        user.setIsLogged(true)
        userRepository.save user
    }

    User logout(String userId) {
        User user = userRepository.findById(userId).get()
        user.setIsLogged(false)
        userRepository.save(user)
    }

    User subscribe(String userId, String targetUserId) {
        User currentUser = userRepository.findById userId get()
        if (currentUser.getSubscribers() === null || currentUser.getSubscribers().isEmpty()) {
            currentUser.setSubscribers(new HashSet<String>())
        }
        Set<String> currentUserSubs = currentUser.getSubscribers()
        currentUserSubs.add targetUserId
        currentUser.setSubscribers currentUserSubs
        userRepository.save currentUser
    }

    User unsubscribe(String userId, String targetUserId) {
        User currentUser = userRepository.findById userId get()
        currentUser.getSubscribers().remove(targetUserId)
        userRepository.save currentUser
    }
}