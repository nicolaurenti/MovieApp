package com.example.data.mapper

import com.example.data.database.entity.MoviesEntity
import com.example.data.service.model.MovieResponse
import com.example.domain.entity.MovieItem

fun MoviesEntity.mapToMovie() =
    MovieItem(
        id = id,
        name = name,
        title = title,
        image = image,
        score = score,
        year = year,
    )

fun List<MoviesEntity>.mapToMoviesList() = this.map { it.mapToMovie() }

fun MovieItem.mapToDatabaseMovie() =
    MoviesEntity(
        id = id,
        name = name,
        title = title,
        image = image,
        score = score,
        year = year,
    )

fun MovieResponse.mapToLocalMovieList(): MovieItem =
    MovieItem(
        id = this.id,
        name = this.name,
        title = this.title,
        image = this.image,
        score = this.score,
        year = this.date,
    )
