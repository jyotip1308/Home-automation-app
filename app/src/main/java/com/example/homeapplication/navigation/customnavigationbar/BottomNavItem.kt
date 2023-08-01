package com.example.homeapplication.navigation.customnavigationbar

import com.example.homeapplication.R


sealed class BottomBarScreen(
        val screen_route : String,
        val title : String,
        val icon : Int
    )

    // for homeScreen
    object Home : BottomBarScreen(
        "home",
        "Home",
        R.drawable.img1
    )
    // for bulbScreen
    object Bulb : BottomBarScreen(
        "bulb",
        "Bulb",
        R.drawable.img2
    )
    // for Fan & AC
    object FanAc : BottomBarScreen(
        "fanAc",
        "FanAc",
        R.drawable.img3
)