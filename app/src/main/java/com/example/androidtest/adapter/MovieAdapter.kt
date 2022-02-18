package com.example.androidtest.adapter

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidtest.R
import com.example.androidtest.data.model.Movie
import com.example.androidtest.ui.dialog.DetailsDialogFragment
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import java.io.ByteArrayOutputStream


class MovieAdapter(val list: List<Movie>, val updateImage:(movie:Movie, byte:ByteArray)->Unit, val fragmentManager: FragmentManager): RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    inner class ViewHolder(v: View):RecyclerView.ViewHolder(v){
        var posterImageView:ImageView
        var nameTextView: TextView
        var voteAverageTextView:TextView
        var releaseTextView: TextView
        var favButton: ImageButton
        var cardView: CardView
        init {
            cardView=v.findViewById(R.id.movies_cardview)
            posterImageView=v.findViewById(R.id.poster_imageview)
            nameTextView=v.findViewById(R.id.name_movie_tv)
            voteAverageTextView=v.findViewById(R.id.vote_average_tv)
            releaseTextView=v.findViewById(R.id.release_date_tv)
            favButton=v.findViewById(R.id.fav_imagebutton)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =LayoutInflater.from(parent.context).inflate(R.layout.movie_cardview,parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        list[position].let {
            holder.cardView.setOnClickListener {view->
                var dialog = DetailsDialogFragment(it.title, it.overview)
                dialog.show(fragmentManager, "DialogFragment")

            }
            holder.nameTextView.text = it.title
            holder.releaseTextView.text = it.releaseDate
            holder.voteAverageTextView.text = it.voteAverage.toString()
            //Picasso.get().load("https://image.tmdb.org/t/p/w500${list[position].posterPath}").into(holder.posterImageView)
            if(it.photo==null){
                Picasso.get()
                .load("https://image.tmdb.org/t/p/w500${it.posterPath}").into(object :
                    Target {
                    override fun onBitmapLoaded(bitmap: Bitmap, from: Picasso.LoadedFrom) {
                        /* Save the bitmap or do something with it here */
                        // Set it in the ImageView
                        holder.posterImageView.setImageBitmap(bitmap)
                        val stream = ByteArrayOutputStream()
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 90, stream)
                        updateImage(it, stream.toByteArray())
                    }
                    override fun onPrepareLoad(placeHolderDrawable: Drawable?) {}
                    override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {}

                })
        }
        else{
            holder.posterImageView.setImageBitmap(BitmapFactory.decodeByteArray(it.photo, 0, it.photo!!.size))
        }
        }


    }

    override fun getItemCount(): Int {
        return list.size
    }
}