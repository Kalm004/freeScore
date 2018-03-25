package com.kalm004.freeScore.score

import com.kalm004.freeScore.application.ApplicationRepository
import com.kalm004.freeScore.exceptions.EntityNotFoundException
import com.kalm004.freeScore.user.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ScoreService(@Autowired val scoreRepository: ScoreRepository,
                   @Autowired val applicationRepository: ApplicationRepository,
                   @Autowired val userRepository : UserRepository) {
    fun getAllScore() = scoreRepository.getAll()

    fun saveScore(appId : Int, userId : Int, value : Long) {
        applicationRepository.getById(appId) ?: throw EntityNotFoundException("Application")
        userRepository.getById(userId) ?: throw EntityNotFoundException("User")
        scoreRepository.saveScore(appId, userId, value)
    }
}