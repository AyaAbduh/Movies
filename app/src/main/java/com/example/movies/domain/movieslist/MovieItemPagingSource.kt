package com.example.movies.domain.movieslist

import androidx.paging.PagingSource
import androidx.paging.PagingState
import androidx.room.Dao
import com.example.movies.data.Movie
import com.example.movies.data.MovieDao
import com.example.movies.data.TheDBInterface

class MovieItemPagingSource(
    private val theDBInterface: TheDBInterface,
    private val dao: MovieDao
) : PagingSource<Int, Movie>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val page = params.key ?: 1

        return try {
            val response = theDBInterface.getMovies("4d117eb81482c3385a4a97c6a874fcef",page)
            var items = response.body()?.movies ?: emptyList()

            dao.insertAll(items)

            LoadResult.Page(
                data = items,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (items.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}