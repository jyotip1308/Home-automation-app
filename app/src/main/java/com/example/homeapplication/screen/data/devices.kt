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
        1,
        "Led Bulb",
        "",
        R.drawable.img2,
        DeviceOne
    ),
    Category(
        2,
        "Fan",
        "",
        R.drawable.img3,
        DeviceTwo
    )
)

val categoryList2 = listOf(
    Category(
        3,
        "AC",
        "",
        R.drawable.img4,
        DeviceOne
    )
)

val categoryList3 = listOf(
    Category(
        5,
        "1st Bulb",
        "Status",
        R.drawable.img2,
        DeviceOne
    ),
    Category(
        6,
        "2nd Bulb",
        "Status",
        R.drawable.img2,
        DeviceTwo
    )
)
val categoryList4 = listOf(
    Category(
        7,
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
        9,
        "5th Bulb",
        "Status",
        R.drawable.img2,
        DeviceTwo
    ),
    Category(
        10,
        "6th Bulb",
        "Status",
        R.drawable.img2,
        DeviceOne
    )
)

val categoryList6 = listOf(
    Category(
        11,
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


