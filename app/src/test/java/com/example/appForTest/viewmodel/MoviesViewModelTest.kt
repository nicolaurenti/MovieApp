package com.example.appForTest.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.domain.entity.MovieItem
import com.example.domain.usecase.GetMoviesUseCase
import com.example.domain.utils.CoroutineResult
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.slot
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
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
    private val movies = listOf(MovieItem("1", "Movie 1"), MovieItem("2", "Movie 2"))


    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        getMoviesUseCase = mockk()
        viewModel = MoviesViewModel(getMoviesUseCase)
    }

    @Test
    fun `getMostPopularMovies returns success`() = runTest {
        val result = CoroutineResult.Success(movies)
        coEvery { getMoviesUseCase.getMostPopularMovies() } returns result

        val observer = mockk<Observer<MoviesData>>(relaxed = true)
        viewModel.state.observeForever(observer)

        viewModel.getMostPopularMovies()
        testDispatcher.scheduler.advanceUntilIdle()

        val slot = slot<MoviesData>()
        coVerify { observer.onChanged(capture(slot)) }
        assertEquals(MoviesState.SHOW_DATA, slot.captured.state)
        assertEquals(movies, slot.captured.movies)
    }

    @Test
    fun `getMostPopularMovies returns failure`() = runTest {
        val result = CoroutineResult.Failure(Exception("Error"))
        coEvery { getMoviesUseCase.getMostPopularMovies() } returns result

        val observer = mockk<Observer<MoviesData>>(relaxed = true)
        viewModel.state.observeForever(observer)

        viewModel.getMostPopularMovies()
        testDispatcher.scheduler.advanceUntilIdle()

        val slot = slot<MoviesData>()
        coVerify { observer.onChanged(capture(slot)) }
        assertEquals(MoviesState.CONNECTION_ERROR, slot.captured.state)
        assertEquals(MoviesViewModel.MOST_POPULAR_FAILED, slot.captured.error)
    }

    @Test
    fun `getTopRatedMovies returns success`() = runTest {
        val result = CoroutineResult.Success(movies)
        coEvery { getMoviesUseCase.getTopRatedMovies() } returns result

        val observer = mockk<Observer<MoviesData>>(relaxed = true)
        viewModel.state.observeForever(observer)

        viewModel.getTopRatedMovies()
        testDispatcher.scheduler.advanceUntilIdle()

        val slot = slot<MoviesData>()
        coVerify { observer.onChanged(capture(slot)) }
        assertEquals(MoviesState.SHOW_DATA, slot.captured.state)
        assertEquals(movies, slot.captured.movies)
    }

    @Test
    fun `getTopRatedMovies returns failure`() = runTest {
        val result = CoroutineResult.Failure(Exception("Error"))
        coEvery { getMoviesUseCase.getTopRatedMovies() } returns result

        val observer = mockk<Observer<MoviesData>>(relaxed = true)
        viewModel.state.observeForever(observer)

        viewModel.getTopRatedMovies()
        testDispatcher.scheduler.advanceUntilIdle()

        val slot = slot<MoviesData>()
        coVerify { observer.onChanged(capture(slot)) }
        assertEquals(MoviesState.CONNECTION_ERROR, slot.captured.state)
        assertEquals(MoviesViewModel.TOP_RATED_FAILED, slot.captured.error)
    }

    @Test
    fun `getBestRecommendationsMovies returns success`() = runTest {
        val result = CoroutineResult.Success(movies)
        coEvery { getMoviesUseCase.getBestRecommendationsMovies() } returns result

        val observer = mockk<Observer<MoviesData>>(relaxed = true)
        viewModel.state.observeForever(observer)

        viewModel.getBestRecommendationsMovies()
        testDispatcher.scheduler.advanceUntilIdle()

        val slot = slot<MoviesData>()
        coVerify { observer.onChanged(capture(slot)) }
        assertEquals(MoviesState.SHOW_DATA, slot.captured.state)
        assertEquals(movies, slot.captured.movies)
    }

    @Test
    fun `getBestRecommendationsMovies returns failure`() = runTest {
        val result = CoroutineResult.Failure(Exception("Error"))
        coEvery { getMoviesUseCase.getBestRecommendationsMovies() } returns result

        val observer = mockk<Observer<MoviesData>>(relaxed = true)
        viewModel.state.observeForever(observer)

        viewModel.getBestRecommendationsMovies()
        testDispatcher.scheduler.advanceUntilIdle()

        val slot = slot<MoviesData>()
        coVerify { observer.onChanged(capture(slot)) }
        assertEquals(MoviesState.CONNECTION_ERROR, slot.captured.state)
        assertEquals(MoviesViewModel.BEST_RECOMMENDATIONS_FAILED, slot.captured.error)
    }
}