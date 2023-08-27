package com.example.twoter.service

import com.example.twoter.model.Comment
import com.example.twoter.model.Post
import com.example.twoter.model.User
import com.example.twoter.repository.CommentRepository
import com.example.twoter.repository.PostRepository
import com.example.twoter.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification

@SpringBootTest
@ActiveProfiles("test") // Use the "test" profile
class PostServiceSpec extends Specification {
    @Autowired
    PostService postService

    @Autowired
    PostRepository postRepository

    @Autowired
    UserService userService

    @Autowired
    CommentRepository commentRepository

    def "should return newsletter by userId"() {
        given:
        def userId = "1"
        def targetUserId = "2"
        def data = "somedata"
        userService.subscribe(userId, targetUserId)
        postService.saveNewPost(targetUserId, data)

        when:
        def posts = postService.getNewsletter(userId)

        then:
        posts != null
        !posts.isEmpty()
    }

    def "should create a post"() {
        given:
        def userId = "1"
        def data = "testuser@example.com"

        when:
        Post post = postService.saveNewPost(userId, data)

        then:
        post != null
        post.data == data
    }

    def "should delete a post"() {
        given:
        def userId = "1"
        def data = "testuser@example.com"
        Post post = postService.saveNewPost(userId, data)

        when:
        postService.deletePost(post.id)

        then:
        Post deletedPost = postRepository.findById(post.id).orElse(null)
        deletedPost == null
    }

    def "should edit post"() {
        given:
        def userId = "1"
        def data = "testuser@example.com"
        def newData = "somenewdata"
        Post post = postService.saveNewPost(userId, data)

        when:
        Post editedPost = postService.editPost(post.id, newData)

        then:
        post.data == data
        editedPost.data == newData
    }

    def "should create a comment"() {
        given:
        def userId = "1"
        def data = "testuser@example.com"
        commentRepository.deleteAll()
        Post post = postService.saveNewPost(userId, data)

        when:
        postService.createComment(data, post.id)

        then:
        List<Comment> comments = postService.getPostComments(post.id)
        comments.get(0).data == data
    }

    def "should get a commentList"() {
        given:
        def userId = "1"
        def data = "testuser@example.com"
        commentRepository.deleteAll()
        Post post = postService.saveNewPost(userId, data)
        postService.createComment(data, post.id)

        when:
        List<Comment> comments = postService.getPostComments(post.id)

        then:
        comments.get(0).data == data
    }
}
