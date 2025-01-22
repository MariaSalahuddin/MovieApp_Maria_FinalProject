package com.example.movieapp_maria_finalproject.Views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.movieapp_maria_finalproject.Model.Movie

@Composable
fun MovieDetailScreen(movie: Movie) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(movie.title, style = MaterialTheme.typography.headlineLarge)
        Text(movie.overview, style = MaterialTheme.typography.bodyLarge)
        //Image(painter = rememberImagePainter("https://image.tmdb.org/t/p/w500${movie.poster_path}"), contentDescription = null)
    }
}