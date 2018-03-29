package com.kalm004.freeScore.tests.user

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
                .columns(USER.NAME, USER.HASHEDPASSWORD, USER.EMAIL)
                .values("PEPE", "hash", "a@a.com")
                .execute()

        context.insertInto(USER)
                .columns(USER.NAME, USER.HASHEDPASSWORD, USER.EMAIL)
                .values("MANOLO", "hash", "a@a.com")
                .execute()
    }

    @Test
    fun testGetAllUsersOk() =
            assertEquals(
                setOf(
                        User(1, "PEPE", "hash", "a@a.com"),
                        User(2, "MANOLO", "hash", "a@a.com")
                ),
                userRepository.getAll())

    @Test
    fun testGetAllUsersNotOk() =
        assertNotEquals(
                setOf(
                        User(2, "MANOLO", "hash", "a@a.com")
                ),
                userRepository.getAll())

    @Test
    fun testGetUserByIdOk() =
            assertEquals(
                    User(1, "PEPE", "hash", "a@a.com"),
                    userRepository.getById(1))

    @Test
    fun testGetUserByIdNotOk() =
        assertEquals(
                null,
                userRepository.getById(3))

}