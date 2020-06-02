package com.netlify.anshulgupta.gdg_finder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.netlify.anshulgupta.gdg_finder.databinding.ActivityMainBinding
import com.netlify.anshulgupta.gdg_finder.databinding.ActivityMainBindingImpl

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        setupNavigation()
    }

    /**
     * Called when the hamburger menu or back button are pressed on the Toolbar
     *
     * Delegate this to Navigation.
     */
    override fun onSupportNavigateUp()
            = navigateUp(findNavController(R.id.nav_host_fragment), binding.drawerLayout)

    /**
     * Setup Navigation for this Activity
     */
    private fun setupNavigation() {
        val navController = findNavController(R.id.nav_host_fragment)
        setSupportActionBar(binding.toolbar)

        setupActionBarWithNavController(navController,binding.drawerLayout)

        binding.navigationView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener {
                _,
                destination: NavDestination,
                _ ->
            val toolbar = supportActionBar ?: return@addOnDestinationChangedListener
            when(destination.id){
                R.id.home -> {
                    toolbar.setDisplayShowHomeEnabled(false)
                    binding.toolbarLogoImage.visibility = View.VISIBLE
                }
                else -> {
                    toolbar.setDisplayShowTitleEnabled(true)
                    binding.toolbarLogoImage.visibility = View.GONE
                }
            }


        }
    }
}
