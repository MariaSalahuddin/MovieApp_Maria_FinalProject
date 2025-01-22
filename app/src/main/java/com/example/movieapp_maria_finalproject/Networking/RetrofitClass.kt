package com.example.movieapp_maria_finalproject.Networking

import com.example.movieapp_maria_finalproject.Model.MovieAPIService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClass {
    val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(AuthInterceptor { "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI2YzlmZWI2YzhjMmJkYTQzMmMyMzFiMzVjNTMyOTNkZiIsIm5iZiI6MTczNjk2MDQxMC4zODUsInN1YiI6IjY3ODdlOTlhY2FhNTNlOTA5ODdiMDc4NSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.aqrCj-VGu5lpxsmGGAJUzVDKcxH1bKspjLphvTqQQ9M" })
        .build()

    private val movieBaseUrl = "https://api.themoviedb.org/"
    var movieRetrofitObject = Retrofit.Builder().client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create()).
    baseUrl(movieBaseUrl).build()

    val movieApi = movieRetrofitObject.create(MovieApiInterface::class.java)
}