package com.example.androidtest.domain.Movie

import com.example.androidtest.data.model.Movie
import com.example.androidtest.data.repository.MovieRepository
import javax.inject.Inject

data class UpdateImageMovie @Inject constructor(private val repository: MovieRepository){
    suspend operator fun invoke(movieId: Int, byteArray: ByteArray) = repository.updateImageMovie(movieId, byteArray)
}