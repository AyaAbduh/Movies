package com.example.movies.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.movies.data.Movie
import com.example.movies.domain.MovieItemRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieItemViewModel @Inject constructor(private val repository: MovieItemRepository) : ViewModel() {

    // Exposing paginated data as a Flow to the UI
    val items: Flow<PagingData<Movie>> = repository.getPagedItems()
        .cachedIn(viewModelScope)  // Cache the data in the viewModel scope for efficient paging
}