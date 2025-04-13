package com.example.movies.presentation.movieslist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.movies.data.Movie
import com.example.movies.domain.movieslist.MovieItemRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieItemViewModel @Inject constructor(private val repository: MovieItemRepository) : ViewModel() {

    val items: Flow<PagingData<Movie>> = repository.getPagedItems()
        .cachedIn(viewModelScope)

    fun updateFavoriteState(movie: Movie) {
        viewModelScope.launch {
            repository.updateFavoriteState(movie.id, !movie.isFavorite)
        }
    }
}