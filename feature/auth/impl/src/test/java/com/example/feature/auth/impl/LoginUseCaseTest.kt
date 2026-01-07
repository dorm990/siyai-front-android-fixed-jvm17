package com.example.feature.auth.impl

import com.example.core.db.UserDao
import com.example.core.db.UserEntity
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class LoginUseCaseTest {
    private val dao = mockk<UserDao>(relaxed = true)
    private val uc = LoginUseCase(dao)

    @Test fun login_ok() = runTest {
        coEvery { dao.getByLogin("a") } returns UserEntity("a", "1")
        assertTrue(uc.login("a", "1"))
    }

    @Test fun login_fail() = runTest {
        coEvery { dao.getByLogin("a") } returns UserEntity("a", "1")
        assertFalse(uc.login("a", "2"))
    }
}
