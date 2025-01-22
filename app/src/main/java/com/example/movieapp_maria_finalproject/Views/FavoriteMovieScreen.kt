package com.example.movieapp_maria_finalproject.Views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.movieapp_maria_finalproject.Model.Movie
import com.example.movieapp_maria_finalproject.Networking.MovieResponse

@Composable
fun FavoriteMoviesScreen(favoriteMovies: MovieResponse, onRemoveFromFavorites: (Movie) -> Unit) {
    LazyColumn {
        items(favoriteMovies.results.size) { index ->
            Column {
                Text(favoriteMovies.results[index].title)
               //Image(painter = rememberImagePainter("https://image.tmdb.org/t/p/w500${movie.poster_path}"), contentDescription = null)
                Button(onClick = { onRemoveFromFavorites(favoriteMovies.results[index]) }) {
                    Text("Remove from Favorites")
                }
            }
        }
    }
}