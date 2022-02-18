package com.example.androidtest.data.network

import android.util.Log
import com.example.androidtest.data.model.Movie
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MovieService @Inject constructor(private val apiClient: MovieApiClient) {
    //metodos
    suspend fun getAllPopularMovies(): List<Movie> {
        return withContext(IO) {
            val response = try {
                apiClient.getAllPopularMovies()

            } catch (e: Exception) {
                Log.e("mTAG", "getCart: $e")
                return@withContext emptyList<Movie>()
            }
            return@withContext response.body()?.movieList ?: emptyList<Movie>()
        }
    }

}