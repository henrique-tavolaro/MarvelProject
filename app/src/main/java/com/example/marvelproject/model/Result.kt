package com.example.marvelproject.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Result(

    val description: String,
    val id: Int,
    val name: String,
    val thumbnail: Thumbnail,

) : Parcelable