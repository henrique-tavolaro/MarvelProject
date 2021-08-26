package com.example.marvelproject.repositories

import com.example.marvelproject.model.DataState
import com.example.marvelproject.model.Response
import kotlinx.coroutines.flow.Flow

interface MarvelRepository {

    suspend fun searchCharacter(
        name: String,
        ts: String,
        apikey: String,
        hash: String
    ) : Flow<DataState<Response>>

}