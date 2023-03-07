package com.example.data.service.model

import com.google.gson.annotations.SerializedName

data class ResultResponse(
    @SerializedName("results") val result: List<MovieResponse>,
)
