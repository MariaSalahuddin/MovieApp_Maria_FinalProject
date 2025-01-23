package com.example.movieapp_maria_finalproject.Views

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.movieapp_maria_finalproject.Room.MovieEntity

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteMoviesScreen(
    navController: NavHostController,
    favoriteMovies: List<MovieEntity>,
    onRemoveFromFavorites: (MovieEntity) -> Unit,
    onSearchQueryChanged: (String) -> Unit
) {
    var searchQuery by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        // Scaffold for Top App Bar and Content
        Scaffold(
            topBar = {
                // Top App Bar with Back Button and Favorites Title
                TopAppBar(
                    title = {
                        Text(
                            text = "Favorites",
                            style = MaterialTheme.typography.headlineSmall
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(
                                imageVector = Icons.Filled.ArrowBack,
                                contentDescription = "Back"
                            )
                        }
                    }
                )
            },
            content = { paddingValues ->
                if (favoriteMovies.isEmpty()) {
                    // Display a message when the list is empty
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "No favorite movies yet. Add some to your favorites!",
                            style = MaterialTheme.typography.bodyLarge.copy(
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                fontSize = 18.sp
                            ),
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                } else {
                    // Show the list when it's not empty
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(favoriteMovies.size) { index ->
                            FavoriteMovieItem(
                                movie = favoriteMovies[index],
                                onRemoveFromFavorites = onRemoveFromFavorites
                            ) {
                                navController.navigate("movieDetail/${favoriteMovies[index].id}")
                            }
                        }
                    }
                }
            }
        )

        // Search Bar below the Scaffold
        TextField(
            value = searchQuery,
            onValueChange = {
                searchQuery = it
                onSearchQueryChanged(searchQuery)
            },
            label = { Text("Search") },
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            singleLine = true
        )
    }
}
@Composable
fun FavoriteMovieItem(
    movie: MovieEntity,
    onRemoveFromFavorites: (MovieEntity) -> Unit,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .padding(horizontal = 8.dp, vertical = 4.dp)
            .clickable(onClick = onClick),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Movie Poster
            Image(
                painter = rememberAsyncImagePainter("https://image.tmdb.org/t/p/w500${movie.poster_path}"),
                contentDescription = movie.title,
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(8.dp))
            )

            Spacer(modifier = Modifier.width(16.dp))

            // Movie Details
            Column(
                modifier = Modifier
                    .weight(1f)
            ) {
                Text(
                    text = movie.title,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    ),
                    maxLines = 1
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Release Date: ${movie.release_date}",
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                )
            }

            // Remove Button
            Button(
                onClick = { onRemoveFromFavorites(movie) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.error
                )
            ) {
                Text(
                    text = "Remove",
                    color = Color.White
                )
            }
        }
    }
}
