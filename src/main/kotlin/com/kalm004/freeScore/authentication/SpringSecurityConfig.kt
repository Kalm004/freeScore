package com.kalm004.freeScore.authentication

import com.kalm004.freeScore.user.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.ProviderManager
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

@Configuration
class SpringSecurityConfig : WebSecurityConfigurerAdapter(false) {
    @Autowired
    lateinit var userService : UserService

    //We want to use our own Custom Authentication Provider
    override fun authenticationManager(): AuthenticationManager {
        return ProviderManager(listOf(CustomAuthenticationProvider(userService)))
    }

    override fun configure(http: HttpSecurity?) {
        http?.csrf()?.disable()?.authorizeRequests()?.
                antMatchers(HttpMethod.GET, "/users")?.hasRole("ADMIN")?.
                antMatchers(HttpMethod.GET, "/games")?.hasRole("USER")?.
                antMatchers(HttpMethod.POST, "/users")?.anonymous()?.
                anyRequest()?.authenticated()?.
                and()?.httpBasic()
    }
}