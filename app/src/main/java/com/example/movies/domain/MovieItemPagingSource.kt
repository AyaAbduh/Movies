package com.example.movies.domain

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.movies.data.Movie
import com.example.movies.data.TheDBInterface

class MovieItemPagingSource(
    private val theDBInterface: TheDBInterface
) : PagingSource<Int, Movie>() {

    // This method loads data based on the current page number
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val page = params.key ?: 1  // Default to page 1 if no key is provided
        val pageSize = params.loadSize

        return try {
            val response = theDBInterface.getMovies("4d117eb81482c3385a4a97c6a874fcef",page)
            val items = response.movies

            // Return paginated data (prev, next key management)
            LoadResult.Page(
                data = items,
                prevKey = if (page == 1) null else page - 1,  // No previous page on the first page
                nextKey = if (items.isEmpty()) null else page + 1  // Next page exists if items are returned
            )
        } catch (e: Exception) {
            LoadResult.Error(e)  // Handle errors
        }
    }

    // Return the refresh key for reloading (for pagination handling)
    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}