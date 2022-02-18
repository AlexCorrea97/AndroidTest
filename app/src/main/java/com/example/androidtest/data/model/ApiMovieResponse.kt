package com.example.androidtest.data.model

import com.google.gson.annotations.SerializedName

data class ApiMovieResponse(val page:Int,
                            @SerializedName("results")
                            val movieList:MutableList<Movie>)