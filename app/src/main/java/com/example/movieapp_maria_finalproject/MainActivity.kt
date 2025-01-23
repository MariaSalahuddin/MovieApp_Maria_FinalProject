package com.example.movieapp_maria_finalproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import com.example.movieapp_maria_finalproject.Navigation.MyNavigationGraph
import com.example.movieapp_maria_finalproject.Room.MovieDatabase
import com.example.movieapp_maria_finalproject.ViewModel.AppRepository
import com.example.movieapp_maria_finalproject.ViewModel.MovieViewModel
import com.example.movieapp_maria_finalproject.ViewModel.MovieViewModelFactory
import com.example.movieapp_maria_finalproject.ui.theme.MovieApp_Maria_FinalProjectTheme

class MainActivity : ComponentActivity() {
    //   private val viewModel: MovieViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val database = MovieDatabase.getDBInstance(this)
        val movieDao = database.getMovieDao()
        val appRepo = AppRepository(movieDao)
        val movieViewModelFactory = MovieViewModelFactory(appRepo)
        val viewModel =
            ViewModelProvider(this, movieViewModelFactory)[MovieViewModel::class.java]
        setContent {


            MovieApp_Maria_FinalProjectTheme {
                val navController = rememberNavController()
                MyNavigationGraph(navController = navController, viewModel = viewModel)

            }
        }
    }

}
