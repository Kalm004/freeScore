package com.kalm004.freeScore.user

import com.kalm004.freeScore.BaseRepository
import com.kalm004.freeScore.createQuery
import com.kalm004.persistence.Tables.USER
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class UserRepository : BaseRepository() {
    fun getAll() : Set<User> {
        val properties = Properties()
        properties.load(Properties::class.java.getResourceAsStream("/config.properties"))
        return createQuery().select().from(USER).fetchInto(User::class.java).toSet()
    }
}