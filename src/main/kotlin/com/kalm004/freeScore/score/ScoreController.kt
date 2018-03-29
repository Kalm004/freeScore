package com.kalm004.freeScore.score

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
class ScoreController(@Autowired var scoreService : ScoreService) {
    @GetMapping("/scores")
    fun getAll() = scoreService.getAllScore()

    @PostMapping("/scores/{appId}/{playerId}")
    fun addScore(@PathVariable appId : Int, @PathVariable userId : Int, @RequestBody value : Long) =
            scoreService.saveScore(appId, userId, value)
}