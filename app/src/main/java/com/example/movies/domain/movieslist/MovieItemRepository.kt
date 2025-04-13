package com.example.movies.domain.movieslist

import android.net.Network
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.movies.data.Movie
import com.example.movies.data.MovieDao
import com.example.movies.data.NetworkUtil
import com.example.movies.data.TheDBInterface
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class MovieItemRepository@Inject constructor(private val theDBInterface: TheDBInterface, private val itemDao: MovieDao) {

    fun getPagedItems(): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                if (NetworkUtil.isConnected) {
                    MovieItemPagingSource(theDBInterface, itemDao)
                } else {
                    itemDao.getMovies()
                }
            }
        ).flow
    }
}