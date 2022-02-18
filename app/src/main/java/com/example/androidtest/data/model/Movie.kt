package com.example.androidtest.data.model

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.room.ColumnInfo
import com.example.androidtest.R
import com.example.androidtest.data.database.entities.MovieEntity
import com.google.gson.annotations.SerializedName
import javax.annotation.Nullable

data class Movie (val id:Int,
                  val adult: Boolean,
                  @SerializedName("original_language")
                  val originalLanguage: String,
                  @SerializedName("original_title")
                  val originalTitle: String,
                  val overview: String,
                  val popularity: Double,
                  val own:Boolean=false,
                  @SerializedName("poster_path")
                  val posterPath: String,
                  @SerializedName("release_date")
                  val releaseDate: String,
                  val title: String,
                  @SerializedName("vote_average")
                  val voteAverage: Double,
                  @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
                  var photo: ByteArray? = null
)
fun MovieEntity.toMovie():Movie{
    return Movie(id, adult, originalLanguage,
        originalTitle,overview,popularity,own,posterPath,releaseDate,title, voteAverage, photo)
}

