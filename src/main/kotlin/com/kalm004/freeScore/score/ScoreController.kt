package com.kalm004.freeScore.score

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ScoreController(@Autowired var scoreService : ScoreService) {
    @GetMapping("/scores")
    fun getAll() = scoreService.getAllScore()

}