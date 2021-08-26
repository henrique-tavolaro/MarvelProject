package com.example.marvelproject.datasource

import com.example.marvelproject.model.Comics
import com.example.marvelproject.model.Item
import com.example.marvelproject.model.Result
import com.example.marvelproject.model.Thumbnail

object MockWebServerResponse {

    val searchResultResponse : String = "{\"status\":\"Ok\",\"data\":{\"offset\":0,\"limit\":20,\"total\":1,\"count\":1,\"results\":[{\"id\":1009368,\"name\":\"Iron Man\",\"description\":\"Wounded\",\"thumbnail\":{\"path\":\"http://i.annihil.us/u/prod/marvel/i/mg/9/c0/527bb7b37ff55\",\"extension\":\"jpg\"}}]}}"

}

