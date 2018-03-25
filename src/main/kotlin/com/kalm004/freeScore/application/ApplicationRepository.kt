package com.kalm004.freeScore.application

import com.kalm004.freeScore.BaseRepository
import com.kalm004.persistence.Tables.APPLICATION
import org.jooq.exception.NoDataFoundException
import org.springframework.stereotype.Repository

@Repository
class ApplicationRepository : BaseRepository() {
    fun getAll() : Set<Application> =
            createQuery()
                    .select()
                    .from(APPLICATION)
                    .fetchInto(Application::class.java).toSet()

    fun getById(id : Int) : Application? =
        try {
            createQuery()
                    .select()
                    .from(APPLICATION)
                    .where(APPLICATION.ID.eq(id))
                    .fetchSingleInto(Application::class.java)
        } catch (e: NoDataFoundException) {
            null
        }

}
