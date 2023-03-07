package com.example.di

import android.content.Context
import androidx.room.Room
import com.example.data.database.MoviesDao
import com.example.data.database.MoviesRepositoryImpl
import com.example.data.database.entity.MoviesDatabase
import com.example.domain.database.MoviesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    private const val DB = "MoviesRepository"

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): MoviesDatabase {
        return Room.databaseBuilder(context, MoviesDatabase::class.java, DB).build()
    }

    @Provides
    @Singleton
    fun provideMoviesDao(moviesDB: MoviesDatabase): MoviesDao = moviesDB.moviesDao()

    @Provides
    fun provideMoviesRepository(moviesDao: MoviesDao): MoviesRepository =
        MoviesRepositoryImpl(moviesDao)
}
