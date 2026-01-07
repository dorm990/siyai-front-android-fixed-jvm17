package com.example.core.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [UserEntity::class, FavoriteEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun favoritesDao(): FavoritesDao
}
