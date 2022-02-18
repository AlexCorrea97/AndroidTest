package com.example.androidtest.data.repository

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.util.Log
import com.example.androidtest.data.database.dao.MovieDao
import com.example.androidtest.data.database.entities.MovieEntity
import com.example.androidtest.data.database.entities.toMovieEntity
import com.example.androidtest.data.model.Movie
import com.example.androidtest.data.model.toMovie
import com.example.androidtest.data.network.MovieService
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import javax.inject.Inject

class MovieRepository @Inject constructor(val movieService: MovieService,
                                          val movieDao: MovieDao,
){
    suspend fun getAllPopularMovies(): List<Movie>{
        //del server o de room
        var movieList = movieService.getAllPopularMovies()
        if(movieList.isNotEmpty()){
            try{
                val movie: List<MovieEntity> = movieList.map { it.toMovieEntity(0) }
                movieDao.insertAll(movie)
            }catch (ex:Exception){
                Log.d("Ex:", ex.toString())
            }
        }else{
            movieList = movieDao.getPopularMovies().map {
                it.toMovie()
            }
        }

        return movieList
    }

    suspend fun getMyMovies():List<MovieEntity>{
        return movieDao.getOwnMovies()
    }

    suspend fun updateImageMovie(id:Int, byteArray: ByteArray){

        try{movieDao.updateBitmap(byteArray, id)}
        catch(ex:Exception){
            Log.d("tag", ex.toString() )
        }
    }

    suspend fun insertMovie(movie: MovieEntity):MovieEntity {
        movieDao.insertMovie(movie)
        return movie
    }




}