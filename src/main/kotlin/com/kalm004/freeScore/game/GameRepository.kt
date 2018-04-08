package com.kalm004.freeScore.game

import com.kalm004.freeScore.common.BaseRepository
import com.kalm004.freeScore.exceptions.EntityNotFoundException
import com.kalm004.persistence.Tables.GAME
import org.jooq.exception.NoDataFoundException
import org.springframework.stereotype.Repository
@Repository
class GameRepository : BaseRepository() {
    fun getAll() : Set<Game> =
            createQuery()
                    .select()
                    .from(GAME)
                    .fetchInto(Game::class.java).toSet()

    fun getById(id : Int) : Game? =
        try {
            createQuery()
                    .select()
                    .from(GAME)
                    .where(GAME.ID.eq(id))
                    .fetchSingleInto(Game::class.java)
        } catch (e: NoDataFoundException) {
            null
        }

    fun getByKey(gameKey : String) : Game =
            try {
                createQuery()
                        .select()
                        .from(GAME)
                        .where(GAME.KEY.eq(gameKey))
                        .fetchSingleInto(Game::class.java)
            } catch (e : NoDataFoundException) {
                throw EntityNotFoundException(GAME.name)
            }

    fun getByNameAndUserId(name: String, userId: Int) =
            createQuery()
                    .select()
                    .from(GAME)
                    .where(GAME.NAME.eq(name)).and(GAME.USERID.eq(userId))
                    .fetchSingleInto(GAME::class.java)
}

