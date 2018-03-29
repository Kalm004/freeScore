package com.kalm004.freeScore.player

import com.kalm004.freeScore.BaseRepository
import com.kalm004.freeScore.user.User
import com.kalm004.persistence.Tables
import org.jooq.exception.NoDataFoundException
import org.springframework.stereotype.Repository

@Repository
class PlayerRepository : BaseRepository() {
    fun getById(id : Int) : User? =
            try {
                createQuery()
                        .select()
                        .from(Tables.USER)
                        .where(Tables.USER.ID.eq(id))
                        .fetchSingleInto(User::class.java)
            } catch (e: NoDataFoundException) {
                null
            }
}

