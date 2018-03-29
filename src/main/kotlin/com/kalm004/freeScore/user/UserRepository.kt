package com.kalm004.freeScore.user

import com.kalm004.freeScore.BaseRepository
import com.kalm004.persistence.Tables.USER
import org.jooq.exception.NoDataFoundException
import org.springframework.stereotype.Repository

@Repository
class UserRepository : BaseRepository() {
    fun getAll() : Set<User> =
            createQuery()
                    .select()
                    .from(USER)
                    .fetchInto(User::class.java)
                    .toSet()

    fun getById(id : Int) : User? =
        try {
            createQuery()
                    .select()
                    .from(USER)
                    .where(USER.ID.eq(id))
                    .fetchSingleInto(User::class.java)
        } catch (e: NoDataFoundException) {
            null
        }
}