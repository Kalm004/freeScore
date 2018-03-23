package com.kalm004.freeScore.tests

import com.kalm004.freeScore.application.Application
import com.kalm004.freeScore.application.ApplicationRepository
import com.kalm004.freeScore.application.ApplicationService
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.junit4.SpringRunner
import kotlin.test.assertEquals

@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApplicationTests {
    @Autowired
    lateinit var applicationService : ApplicationService

    @MockBean
    lateinit var applicationRepository: ApplicationRepository

    @Test
    fun testGetAllApplications() {
        given(applicationRepository.getAll()).willReturn(setOf(Application(1, "Test")))
        assertEquals(
                setOf(Application(1, "Test")),
                applicationService.getAllApplications(),
                "Get all users different result"
        )
    }
}