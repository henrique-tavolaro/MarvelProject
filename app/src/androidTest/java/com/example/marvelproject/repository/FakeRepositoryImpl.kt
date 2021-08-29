package com.example.marvelproject.repository

import com.example.marvelproject.model.*
import com.example.marvelproject.repositories.MarvelRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeRepositoryImpl : MarvelRepository {
    override suspend fun searchCharacter(
        name: String,
        limit: Int,
        offset: Int,
        ts: String,
        apikey: String,
        hash: String
    ): Flow<DataState<Response>> = flow {
        if (name == "iron man") {
            emit(DataState.success(response))
        } else {
            emit(DataState.error<Response>("Error"))
        }
    }
}


val response = Response(
    data = Data(
        count = 4, limit = 4, offset = 0, results =
        listOf(
            Result(
                description = "Wounded, ", id = 1009368, name = "Iron Man",
                thumbnail = Thumbnail(
                    extension = "jpg",
                    path = "http://i.annihil.us/u/prod/marvel/i/mg/9/c0/527bb7b37ff55"
                )
            ),
            Result(
                description = "", id = 1017320, name = "Iron Man (Iron Man 3 - The Official Game)",
                thumbnail = Thumbnail(
                    extension = "jpg",
                    path = "http://i.annihil.us/u/prod/marvel/i/mg/9/03/5239c1408c936"
                )
            ),
            Result(
                description = "", id = 1017294, name = "Iron Man (LEGO Marvel Super Heroes)",
                thumbnail = Thumbnail(
                    extension = "jpg",
                    path = "http://i.annihil.us/u/prod/marvel/i/mg/6/90/5239c3cc8a259"
                )
            ),
            Result(
                description = "", id = 1017310, name = "Iron Man (Marvel Heroes)",
                thumbnail = Thumbnail(
                    extension = "jpg",
                    path = "http://i.annihil.us/u/prod/marvel/i/mg/9/40/5239be60a67da"
                )
            )
        ),
        total = 7
    ), status = "Ok"
)

val result = Result(
    description = "Wounded, ", id = 1009368, name = "Iron Man",
    thumbnail = Thumbnail(
        extension = "jpg",
        path = "http://i.annihil.us/u/prod/marvel/i/mg/9/c0/527bb7b37ff55"
    )
)