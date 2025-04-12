package com.example.movies.data

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TheDBInterface {

    //list of movies
    @GET("discover/movie")
    suspend fun getMovies(
        @Query("api_key") apikey: String,
        @Query("page") page: Int,
    ): Response<MoviesResponse>


    //movie details
    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movie_id: String,
        @Query("api_key") api_key: String
    ): Movie


}