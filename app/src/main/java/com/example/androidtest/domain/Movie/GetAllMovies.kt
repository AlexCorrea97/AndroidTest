package com.example.androidtest.domain.Movie

import com.example.androidtest.data.repository.MovieRepository
import javax.inject.Inject

class GetAllPopularMovies @Inject constructor(private val repository: MovieRepository){
    suspend operator fun invoke() = repository.getAllPopularMovies()
}