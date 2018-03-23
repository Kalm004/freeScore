package com.kalm004.freeScore.user

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(@Autowired var userService : UserService) {

    @GetMapping("/getAll")
    fun getAll() = userService.getAllUsers()

}