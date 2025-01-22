package com.example.movieapp_maria_finalproject.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.movieapp_maria_finalproject.ViewModel.MovieViewModel
import com.example.movieapp_maria_finalproject.Views.FavoriteMoviesScreen
import com.example.movieapp_maria_finalproject.Views.HomeScreen
import com.example.movieapp_maria_finalproject.Views.MovieDetailScreen

@Composable
fun MyNavigationGraph(navController: NavHostController, viewModel: MovieViewModel) {
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(
                navController = navController,
                movies = viewModel.movies,
                onAddToFavorites = { movie -> viewModel.addToFavorites(movie) },
                onSearch = { query -> viewModel.getSearchedMovies(query) },
                onScreenLoading = { viewModel.getPopularMovies() }
            )
        }
        composable("movieDetail/{movieId}") { backStackEntry ->
            val movieId = backStackEntry.arguments?.getString("movieId")?.toIntOrNull()
            val movie = viewModel.movies.results.find { it.id == movieId }
            movie?.let { MovieDetailScreen(movie = it) }
        }
        composable("favorites") {
            FavoriteMoviesScreen(
                favoriteMovies = viewModel.movies,
                onRemoveFromFavorites = { movie -> viewModel.removeFromFavorites(movie) }
            )
        }
    }
}