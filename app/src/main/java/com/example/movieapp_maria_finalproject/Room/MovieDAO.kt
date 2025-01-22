package com.example.movieapp_maria_finalproject.Room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MovieDao {

    @Insert
    suspend fun insertMovie(movie: MovieEntity)

    @Query("SELECT * FROM favorite_movies")
    suspend fun getFavoriteMovies(): List<MovieEntity>

    @Query("DELETE FROM favorite_movies WHERE id = :movieId")
    suspend fun deleteMovie(movieId: Int)
}
