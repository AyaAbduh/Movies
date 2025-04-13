package com.example.movies.domain.favourite

import com.example.movies.data.Movie
import com.example.movies.data.MovieDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class FavouriteMovieRepository @Inject constructor(
    private val dao: MovieDao
) {
    suspend fun getFavoriteMovies(): List<Movie> {
        val movies = dao.getFavoriteMovies()
        return movies
    }

    suspend fun updateFavorite(movieId: Int, isFavorite: Boolean) {
        dao.updateFavoriteStatus(movieId, isFavorite)
    }
}