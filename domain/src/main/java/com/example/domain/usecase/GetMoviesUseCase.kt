package com.example.domain.usecase

import com.example.domain.database.MoviesRepository
import com.example.domain.entity.MovieItem
import com.example.domain.service.MoviesService
import com.example.domain.utils.CoroutineResult
import javax.inject.Inject

interface GetMoviesUseCase {
    fun getMostPopularMovies(): CoroutineResult<List<MovieItem>>
    fun getTopRatedMovies(): CoroutineResult<List<MovieItem>>
    fun getBestRecommendationsMovies(): CoroutineResult<List<MovieItem>>
}

class GetMoviesUseCaseImpl @Inject constructor(
    private val moviesService: MoviesService,
    private val moviesRepository: MoviesRepository,
) : GetMoviesUseCase {

    override fun getMostPopularMovies(): CoroutineResult<List<MovieItem>> {
        return when (val serviceResult = moviesService.getMostPopularMovies()) {
            is CoroutineResult.Success -> {
                moviesRepository.saveMovies(serviceResult.data)
                moviesRepository.getMovies()
            }
            is CoroutineResult.Failure -> {
                moviesRepository.getMovies()
            }
        }
    }

    override fun getTopRatedMovies(): CoroutineResult<List<MovieItem>> {
        return when (val serviceResult = moviesService.getTopRatedMovies()) {
            is CoroutineResult.Success -> {
                moviesRepository.saveMovies(serviceResult.data)
                moviesRepository.getMovies()
            }
            is CoroutineResult.Failure -> {
                moviesRepository.getMovies()
            }
        }
    }

    override fun getBestRecommendationsMovies(): CoroutineResult<List<MovieItem>> {
        return when (val serviceResult = moviesService.getBestRecommendationsMovies()) {
            is CoroutineResult.Success -> {
                moviesRepository.saveMovies(serviceResult.data)
                moviesRepository.getMovies()
            }
            is CoroutineResult.Failure -> {
                moviesRepository.getMovies()
            }
        }
    }
}
