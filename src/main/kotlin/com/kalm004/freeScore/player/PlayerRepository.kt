package com.kalm004.freeScore.player

import com.kalm004.freeScore.common.BaseRepository
import com.kalm004.freeScore.exceptions.EntityNotFoundException
import com.kalm004.persistence.Tables
import org.jooq.exception.NoDataFoundException
import org.springframework.stereotype.Repository

@Repository
class PlayerRepository : BaseRepository() {
    fun getById(id : Int) : Player? =
            try {
                createQuery()
                        .select()
                        .from(Tables.PLAYER)
                        .where(Tables.PLAYER.ID.eq(id))
                        .fetchSingleInto(Player::class.java)
            } catch (e: NoDataFoundException) {
                null
            }

    fun getByNameAndGameId(playerName: String, gameId: Int) : Player =
            try {
                createQuery()
                        .select()
                        .from(Tables.PLAYER)
                        .where(
                                Tables.PLAYER.NAME.eq(playerName)
                                .and(Tables.PLAYER.GAMEID.eq(gameId))
                        )
                        .fetchSingleInto(Player::class.java)
            } catch (e: NoDataFoundException) {
                throw EntityNotFoundException(Tables.PLAYER.name)
            }
}

