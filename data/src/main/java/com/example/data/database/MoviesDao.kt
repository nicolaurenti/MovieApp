package com.example.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.database.entity.MoviesEntity
@Dao
interface MoviesDao {
    @Query("SELECT * FROM movies")
    fun getMovies(): List<MoviesEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveMovies(movie: MoviesEntity)
}
