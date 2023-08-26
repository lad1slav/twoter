package com.example.twoter.controller

import com.example.twoter.model.Post
import com.example.twoter.model.PostDTO
import com.example.twoter.model.User
import com.example.twoter.service.PostService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/post")
class PostController {
    @Autowired
    PostService postService;

    @PostMapping("/save")
    Post savePost(@RequestBody PostDTO postDTO) {
        postService.saveNewPost postDTO.getUserId(), postDTO.getData()
    }

    @PutMapping("/edit/{postId}")
    Post editPost(@RequestBody PostDTO postDTO, @PathVariable String postId) {
        postService.editPost postId, postDTO.getData()
    }

    @DeleteMapping("/delete/{postId}")
    Post deletePost(@PathVariable postId) {
        postService.deletePost postId
    }

    @GetMapping("/newsletter/{userId}")
    List<Post> getNewsletter(@PathVariable userId) {
        postService.getNewsletter userId
    }
}
