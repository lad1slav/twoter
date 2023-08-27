package com.example.twoter.service

import com.example.twoter.model.User
import com.example.twoter.repository.UserRepository
import spock.lang.Specification
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.beans.factory.annotation.Autowired

@SpringBootTest
@ActiveProfiles("test") // Use the "test" profile
class UserServiceSpec extends Specification {

    @Autowired
    UserService userService

    @Autowired
    UserRepository userRepository

    def "should return user by id"() {
        given:
        def id = "1"

        when:
        def user = userService.getUserById(id)

        then:
        user != null
        user.id == id
    }

    def "should delete a user"() {
        given:
        def username = "testuser"
        def email = "testuser@example.com"
        User user = new User(username: username, email: email)
        user = userRepository.save(user)

        when:
        userService.deleteUser(user.id)

        then:
        User deletedUser = userRepository.findById(user.id).orElse(null)
        deletedUser == null
    }

    def "should create a user"() {
        given:
        def username = "testuser"
        def email = "testuser@example.com"

        when:
        User user = userService.saveUser(new User(username:username, email:email))

        then:
        user != null
        user.username == username
        user.email == email

        and:
        user.id != null // Assuming your User class has an id field
    }

    def "should subscribe user to another user"() {
        given:
        def firstuser = "1"
        def targetUser = "2"

        when:
        User user = userService.subscribe(firstuser, targetUser)

        then:
        user != null
        user.getSubscribers().get(0) == targetUser
    }

    def "should unsubscribe user"() {
        given:
        def firstuser = "1"
        def targetUser = "2"

        when:
        User user = userService.unsubscribe(firstuser, targetUser)

        then:
        user != null
        user.getSubscribers().isEmpty()
    }
}
