package com.example.domain.usecase

import com.example.domain.database.MoviesRepository
import com.example.domain.entity.MovieItem
import com.example.domain.service.MoviesService
import com.example.domain.utils.CoroutineResult
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class GetMoviesUseCaseTest {
    private lateinit var getMoviesUseCase: GetMoviesUseCase
    private val moviesService: MoviesService = mockk()
    private val moviesRepository: MoviesRepository = mockk()
    private val movies = listOf(MovieItem(id = "1", title = "Test Movie"))

    @Before
    fun setUp() {
        getMoviesUseCase = GetMoviesUseCaseImpl(moviesService, moviesRepository)
    }

    @Test
    fun `getMostPopularMovies returns success`() =
        runTest {
            val successResult = CoroutineResult.Success(movies)
            coEvery { moviesService.getMostPopularMovies() } returns successResult
            coEvery { moviesRepository.saveMovies(any()) } returns Unit
            coEvery { moviesRepository.getMovies() } returns successResult

            val result = getMoviesUseCase.getMostPopularMovies()

            assertEquals(successResult, result)
            verify { moviesRepository.saveMovies(movies) }
            verify { moviesRepository.getMovies() }
        }

    @Test
    fun `getMostPopularMovies returns failure`() =
        runTest {
            val failureResult = CoroutineResult.Failure(Exception("Error"))
            coEvery { moviesService.getMostPopularMovies() } returns failureResult
            coEvery { moviesRepository.getMovies() } returns failureResult

            val result = getMoviesUseCase.getMostPopularMovies()

            assertEquals(failureResult, result)
            verify { moviesRepository.getMovies() }
        }

    @Test
    fun `getTopRatedMovies returns success`() =
        runTest {
            val successResult = CoroutineResult.Success(movies)
            coEvery { moviesService.getTopRatedMovies() } returns successResult
            coEvery { moviesRepository.saveMovies(any()) } returns Unit
            coEvery { moviesRepository.getMovies() } returns successResult

            val result = getMoviesUseCase.getTopRatedMovies()

            assertEquals(successResult, result)
            verify { moviesRepository.saveMovies(movies) }
            verify { moviesRepository.getMovies() }
        }

    @Test
    fun `getTopRatedMovies returns failure`() =
        runTest {
            val failureResult = CoroutineResult.Failure(Exception("Error"))
            coEvery { moviesService.getTopRatedMovies() } returns failureResult
            coEvery { moviesRepository.getMovies() } returns failureResult

            val result = getMoviesUseCase.getTopRatedMovies()

            assertEquals(failureResult, result)
            verify { moviesRepository.getMovies() }
        }

    @Test
    fun `getBestRecommendationsMovies returns success`() =
        runTest {
            val successResult = CoroutineResult.Success(movies)
            coEvery { moviesService.getBestRecommendationsMovies() } returns successResult
            coEvery { moviesRepository.saveMovies(any()) } returns Unit
            coEvery { moviesRepository.getMovies() } returns successResult

            val result = getMoviesUseCase.getBestRecommendationsMovies()

            assertEquals(successResult, result)
            verify { moviesRepository.saveMovies(movies) }
            verify { moviesRepository.getMovies() }
        }

    @Test
    fun `getBestRecommendationsMovies returns failure`() =
        runTest {
            val failureResult = CoroutineResult.Failure(Exception("Error"))
            coEvery { moviesService.getBestRecommendationsMovies() } returns failureResult
            coEvery { moviesRepository.getMovies() } returns failureResult

            val result = getMoviesUseCase.getBestRecommendationsMovies()

            assertEquals(failureResult, result)
            verify { moviesRepository.getMovies() }
        }
}
