package com.kalm004.freeScore.tests.unitTests.user

import com.kalm004.freeScore.roles.Role
import com.kalm004.freeScore.tests.BaseH2Test
import com.kalm004.freeScore.user.User
import com.kalm004.freeScore.user.UserRepository
import com.kalm004.persistence.Tables.USER
import org.jooq.DSLContext
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

@RunWith(SpringRunner::class)
@SpringBootTest
class UserRepositoryTests : BaseH2Test() {
    @Autowired
    lateinit var userRepository : UserRepository

    @Before
    fun beforeTests() {
        val context : DSLContext = createQuery()

        context.insertInto(USER)
                .columns(USER.NAME, USER.HASHEDPASSWORD, USER.EMAIL, USER.ROLE)
                .values("PEPE", "hash", "a@a.com", Role.GAME_DEVELOPER.name)
                .execute()

        context.insertInto(USER)
                .columns(USER.NAME, USER.HASHEDPASSWORD, USER.EMAIL, USER.ROLE)
                .values("MANOLO", "hash", "a@a.com", Role.GAME_DEVELOPER.name)
                .execute()
    }

    @Test
    fun testGetAllUsersOk() =
            assertEquals(
                setOf(
                        User(1, "PEPE", "hash", "a@a.com", Role.GAME_DEVELOPER),
                        User(2, "MANOLO", "hash", "a@a.com", Role.GAME_DEVELOPER)
                ),
                userRepository.getAll())

    @Test
    fun testGetAllUsersNotOk() =
        assertNotEquals(
                setOf(
                        User(2, "MANOLO", "hash", "a@a.com", Role.GAME_DEVELOPER)
                ),
                userRepository.getAll())

    @Test
    fun testGetUserByIdOk() =
            assertEquals(
                    User(1, "PEPE", "hash", "a@a.com", Role.GAME_DEVELOPER),
                    userRepository.getById(1))

    @Test
    fun testGetUserByIdNotOk() =
        assertEquals(
                null,
                userRepository.getById(3))


    @Test
    fun testCreateUser() {
        //given:
        //Empty database

        //when:
        userRepository.createUser("User1", "1234", "user@server.com", Role.GAME_DEVELOPER)

        //then:
        assert(userRepository.getAll().any {
            it.name == "User1"
            && it.hashedPassword == "1234"
            && it.email == "user@server.com"
            && it.role == Role.GAME_DEVELOPER
        })
    }

    @Test
    fun updateUserRole() {
        //given:
        userRepository.createUser("User1", "1234", "user@server.com", Role.GAME_DEVELOPER)

        //when:
        val userId = userRepository.getByEmail("user@server.com")?.id
        userRepository.updateUserRol(userId!!, Role.ADMIN)

        //then:
        assertEquals(
                User(userId, "User1", "1234", "user@server.com", Role.ADMIN),
                userRepository.getByEmail("user@server.com")
        )
    }
}