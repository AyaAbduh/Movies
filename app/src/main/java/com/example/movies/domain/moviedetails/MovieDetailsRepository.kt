package com.example.movies.domain.moviedetails

import com.example.movies.data.Movie
import com.example.movies.data.TheDBInterface
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class MovieDetailsRepository @Inject constructor(private val theDBInterface: TheDBInterface) {
        fun getMovieDetails( movie_id:String): Flow<Movie> = flow {
            emit(theDBInterface.getMovieDetails(movie_id , "4d117eb81482c3385a4a97c6a874fcef"))
        }
    }
