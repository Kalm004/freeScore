package com.kalm004.freeScore.game

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class GameService(@Autowired var gameRepository: GameRepository) {
    fun getAllGames() = gameRepository.getAll()
}