package com.example.homeapplication.screen

import android.app.Activity
import android.content.Context
import android.content.pm.ActivityInfo
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.homeapplication.R
import com.example.homeapplication.screen.data.Category
import com.example.homeapplication.screen.data.categoryList3
import com.example.homeapplication.screen.data.categoryList4
import com.example.homeapplication.screen.data.categoryList5
import com.example.homeapplication.screen.data.categoryList6
import com.example.homeapplication.viewModel.HomeAppViewModel

@Composable
fun LedBulbScreen(context :Context,viewModel: HomeAppViewModel){

    // Lock the screen orientation to portrait mode
    val activity = LocalContext.current as Activity
    activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

    LazyColumn(modifier = Modifier
        .fillMaxWidth()
        .fillMaxSize()
        //.padding(20.dp)
        .background(Color(0, 0, 51)))
    {
        item {
            LedText()
            Spacer(modifier = Modifier.height(35.dp))
            BulbRow(viewModel)
            Spacer(modifier = Modifier.height(25.dp))
            BulbRow22(viewModel)
            Spacer(modifier = Modifier.height(25.dp))
            BulbRow33(viewModel)
            Spacer(modifier = Modifier.height(25.dp))
            BulbRow44(viewModel)
            Spacer(modifier = Modifier.height(25.dp))
           }

        Log.w("led","done ....... 1 ")
    }
}

@Composable
fun LedText(){
    Box(
       modifier = Modifier
           .fillMaxWidth()
           .size(width = 400.dp, height = 80.dp)
           .background(Color(153, 204, 255), RoundedCornerShape(3.dp))) {

        Text(text = stringResource(id = R.string.Bulb),
        style = TextStyle(
            fontSize = 35.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Serif),
            modifier = Modifier
                .padding(start = 5.dp)
                .align(Alignment.CenterStart)
        )
    }
}

@Composable
fun BulbRow(viewModel: HomeAppViewModel){

    LazyRow {
        items(categoryList3) { category ->
            BulbRow1(category = category,viewModel)
        }
    }

}

@Composable
fun BulbRow22(viewModel: HomeAppViewModel){
    LazyRow {
        items(categoryList4) { category ->
            BulbRow1(category = category,viewModel)
        }
    }
}

@Composable
fun BulbRow33(viewModel: HomeAppViewModel){
    LazyRow {
        items(categoryList5) { category ->
            BulbRow1(category = category,viewModel)
        }
    }
}

@Composable
fun BulbRow44(viewModel: HomeAppViewModel){
    LazyRow {
        items(categoryList6) { category ->
            BulbRow1(category = category,viewModel)
        }
    }
}

@Composable
fun BulbRow1(
    category: Category,
    viewModel: HomeAppViewModel
){
    var clr = remember {
        mutableStateOf<Boolean>(viewModel.bulbsState[category.id])
    }
    Box(modifier = Modifier
        .padding(end = 15.dp, start = 15.dp)
        .clickable {
            viewModel.bulbSwitch(category.id)
            clr.value = !clr.value
        } // Call the provided onClick callback
        .background(
            if (clr.value) Color(255, 255, 153) else category.color,
            RoundedCornerShape(8.dp)
        )
        .width(168.dp)
        .height(110.dp)
    ){
        Text(text = category.title, style = TextStyle(
            fontSize = 15.sp,
            fontWeight = FontWeight.W400,
            color = Color.Black
        ),
            modifier = Modifier
                .padding(start = 5.dp)
                .align(Alignment.CenterStart)
        )
        Text(text = category.subTitle, style = TextStyle(
            fontSize = 12.sp,
            fontWeight = FontWeight.W400,
            color = Color(0xFF625b71)
        ),
            modifier = Modifier
                .padding(start = 7.dp, top = 65.dp)
        )
        Image(painter = painterResource(id = category.image), contentDescription = "",
            modifier = Modifier
                .size(60.dp)
                .padding(end = 9.dp)
                .align(Alignment.BottomEnd))
    }
}