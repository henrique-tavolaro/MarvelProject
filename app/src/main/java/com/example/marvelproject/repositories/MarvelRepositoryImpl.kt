package com.example.marvelproject.repositories

import com.example.marvelproject.datasource.RetrofitService
import com.example.marvelproject.model.DataState
import com.example.marvelproject.model.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MarvelRepositoryImpl @Inject constructor(
    private val retrofitService: RetrofitService
) : MarvelRepository {

    override suspend fun searchCharacter(
        name: String,
        limit: Int,
        offset: Int,
        ts: String,
        apikey: String,
        hash: String
    ): Flow<DataState<Response>> = flow {
        try {
            emit(DataState.loading())

            val response = retrofitService.search(name, limit, offset, ts, apikey, hash)
            emit(DataState.success(response))
        } catch (e: Exception) {
            emit(DataState.error<Response>(e.message ?: "Unknown Error"))
        }

    }
}