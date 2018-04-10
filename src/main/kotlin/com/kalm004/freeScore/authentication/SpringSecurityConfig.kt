package com.kalm004.freeScore.authentication

import com.kalm004.freeScore.roles.Role
import com.kalm004.freeScore.user.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.ProviderManager
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

@Configuration
//@EnableGlobalMethodSecurity(securedEnabled = true)
class SpringSecurityConfig : WebSecurityConfigurerAdapter(false) {
    @Autowired
    lateinit var userService : UserService

    //We want to use our own Custom Authentication Provider
    override fun authenticationManager(): AuthenticationManager {
        return ProviderManager(listOf(CustomAuthenticationProvider(userService)))
    }

    override fun configure(http: HttpSecurity?) {
        http?.csrf()?.disable()?.authorizeRequests()?.
                //Users operations
                antMatchers(HttpMethod.GET, "/users")?.hasRole(Role.ADMIN.name)?.
                antMatchers(HttpMethod.POST, "/users/{\\d+}/activate")?.hasRole(Role.ADMIN.name)?.
                antMatchers(HttpMethod.DELETE, "/users/{\\d+}/activate")?.hasRole(Role.ADMIN.name)?.
                antMatchers(HttpMethod.POST, "/users/{\\d+}/admin")?.hasRole(Role.ADMIN.name)?.
                antMatchers(HttpMethod.DELETE, "/users/{\\d+}/admin")?.hasRole(Role.ADMIN.name)?.
                antMatchers(HttpMethod.POST, "/users")?.anonymous()?.

                //Games operations
                antMatchers(HttpMethod.GET, "/users/{\\d+}/games")?.hasRole(Role.GAME_DEVELOPER.name)?.
                antMatchers(HttpMethod.POST, "/users/{\\d+}/games")?.hasRole(Role.GAME_DEVELOPER.name)?.
                antMatchers(HttpMethod.DELETE, "/users/{\\d+}/games/{\\d+}")?.hasRole(Role.GAME_DEVELOPER.name)?.
                antMatchers(HttpMethod.PUT, "/users/{\\d+}/games/{\\d+}")?.hasRole(Role.GAME_DEVELOPER.name)?.
                antMatchers(HttpMethod.GET, "/users/{\\d+}/games/{\\d+}/scores")?.hasRole(Role.GAME_DEVELOPER.name)?.

                //Use HTTP basic authentication
                anyRequest()?.authenticated()?.and()?.httpBasic()
    }
}