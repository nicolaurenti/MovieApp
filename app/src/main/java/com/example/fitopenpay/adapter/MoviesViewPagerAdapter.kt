package com.example.fitopenpay.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.fitopenpay.fragment.ImagesFragment
import com.example.fitopenpay.fragment.LocationFragment
import com.example.fitopenpay.fragment.MoviesFragment

class MoviesViewPagerAdapter(fragment: FragmentActivity) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> MoviesFragment.newInstance()
            1 -> LocationFragment.newInstance()
            2 -> ImagesFragment.newInstance()
            else -> MoviesFragment.newInstance()
        }
    }
}