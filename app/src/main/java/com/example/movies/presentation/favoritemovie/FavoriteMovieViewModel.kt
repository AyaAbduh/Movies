package com.example.movies.presentation.favoritemovie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movies.data.Movie
import com.example.movies.domain.favourite.FavouriteMovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class FavoriteMovieViewModel @Inject constructor(
    private val repository: FavouriteMovieRepository
) : ViewModel() {

    private val _movies = MutableStateFlow<List<Movie>>(emptyList())
    val movies: StateFlow<List<Movie>> = _movies.asStateFlow()

    init {
        viewModelScope.launch {
            val favoriteMovies = repository.getFavoriteMovies()
            _movies.value = favoriteMovies
        }
    }

    fun toggleFavorite(movie: Movie) {
        viewModelScope.launch {
            repository.updateFavorite(movie.id, !movie.isFavorite)
        }
    }
}
