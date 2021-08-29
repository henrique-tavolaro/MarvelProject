package com.example.marvelproject.di

import com.example.marvelproject.repositories.MarvelRepository
import com.example.marvelproject.repositories.MarvelRepositoryImpl
import com.example.marvelproject.repository.FakeRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [RepositoryModule::class]
)
class FakeRepositoryModule {

    @Singleton
    @Provides
    fun provideFakeRepository(): MarvelRepository {
        return FakeRepositoryImpl()
    }

}