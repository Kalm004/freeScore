package com.kalm004.freeScore.score

import org.jooq.exception.NoDataFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
class ScoreController(@Autowired var scoreService : ScoreService) {
    @GetMapping("/scores")
    fun getAll() = scoreService.getAllScore()

    @PostMapping("/scores/{gameKey}/{playerName}")
    fun addScore(@PathVariable gameKey : String, @PathVariable playerName : String, @RequestBody value : Long) {
            return scoreService.saveScore(gameKey, playerName, value)
    }
}