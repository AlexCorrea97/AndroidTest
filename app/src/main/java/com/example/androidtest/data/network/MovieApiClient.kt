package com.example.androidtest.data.network

import com.example.androidtest.data.model.ApiMovieResponse
import retrofit2.Response
import retrofit2.http.GET

interface MovieApiClient {
    @GET("movie/popular?api_key=c93942ad7c6faeb10e25e5de6f8f50c1&language=en-US&page=1")
    suspend fun getAllPopularMovies():Response<ApiMovieResponse>
}