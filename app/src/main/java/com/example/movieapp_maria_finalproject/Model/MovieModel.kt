package com.example.movieapp_maria_finalproject.Model

data class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    val poster_path: String,
    val original_language: String,
    val release_date: String,
    val vote_average: Double,
    val vote_count: Double
)

