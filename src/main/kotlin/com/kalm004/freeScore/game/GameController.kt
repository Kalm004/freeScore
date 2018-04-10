package com.kalm004.freeScore.game

import com.kalm004.freeScore.user.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users/{userId}/games")
class GameController(@Autowired var gameService : GameService) {

    @GetMapping
    fun getAll(@PathVariable userId : Int, principal : UsernamePasswordAuthenticationToken) : Set<Game> {
        val loggedInUser = principal.principal as User
        if (loggedInUser.id == userId)
            return gameService.getAllGamesByUser(userId)
        else
            throw BadCredentialsException("You don't have permissions to see the games for that user")
    }

    @PostMapping
    fun createGame(@RequestBody game : GameCreationData) = gameService.createGame(game)
}