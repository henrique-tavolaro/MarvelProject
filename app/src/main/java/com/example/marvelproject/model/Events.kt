package com.example.marvelproject.model


import com.google.gson.annotations.SerializedName

data class Events(
    val available: Int,
    val collectionURI: String,
    val items: List<ItemX>,
    val returned: Int
)