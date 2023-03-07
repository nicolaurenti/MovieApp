package com.example.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class MoviesEntity(
    @PrimaryKey val id: String,
    val title: String?,
    val name: String?,
    val image: String,
    val score: Double,
    val year: String,
)
