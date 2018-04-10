package com.kalm004.freeScore.user

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.annotation.Secured
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
class UserController(@Autowired var userService : UserService) {

    @GetMapping
    fun getAll() =
            userService.getAllUsers()

    @PostMapping("/{userId}/activate")
    fun activateUser(@PathVariable userId : Int) : String = TODO()

    @DeleteMapping("/{userId}/activate")
    fun deactivateUser(@PathVariable userId : Int) : String = TODO()

    @PostMapping
    fun createUser(@RequestBody userRegistrationData : UserRegistrationData) =
            userService.createUser(userRegistrationData)
}