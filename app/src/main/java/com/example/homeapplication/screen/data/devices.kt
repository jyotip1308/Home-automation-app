package com.example.homeapplication.screen.data

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import com.example.homeapplication.R
import com.example.homeapplication.ui.theme.DeviceOne
import com.example.homeapplication.ui.theme.DeviceTwo

data class Category(
    val id: Int,
    val title: String,
    val subTitle : String,
    @DrawableRes val image: Int,
    val color: Color
)
 val categoryList = listOf(
    Category(
        5,
        "Led Bulb",
        "",
        R.drawable.img2,
        DeviceOne
    ),
    Category(
        6,
        "Fan",
        "",
        R.drawable.img3,
        DeviceTwo
    )
)

val categoryList2 = listOf(
    Category(
        7,
        "AC",
        "",
        R.drawable.img4,
        DeviceOne
    )
)

val categoryList3 = listOf(
    Category(
        1,
        "1st Bulb",
        "Status",
        R.drawable.img2,
        DeviceOne
    ),
    Category(
        2,
        "2nd Bulb",
        "Status",
        R.drawable.img2,
        DeviceTwo
    )
)
val categoryList4 = listOf(
    Category(
        4,
        "3rd Bulb",
        "Status",
        R.drawable.img2,
        DeviceOne
    ),
    Category(
        8,
        "4th Bulb",
        "Status",
        R.drawable.img2,
        DeviceTwo
    )
)
val categoryList5 = listOf(
    Category(
        16,
        "5th Bulb",
        "Status",
        R.drawable.img2,
        DeviceTwo
    ),
    Category(
        32,
        "6th Bulb",
        "Status",
        R.drawable.img2,
        DeviceOne
    )
)

val categoryList6 = listOf(
    Category(
        64,
        "7th Bulb",
        "Status",
        R.drawable.img2,
        DeviceTwo
    )
)
val categoryList7 = listOf(
    Category(
        1,
        "Ac",
        "Status",
        R.drawable.img4,
        DeviceTwo
    ),
    Category(
        2,
        "Fan",
        "Status",
        R.drawable.img3,
        DeviceTwo
    )
)


