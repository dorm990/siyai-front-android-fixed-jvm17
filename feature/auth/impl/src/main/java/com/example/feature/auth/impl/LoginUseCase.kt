package com.example.feature.auth.impl

import com.example.core.db.UserDao
import com.example.core.db.UserEntity
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val userDao: UserDao
) {
    suspend fun register(login: String, password: String) {
        userDao.upsert(UserEntity(login, password))
    }

    suspend fun login(login: String, password: String): Boolean {
        val u = userDao.getByLogin(login) ?: return false
        return u.password == password
    }
}
