package com.kalm004.freeScore.tests.score

import com.kalm004.freeScore.score.Score
import com.kalm004.freeScore.score.ScoreRepository
import com.kalm004.freeScore.tests.BaseH2Test
import com.kalm004.persistence.Tables
import org.jooq.DSLContext
import org.jooq.exception.DataAccessException
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import kotlin.test.assertEquals

@RunWith(SpringRunner::class)
@SpringBootTest
class ScoreRepository : BaseH2Test() {
    @Autowired
    lateinit var scoreRepository: ScoreRepository

    @Before
    fun beforeTests() {
        val context : DSLContext = createQuery()

        context.insertInto(Tables.USER)
                .columns(Tables.USER.NAME, Tables.USER.HASHEDPASSWORD, Tables.USER.EMAIL)
                .values("PEPE", "hash", "a@a.com")
                .execute()

        context.insertInto(Tables.PLAYER)
                .columns(Tables.PLAYER.NAME)
                .values("Player1")
                .execute()

        context.insertInto(Tables.GAME)
                .columns(Tables.GAME.NAME, Tables.GAME.USERID)
                .values("APP_PEPE", 1)
                .execute()

        context.insertInto(Tables.SCORE)
                .columns(Tables.SCORE.GAMEID, Tables.SCORE.PLAYERID, Tables.SCORE.VALUE)
                .values(1, 1, 50)
                .execute()
    }

    @Test
    fun testSaveScoreOk() {
        scoreRepository.saveScore(1, 1, 100)
    }

    @Test(expected = DataAccessException::class)
    fun testSaveScoreUserNotFound() {
        scoreRepository.saveScore(1, 2, 100)
    }

    @Test(expected = DataAccessException::class)
    fun testSaveScoreApplicationNotFound() {
        scoreRepository.saveScore(2, 1, 100)
    }

    @Test
    fun getAllScoresOk() {
        assertEquals(setOf(Score(1, 1, 1, 50)), scoreRepository.getAll())
    }
}