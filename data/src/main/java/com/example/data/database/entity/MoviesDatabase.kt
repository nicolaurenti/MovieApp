package com.example.data.database.entity

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.database.MoviesDao

@Database(entities = [MoviesEntity::class], version = 1)
abstract class MoviesDatabase : RoomDatabase() {
    abstract fun moviesDao(): MoviesDao
}
