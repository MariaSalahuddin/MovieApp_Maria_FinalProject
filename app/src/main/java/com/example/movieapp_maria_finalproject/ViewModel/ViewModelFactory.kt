package com.example.movieapp_maria_finalproject.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MovieViewModelFactory(private val appRepo: AppRepository):
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MovieViewModel::class.java)){
            return MovieViewModel(appRepo) as T
        }
        else{
            throw IllegalArgumentException("Not matching class")
        }
    }
}