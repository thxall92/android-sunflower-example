package com.eunhye.com.android_sunflower_example

import android.os.Bundle
import androidx.core.view.GravityCompat
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.Navigation
import androidx.navigation.ui.*
import com.eunhye.com.android_sunflower_example.databinding.ActivityGardenBinding
import kotlinx.android.synthetic.main.activity_garden.*

class GardenActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityGardenBinding = DataBindingUtil.setContentView(this, R.layout.activity_garden)
        drawerLayout = binding.drawerLayout


        val navController = Navigation.findNavController(this, R.id.garden_nav_fragment)

        // Set up ActionBar
        setSupportActionBar(binding.toolbar)
        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)

        // Set up navigation menu
        binding.navigationView.setupWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(
            Navigation.findNavController(this, R.id.garden_nav_fragment), drawerLayout)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}
