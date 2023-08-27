package com.example.twoter.service

import com.example.twoter.model.Like
import com.example.twoter.model.Post
import com.example.twoter.repository.LikeRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification

@SpringBootTest
@ActiveProfiles("test") // Use the "test" profile
class LikeServiceSpec extends Specification {
    @Autowired
    LikeService likeService

    @Autowired
    PostService postService

    @Autowired
    LikeRepository likeRepository

    def "should create a like"() {
        given:
        def userId = "1"
        Post post = postService.saveNewPost(userId, "somepost")

        when:
        Like like = likeService.like(userId, post.id)

        then:
        like != null
        like.postId == post.id
        like.userId == userId
    }

    def "should delete a post"() {
        given:
        def userId = "1"
        Post post = postService.saveNewPost(userId, "somepost")
        Like like = likeService.like(userId, post.id)

        when:
        likeService.deleteLike(like.id)

        then:
        Like deletedLike = likeRepository.findById(like.id).orElse(null)
        deletedLike == null
    }
}
