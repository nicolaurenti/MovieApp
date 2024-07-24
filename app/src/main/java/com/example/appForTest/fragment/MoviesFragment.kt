package com.example.appForTest.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appForTest.adapter.MoviesAdapter
import com.example.movieapp.databinding.FragmentMoviesBinding
import com.example.appForTest.viewmodel.MoviesData
import com.example.appForTest.viewmodel.MoviesState
import com.example.appForTest.viewmodel.MoviesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MoviesFragment : Fragment() {

    private lateinit var binding: FragmentMoviesBinding
    private val viewModel: MoviesViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.getMostPopularMovies()
        lifecycleScope.launch {
            viewModel.moviesState.collect(::updateState)
        }
        binding.popularButton.setOnClickListener { viewModel.getMostPopularMovies() }
        binding.ratedButton.setOnClickListener { viewModel.getTopRatedMovies() }
        binding.recommendationsButton.setOnClickListener { viewModel.getBestRecommendationsMovies() }
    }

    private fun updateState(moviesData: MoviesData) {
        when (moviesData.state) {
            MoviesState.SHOW_DATA -> {
                binding.recycler.adapter = MoviesAdapter(moviesData.movies)
                binding.recycler.layoutManager = LinearLayoutManager(context)
            }
            MoviesState.CONNECTION_ERROR -> {}
            MoviesState.LOADING -> {}
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            MoviesFragment().apply {
                arguments = Bundle()
            }
    }
}
