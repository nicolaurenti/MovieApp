package com.example.domain.database

import com.example.domain.entity.MovieItem
import com.example.domain.utils.CoroutineResult

interface MoviesRepository {
    fun getMovies(): CoroutineResult<List<MovieItem>>
    fun saveMovies(moviesList: List<MovieItem>)
}
