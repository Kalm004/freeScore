package com.kalm004.freeScore.score

import com.kalm004.freeScore.game.GameRepository
import com.kalm004.freeScore.player.PlayerRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ScoreService(@Autowired val scoreRepository: ScoreRepository,
                   @Autowired val gameRepository: GameRepository,
                   @Autowired val playerRepository : PlayerRepository) {
    fun getAllScore() = scoreRepository.getAll()

    fun saveScore(gameKey : String, playerName : String, value : Long) {
        val game = gameRepository.getByKey(gameKey)
        val player = playerRepository.getByNameAndGameId(playerName, game.id)
        scoreRepository.saveScore(game.id, player.id, value)
    }

    fun getScoresByGameAndPlayer(gameKey: String, playerName: String) : Set<Score> = TODO()
}