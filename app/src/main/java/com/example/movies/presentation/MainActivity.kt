package com.example.movies.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movies.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var  movieItemViewModel: MovieItemViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getMovies()
    }

    private fun getMovies() {

        val adapter:ItemAdapter = ItemAdapter()
        val recyclerView: RecyclerView = findViewById(R.id.MovieRecycler)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter


        lifecycleScope.launch {
            movieItemViewModel.items.collectLatest {
                adapter.submitData(it)
            }
        }

    }
}