package com.example.appForTest.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.domain.entity.MovieItem
import com.example.domain.usecase.GetMoviesUseCase
import com.example.domain.utils.CoroutineResult
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class MoviesViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = StandardTestDispatcher()
    private lateinit var getMoviesUseCase: GetMoviesUseCase
    private lateinit var viewModel: MoviesViewModel
    private val movies =
        listOf(
            MovieItem(id = "1", title = "Movie 1"),
            MovieItem(id = "2", title = "Movie 2"),
        )
    private val successResult = CoroutineResult.Success(movies)
    private val failureResult = CoroutineResult.Failure(Exception("Network error"))

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        getMoviesUseCase = mockk()
        viewModel = MoviesViewModel(getMoviesUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `getMostPopularMovies returns success`() {
        runTest(UnconfinedTestDispatcher()) {
            val listOfEmittedResult = mutableListOf<MoviesData>()
            val job =
                viewModel.moviesState.onEach(
                    listOfEmittedResult::add,
                ).launchIn(CoroutineScope(UnconfinedTestDispatcher(testScheduler)))
            coEvery { getMoviesUseCase.getMostPopularMovies() } returns successResult
            viewModel.getMostPopularMovies().join()
            assertEquals(MoviesState.LOADING, listOfEmittedResult[0].state)
            assertEquals(MoviesState.SHOW_DATA, listOfEmittedResult[1].state)
            assertEquals(movies, listOfEmittedResult[1].movies)
            job.cancel()
        }
    }

    @Test
    fun `getMostPopularMovies returns failure`() {
        runTest(UnconfinedTestDispatcher()) {
            val listOfEmittedResult = mutableListOf<MoviesData>()
            val job =
                viewModel.moviesState.onEach(
                    listOfEmittedResult::add,
                ).launchIn(CoroutineScope(UnconfinedTestDispatcher(testScheduler)))
            coEvery { getMoviesUseCase.getMostPopularMovies() } returns failureResult
            viewModel.getMostPopularMovies().join()
            assertEquals(MoviesState.LOADING, listOfEmittedResult[0].state)
            assertEquals(MoviesState.CONNECTION_ERROR, listOfEmittedResult[1].state)
            job.cancel()
        }
    }

    @Test
    fun `getTopRatedMovies returns success`() {
        runTest(UnconfinedTestDispatcher()) {
            val listOfEmittedResult = mutableListOf<MoviesData>()
            val job =
                viewModel.moviesState.onEach(
                    listOfEmittedResult::add,
                ).launchIn(CoroutineScope(UnconfinedTestDispatcher(testScheduler)))
            coEvery { getMoviesUseCase.getTopRatedMovies() } returns successResult
            viewModel.getTopRatedMovies().join()
            assertEquals(MoviesState.LOADING, listOfEmittedResult[0].state)
            assertEquals(MoviesState.SHOW_DATA, listOfEmittedResult[1].state)
            assertEquals(movies, listOfEmittedResult[1].movies)
            job.cancel()
        }
    }

    @Test
    fun `getTopRatedMovies returns failure`() {
        runTest(UnconfinedTestDispatcher()) {
            val listOfEmittedResult = mutableListOf<MoviesData>()
            val job =
                viewModel.moviesState.onEach(
                    listOfEmittedResult::add,
                ).launchIn(CoroutineScope(UnconfinedTestDispatcher(testScheduler)))
            coEvery { getMoviesUseCase.getTopRatedMovies() } returns failureResult
            viewModel.getTopRatedMovies().join()
            assertEquals(MoviesState.LOADING, listOfEmittedResult[0].state)
            assertEquals(MoviesState.CONNECTION_ERROR, listOfEmittedResult[1].state)
            job.cancel()
        }
    }

    @Test
    fun `getBestRecommendationsMovies returns success`() {
        runTest(UnconfinedTestDispatcher()) {
            val listOfEmittedResult = mutableListOf<MoviesData>()
            val job =
                viewModel.moviesState.onEach(
                    listOfEmittedResult::add,
                ).launchIn(CoroutineScope(UnconfinedTestDispatcher(testScheduler)))
            coEvery { getMoviesUseCase.getBestRecommendationsMovies() } returns successResult
            viewModel.getBestRecommendationsMovies().join()
            assertEquals(MoviesState.LOADING, listOfEmittedResult[0].state)
            assertEquals(MoviesState.SHOW_DATA, listOfEmittedResult[1].state)
            assertEquals(movies, listOfEmittedResult[1].movies)
            job.cancel()
        }
    }

    @Test
    fun `getBestRecommendationsMovies returns failure`() {
        runTest(UnconfinedTestDispatcher()) {
            val listOfEmittedResult = mutableListOf<MoviesData>()
            val job =
                viewModel.moviesState.onEach(
                    listOfEmittedResult::add,
                ).launchIn(CoroutineScope(UnconfinedTestDispatcher(testScheduler)))
            coEvery { getMoviesUseCase.getBestRecommendationsMovies() } returns failureResult
            viewModel.getBestRecommendationsMovies().join()
            assertEquals(MoviesState.LOADING, listOfEmittedResult[0].state)
            assertEquals(MoviesState.CONNECTION_ERROR, listOfEmittedResult[1].state)
            job.cancel()
        }
    }
}
