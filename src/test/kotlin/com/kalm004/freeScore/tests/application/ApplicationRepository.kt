package com.kalm004.freeScore.tests.application

import com.kalm004.freeScore.application.Application
import com.kalm004.freeScore.application.ApplicationRepository
import com.kalm004.freeScore.tests.BaseH2Test
import com.kalm004.persistence.Tables.APPLICATION
import junit.framework.Assert.assertEquals
import org.jooq.DSLContext
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import kotlin.test.assertNotEquals

@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApplicationRepositoryTests : BaseH2Test() {
    @Autowired
    lateinit var applicationRepository: ApplicationRepository

    @Before
    fun beforeTests() {
        val context : DSLContext = createQuery()

        context.insertInto(APPLICATION)
                .columns(APPLICATION.NAME)
                .values("APP_PEPE")
                .execute()

        context.insertInto(APPLICATION)
                .columns(APPLICATION.NAME)
                .values("APP_MANOLO")
                .execute()
    }

    @Test
    fun testGetAllApplicationsOk() {
        assertEquals(setOf(Application(1, "APP_PEPE"), Application(2, "APP_MANOLO")), applicationRepository.getAll())
    }

    @Test
    fun testGetAllApplicationsNotOk() {
        assertNotEquals(setOf(Application(2, "APP_MANOLO")), applicationRepository.getAll())
    }

    @Test
    fun testGetApplicationByIdOk() {
        assertEquals(Application(1, "APP_PEPE"), applicationRepository.getById(1))
    }

    @Test
    fun testGetApplicationByIdNotOk() {
        assertEquals(null, applicationRepository.getById(3))
    }
}