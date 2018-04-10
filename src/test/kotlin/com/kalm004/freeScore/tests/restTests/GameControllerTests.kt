package com.kalm004.freeScore.tests.restTests

import com.kalm004.freeScore.tests.BaseH2Test
import com.kalm004.freeScore.user.UserRegistrationData
import com.kalm004.freeScore.user.UserService
import io.restassured.RestAssured
import io.restassured.RestAssured.get
import io.restassured.RestAssured.given
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.embedded.LocalServerPort
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import java.util.*


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner::class)
class GameControllerTests : BaseH2Test() {
    @LocalServerPort
    private val port: Int = 0

    @Autowired
    private lateinit var userService : UserService

    @Before
    fun beforeTests() {
        RestAssured.useRelaxedHTTPSValidation("SSL")
    }

    @Test
    fun getAllGamesRestUnauthorizedTest() {
        get("https://localhost:$port/games").then().statusCode(401)
    }

    @Test
    fun getAllGamesRestOkTest() {
        val base64userPassword = Base64.getEncoder().encodeToString("user1:password".toByteArray())
        userService.createUser(UserRegistrationData("user1", "password", "user@server.com"))

        //GET /games with basic authentication
        given().
                header("Authorization", "Basic $base64userPassword").
        `when`().
                get("https://localhost:$port/users/1/games").
        then().
                statusCode(200)
    }

    @Test
    fun getAllGamesRestForbiddenTest() {
        val base64userPassword = Base64.getEncoder().encodeToString("user1:password".toByteArray())
        userService.createUser(UserRegistrationData("user1", "password", "user@server.com"))

        //GET /games with basic authentication
        given().
                header("Authorization", "Basic $base64userPassword").
        `when`().
                get("https://localhost:$port/users/2/games").
        then().
                statusCode(403)
    }

}