package com.example.androidtest.data.database.entities

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.util.Log
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.androidtest.R
import com.example.androidtest.data.model.Movie
import com.google.gson.annotations.SerializedName
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import javax.annotation.Nullable

@Entity(tableName="movie")
data class MovieEntity(@PrimaryKey(autoGenerate = true) val id:Int = 0,
                       @ColumnInfo(name="type_movie")val typeMovie:Int, //0->POPULAR  1->TOP_RATED  2->UPCOMING
                       val adult: Boolean,
                       @ColumnInfo(name="original_language")val originalLanguage: String,
                       @ColumnInfo(name="original_title")val originalTitle: String,
                       val overview: String,
                       val popularity: Double,
                       val own: Boolean = false,
                       @ColumnInfo(name="poster_path")val posterPath: String,
                       @ColumnInfo(name="release_date")val releaseDate: String,
                       val title: String,
                       @ColumnInfo(name="vote_average")val voteAverage: Double,
                       @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
                       var photo: ByteArray? = null
                        )

fun Movie.toMovieEntity(typeMovie:Int):MovieEntity {

    return MovieEntity(
        id, typeMovie, adult, originalLanguage, originalTitle, overview, popularity, own,
        posterPath, releaseDate, title, voteAverage, photo
    )
}

