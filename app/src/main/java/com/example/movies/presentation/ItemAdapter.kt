package com.example.movies.presentation

import android.content.ClipData.Item
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movies.R
import com.example.movies.data.Movie

class ItemAdapter(): PagingDataAdapter<Movie, ItemAdapter.ItemViewHolder>(DiffUtilCallBack) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.text_row_item, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textView: TextView = itemView.findViewById<TextView>(R.id.textView)
        private val imageView: ImageView = itemView.findViewById<ImageView>(R.id.CharacterImageView)

        fun bind(item: Movie?) {
            textView.text = item?.title
           // Glide.with(imageView.context)
             //   .load(item?.thumbnail?.path?.replace("http","https")+"."+item?.thumbnail?.extension)
               // .into(imageView)

            imageView.setOnClickListener {
              //  val intent = Intent(imageView.context, CharacterDetailsActivity::class.java)
               // intent.putExtra("id", item?.id)
               // imageView.context.startActivity(intent)
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