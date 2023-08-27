package com.example.twoter.service

import com.example.twoter.model.Comment
import com.example.twoter.model.Post
import com.example.twoter.model.User
import com.example.twoter.repository.CommentRepository
import com.example.twoter.repository.PostRepository
import com.example.twoter.repository.UserRepository
import org.springframework.stereotype.Service

import java.time.LocalDate

@Service
class PostService {
    private final PostRepository postRepository
    private final UserRepository userRepository
    private final CommentRepository commentRepository

    PostService(PostRepository postRepository,
                UserRepository userRepository,
                CommentRepository commentRepository) {
        this.postRepository = postRepository
        this.userRepository = userRepository
        this.commentRepository = commentRepository
    }

    Post saveNewPost(String userId, String data) {
        Post newPost = new Post()
        newPost.setData(data)
        newPost.setUserId(userId)
        newPost.setDate(LocalDate.now())
        postRepository.save(newPost)

        User user = userRepository.findById(userId).get();
        if (user.getPosts() === null || user.getPosts().isEmpty()) {
            user.setPosts(new ArrayList<Post>())
        }
        List<Post> newPostList = user.getPosts()
        newPostList.add(newPost)
        user.setPosts(newPostList)
        userRepository.save(user)
        return newPost
    }

    Post deletePost(String postId) {
        postRepository.deleteById(postId)
    }

    Post editPost(String postId, String data) {
        Post newPost = postRepository.findById(postId).get()
        newPost.setDate(LocalDate.now())
        newPost.setData(data)
        postRepository.save(newPost)
    }

    List<Post> getNewsletter(String userId) {
        User currentUser = userRepository.findById userId get()
        List<Post> userSubsPosts = new ArrayList<>()
        currentUser.getSubscribers().forEach { userSubsPosts.addAll userRepository.findById(it).get().getPosts()}
        userSubsPosts.remove null
        return userSubsPosts
    }

    List<Post> getUserPosts(String userId) {
        User currentUser = userRepository.findById userId get()
        return currentUser.getPosts()
    }

    Comment createComment(String data, String postId) {
        Comment newComment = new Comment()
        newComment.setData data
        newComment.setDate LocalDate.now()
        newComment.setPostId postId
        commentRepository.save newComment

        Post commentedPost = postRepository.findById(postId).get()
        if (commentedPost.getComments() === null || commentedPost.getComments().isEmpty()) {
            commentedPost.setComments(new ArrayList<Comment>())
        }
        List<Comment> commentList = commentedPost.getComments()
        commentList.add(newComment)
        commentedPost.setComments commentList
        postRepository.save commentedPost

        return newComment
    }

    List<Comment> getPostComments(String postId) {
        Post commentedPost = postRepository.findById(postId).get()
        return commentedPost.getComments()
    }
}
