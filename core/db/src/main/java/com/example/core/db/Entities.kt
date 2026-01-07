package com.example.core.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey val login: String,
    val password: String
)

@Entity(tableName = "favorites")
data class FavoriteEntity(
    @PrimaryKey val productId: Int
)
