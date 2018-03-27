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
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ScoreRepository : BaseH2Test() {
    @Autowired
    lateinit var scoreRepository: ScoreRepository

    @Before
    fun beforeTests() {
        val context : DSLContext = createQuery()

        context.insertInto(Tables.USER)
                .columns(Tables.USER.NAME)
                .values("PEPE")
                .execute()

        context.insertInto(Tables.APPLICATION)
                .columns(Tables.APPLICATION.NAME)
                .values("APP_PEPE")
                .execute()

        context.insertInto(Tables.SCORE)
                .columns(Tables.SCORE.APPLICATIONID, Tables.SCORE.USERID, Tables.SCORE.VALUE)
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