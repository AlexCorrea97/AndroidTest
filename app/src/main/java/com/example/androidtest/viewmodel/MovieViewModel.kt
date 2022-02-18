package com.example.androidtest.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidtest.data.model.Movie
import com.example.androidtest.domain.Movie.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class MovieViewModel @Inject constructor(val movieUseCase: MovieUseCase):ViewModel() {
    val movieList = MutableLiveData<List<Movie>>(emptyList<Movie>())
    fun getAllPopularMovies(){
        viewModelScope.launch {
            if(movieList==null||movieList.value?.size==0)
            movieList.postValue(movieUseCase.getAllPopularMovies())
        }
    }

     fun updateImage(id:Int, byte: ByteArray){
        viewModelScope.launch {
            movieUseCase.updateImageMovie(id, byte)

        }

    }




}