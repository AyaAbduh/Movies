package com.example.movies.domain

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.movies.data.Movie
import com.example.movies.data.TheDBInterface
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class MovieItemRepository@Inject constructor(private val theDBInterface: TheDBInterface) {

    fun getPagedItems(): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,      // Number of items per page
                enablePlaceholders = false  // Disable placeholders for empty pages
            ),
            pagingSourceFactory = { MovieItemPagingSource(theDBInterface) }
        ).flow
    }
}