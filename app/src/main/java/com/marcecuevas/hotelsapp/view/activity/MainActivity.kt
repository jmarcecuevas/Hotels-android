package com.marcecuevas.hotelsapp.view.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.marcecuevas.hotelsapp.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navController = Navigation.findNavController(this,R.id.fragment)

        //Setting the navigation controller to Bottom Nav
        bottomNav.setupWithNavController(navController)

        NavigationUI.setupWithNavController(toolbar,navController)

        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            when (destination.id) {
                R.id.hotelDetailFragment -> hideBottomNavigation()
                R.id.imageViewerFragment -> hideBottomNavigation()
                else -> showBottomNavigation()
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, null)
    }

    private fun hideBottomNavigation() {
        with(bottomNav) {
            if (visibility == View.VISIBLE && alpha == 1f) {
                visibility = View.GONE
                shadowToolbar.visibility = View.GONE
            }
        }
    }

    private fun showBottomNavigation() {
        with(bottomNav) {
            visibility = View.VISIBLE
            shadowToolbar.visibility = View.VISIBLE
            val ENTER_DURATION = 1000L
            animate()
                .alpha(1f)
                .duration = ENTER_DURATION
        }
    }
}
