package com.kalm004.freeScore.utils

import org.mindrot.jbcrypt.BCrypt
import java.util.*


fun encodePassword(password: String) : String {
    val properties = Properties()
    properties.load(Properties::class.java.getResourceAsStream("/config.properties"))
    return BCrypt.hashpw(password, properties.getProperty("salt"))
}

