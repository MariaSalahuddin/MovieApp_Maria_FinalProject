package com.example.movieapp_maria_finalproject.Navigation

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.movieapp_maria_finalproject.ViewModel.MovieViewModel
import com.example.movieapp_maria_finalproject.Views.FavoriteMoviesScreen
import com.example.movieapp_maria_finalproject.Views.HomeScreen
import com.example.movieapp_maria_finalproject.Views.MovieDetailScreen
import androidx.compose.ui.platform.LocalContext
@Composable
fun MyNavigationGraph(navController: NavHostController, viewModel: MovieViewModel) {
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            val context = LocalContext.current
            HomeScreen(
                navController = navController,
                movies = viewModel.movies,
                onAddToFavorites = { movie ->
                    viewModel.addToFavorites(movie)
                    Toast.makeText(context, "${movie.title} is added to Favorites!", Toast.LENGTH_SHORT).show()
                },
                onSearch = { query -> viewModel.getSearchedMovies(query) },
                onScreenLoading = { viewModel.getPopularMovies() }
            )
        }
        composable("movieDetail/{movieId}") { backStackEntry ->
            val movieId = backStackEntry.arguments?.getString("movieId")?.toIntOrNull()
            val movie = viewModel.movies.results.find { it.id == movieId }
            movie?.let { MovieDetailScreen(movie = it, navController = navController) }
        }
        composable("favorites") {
            FavoriteMoviesScreen(
                navController = navController,
                favoriteMovies = viewModel.getFavoriteMovies()
                    .collectAsState(initial = emptyList()).value,
                onRemoveFromFavorites = { movie -> viewModel.removeFromFavorites(movie) },
                onSearchQueryChanged ={text -> viewModel.getSearchFavMovies(text)}
            )
        }
    }
}