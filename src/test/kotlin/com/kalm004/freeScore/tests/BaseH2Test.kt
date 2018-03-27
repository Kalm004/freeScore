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
                .execute()

        context.createTableIfNotExists(Tables.APPLICATION)
                .column(Tables.APPLICATION.ID, H2DataType.INTEGER.nullable(false).identity(true))
                .column(Tables.APPLICATION.NAME)
                .execute()

        context.createTableIfNotExists(Tables.SCORE)
                .column(Tables.SCORE.ID, H2DataType.INTEGER.nullable(false).identity(true))
                .column(Tables.SCORE.APPLICATIONID)
                .column(Tables.SCORE.USERID)
                .column(Tables.SCORE.VALUE)
                .execute()

        context.alterTable(Tables.SCORE).add(
                constraint("FK_APPLICATION_ID")
                        .foreignKey(Tables.SCORE.APPLICATIONID)
                        .references(Tables.APPLICATION, Tables.APPLICATION.ID))
                        .execute()

        context.alterTable(Tables.SCORE).add(
                constraint("FK_USER_ID")
                        .foreignKey(Tables.SCORE.USERID)
                        .references(Tables.USER, Tables.USER.ID))
                .execute()
    }

    @After
    fun destroyH2Database() {
        val context = createQuery()
        context.dropTableIfExists(Tables.USER).execute()
        context.dropTableIfExists(Tables.APPLICATION).execute()
        context.dropTableIfExists(Tables.SCORE).execute()
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