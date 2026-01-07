package com.example.feature.favorites.impl

import com.example.core.db.FavoriteEntity
import com.example.core.db.FavoritesDao
import javax.inject.Inject

class ToggleFavoriteUseCase @Inject constructor(
    private val dao: FavoritesDao
) {
    suspend fun execute(id: Int) {
        if (dao.isFavorite(id)) dao.remove(id) else dao.add(FavoriteEntity(id))
    }
}
