package com.kalm004.freeScore.score

import com.kalm004.freeScore.exceptions.EntityNotFoundException
import com.kalm004.freeScore.game.GameRepository
import com.kalm004.freeScore.player.PlayerRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ScoreService(@Autowired val scoreRepository: ScoreRepository,
                   @Autowired val gameRepository: GameRepository,
                   @Autowired val playerRepository : PlayerRepository) {
    fun getAllScore() = scoreRepository.getAll()

    fun saveScore(appId : Int, playerId : Int, value : Long) {
        gameRepository.getById(appId) ?: throw EntityNotFoundException("Game")
        playerRepository.getById(playerId) ?: throw EntityNotFoundException("Player")
        scoreRepository.saveScore(appId, playerId, value)
    }
}