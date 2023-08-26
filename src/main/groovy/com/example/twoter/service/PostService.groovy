package com.example.twoter.service

import com.example.twoter.model.Post
import com.example.twoter.model.User
import com.example.twoter.repository.PostRepository
import com.example.twoter.repository.UserRepository
import org.springframework.stereotype.Service

import java.time.LocalDate

@Service
class PostService {
    private final PostRepository postRepository
    private final UserRepository userRepository

    PostService(PostRepository postRepository,
                UserRepository userRepository) {
        this.postRepository = postRepository
        this.userRepository = userRepository
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
}
