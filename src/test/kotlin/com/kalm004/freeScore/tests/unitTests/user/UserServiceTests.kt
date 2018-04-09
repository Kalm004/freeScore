package com.kalm004.freeScore.tests.unitTests.user

import com.kalm004.freeScore.roles.Role
import com.kalm004.freeScore.user.User
import com.kalm004.freeScore.user.UserRegistrationData
import com.kalm004.freeScore.user.UserRepository
import com.kalm004.freeScore.user.UserService
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.junit4.SpringRunner
import kotlin.test.assertEquals

@RunWith(SpringRunner::class)
@SpringBootTest
class UserServiceTests {
    @Autowired lateinit var userService: UserService

    @MockBean lateinit var userRepository: UserRepository

    @Test
    fun testGetAllUsers() {
        given(userRepository.getAll()).willReturn(setOf(User(1, "Test", "hash", "a@a.com", Role.GAME_DEVELOPER)))
        assertEquals(
                setOf(User(1, "Test", "hash", "a@a.com", Role.GAME_DEVELOPER)),
                userService.getAllUsers(),
                "Get all users different result"
        )
    }

    @Test
    fun testCreateUser() {
        // given:
        // when:
        userService.createUser(UserRegistrationData("User", "pass", "user@server.com"))

        // then:
        Mockito.verify(userRepository).createUser(
                "User",
                "\$2a\$10\$g7BNVysi8m9Ho6w8YT6YnuADwpg3d8Q9l3lapXDQGZF2M/HH3X9ca",
                "user@server.com",
                Role.GAME_DEVELOPER
        )
    }
}