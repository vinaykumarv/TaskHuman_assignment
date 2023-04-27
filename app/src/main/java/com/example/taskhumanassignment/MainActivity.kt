package com.example.taskhumanassignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.taskhumanassignment.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mNavController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val navHost =
            supportFragmentManager.findFragmentById(R.id.container) as NavHostFragment
        binding.bottomNavigation.setupWithNavController(navHost.navController)
        mNavController = navHost.navController
//        mNavController.addOnDestinationChangedListener(this)

        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.navigation_discover, R.id.navigation_reconnect, R.id.navigation_bookings, R.id.navigation_messages, R.id.navigation_blogs)
        )
        //setupActionBarWithNavController(mNavController, appBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean {
        return mNavController.navigateUp(appBarConfiguration)
    }

//    override fun onDestinationChanged(
//        controller: NavController,
//        destination: NavDestination,
//        arguments: Bundle?,
//    ) {
//        val (id, desc) = when (destination.id) {
//            R.id.navigation_discover -> {
//               R.drawable.ic_home to R.string.title_discover
//            }
//            R.id.navigation_reconnect -> {
//                R.drawable.ic_connect to R.string.title_reconnect
//            }
//            R.id.navigation_bookings -> {
//                R.drawable.ic_bookmark to R.string.title_bookings
//            }
//            R.id.navigation_messages -> {
//                R.drawable.ic_messages to R.string.title_messages
//            }
//            else -> {
//                R.drawable.ic_blogs to R.string.title_blogs
//            }
//        }
//    }
}