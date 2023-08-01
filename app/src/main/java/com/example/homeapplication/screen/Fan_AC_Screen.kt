package com.example.homeapplication.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterStart
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import com.example.homeapplication.screen.data.categoryList7


@Composable
fun FanAcScreen(navController: NavHostController ){
    LazyColumn(modifier = Modifier
        .fillMaxWidth()
        .padding(20.dp)
        .background(Color.White)){
        item {
            FanAcText()
            Spacer(modifier = Modifier.height(80.dp))
            FanAcColumn()
            Spacer(modifier = Modifier.height(30.dp))
            AcStatus()
            Spacer(modifier = Modifier.height(145.dp))
            BottomNav3(navController = navController)

        }
    }
}

@Composable
fun FanAcText(){

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .size(width = 400.dp, height = 80.dp)
            .background(Color(0xFFD0BCFF), RoundedCornerShape(3.dp)))
    {

        Text(text = stringResource(id = R.string.FanAc),
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
fun FanAcColumn() {
    //Column for Fan And Ac
    LazyRow {
        items(categoryList7, key = { it.id }) {
            FanStatus(category = it)
        }
    }

}

//Status Box of Fan
@Composable
fun FanStatus(
    category: Category
){
    var isBulbOn by remember { mutableStateOf(false) }
    Box(modifier = Modifier
        .padding(start = 80.dp, end = 15.dp)
        .clickable { isBulbOn = !isBulbOn }
        .background(
            if (isBulbOn) Color(51,255,255) else category.color,
            RoundedCornerShape(8.dp))
        .width(188.dp)
        .height(150.dp),
        contentAlignment = CenterStart
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
                .padding(start = 5.dp, top = 45.dp)


        )
        Image(painter = painterResource(id = category.image), contentDescription = "",
            modifier = Modifier
                .size(60.dp)
                .padding(end = 9.dp)
                .align(Alignment.BottomEnd))
    }

}


//Status Box of AC
@Composable
fun AcStatus(

){
    var isBulbOn by remember { mutableStateOf(false) }
    Box(modifier = Modifier
        .padding(start = 80.dp, end = 15.dp)
        .clickable { isBulbOn = !isBulbOn }
        .background(
            if (isBulbOn) Color(51,255,255) else Color(0XFFD7D5D3), RoundedCornerShape(8.dp))
        .width(188.dp)
        .height(150.dp),
        contentAlignment = CenterStart
    ){
        Text(text = "AC", style = TextStyle(
            fontSize = 15.sp,
            fontWeight = FontWeight.W400,
            color = Color.Black
        ),
            modifier = Modifier
                .padding(start = 5.dp)
                .align(Alignment.CenterStart)
        )
        Text(text = "Status", style = TextStyle(
            fontSize = 12.sp,
            fontWeight = FontWeight.W400,
            color = Color(0xFF625b71)
        ),
            modifier = Modifier
                .padding(start = 7.dp, top = 50.dp)


        )
        Image(painter = painterResource(id =R.drawable.img4), contentDescription = "",
            modifier = Modifier
                .size(60.dp)
                .padding(end = 9.dp)
                .align(Alignment.BottomEnd))
    }

}



@Composable
fun BottomNav3(navController: NavController){
    Box(modifier = Modifier
        .fillMaxWidth()
        .size(width = 400.dp, height = 60.dp)
        .background(Color.DarkGray, RoundedCornerShape(1.dp))){
        Row() {
            // HomeScreen navigation
            IconButton(
                onClick = { navController?.navigate("HomeScreen") },

                )
            {
                Image(
                    painter = painterResource(id = R.drawable.img6),
                    contentDescription = "",
                    modifier = Modifier
                        .size(70.dp)
                       // .padding(9.dp),

                    )
            }
            Spacer(modifier = Modifier.width(260.dp))

            IconButton(
                onClick = { navController?.navigate("LedBulbScreen") },

                ) {
                Image(
                    painter = painterResource(id = R.drawable.img7),
                    contentDescription = "",
                    modifier = Modifier
                        .size(75.dp)
                       // .padding(9.dp),
                )
            }

        }

    }
}


//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview2() {
//    Surface(Modifier.fillMaxSize()) {
//        FanAcScreen()
//    }
//}


