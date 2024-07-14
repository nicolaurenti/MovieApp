package com.example.domain.entity

data class MovieItem(
    val id: String,
    val title: String? = null,
    val name: String? = null,
    val image: String = "",
    val score: Double = 0.0,
    val year: String = "2024",
)
