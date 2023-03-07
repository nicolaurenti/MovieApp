package com.example.data.service

import com.example.data.mapper.mapToLocalMovieList
import com.example.domain.entity.MovieItem
import com.example.domain.service.MoviesService
import com.example.domain.utils.CoroutineResult
import javax.inject.Inject

class MoviesServiceImpl @Inject constructor(
    private val moviesApi: MoviesApi,
) : MoviesService {

    override fun getMostPopularMovies(): CoroutineResult<List<MovieItem>> {
        try {
            val callResponse = moviesApi.getMostPopularMovies()
            val response = callResponse.execute()
            if (response.isSuccessful) {
                response.body()?.let {
                    val movieList = mutableListOf<MovieItem>()
                    it.result.forEach {
                        movieList.add(it.mapToLocalMovieList())
                    }
                    return CoroutineResult.Success(movieList)
                }
            }
        } catch (exception: Exception) {
            return CoroutineResult.Failure(Exception())
        }
        return CoroutineResult.Failure(Exception())
    }

    override fun getTopRatedMovies(): CoroutineResult<List<MovieItem>> {
        try {
            val callResponse = moviesApi.getBestRatedMovies()
            val response = callResponse.execute()
            if (response.isSuccessful) {
                response.body()?.let {
                    val movieList = mutableListOf<MovieItem>()
                    it.result.forEach {
                        movieList.add(it.mapToLocalMovieList())
                    }
                    return CoroutineResult.Success(movieList)
                }
            }
        } catch (exception: Exception) {
            return CoroutineResult.Failure(Exception())
        }
        return CoroutineResult.Failure(Exception())
    }

    override fun getBestRecommendationsMovies(): CoroutineResult<List<MovieItem>> {
        try {
            val callResponse = moviesApi.getBestRecommendationsMovies()
            val response = callResponse.execute()
            if (response.isSuccessful) {
                response.body()?.let {
                    val movieList = mutableListOf<MovieItem>()
                    it.result.forEach {
                        movieList.add(it.mapToLocalMovieList())
                    }
                    return CoroutineResult.Success(movieList)
                }
            }
        } catch (exception: Exception) {
            return CoroutineResult.Failure(Exception())
        }
        return CoroutineResult.Failure(Exception())
    }
}
