package com.example.movieapp_maria_finalproject.Networking


import com.example.movieapp_maria_finalproject.Model.Movie
import retrofit2.http.GET
import retrofit2.http.Query


interface MovieApiInterface {
    @GET("3/movie/popular")
    suspend fun getPopularMovies(@Query("page") page: Int): MovieResponse

    @GET("3/search/movie")
    suspend fun getSearchedMovie(@Query("query") query: String): MovieResponse
}

data class MovieResponse(val results: List<Movie>)