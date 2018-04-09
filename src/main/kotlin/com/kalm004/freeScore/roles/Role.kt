package com.kalm004.freeScore.roles

enum class Role {
    ADMIN,
    GAME_DEVELOPER,
    PLAYER;

    val fullName = "ROLE_$name"
}

