package com.example.fitopenpay.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.MovieItem
import com.example.domain.usecase.GetMoviesUseCase
import com.example.domain.utils.CoroutineResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(private val getMoviesUseCase: GetMoviesUseCase) :
    ViewModel() {

    private val mutableStateLiveData: MutableLiveData<MoviesData> = MutableLiveData()
    val state: LiveData<MoviesData>
        get() = mutableStateLiveData

    fun getMostPopularMovies() = viewModelScope.launch {
        withContext(Dispatchers.IO) { getMoviesUseCase.getMostPopularMovies() }.let { result ->
            when (result) {
                is CoroutineResult.Success -> {
                    mutableStateLiveData.postValue(
                        MoviesData(
                            state = MoviesState.SHOW_DATA,
                            movies = result.data,
                        ),
                    )
                }
                is CoroutineResult.Failure -> {
                    mutableStateLiveData.postValue(
                        MoviesData(
                            state = MoviesState.CONNECTION_ERROR,
                            error = MOST_POPULAR_FAILED,
                        ),
                    )
                }
            }
        }
    }

    fun getTopRatedMovies() = viewModelScope.launch {
        withContext(Dispatchers.IO) { getMoviesUseCase.getTopRatedMovies() }.let { result ->
            when (result) {
                is CoroutineResult.Success -> {
                    mutableStateLiveData.postValue(
                        MoviesData(
                            state = MoviesState.SHOW_DATA,
                            movies = result.data,
                        ),
                    )
                }
                is CoroutineResult.Failure -> {
                    mutableStateLiveData.postValue(
                        MoviesData(
                            state = MoviesState.CONNECTION_ERROR,
                            error = TOP_RATED_FAILED,
                        ),
                    )
                }
            }
        }
    }

    fun getBestRecommendationsMovies() = viewModelScope.launch {
        withContext(Dispatchers.IO) { getMoviesUseCase.getBestRecommendationsMovies() }.let { result ->
            when (result) {
                is CoroutineResult.Success -> {
                    mutableStateLiveData.postValue(
                        MoviesData(
                            state = MoviesState.SHOW_DATA,
                            movies = result.data,
                        ),
                    )
                }
                is CoroutineResult.Failure -> {
                    mutableStateLiveData.postValue(
                        MoviesData(
                            state = MoviesState.CONNECTION_ERROR,
                            error = BEST_RECOMMENDATIONS_FAILED,
                        ),
                    )
                }
            }
        }
    }

    companion object {
        const val BEST_RECOMMENDATIONS_FAILED = "BEST_RECOMMENDATIONS_FAILED"
        const val TOP_RATED_FAILED = "TOP_RATED_FAILED"
        const val MOST_POPULAR_FAILED = "MOST_POPULAR_FAILED"
    }
}

data class MoviesData(
    val state: MoviesState,
    val movies: List<MovieItem> = listOf(),
    val error: String? = null,
)

enum class MoviesState {
    SHOW_DATA,
    CONNECTION_ERROR,
}
