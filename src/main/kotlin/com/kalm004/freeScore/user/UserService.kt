package com.kalm004.freeScore.user

import com.kalm004.freeScore.roles.Role
import com.kalm004.freeScore.utils.encodePassword
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserService(@Autowired var userRepository: UserRepository) {
    fun getAllUsers() = userRepository.getAll()

    fun getByNameAndPassword(userName : String, password : String) =
        userRepository.getByNameAndHashedPassword(userName, encodePassword(password))

    fun createUser(userRegistrationData: UserRegistrationData) {
        userRepository.createUser(
                userRegistrationData.name,
                encodePassword(userRegistrationData.password),
                userRegistrationData.email,
                Role.GAME_DEVELOPER
        )
    }

    fun setAdminRoleToUser(userId: Int) =
            userRepository.updateUserRol(userId, Role.ADMIN)

    fun removeAdminRoleUser(userId: Int) =
            userRepository.updateUserRol(userId, Role.GAME_DEVELOPER)
}