package com.kalm004.freeScore.tests.score

import com.kalm004.freeScore.application.Application
import com.kalm004.freeScore.application.ApplicationRepository
import com.kalm004.freeScore.score.Score
import com.kalm004.freeScore.score.ScoreRepository
import com.kalm004.freeScore.score.ScoreService
import com.kalm004.freeScore.user.User
import com.kalm004.freeScore.user.UserRepository
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
class ScoreServiceTests {
    @Autowired
    lateinit var scoreService: ScoreService

    @MockBean
    lateinit var scoreRepository: ScoreRepository

    @MockBean
    lateinit var applicationRepository: ApplicationRepository

    @MockBean
    lateinit var userRepository: UserRepository

    @Test
    fun testGetAllScores() {
        given(scoreRepository.getAll()).willReturn(setOf(Score(1, 1, 1, 1)))
        assertEquals(
                setOf(Score(1, 1, 1, 1)),
                scoreService.getAllScore(),
                "Get all users different result"
        )
    }

    @Test
    fun saveScore() {
        given(applicationRepository.getById(1)).willReturn(Application(1, "Test"))
        given(userRepository.getById(1)).willReturn(User(1, "Test"))
        given(scoreRepository.saveScore(1, 1, 100)).will { println("Score saved!") }
        scoreService.saveScore(1, 1, 100)
        assert(true)
    }
}