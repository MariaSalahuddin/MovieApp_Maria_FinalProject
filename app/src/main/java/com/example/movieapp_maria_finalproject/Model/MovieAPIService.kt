package com.example.movieapp_maria_finalproject.Model

import com.example.movieapp_maria_finalproject.Networking.MovieResponse
import com.example.movieapp_maria_finalproject.Networking.RetrofitClass

interface MovieAPIServiceInterface{
    suspend fun getPopularMovies(): MovieResponse
    suspend fun getSearchedMovieList(text: String):MovieResponse
}

class MovieAPIService() {
    private val movieApiService = RetrofitClass.movieApi
    suspend fun getPopularMovies(): MovieResponse {
        return movieApiService.getPopularMovies(1)

    }

    suspend fun getSearchedMovieList(text: String): MovieResponse {
        return movieApiService.getSearchedMovie(text)
    }
}