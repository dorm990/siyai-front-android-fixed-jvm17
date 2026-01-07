package com.example.feature.favorites.impl

import com.example.core.db.FavoritesDao
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test

class ToggleFavoriteUseCaseTest {
    private val dao = mockk<FavoritesDao>(relaxed = true)
    private val uc = ToggleFavoriteUseCase(dao)

    @Test fun toggles_add() = runTest {
        coEvery { dao.isFavorite(1) } returns false
        uc.execute(1)
        coVerify { dao.add(any()) }
    }
}
