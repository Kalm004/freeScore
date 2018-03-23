package com.kalm004.freeScore.tests

import com.kalm004.freeScore.score.Score
import com.kalm004.freeScore.score.ScoreRepository
import com.kalm004.freeScore.score.ScoreService
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
class ScoreTests {
    @Autowired
    lateinit var scoreService: ScoreService

    @MockBean
    lateinit var scoreRepository: ScoreRepository

    @Test
    fun testGetAllScores() {
        given(scoreRepository.getAll()).willReturn(setOf(Score(1, 1, 1, 1)))
        assertEquals(
                setOf(Score(1, 1, 1, 1)),
                scoreService.getAllScore(),
                "Get all users different result"
        )
    }
}