package com.example.marvelproject.di

import com.example.marvelproject.BASE_URL
import com.example.marvelproject.datasource.RetrofitService
import com.example.marvelproject.repositories.MarvelRepository
import com.example.marvelproject.repositories.MarvelRepositoryImpl
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    val logger: HttpLoggingInterceptor = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)

    val okHttp = OkHttpClient.Builder().addInterceptor(logger)

    @Singleton
    @Provides
    fun provideRetrofitService(): RetrofitService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .client(okHttp.build())
            .build()
            .create(RetrofitService::class.java)
    }

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