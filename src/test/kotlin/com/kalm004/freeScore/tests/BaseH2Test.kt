package com.kalm004.freeScore.tests

import com.kalm004.persistence.Tables
import org.jooq.DSLContext
import org.jooq.impl.DSL
import org.jooq.impl.DSL.constraint
import org.jooq.util.h2.H2DataType
import org.junit.After
import org.junit.Before
import java.util.*

abstract class BaseH2Test {
    @Before
    fun createH2Database() {
        val context = createQuery()

        context.createTableIfNotExists(Tables.USER)
                .column(Tables.USER.ID, H2DataType.INTEGER.nullable(false).identity(true))
                .column(Tables.USER.NAME)
                .column(Tables.USER.HASHEDPASSWORD)
                .column(Tables.USER.EMAIL)
                .execute()

        context.createTableIfNotExists(Tables.GAME)
                .column(Tables.GAME.ID, H2DataType.INTEGER.nullable(false).identity(true))
                .column(Tables.GAME.NAME)
                .column(Tables.GAME.USERID)
                .execute()

        context.createTableIfNotExists(Tables.SCORE)
                .column(Tables.SCORE.ID, H2DataType.INTEGER.nullable(false).identity(true))
                .column(Tables.SCORE.GAMEID)
                .column(Tables.SCORE.PLAYERID)
                .column(Tables.SCORE.VALUE)
                .execute()

        context.createTableIfNotExists(Tables.PLAYER)
                .column(Tables.PLAYER.ID, H2DataType.INTEGER.nullable(false).identity(true))
                .column(Tables.PLAYER.NAME)
                .execute()

        context.alterTable(Tables.SCORE).add(
                constraint("FK_GAME_ID")
                        .foreignKey(Tables.SCORE.GAMEID)
                        .references(Tables.GAME, Tables.GAME.ID))
                        .execute()

        context.alterTable(Tables.SCORE).add(
                constraint("FK_PLAYER_ID")
                        .foreignKey(Tables.SCORE.PLAYERID)
                        .references(Tables.PLAYER, Tables.PLAYER.ID))
                .execute()
    }

    @After
    fun destroyH2Database() {
        val context = createQuery()
        context.dropTableIfExists(Tables.USER).execute()
        context.dropTableIfExists(Tables.GAME).execute()
        context.dropTableIfExists(Tables.SCORE).execute()
        context.dropTableIfExists(Tables.PLAYER).execute()
    }

    fun createQuery() : DSLContext {
        val properties = Properties()
        properties.load(Properties::class.java.getResourceAsStream("/config.properties"))

        return DSL.using(
                properties.getProperty("db.url"),
                properties.getProperty("db.username"),
                properties.getProperty("db.password"))

    }
}