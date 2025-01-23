package com.example.movieapp_maria_finalproject.Room
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_movies")
data class MovieEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val overview: String,
    val poster_path: String,
    val original_language: String,
    val release_date: String,
    val vote_average: Double,
    val vote_count: Double,
    var isFav: Boolean = false
)