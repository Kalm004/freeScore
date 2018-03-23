package com.kalm004.freeScore.score

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ScoreService(@Autowired var scoreRepository: ScoreRepository) {
    fun getAllScore() = scoreRepository.getAll()
}