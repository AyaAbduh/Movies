package com.example.movies.presentation

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.example.movies.R
import com.example.movies.data.NetworkUtil
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NavGraphActivity : AppCompatActivity() {


    lateinit var navController:NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nav_graph)

        NetworkUtil.startNetworkMonitoring(this)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
         navController = navHostFragment.navController

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_favorite -> {
                if(navController.currentDestination?.id==R.id.moviesListFragment)
                    navController.navigate(R.id.action_moviesListFragment_to_favouriteFragment)
                else if(navController.currentDestination?.id==R.id.movieDetailsFragment)
                    navController.navigate(R.id.action_movieDetailsFragment_to_favouriteFragment2)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}
