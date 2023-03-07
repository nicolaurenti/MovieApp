package com.example.fitopenpay

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.movieapp.R
import com.example.fitopenpay.adapter.MoviesViewPagerAdapter
import com.example.movieapp.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val adapter by lazy {
        MoviesViewPagerAdapter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.mainScreenViewPager.isUserInputEnabled = false
        binding.mainScreenViewPager.adapter = adapter
        val tabLayoutMediator = TabLayoutMediator(
            binding.mainScreenTabLayout,
            binding.mainScreenViewPager,
        ) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = getString(R.string.movies_tab_layout_text)
                }
                1 -> {
                    tab.text = getString(R.string.location_tab_layout_text)
                }
                2 -> {
                    tab.text = getString(R.string.images_tab_layout_text)
                }
            }
        }
        tabLayoutMediator.attach()
    }
}
