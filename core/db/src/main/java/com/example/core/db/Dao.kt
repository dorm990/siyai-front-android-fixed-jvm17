package com.example.core.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(user: UserEntity)

    @Query("SELECT * FROM users WHERE login = :login LIMIT 1")
    suspend fun getByLogin(login: String): UserEntity?
}

@Dao
interface FavoritesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun add(fav: FavoriteEntity)

    @Query("DELETE FROM favorites WHERE productId = :id")
    suspend fun remove(id: Int)

    @Query("SELECT productId FROM favorites")
    suspend fun getAllIds(): List<Int>

    @Query("SELECT EXISTS(SELECT 1 FROM favorites WHERE productId = :id)")
    suspend fun isFavorite(id: Int): Boolean
}
