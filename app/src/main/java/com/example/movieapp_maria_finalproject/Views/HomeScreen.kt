package com.example.movieapp_maria_finalproject.Views

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import com.example.movieapp_maria_finalproject.Model.Movie
import com.example.movieapp_maria_finalproject.Networking.MovieResponse
import com.example.movieapp_maria_finalproject.ViewModel.MovieViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavHostController,
    movies: MovieResponse,
    onAddToFavorites: (Movie) -> Unit,
    onSearch: (String) -> Unit,
    onScreenLoading: () -> Unit
) {
    var searchQuery by remember { mutableStateOf("") }
    LaunchedEffect(Unit) {
        onScreenLoading()
    }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        // Search Bar Section with Favorites Button
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Search TextField
            TextField(
                value = searchQuery,
                onValueChange = {
                    searchQuery = it
                    if (searchQuery.length > 2) {
                        onSearch(searchQuery) // Trigger search on typing
                    }
                },
                label = { Text("Search Movies...") },
                modifier = Modifier.weight(1f),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                ),
                singleLine = true
            )

            Spacer(modifier = Modifier.width(8.dp))

            // Favorites Heart Button
            IconButton(onClick = {
                navController.navigate("favorites")
            }) {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = "Favorites",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }

        // Movies List Section
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(movies.results.size) { index ->
                MovieItem(movies.results[index], onAddToFavorites) {
                    navController.navigate("movieDetail/${movies.results[index].id}")
                }
            }
        }
    }
}


@Composable
fun MovieItem(movie: Movie, onAddToFavorites: (Movie) -> Unit, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable(onClick = onClick)
            .padding(bottom = 16.dp)
    ) {
        // Movie Title
        Text(
            text = movie.title,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        // Movie Poster Image
        AsyncImage(
            model = "https://image.tmdb.org/t/p/w500${movie.poster_path}",
            contentDescription = movie.title,
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .padding(bottom = 8.dp)
        )

        // Add to Favorites Button
        Button(
            onClick = { onAddToFavorites(movie) },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth() // Make button stretch across the width
        ) {
            Text("Add to Favorites")
        }
    }
}
