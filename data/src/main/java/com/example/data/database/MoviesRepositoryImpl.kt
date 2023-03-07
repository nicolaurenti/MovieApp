package com.example.data.database

import com.example.data.mapper.mapToDatabaseMovie
import com.example.data.mapper.mapToMoviesList
import com.example.domain.database.MoviesRepository
import com.example.domain.entity.MovieItem
import com.example.domain.utils.CoroutineResult
import java.lang.Exception

class MoviesRepositoryImpl(private val moviesDao: MoviesDao) : MoviesRepository {
    override fun getMovies(): CoroutineResult<List<MovieItem>> =
        moviesDao.getMovies().let {
            if (it.isNotEmpty()) {
                CoroutineResult.Success(it.mapToMoviesList())
            } else {
                CoroutineResult.Failure(Exception())
            }
        }

    override fun saveMovies(moviesList: List<MovieItem>) {
        moviesList.forEach {
            moviesDao.saveMovies(it.mapToDatabaseMovie())
        }
    }
}
