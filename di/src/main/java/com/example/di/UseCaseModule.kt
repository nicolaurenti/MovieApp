package com.example.di

import com.example.domain.database.MoviesRepository
import com.example.domain.service.FirebaseService
import com.example.domain.service.MoviesService
import com.example.domain.usecase.FirebaseUseCase
import com.example.domain.usecase.FirebaseUseCaseImpl
import com.example.domain.usecase.GetMoviesUseCase
import com.example.domain.usecase.GetMoviesUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Provides
    fun provideGetMoviesUseCase(
        moviesService: MoviesService,
        moviesRepository: MoviesRepository,
    ): GetMoviesUseCase = GetMoviesUseCaseImpl(moviesService, moviesRepository)

    @Provides
    fun provideFirebaseUseCase(firebaseService: FirebaseService): FirebaseUseCase =
        FirebaseUseCaseImpl(firebaseService)
}
