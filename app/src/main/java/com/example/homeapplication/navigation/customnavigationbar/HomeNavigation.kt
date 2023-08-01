package com.example.homeapplication.navigation.customnavigationbar

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.homeapplication.screen.FanAcScreen
import com.example.homeapplication.screen.HomeScreen
import com.example.homeapplication.screen.LedBulbScreen


@Composable
fun Navigation(context: Context) {
    var navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "HomeScreen",
    )

    {
        composable("HomeScreen") {
            HomeScreen(navController = navController)
        }
        composable("LedBulbScreen") {
            LedBulbScreen(navController = navController, context )
        }
        composable("FanAcScreen") {
            FanAcScreen(navController = navController)
        }


    }


}


