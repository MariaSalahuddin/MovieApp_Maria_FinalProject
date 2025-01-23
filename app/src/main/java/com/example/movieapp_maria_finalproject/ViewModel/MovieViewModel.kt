package com.example.movieapp_maria_finalproject.ViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp_maria_finalproject.Model.Movie
import com.example.movieapp_maria_finalproject.Networking.MovieResponse
import com.example.movieapp_maria_finalproject.Room.MovieEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class MovieViewModel( var appRepo: AppRepository) : ViewModel() {
    var movies by mutableStateOf(MovieResponse(emptyList()))

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
     fun getFavoriteMovies(): Flow<List<MovieEntity>> {
        return appRepo.getFavoriteMovies()

    }

      fun addToFavorites(movie: MovieEntity) {
        viewModelScope.launch {
            appRepo.addToFav(movie)
        }
    }

    fun removeFromFavorites(movie: MovieEntity) {
        viewModelScope.launch {
            appRepo.removeFromFavorite(movie)
        }
    }
    fun getSearchFavMovies(text: String): Flow<List<MovieEntity>> {
        return appRepo.searchFromFav(text)

    }

}