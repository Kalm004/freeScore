package com.kalm004.freeScore.tests.game

import com.kalm004.freeScore.game.Game
import com.kalm004.freeScore.game.GameRepository
import com.kalm004.freeScore.tests.BaseH2Test
import com.kalm004.persistence.Tables.GAME
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
class GameRepositoryTests : BaseH2Test() {
    @Autowired
    lateinit var gameRepository: GameRepository

    @Before
    fun beforeTests() {
        val context : DSLContext = createQuery()

        context.insertInto(USER)
                .columns(USER.NAME, USER.HASHEDPASSWORD, USER.EMAIL)
                .values("APP_PEPE", "hash", "a@a.com")
                .execute()

        context.insertInto(GAME)
                .columns(GAME.NAME, GAME.USERID)
                .values("APP_PEPE", 1)
                .execute()

        context.insertInto(GAME)
                .columns(GAME.NAME, GAME.USERID)
                .values("APP_MANOLO", 1)
                .execute()
    }

    @Test
    fun testGetAllGamesOk() {
        assertEquals(setOf(Game(1, "APP_PEPE", 1), Game(2, "APP_MANOLO", 1)), gameRepository.getAll())
    }

    @Test
    fun testGetAllGamesNotOk() {
        assertNotEquals(setOf(Game(2, "APP_MANOLO", 1)), gameRepository.getAll())
    }

    @Test
    fun testGetGameByIdOk() {
        assertEquals(Game(1, "APP_PEPE", 1), gameRepository.getById(1))
    }

    @Test
    fun testGetGameByIdNotOk() {
        assertEquals(null, gameRepository.getById(3))
    }
}