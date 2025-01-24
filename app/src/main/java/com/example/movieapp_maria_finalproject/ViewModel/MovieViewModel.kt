package com.example.movieapp_maria_finalproject.ViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp_maria_finalproject.Networking.MovieResponse
import com.example.movieapp_maria_finalproject.Room.MovieEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MovieViewModel(var appRepo: AppRepository) : ViewModel() {
    var movies by mutableStateOf(MovieResponse(emptyList()))
    private val _searchResults = MutableStateFlow<List<MovieEntity>>(emptyList())
    val searchResults: StateFlow<List<MovieEntity>> = _searchResults

    fun getPopularMovies() {
        viewModelScope.launch {
            val popularMovies = appRepo.movieApiService.getPopularMovies()
            movies = popularMovies
        }
    }

    fun getSearchedMovies(query: String) {
        viewModelScope.launch {
            val searchedMovies = appRepo.movieApiService.getSearchedMovieList(query)
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
    fun getSearchFavMovies(query: String) {
        viewModelScope.launch {
            appRepo.searchFromFav(query).collectLatest { results ->
                _searchResults.value = results
            }
        }
    }
    fun isMovieInFavorites(movieId: Int): Flow<Boolean> {
        return appRepo.isMovieInFavorites(movieId)
    }
}