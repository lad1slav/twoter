package com.example.twoter.controller

import com.example.twoter.model.User
import com.example.twoter.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user")
class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/get/all")
    List<User> getAll() {
        userService.getAllUsers();
    }

    @GetMapping("/get/{id}")
    User getUserById(@PathVariable String id) {
        userService.getUserById id
    }

    @PostMapping("/save")
    User saveUser(@RequestBody User user) {
        userService.saveUser user
    }

    @PutMapping("/edit")
    User editUser(@RequestBody User user) {
        userService.editUser user
    }

    @DeleteMapping("/delete/{userId}")
    User deleteUser(@PathVariable String userId) {
        userService.deleteUser userId
    }

    @PostMapping("/subscribe/{userId}/{targetUser}")
    User subscribeToUser(@PathVariable String userId, @PathVariable String targetUser) {
        userService.subscribe userId, targetUser
    }

    @PostMapping("/unsubscribe/{userId}/{targetUser}")
    User unsubscribeFromUser(@PathVariable String userId, @PathVariable String targetUser) {
        userService.unsubscribe userId, targetUser
    }
}
