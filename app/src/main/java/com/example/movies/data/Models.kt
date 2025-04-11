package com.example.movies.data

import com.google.gson.annotations.SerializedName

data class MoviesResponse(
    val page: Int,
    @SerializedName("results")
    val movies: List<Movie>
)

data class Movie(
    val id: Int,
    val title: String,
    val release_date: String,
    val poster_path:String,
    val overview:String,
    val runtime:Int,
)