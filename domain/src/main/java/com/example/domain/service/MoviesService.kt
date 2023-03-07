package com.example.domain.service

import com.example.domain.entity.MovieItem
import com.example.domain.utils.CoroutineResult

interface MoviesService {
    fun getMostPopularMovies(): CoroutineResult<List<MovieItem>>
    fun getTopRatedMovies(): CoroutineResult<List<MovieItem>>
    fun getBestRecommendationsMovies(): CoroutineResult<List<MovieItem>>
}
