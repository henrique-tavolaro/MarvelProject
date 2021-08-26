package com.example.marvelproject.datasource

import com.example.marvelproject.model.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {

    @GET("characters")
    suspend fun search(
        @Query("name") name: String,
        @Query("ts") timestamp: String,
        @Query("apikey") apikey: String,
        @Query("hash") hash: String

        ) : Response
}