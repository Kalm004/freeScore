package com.kalm004.freeScore.user

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.annotation.Secured
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(@Autowired var userService : UserService) {

    @GetMapping("/users")
    fun getAll() = userService.getAllUsers()

    @PostMapping("/users")
    fun createUser(@RequestBody userRegistrationData : UserRegistrationData) =
            userService.createUser(userRegistrationData)
}