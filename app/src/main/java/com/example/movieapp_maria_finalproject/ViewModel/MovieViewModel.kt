package com.example.movieapp_maria_finalproject.ViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp_maria_finalproject.Model.Movie
import com.example.movieapp_maria_finalproject.Networking.MovieResponse
import com.example.movieapp_maria_finalproject.Room.MovieDao
import com.example.movieapp_maria_finalproject.Room.MovieDatabase
import com.example.movieapp_maria_finalproject.Room.MovieEntity
import kotlinx.coroutines.launch

class MovieViewModel() : ViewModel() {
    var movies by mutableStateOf(MovieResponse(emptyList()))
    var favoriteMovies by mutableStateOf(MovieResponse(emptyList()))
   // private val movieDao: MovieDao = MovieDatabase.getDatabase(application).movieDao()
    var appRepo = AppRepository()

    fun getPopularMovies(){
        viewModelScope.launch {
            val popularMovies = appRepo.movieApiService.getPopularMovies()
            movies = popularMovies
        }
    }
    fun getSearchedMovies(query: String){
        viewModelScope.launch {
            val searchedMovies =  appRepo.movieApiService.getSearchedMovieList(query)
            movies = searchedMovies
        }
    }
    init {
        fetchFavoriteMovies()
    }



    private fun fetchFavoriteMovies() {
//        viewModelScope.launch {
//            favoriteMovies.clear()
//            favoriteMovies.addAll(movieRepository.getFavoriteMovies())
//        }
    }

    fun addToFavorites(movie: Movie) {
//        val movieEntity = MovieEntity(
//            id = movie.id,
//            title = movie.title,
//            overview = movie.overview,
//            poster_path = movie.poster_path,
//         original_language = movie.original_language,
//         release_date = movie.release_date,
//         vote_average = movie.vote_average,
//         vote_count = movie.vote_count
//        )
//        viewModelScope.launch {
//         //   movieDao.insertMovie(movieEntity)
//
//        }
    }

    fun removeFromFavorites(movie: Movie) {
//        viewModelScope.launch {
//            movieRepository.removeFavorite(movie)
//            favoriteMovies.remove(movie)
//        }
    }

}