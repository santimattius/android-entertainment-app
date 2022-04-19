package com.santimattius.template.domain.entities

data class Movie(
    val id: Int,
    val overview: String,
    val title: String,
    val posterPath: String,
    val backdropPath: String,
)