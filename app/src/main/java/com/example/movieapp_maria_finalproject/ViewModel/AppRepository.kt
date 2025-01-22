package com.example.movieapp_maria_finalproject.ViewModel

import com.example.movieapp_maria_finalproject.Model.Movie
import com.example.movieapp_maria_finalproject.Model.MovieAPIService
import com.example.movieapp_maria_finalproject.Model.MovieAPIServiceInterface
import com.example.movieapp_maria_finalproject.Networking.MovieResponse

class AppRepository: MovieAPIServiceInterface {
    var movieApiService = MovieAPIService()
    override suspend fun getPopularMovies(): MovieResponse {
        return movieApiService.getPopularMovies()
    }

    override suspend fun getSearchedMovieList(text: String): MovieResponse {
       return movieApiService.getSearchedMovieList(text)
    }
}