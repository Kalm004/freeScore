package com.kalm004.freeScore.user

import org.springframework.stereotype.Repository

@Repository
class UserRepository {
    fun getAll() : Set<User> {
        return setOf(User(1, "Test"))
    }
}