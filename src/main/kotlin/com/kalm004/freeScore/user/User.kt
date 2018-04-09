package com.kalm004.freeScore.user

import com.kalm004.freeScore.roles.Role

data class User(val id : Int, val name : String, val hashedPassword : String, val email : String, val role: Role)