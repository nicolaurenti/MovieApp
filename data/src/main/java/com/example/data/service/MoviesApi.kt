package com.example.data.service

import com.example.data.service.model.ResultResponse
import retrofit2.Call
import retrofit2.http.GET

interface MoviesApi {
    @GET("3/movie/popular")
    fun getMostPopularMovies(): Call<ResultResponse>

    @GET("3/movie/top_rated")
    fun getBestRatedMovies(): Call<ResultResponse>

    @GET("3/movie/1058949/recommendations")
    fun getBestRecommendationsMovies(): Call<ResultResponse>
}
