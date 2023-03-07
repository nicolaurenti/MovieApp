package com.example.fitopenpay.di

import com.example.di.ApiModule
import com.example.di.ServiceModule
import com.example.di.UseCaseModule
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [ApiModule::class, ServiceModule::class, UseCaseModule::class])
@InstallIn(SingletonComponent::class)
class AppModule
