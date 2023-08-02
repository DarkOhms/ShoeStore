package com.udacity.shoestore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.udacity.shoestore.R.navigation.navigation
import com.udacity.shoestore.databinding.ActivityMainBinding
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private val storeViewModel: StoreViewModel by viewModels()
    private lateinit var appBarConfiguration : AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        //setup navigation
        val navController = findNavController(R.id.nav_host_fragment)
        setSupportActionBar(binding.toolbar)

        appBarConfiguration = AppBarConfiguration(navController.graph)

        //one way to do it
        NavigationUI.setupActionBarWithNavController(this,navController,appBarConfiguration)

        //another way
        //binding.toolbar.setupWithNavController(navController,appBarConfiguration)

        Timber.plant(Timber.DebugTree())
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.nav_host_fragment)
        return super.onSupportNavigateUp()
    }
}
