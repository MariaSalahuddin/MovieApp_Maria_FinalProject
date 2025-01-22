package com.example.movieapp_maria_finalproject.ViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp_maria_finalproject.Model.Movie
import com.example.movieapp_maria_finalproject.Networking.MovieResponse
import kotlinx.coroutines.launch

class MovieViewModel() : ViewModel() {
    var movies by mutableStateOf(MovieResponse(emptyList()))
 //   var favoriteMovies by mutableStateOf(MovieResponse(emptyList()))
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
        fetchPopularMovies()
        fetchFavoriteMovies()
    }

    private fun fetchPopularMovies() {
//        viewModelScope.launch {
//            try {
//                val response = movieRepository.getPopularMovies(1)
//                movies.addAll(response.results)
//            } catch (e: Exception) {
//                // Handle the error
//            }
//        }
    }

    private fun fetchFavoriteMovies() {
//        viewModelScope.launch {
//            favoriteMovies.clear()
//            favoriteMovies.addAll(movieRepository.getFavoriteMovies())
//        }
    }

    fun addToFavorites(movie: Movie) {
//        viewModelScope.launch {
//            movieRepository.addFavorite(movie)
//            favoriteMovies.add(movie)
//        }
    }

    fun removeFromFavorites(movie: Movie) {
//        viewModelScope.launch {
//            movieRepository.removeFavorite(movie)
//            favoriteMovies.remove(movie)
//        }
    }

    fun searchMovies(query: String) {
    //    movies = mutableStateListOf(movies.filter { it.title.contains(query, ignoreCase = true) })
    }
}