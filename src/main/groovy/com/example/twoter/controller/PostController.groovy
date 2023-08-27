package com.example.twoter.controller

import com.example.twoter.model.Comment
import com.example.twoter.model.CommentDTO
import com.example.twoter.model.Like
import com.example.twoter.model.Post
import com.example.twoter.model.PostDTO
import com.example.twoter.service.LikeService
import com.example.twoter.service.PostService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

import java.nio.file.Path

@RestController
@RequestMapping("/post")
class PostController {
    @Autowired
    PostService postService;

    @Autowired
    LikeService likeService;

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

    @PostMapping("/comment")
    Comment saveComment(@RequestBody CommentDTO data) {
        postService.createComment data.getData(), data.getPostId()
    }

    @GetMapping("/list/{userId}")
    List<Post> getUserPosts(@PathVariable String userId) {
        postService.getUserPosts userId
    }

    @PostMapping("/like/{userId}/{postId}")
    Like saveLike(@PathVariable String userId, @PathVariable String postId) {
        likeService.like userId, postId
    }

    @DeleteMapping("/dislike/{likeId}")
    Like deleteLike(@PathVariable String likeId) {
        likeService.deleteLike likeId
    }

    @GetMapping("/comment/{postId}")
    List<Comment> getComment(@PathVariable String postId) {
        postService.getPostComments postId
    }
}
