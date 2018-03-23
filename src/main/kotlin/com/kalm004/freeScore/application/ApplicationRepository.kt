package com.kalm004.freeScore.application

import com.kalm004.freeScore.BaseRepository
import com.kalm004.persistence.Tables.APPLICATION
import org.springframework.stereotype.Repository

@Repository
class ApplicationRepository : BaseRepository() {
    fun getAll() : Set<Application> {
        return createQuery().select().from(APPLICATION).fetchInto(Application::class.java).toSet()
    }
}