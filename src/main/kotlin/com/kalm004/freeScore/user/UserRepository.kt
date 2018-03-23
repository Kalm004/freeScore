package com.kalm004.freeScore.user

import com.kalm004.freeScore.BaseRepository
import com.kalm004.persistence.Tables.USER
import org.springframework.stereotype.Repository

@Repository
class UserRepository : BaseRepository() {
    fun getAll() : Set<User> {
        return createQuery().select().from(USER).fetchInto(User::class.java).toSet()
    }
}