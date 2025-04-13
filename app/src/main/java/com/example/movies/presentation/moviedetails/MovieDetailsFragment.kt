package com.example.movies.presentation.moviedetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.movies.R
import com.example.movies.presentation.movieslist.MovieItemViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MovieDetailsFragment : Fragment() {

    private val movieDetailsViewModel: MovieDetailsViewModel by viewModels()


    private val safeArgs : MovieDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Toast.makeText(view.context,safeArgs.Id.toString(),Toast.LENGTH_LONG).show()

        movieDetailsViewModel.loadMovie(safeArgs.Id.toString())
        lifecycleScope.launch {
            movieDetailsViewModel.movie.collect { movie ->
                movie?.let {
                    Glide.with(view.context)
                        .load("https://image.tmdb.org/t/p/w500"+it.poster_path)
                        .into(view.findViewById(R.id.movieImageView))
                   val title  =view.findViewById<TextView>(R.id.movieTitleTextView)
                    title.text=it.title
                    view.findViewById<TextView>(R.id.releaseDateTextView).text=it.release_date

                    view.findViewById<TextView>(R.id.overViewTextView).text=it.overview
                    view.findViewById<TextView>(R.id.runTimeTextView).text=it.runtime.toString()



                }
            }
        }



    }
}