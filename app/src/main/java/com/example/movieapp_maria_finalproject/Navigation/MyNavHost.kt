package com.example.movieapp_maria_finalproject.Navigation

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
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
        //home screen navigation
        composable("home") {
            val context = LocalContext.current
            HomeScreen(
                viewModel = viewModel,
                navController = navController,
                movies = viewModel.movies,
                onAddToFavorites = { movie ->
                    viewModel.addToFavorites(movie)
                    Toast.makeText(
                        context,
                        "${movie.title} is added to Favorites!",
                        Toast.LENGTH_SHORT
                    ).show()
                },
                onSearch = { query -> viewModel.getSearchedMovies(query) },
                onScreenLoading = { viewModel.getPopularMovies() }
            )
        }
        //movie detail screen navigation
        composable("movieDetail/{movieId}") { backStackEntry ->
            val movieId = backStackEntry.arguments?.getString("movieId")?.toIntOrNull()
            val movie = viewModel.movies.results.find { it.id == movieId }
            val context = LocalContext.current
            movie?.let {
                MovieDetailScreen(
                    movie = it,
                    navController = navController,
                    viewModel = viewModel,
                    onAddToFavorites = { movie ->
                        viewModel.addToFavorites(movie)
                        Toast.makeText(
                            context,
                            "${movie.title} is added to Favorites!",
                            Toast.LENGTH_SHORT
                        ).show()
                    },
                )
            }
        }
        //favorites screen navigation
        composable("favorites") {
            FavoriteMoviesScreen(
                navController = navController,
                viewModel = viewModel,
                favoriteMovies = viewModel.getFavoriteMovies()
                    .collectAsState(initial = emptyList()).value
            )
        }
    }
}