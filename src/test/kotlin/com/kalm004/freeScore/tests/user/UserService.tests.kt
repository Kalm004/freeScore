package com.kalm004.freeScore.tests.user

import com.kalm004.freeScore.user.User
import com.kalm004.freeScore.user.UserRepository
import com.kalm004.freeScore.user.UserService
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.junit4.SpringRunner
import kotlin.test.assertEquals

@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserServiceTests {
    @Autowired lateinit var userService: UserService

    @MockBean lateinit var userRepository: UserRepository

    @Test
    fun testGetAllUsers() {
        given(userRepository.getAll()).willReturn(setOf(User(1, "Test")))
        assertEquals(
                setOf(User(1, "Test")),
                userService.getAllUsers(),
                "Get all users different result"
        )
    }
}