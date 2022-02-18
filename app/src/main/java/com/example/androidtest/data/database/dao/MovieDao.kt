package com.example.androidtest.data.database.dao

import androidx.room.*
import com.example.androidtest.data.database.entities.MovieEntity
import com.example.androidtest.data.model.Movie

@Dao
interface MovieDao {
    @Query("SELECT * FROM movie WHERE type_movie=0")
    suspend fun getPopularMovies():List<MovieEntity>

    @Query("SELECT * FROM movie WHERE own=:flag")
    suspend fun getOwnMovies(flag:Boolean=true):List<MovieEntity>

    @Query("SELECT * FROM movie WHERE type_movie=1")
    suspend fun getTopRatedMovies():List<MovieEntity>

    @Query("SELECT * FROM movie WHERE type_movie=2")
    suspend fun getUpComingMovies():List<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(movies:List<MovieEntity>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMovie(movies:MovieEntity)

    @Query("UPDATE movie SET photo = :bitmap WHERE id=:id")
    suspend fun updateBitmap(bitmap: ByteArray, id:Int)





}