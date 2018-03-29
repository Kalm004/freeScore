package com.kalm004.freeScore.tests.score

import com.kalm004.freeScore.game.Game
import com.kalm004.freeScore.game.GameRepository
import com.kalm004.freeScore.player.Player
import com.kalm004.freeScore.player.PlayerRepository
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
@SpringBootTest
class ScoreServiceTests {
    @Autowired
    lateinit var scoreService: ScoreService

    @MockBean
    lateinit var scoreRepository: ScoreRepository

    @MockBean
    lateinit var gameRepository: GameRepository

    @MockBean
    lateinit var playerRepository: PlayerRepository

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
        given(gameRepository.getById(1)).willReturn(Game(1, "Test",  1))
        given(playerRepository.getById(1)).willReturn(Player(1, "Player1"))
        given(scoreRepository.saveScore(1, 1, 100)).will { println("Score saved!") }
        scoreService.saveScore(1, 1, 100)
        assert(true)
    }
}