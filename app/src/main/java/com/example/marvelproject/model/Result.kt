package com.example.marvelproject.model


import com.google.gson.annotations.SerializedName

data class Result(

    val description: String,
    val id: Int,
    val name: String,
    val thumbnail: Thumbnail,

)