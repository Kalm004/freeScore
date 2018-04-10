package com.kalm004.freeScore.tests.unitTests.game

import com.kalm004.freeScore.game.Game
import com.kalm004.freeScore.game.GameRepository
import com.kalm004.freeScore.game.GameService
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
class GameServiceTests {
    @Autowired
    lateinit var gameService : GameService

    @MockBean
    lateinit var gameRepository: GameRepository

    @Test
    fun testGetAllGamesByUserOk() {
        given(gameRepository.getAllByUser(1)).willReturn(setOf(Game(1, "Test", 1, "KEY")))
        assertEquals(
                setOf(Game(1, "Test", 1, "KEY")),
                gameService.getAllGamesByUser(1),
                "Get all users different result"
        )
    }
}