package com.example.data.service.model

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("id") val id: String,
    @SerializedName("title") val title: String?,
    @SerializedName("name") val name: String?,
    @SerializedName("poster_path") val image: String,
    @SerializedName("vote_average") val score: Double,
    @SerializedName("release_date") val date: String,
)
