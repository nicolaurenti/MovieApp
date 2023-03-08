package com.example.fitopenpay.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fitopenpay.adapter.MoviesAdapter
import com.example.movieapp.databinding.FragmentMoviesBinding
import com.example.fitopenpay.viewmodel.MoviesData
import com.example.fitopenpay.viewmodel.MoviesState
import com.example.fitopenpay.viewmodel.MoviesViewModel
import dagger.hilt.android.AndroidEntryPoint

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
        viewModel.state.observe({ lifecycle }, ::updateState)
        viewModel.getMostPopularMovies()
        binding.popularButton.setOnClickListener { viewModel.getMostPopularMovies() }
        binding.ratedButton.setOnClickListener { viewModel.getTopRatedMovies() }
        binding.recommendationsButton.setOnClickListener { viewModel.getBestRecommendationsMovies() }
    }

    private fun updateState(moviesData: MoviesData) {
        when (moviesData.state) {
            MoviesState.SHOW_DATA -> {
                val adapter = MoviesAdapter()
                binding.recycler.adapter = adapter
                adapter.submitList(moviesData.movies)
                binding.recycler.layoutManager = LinearLayoutManager(context)
            }
            MoviesState.CONNECTION_ERROR -> {}
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
