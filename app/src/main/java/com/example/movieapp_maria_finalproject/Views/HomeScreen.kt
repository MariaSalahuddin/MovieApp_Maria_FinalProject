package com.example.movieapp_maria_finalproject.Views

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.example.movieapp_maria_finalproject.Model.Movie
import com.example.movieapp_maria_finalproject.Networking.MovieResponse
import com.example.movieapp_maria_finalproject.ViewModel.MovieViewModel

//import coil.compose.rememberImagePainter

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
    var activeStateOfSearchBar by remember { mutableStateOf(true) }
    LaunchedEffect(Unit) {
        onScreenLoading()
    }
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Row (modifier = Modifier.
        fillMaxHeight(0.15f),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            SearchBar(
                modifier = Modifier.fillMaxWidth().padding(8.dp),
                query = searchQuery,
                onQueryChange = {
                    searchQuery = it
                    if (searchQuery.length > 2) {
                        onSearch(searchQuery)
                    }
                },
                onSearch = {
                    activeStateOfSearchBar = true;

                },
                active = activeStateOfSearchBar,
                onActiveChange = {
                    activeStateOfSearchBar = it
                },
                placeholder = { Text("Search Movies...") }
            ) {

            }
        }
        Row {
            LazyColumn {
                items(movies.results.size) { index ->
                    MovieItem(movies.results[index], onAddToFavorites) {
                        navController.navigate("movieDetail/${movies.results[index].id}")
                    }
                }
            }
        }
    }
}

@Composable
fun MovieItem(movie: Movie, onAddToFavorites: (Movie) -> Unit, onClick: () -> Unit) {
    Column(modifier = Modifier.fillMaxWidth().padding(8.dp).clickable(onClick = onClick)) {
        Text(movie.title, style = MaterialTheme.typography.bodyMedium)
        Image(painter =  rememberImagePainter("https://image.tmdb.org/t/p/w500${movie.poster_path}"), contentDescription = null)
        Button(onClick = { onAddToFavorites(movie) }) {
            Text("Add to Favorites")
        }
    }
}