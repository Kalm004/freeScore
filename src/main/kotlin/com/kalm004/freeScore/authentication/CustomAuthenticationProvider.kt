package com.kalm004.freeScore.authentication

import com.kalm004.freeScore.exceptions.EntityNotFoundException
import com.kalm004.freeScore.user.User
import com.kalm004.freeScore.user.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.authority.SimpleGrantedAuthority
import java.util.*

class CustomAuthenticationProvider(@Autowired private val userService: UserService) : AuthenticationProvider {
    override fun authenticate(authentication: Authentication?): Authentication {
        if (authentication == null || authentication.principal == null || authentication.credentials == null) {
            throw BadCredentialsException("Incorrect user and password combination")
        }
        val user : User
        try {
            user = userService.getByNameAndPassword(
                    authentication.principal.toString(),
                    authentication.credentials.toString())
            val authorities = Collections.singleton(SimpleGrantedAuthority("ROLE_${user.role}"))
            return UsernamePasswordAuthenticationToken(user, authentication.credentials.toString(), authorities)
        } catch (e : EntityNotFoundException) {
            throw BadCredentialsException("Incorrect user and password combination")
        }
    }

    override fun supports(authentication: Class<*>?): Boolean {
        return true
    }
}