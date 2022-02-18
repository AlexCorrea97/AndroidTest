package com.example.androidtest.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidtest.data.model.Movie
import com.example.androidtest.data.model.toMovie
import com.example.androidtest.domain.Movie.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyMoviesViewModel @Inject constructor(val movieUseCase: MovieUseCase) : ViewModel(){
    val myMovieList = MutableLiveData<List<Movie>>(emptyList<Movie>())
    fun insertMovie(movie:Movie){
        viewModelScope.launch {

        }
    }
    fun getMyMovies(){
        viewModelScope.launch {
            myMovieList.postValue(movieUseCase.getMyMovies().map { it.toMovie() })
        }
    }
}