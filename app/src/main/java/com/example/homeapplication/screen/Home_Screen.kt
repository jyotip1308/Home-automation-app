package com.example.homeapplication.screen

import android.app.Activity
import android.content.pm.ActivityInfo
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.BottomEnd
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterStart
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
import com.example.homeapplication.screen.data.categoryList
import com.example.homeapplication.screen.data.categoryList2
import com.example.homeapplication.viewModel.HomeAppViewModel


@Composable
fun HomeScreen(viewModel: HomeAppViewModel){

    // Lock the screen orientation to portrait mode
    val activity = LocalContext.current as Activity
    activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT


    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxSize()
            //.padding(5.dp)
            .background(Color(0,0,51))
    ){

        item {
            Header()
            Spacer(modifier = Modifier.height(15.dp))
            Heading()
            Spacer(modifier = Modifier.height(60.dp))
            DeviceRow()
        }
    }
}
@Composable 
fun Heading(){
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .size(width = 400.dp, height = 100.dp)
            .background(Color(51, 153, 225), RoundedCornerShape(2.dp))
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
        .padding(end = 15.dp, start = 15.dp)
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
fun DeviceEachRow2(
    category: Category
){
    Box(modifier = Modifier
        .padding(end = 15.dp, start = 15.dp)
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
    //onClick: () -> Unit = {}
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 5.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = title, style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.W600,
                color = Color(204,225,225)
            )
        )
    }
}

@Composable
fun Header(){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 5.dp)
    ) {
     Text(
         text = stringResource(id = R.string.heading_text),
         style = TextStyle(
             fontSize = 35.sp,
             fontWeight = FontWeight.Bold,
             fontFamily = FontFamily.Serif,
             color = Color.White,

         )
     
     )
    }
}


