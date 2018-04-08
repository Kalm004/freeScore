package com.kalm004.freeScore.game

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.annotation.Secured
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.security.Principal

@RestController
class GameController(@Autowired var gameService : GameService) {

    @GetMapping("/games")
    fun getAll(principal : Principal) : Set<Game> {
        return gameService.getAllGames()
    }

    @PostMapping("/games")
    fun createGame(@RequestBody game : GameCreationData) = gameService.createGame(game)
}