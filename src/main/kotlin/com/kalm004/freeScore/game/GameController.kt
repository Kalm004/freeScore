package com.kalm004.freeScore.game

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class GameController(@Autowired var gameService : GameService) {

    @GetMapping("/games")
    fun getAll() = gameService.getAllGames()

}