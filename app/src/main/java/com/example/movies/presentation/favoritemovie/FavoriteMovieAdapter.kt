package com.example.movies.presentation.favoritemovie

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movies.R
import com.example.movies.data.Movie
import javax.inject.Inject

class FavoriteMovieAdapter@Inject constructor(private val dataSet: List<Movie>,
                                              private val onFavoriteClick: (Movie) -> Unit) :
    RecyclerView.Adapter<FavoriteMovieAdapter.ViewHolder>() {


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView
        val imageView: ImageView
        val favouriteImageView:ImageView

        init {
            textView = view.findViewById(R.id.textView)
            imageView=view.findViewById<ImageView>(R.id.CharacterImageView)
            favouriteImageView=view.findViewById<ImageView>(R.id.FavoriteImageView)
        }

    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.text_row_item, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.textView.text = dataSet.get(position).title

        Glide.with(viewHolder.imageView.context)
            .load("https://image.tmdb.org/t/p/w500"+dataSet.get(position)?.poster_path)
            .into(viewHolder.imageView)

        viewHolder.favouriteImageView.setOnClickListener {
            onFavoriteClick(dataSet.get(position))
        }

    }

    override fun getItemCount() = dataSet.size

}