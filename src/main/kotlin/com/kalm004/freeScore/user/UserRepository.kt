package com.kalm004.freeScore.user

import com.kalm004.freeScore.common.BaseRepository
import com.kalm004.freeScore.exceptions.EntityNotFoundException
import com.kalm004.freeScore.roles.Role
import com.kalm004.persistence.Tables.USER
import org.jooq.exception.NoDataFoundException
import org.springframework.stereotype.Repository

@Repository
class UserRepository : BaseRepository() {
    fun getAll() : Set<User> =
            createQuery()
                    .select()
                    .from(USER)
                    .fetchInto(User::class.java)
                    .toSet()

    fun getById(id : Int) : User? =
        try {
            createQuery()
                    .select()
                    .from(USER)
                    .where(USER.ID.eq(id))
                    .fetchSingleInto(User::class.java)
        } catch (e: NoDataFoundException) {
            null
        }

    fun getByEmail(email : String) : User? =
            try {
                createQuery()
                        .select()
                        .from(USER)
                        .where(USER.EMAIL.eq(email))
                        .fetchSingleInto(User::class.java)
            } catch (e: NoDataFoundException) {
                null
            }

    fun getByNameAndHashedPassword(name : String, hashedPassword: String) : User =
            try {
                createQuery()
                        .select()
                        .from(USER)
                        .where(
                            USER.NAME.eq(name)
                            .and(
                            USER.HASHEDPASSWORD.eq(hashedPassword))
                        )
                        .fetchSingleInto(User::class.java)
            } catch (e: NoDataFoundException) {
                throw EntityNotFoundException("USER")
            }

    fun createUser(name: String, hashedPassword: String, email: String, role: Role) =
        createQuery()
                .insertInto(USER)
                .columns(USER.NAME, USER.HASHEDPASSWORD, USER.EMAIL, USER.ROLE)
                .values(name, hashedPassword, email, role.name)
                .execute()

    fun updateUserRol(userId: Int, role: Role) =
            try {
                createQuery()
                        .update(USER)
                        .set(USER.ROLE, role.name)
                        .where(USER.ID.eq(userId))
                        .execute()
            } catch (e: NoDataFoundException) {
                throw EntityNotFoundException("USER")
            }
}