package com.kalm004.freeScore.score

import com.kalm004.freeScore.common.BaseRepository
import com.kalm004.persistence.Tables.SCORE
import org.springframework.stereotype.Repository

@Repository
class ScoreRepository : BaseRepository() {
    fun getAll() : Set<Score> {
        return createQuery().select().from(SCORE).fetchInto(Score::class.java).toSet()
    }

    fun saveScore(gameId : Int, playerId : Int, value : Long) {
        createQuery()
                .insertInto(SCORE)
                .columns(SCORE.GAMEID, SCORE.PLAYERID, SCORE.VALUE)
                .values(gameId, playerId, value)
                .execute()
    }
}