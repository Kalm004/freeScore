package com.kalm004.freeScore.tests.unitTests.player

import com.kalm004.freeScore.player.Player
import com.kalm004.freeScore.player.PlayerRepository
import com.kalm004.freeScore.tests.BaseH2Test
import com.kalm004.persistence.Tables
import org.jooq.DSLContext
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import kotlin.test.assertEquals

@RunWith(SpringRunner::class)
@SpringBootTest
class PlayerRepositoryTests : BaseH2Test() {
    @Autowired
    lateinit var playerRepository: PlayerRepository

    @Before
    fun beforeTests() {
        val context : DSLContext = createQuery()

        context.insertInto(Tables.GAME)
                .columns(Tables.GAME.NAME, Tables.GAME.USERID, Tables.GAME.KEY)
                .values("APP_PEPE", 1, "KEY")
                .execute()

        context.insertInto(Tables.PLAYER)
                .columns(Tables.PLAYER.NAME, Tables.PLAYER.GAMEID)
                .values("Player1", 1)
                .execute()
    }

    @Test
    fun testGetPlayerById() =
            assertEquals(
                    Player(1, "Player1", 1),
                    playerRepository.getById(1)
            )
}