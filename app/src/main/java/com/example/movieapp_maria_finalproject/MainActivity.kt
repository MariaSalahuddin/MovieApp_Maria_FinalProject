package com.example.movieapp_maria_finalproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.navigation.compose.rememberNavController
import com.example.movieapp_maria_finalproject.Navigation.MyNavigationGraph
import com.example.movieapp_maria_finalproject.ViewModel.MovieViewModel
import com.example.movieapp_maria_finalproject.ui.theme.MovieApp_Maria_FinalProjectTheme

class MainActivity : ComponentActivity() {
    private val viewModel: MovieViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MovieApp_Maria_FinalProjectTheme {
                val navController = rememberNavController()
                MyNavigationGraph(navController = navController, viewModel = viewModel)

            }
        }
    }

}
