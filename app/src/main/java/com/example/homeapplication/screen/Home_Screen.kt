package com.example.homeapplication.screen

import android.app.Activity
import android.content.Context
import android.content.pm.ActivityInfo
import androidx.activity.addCallback
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomEnd
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterEnd
import androidx.compose.ui.Alignment.Companion.CenterStart
import androidx.compose.ui.Alignment.Companion.TopStart
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
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.homeapplication.R
import com.example.homeapplication.screen.data.Category
import com.example.homeapplication.screen.data.categoryList
import com.example.homeapplication.screen.data.categoryList2
import com.example.homeapplication.ui.theme.DarkOrange
import com.example.homeapplication.ui.theme.PurpleGrey40
import com.example.homeapplication.ui.theme.PurpleGrey80



@Composable
fun HomeScreen(navController: NavController){


    // Lock the screen orientation to portrait mode
    val activity = LocalContext.current as Activity
    activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

    // Handle back press (optional)
    val onBackPressed = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher
    onBackPressed?.addCallback {
        // Handle back press event here
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
            .background(Color.White)
    ){

        item {
            Header()
            Spacer(modifier = Modifier.height(15.dp))
            Heading()
            Spacer(modifier = Modifier.height(40.dp))
            DeviceRow()
            Spacer(modifier = Modifier.height(110.dp))
            BottomNav(navController = navController)
        }
    }
}


@Composable
fun Heading(){
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .size(width = 400.dp, height = 120.dp)
            .background(Color(153, 51, 255), RoundedCornerShape(10.dp))
            .padding(18.dp),

        ){
       Box(
           contentAlignment = Center,
           modifier = Modifier.fillMaxWidth(1/2f)
       ){
           Icon(painter = painterResource(id = R.drawable.img1),
               contentDescription = "My Icon",
               tint = Color.Unspecified,
               modifier = Modifier
                   .size(60.dp)
           )
       }

        Box(contentAlignment = Center,
            modifier = Modifier.fillMaxWidth(1f)){
            Column{
                Text(
                    text = stringResource(id = R.string.total_device),
                    style = TextStyle(
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.SansSerif,
                        color = Color.White
                    )
                )
                Text(
                    text = stringResource(id = R.string.Num_device),
                    style = TextStyle(
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.SansSerif,
                        color = Color(224,224,224)
                    )
                )

            }
        }

    }
}

@Composable
fun DeviceRow(){
    Column{
        CommonTitle(title = stringResource(id = R.string.categories))
        Spacer(Modifier.height(30.dp))
        LazyRow{
            items(categoryList, key = { it.id }) {
                DeviceEachRow1(category = it)
            }
        }
        Spacer(Modifier.height(20.dp))
        LazyRow {
            items(categoryList2, key = { it.id }) {
                DeviceEachRow2(category = it)
            }
        }
    }
}

@Composable
fun DeviceEachRow1(
    category: Category
){
    Box(modifier = Modifier
        .padding(end = 15.dp)

        .background(category.color, RoundedCornerShape(8.dp)
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
            .align(CenterStart)
        )
        Image(painter = painterResource(id = category.image), contentDescription = "",
        modifier = Modifier
            .size(60.dp)
            .padding(end = 9.dp)
            .align(BottomEnd))
    }
}

@Composable
fun DeviceEachRow2(
    category: Category
){
    Box(modifier = Modifier
        .padding(end = 15.dp)
        .background(category.color, RoundedCornerShape(8.dp))
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
                .align(CenterStart)
        )
        Image(painter = painterResource(id = category.image), contentDescription = "",
            modifier = Modifier
                .size(60.dp)
                .padding(end = 9.dp)
                .align(BottomEnd))
    }
}

@Composable
fun CommonTitle(
    title: String,
    onClick: () -> Unit = {}
) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = title, style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.W600,
                color = PurpleGrey40
            )
        )
    }
}



@Composable
fun Header(){
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
     Text(
         text = stringResource(id = R.string.heading_text),
         style = TextStyle(
             fontSize = 35.sp,
             fontWeight = FontWeight.Bold,
             fontFamily = FontFamily.Serif,
             color = Color.Black
         )
     
     )
    }
}

@Composable
fun BottomNav(navController: NavController){

    Box(modifier = Modifier
        .fillMaxWidth()
        .size(width = 400.dp, height = 60.dp)
        .background(Color.DarkGray, RoundedCornerShape(1.dp))) {
        Row() {
            // Led Bulb navigation
            IconButton(
                onClick = { navController?.navigate("LedBulbScreen") },

                ) {
                Image(
                    painter = painterResource(id = R.drawable.img7),
                    contentDescription = "",
                    modifier = Modifier
                        .size(90.dp)
                     //   .padding(top = 4.dp),

                    )
            }
            Spacer(modifier = Modifier.width(260.dp))

            // Fan navigation
            IconButton(
                onClick = { navController?.navigate("FanAcScreen") },

                ) {
                Image(
                    painter = painterResource(id = R.drawable.img8),
                    contentDescription = "",
                    modifier = Modifier
                        .size(105.dp)
                       // .padding(9.dp),
                )
            }


        }

    }
}

//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview(){
//    Surface(Modifier.fillMaxSize()) {
//        HomeScreen()
//    }
//
//}