package com.kalm004.freeScore.user

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserService(@Autowired
                  var userRepository: UserRepository) {
    fun getAllUsers() = userRepository.getAll()
}