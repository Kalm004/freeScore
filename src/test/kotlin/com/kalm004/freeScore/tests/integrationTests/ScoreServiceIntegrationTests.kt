package com.kalm004.freeScore.tests.integrationTests

import com.kalm004.freeScore.game.Game
import com.kalm004.freeScore.game.GameCreationData
import com.kalm004.freeScore.game.GameService
import com.kalm004.freeScore.score.Score
import com.kalm004.freeScore.score.ScoreService
import com.kalm004.freeScore.tests.BaseH2Test
import com.kalm004.freeScore.user.UserRegistrationData
import com.kalm004.freeScore.user.UserService
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
class ScoreServiceIntegrationTests : BaseH2Test() {
    @Autowired lateinit var userService : UserService

    @Autowired lateinit var gameService : GameService

    @Autowired lateinit var scoreService: ScoreService

    @Before
    fun beforeTests() {
        val context = createQuery()
    }

    @Test
    fun testSaveScoreOk() {
        //given:
        userService.createUser(UserRegistrationData("User1", "password", "a@a.com"))
        val user = userService.getAllUsers().find { it.name == "User1" }
        gameService.createGame(GameCreationData("Game1", user!!.id, "KEY"))

        val game = gameService.getAllGames().find { it.name == "Game1" }

        //when:
        scoreService.saveScore(game!!.key, "Player1", 100)

        //then:
        scoreService.getScoresByGameAndPlayer(game.key, "Player1").any { it.value == 100 }
    }
}