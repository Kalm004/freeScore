package com.kalm004.freeScore.game

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class GameService(@Autowired var gameRepository: GameRepository) {
    fun getAllGamesByUser(userId: Int) = gameRepository.getAllByUser(userId)

    fun createGame(game: GameCreationData) : String = TODO()

    fun getByNameAndUserId(name: String, userId: Int) = gameRepository.getByNameAndUserId(name, userId)
}