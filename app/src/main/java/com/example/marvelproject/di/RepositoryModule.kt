package com.example.marvelproject.di

import com.example.marvelproject.datasource.RetrofitService
import com.example.marvelproject.repositories.MarvelRepository
import com.example.marvelproject.repositories.MarvelRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule{

    @Singleton
    @Provides
    fun provideRepository(
        retrofitService: RetrofitService
    ): MarvelRepository {
        return MarvelRepositoryImpl(
            retrofitService = retrofitService
        )
    }
}

