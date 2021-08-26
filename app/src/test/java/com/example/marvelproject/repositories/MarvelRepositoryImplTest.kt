package com.example.marvelproject.repositories

import com.example.marvelproject.BASE_URL
import com.example.marvelproject.datasource.MockWebServerResponse.searchResultResponse
import com.example.marvelproject.datasource.RetrofitService
import com.example.marvelproject.model.Data
import com.example.marvelproject.model.Response
import com.google.gson.GsonBuilder
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import okhttp3.HttpUrl
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.HttpURLConnection

class MarvelRepositoryImplTest{

    private lateinit var mockWebServer: MockWebServer
    private lateinit var baseUrl: HttpUrl
    private val DUMMY_TOKEN = "43234"
    private val DUMMY_QUERY = "Iron Man"
    private val DUMMY_TS = "1234"
    private val DUMMY_HASH = "123123"

    //system in test
    private lateinit var repository: MarvelRepository

    // dependencies
    private lateinit var retrofitService: RetrofitService

    @Before
    fun setup(){
        mockWebServer = MockWebServer()
        mockWebServer.start()
        baseUrl = mockWebServer.url("characters/")
        retrofitService = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(RetrofitService::class.java)

        repository = MarvelRepositoryImpl(retrofitService)
    }

    @Test
    fun `Get character from network and emit`() = runBlocking {

        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(HttpURLConnection.HTTP_OK)
                .setBody(searchResultResponse)
        )

        val flowItems = repository.searchCharacter(DUMMY_QUERY, DUMMY_TS, DUMMY_TOKEN, DUMMY_HASH).toList()

        //first emission should be loading
        assert(flowItems[0].loading)

        // second emission should be the response
        val response = flowItems[1].data

        assert(response != null)
        assert(response is Response)

        //loading should be false
        assert(!flowItems[1].loading)
    }


    @Test
    fun `try to get charactor and emit error`() = runBlocking {

        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(HttpURLConnection.HTTP_BAD_REQUEST)
                .setBody("{}")
        )

        val flowItems = repository.searchCharacter(DUMMY_QUERY, DUMMY_TS, DUMMY_TOKEN, DUMMY_HASH).toList()

        //first emission should be loading
        assert(flowItems[0].loading)

        // second emission should be the error
        val response = flowItems[1].error

        assert(response != null)


        //loading should be false
        assert(!flowItems[1].loading)
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
    }

}