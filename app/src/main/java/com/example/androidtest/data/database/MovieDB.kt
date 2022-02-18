package com.example.androidtest.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.androidtest.data.database.dao.MovieDao
import com.example.androidtest.data.database.entities.MovieEntity

@Database(entities = [MovieEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class MovieDB:RoomDatabase() {
    abstract fun getMovieDao():MovieDao
}