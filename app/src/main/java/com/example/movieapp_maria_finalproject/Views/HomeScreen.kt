package com.example.movieapp_maria_finalproject.Views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.movieapp_maria_finalproject.Networking.MovieResponse
import com.example.movieapp_maria_finalproject.Room.MovieEntity
import com.example.movieapp_maria_finalproject.ViewModel.MovieViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: MovieViewModel,
    navController: NavHostController,
    movies: MovieResponse,
    onAddToFavorites: (MovieEntity) -> Unit,
    onSearch: (String) -> Unit,
    onScreenLoading: () -> Unit
) {
    var searchQuery by remember { mutableStateOf("") }
    LaunchedEffect(Unit) {
        onScreenLoading()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
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
                    if (searchQuery.isNotEmpty()) {
                        onSearch(searchQuery)
                    } else {
                        onScreenLoading()
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
                    tint = MaterialTheme.colorScheme.error
                )
            }
        }

        // Movies List Section
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(movies.results.size) { index ->
                MovieItem(viewModel, movies.results[index], onAddToFavorites) {
                    navController.navigate("movieDetail/${movies.results[index].id}")
                }
            }
        }
    }
}


@Composable
fun MovieItem(
    viewModel: MovieViewModel,
    movie: MovieEntity, onAddToFavorites: (MovieEntity) -> Unit,
    onClick: () -> Unit
) {
    movie.isFav = viewModel.isMovieInFavorites(movie.id).collectAsState(initial = false).value
    var showDialog by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable(onClick = onClick)
            .padding(bottom = 16.dp)
    ) {
        // Title Row with Movie Title and Heart Icon
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Movie Title
            Text(
                text = movie.title,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier.weight(1f)
            )

            // Heart Icon Button
            IconButton(
                onClick = {
                    if (movie.isFav) {
                        showDialog = true
                    } else {
                        onAddToFavorites(movie)

                    }
                },
                modifier = Modifier
            ) {
                if (movie.isFav) {
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = "Add to Favorites",
                        tint = MaterialTheme.colorScheme.error
                    )
                } else {
                    Icon(
                        imageVector = Icons.Default.FavoriteBorder,
                        contentDescription = "Add to Favorites",
                        tint = MaterialTheme.colorScheme.error
                    )
                }

            }
        }

        // Movie Poster Image
        AsyncImage(
            model = "https://image.tmdb.org/t/p/w500${movie.poster_path}",
            contentDescription = movie.title,
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .padding(bottom = 8.dp)
        )
        //delete confirmation dialogue
        if (showDialog) {
            DeleteAlertDialog(titleText = "Are You Sure You Want to Remove ${movie.title} From Favorites?",
                onYes = {
                    viewModel.removeFromFavorites(movie)
                    showDialog = false
                },
                onNo = {
                    showDialog = false
                })
        }
    }
}
