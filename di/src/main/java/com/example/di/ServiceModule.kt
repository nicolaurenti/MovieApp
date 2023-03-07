package com.example.di

import com.example.data.service.FirebaseServiceImpl
import com.example.data.service.MoviesApi
import com.example.data.service.MoviesServiceImpl
import com.example.domain.service.FirebaseService
import com.example.domain.service.MoviesService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {
    @Provides
    fun provideMoviesService(moviesApi: MoviesApi): MoviesService =  MoviesServiceImpl(moviesApi)

    @Provides
    fun provideFirebaseService(): FirebaseService = FirebaseServiceImpl()
}