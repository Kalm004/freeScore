package com.kalm004.freeScore.common

import org.jooq.DSLContext
import org.jooq.impl.DSL
import java.util.*

abstract class BaseRepository {
    fun createQuery(): DSLContext {
        val properties = Properties()
        properties.load(Properties::class.java.getResourceAsStream("/" +
                "config.properties"))
        return DSL.using(
                properties.getProperty("db.url"),
                properties.getProperty("db.username"),
                properties.getProperty("db.password")
        )
    }
}