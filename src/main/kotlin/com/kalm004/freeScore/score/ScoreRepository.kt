package com.kalm004.freeScore.score

import com.kalm004.freeScore.BaseRepository
import com.kalm004.persistence.Tables.SCORE
import org.springframework.stereotype.Repository

@Repository
class ScoreRepository : BaseRepository() {
    fun getAll() : Set<Score> {
        return createQuery().select().from(SCORE).fetchInto(Score::class.java).toSet()
    }
}