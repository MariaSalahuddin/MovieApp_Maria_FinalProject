package com.example.movieapp_maria_finalproject.Room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    suspend  fun addToFavorites(movie: MovieEntity)

    @Query("select * from favorite_movies")
     fun getFavoriteMovies(): Flow<List<MovieEntity>>

   // @Query("DELETE FROM favorite_movies WHERE id = :movieId")
    @Delete
    suspend fun deleteFromFavorites(movie: MovieEntity)

    @Query("SELECT * FROM favorite_movies WHERE title LIKE '%' || :query || '%' ORDER BY title")
    fun searchFavoriteMovies(query: String): Flow<List<MovieEntity>>
}
