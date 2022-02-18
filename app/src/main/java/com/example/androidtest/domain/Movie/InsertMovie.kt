package com.example.androidtest.domain.Movie

import com.example.androidtest.data.database.entities.MovieEntity
import com.example.androidtest.data.model.Movie
import com.example.androidtest.data.repository.MovieRepository
import javax.inject.Inject

data class InsertMovie @Inject constructor(private val repository: MovieRepository) {
    suspend operator fun invoke(movie: MovieEntity) =
        repository.insertMovie(movie)
}