package com.example.movieapp_maria_finalproject.ViewModel

import com.example.movieapp_maria_finalproject.Model.Movie
import com.example.movieapp_maria_finalproject.Model.MovieAPIService
import com.example.movieapp_maria_finalproject.Model.MovieAPIServiceInterface
import com.example.movieapp_maria_finalproject.Networking.MovieResponse
import com.example.movieapp_maria_finalproject.Room.MovieDao
import com.example.movieapp_maria_finalproject.Room.MovieEntity
import kotlinx.coroutines.flow.Flow

class AppRepository(private val movieDao: MovieDao): MovieAPIServiceInterface {
    var movieApiService = MovieAPIService()
    override suspend fun getPopularMovies(): MovieResponse {
        return movieApiService.getPopularMovies()
    }

    override suspend fun getSearchedMovieList(text: String): MovieResponse {
       return movieApiService.getSearchedMovieList(text)
    }
    fun getFavoriteMovies() : Flow<List<MovieEntity>> {
        return movieDao.getFavoriteMovies()
    }

    suspend fun removeFromFavorite(movie: MovieEntity){
        movieDao.deleteFromFavorites(movie)

    }
    fun searchFromFav(text: String): Flow<List<MovieEntity>>{
        return movieDao.searchFavoriteMovies(text)
    }

    suspend  fun addToFav(movie: MovieEntity){
        movieDao.addToFavorites(movie)
    }


}