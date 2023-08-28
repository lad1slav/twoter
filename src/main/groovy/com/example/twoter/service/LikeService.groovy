package com.example.twoter.service

import com.example.twoter.model.Like
import com.example.twoter.model.Post
import com.example.twoter.model.User
import com.example.twoter.repository.LikeRepository
import com.example.twoter.repository.PostRepository
import com.example.twoter.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class LikeService {
    private final UserRepository userRepository
    private final LikeRepository likeRepository
    private final PostRepository postRepository

    LikeService(UserRepository userRepository,
                LikeRepository likeRepository,
                PostRepository postRepository) {
        this.userRepository = userRepository
        this.likeRepository = likeRepository
        this.postRepository = postRepository
    }

    Like like(String userId, String postId) {
        Like like = new Like(userId: userId, postId: postId)
        likeRepository.save like
        User user = userRepository.findById(userId).get()
        if (user.getLikes() === null || user.getLikes().isEmpty()) {
            user.setLikes(new HashSet<Like>())
        }
        Set<Like> newLikeList = user.getLikes()
        newLikeList.add(like)
        user.setLikes(newLikeList)
        userRepository.save(user)

        Post post = postRepository.findById(postId).get()
        if(post.getLikes() === null || post.getLikes().isEmpty()) {
            post.setLikes(new HashSet<Like>())
        }
        Set<Like> newPostLikeList = post.getLikes()
        newPostLikeList.add(like)
        post.setLikes(newPostLikeList)
        postRepository.save(post)
        return like
    }

    Like deleteLike(String likeId) {
        likeRepository.deleteById likeId
    }
}
