package com.example.movies.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movies.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MoviesListFragment : Fragment() {

    @Inject
    lateinit var  movieItemViewModel: MovieItemViewModel
    @Inject
    lateinit var adapter: ItemAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movies_list, container, false)
    }

//    override fun onResume() {
//        super.onResume()
//         val navGraphActivity=activity as NavGraphActivity
//        navGraphActivity.findNavController(R.id.nav_graph).navigate(R.id.action_moviesListFragment_to_movieDetailsFragment)
//    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getMovies(view)
    }

    private fun getMovies(view: View) {

       // findNavController().navigate(R.id.action_moviesListFragment_to_movieDetailsFragment)
        val recyclerView: RecyclerView = view.findViewById(R.id.MovieRecycler)
        recyclerView.layoutManager = LinearLayoutManager(view.context)
        recyclerView.adapter = adapter


        lifecycleScope.launch {
            movieItemViewModel.items.collectLatest {
                adapter.submitData(it)
            }
        }

    }
}