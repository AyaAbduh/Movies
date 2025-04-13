package com.example.movies.presentation.movieslist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation.findNavController
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Dao
import com.bumptech.glide.Glide
import com.example.movies.R
import com.example.movies.data.Movie
import javax.inject.Inject


class ItemAdapter @Inject constructor(
    private val onMovieClicked: (Movie) -> Unit
) : PagingDataAdapter<Movie, ItemAdapter.ItemViewHolder>(
    DiffUtilCallBack
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.text_row_item, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textView: TextView = itemView.findViewById<TextView>(R.id.textView)
        private val imageView: ImageView = itemView.findViewById<ImageView>(R.id.CharacterImageView)
        private val favImageView: ImageView =
            itemView.findViewById<ImageView>(R.id.FavoriteImageView)

        fun bind(item: Movie?) {
            textView.text = item?.title
            favImageView.setImageResource(if (item?.isFavorite == true) R.drawable.baseline_favorite_24 else R.drawable.baseline_favorite_border_24 )
            Glide.with(imageView.context)
                .load("https://image.tmdb.org/t/p/w500" + item?.poster_path)
                .into(imageView)

            imageView.setOnClickListener {
                val directions =
                    MoviesListFragmentDirections.actionMoviesListFragmentToMovieDetailsFragment(Id = item!!.id)
                findNavController(imageView).navigate(directions)
            }
            favImageView.setOnClickListener {
                item?.let {
                    if (it.isFavorite) {
                        favImageView.setImageResource(R.drawable.baseline_favorite_border_24)
                    } else {
                        favImageView.setImageResource(R.drawable.baseline_favorite_24)
                    }
                    onMovieClicked(it)
                }
            }

        }
    }

    object DiffUtilCallBack : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }
    }
}