package com.example.androidtest.domain.Movie

data class MovieUseCase(val getAllPopularMovies: GetAllPopularMovies,
                        val updateImageMovie: UpdateImageMovie,
                        val getMyMovies: GetMyMovies,
                        val insertMovie: InsertMovie
                        )