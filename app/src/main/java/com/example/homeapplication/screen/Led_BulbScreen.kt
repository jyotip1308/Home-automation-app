package com.example.homeapplication.screen

import android.app.Activity
import android.content.Context
import android.content.pm.ActivityInfo
import android.util.Log
import androidx.activity.addCallback
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
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
import com.example.homeapplication.mqtt.MqttClientHelper
import com.example.homeapplication.screen.data.Category
import com.example.homeapplication.screen.data.categoryList3
import com.example.homeapplication.screen.data.categoryList4
import com.example.homeapplication.screen.data.categoryList5
import com.example.homeapplication.screen.data.categoryList6
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended
import org.eclipse.paho.client.mqttv3.MqttMessage


@Composable
fun LedBulbScreen(navController: NavHostController,context :Context){

    val mqttClient by lazy { MqttClientHelper(context) }
    mqttClient.setCallback(object : MqttCallbackExtended {
        override fun connectComplete(reconnect: Boolean, serverURI: String) {
            Log.w(MqttClientHelper.TAG, "MQTT reconnect...$reconnect")

        }

        override fun connectionLost(cause: Throwable) {
            Log.e(MqttClientHelper.TAG, "MQTT lost..." + cause.message)
            val st = "MQTT lost! " + cause.message
        }

        override fun messageArrived(topic: String, message: MqttMessage) {
            val mess = message.toString()
            val log = String.format("MQTT RX [%s]: %s", topic, mess)
            Log.w(MqttClientHelper.TAG, log)

        }

        override fun deliveryComplete(token: IMqttDeliveryToken) {
            Log.w(MqttClientHelper.TAG, "Publish success...")
        }
    })

            // Lock the screen orientation to portrait mode
    val activity = LocalContext.current as Activity
    activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

    // Handle back press (optional)
    val onBackPressed = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher
    onBackPressed?.addCallback {
        // Handle back press event here
    }

    LazyColumn(modifier = Modifier
        .fillMaxWidth()
        .padding(20.dp)
        .background(Color.White))
    {
        item {
            LedText()
            Spacer(modifier = Modifier.height(35.dp))
            BulbRow()
            Spacer(modifier = Modifier.height(40.dp))
            BottomNav2(navController = navController)
        }
    }
}

@Composable
fun LedText(){
    Box(
       modifier = Modifier
           .fillMaxWidth()
           .size(width = 400.dp, height = 80.dp)
           .background(Color(0xFFD0BCFF), RoundedCornerShape(3.dp))) {

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
fun BulbRow(){

        LazyRow{
            items(categoryList3, key = { it.id }) {
                BulbRow1(category = it)
            }
        }
        Spacer(Modifier.height(20.dp))
        LazyRow {
            items(categoryList4, key = { it.id }) {
                BulbRow2(category = it)
            }
        }
        Spacer(Modifier.height(20.dp))
        LazyRow {
            items(categoryList5, key = { it.id }) {
                BulbRow3(category = it)
            }
        }
        Spacer(Modifier.height(20.dp))
        LazyRow {
            items(categoryList6, key = { it.id }) {
                BulbRow4(category = it)
            }
        }

}

@Composable
fun BulbRow1(
    category: Category
){
    var isBulbOn by remember { mutableStateOf(false) }

    Box(modifier = Modifier
        .padding(end = 15.dp)
        .clickable { isBulbOn = !isBulbOn }
        .background(
            if (isBulbOn) Color.Yellow else category.color,
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

@Composable
fun BulbRow2(
    category: Category
){
    var isBulbOn by remember { mutableStateOf(false) }

    Box(modifier = Modifier
        .padding(end = 15.dp)
        .clickable { isBulbOn = !isBulbOn }
        .background(
            if (isBulbOn) Color.Yellow else category.color,
            RoundedCornerShape(8.dp))
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

@Composable
fun BulbRow3(
    category: Category
){
    var isBulbOn by remember { mutableStateOf(false) }

    Box(modifier = Modifier
        .padding(end = 15.dp)
        .clickable { isBulbOn = !isBulbOn }
        .background(
            if (isBulbOn) Color.Yellow else category.color,
            RoundedCornerShape(8.dp))
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

@Composable
fun BulbRow4(
    category: Category
){
    var isBulbOn by remember { mutableStateOf(false) }
    Box(modifier = Modifier
        .padding(end = 15.dp)
        .clickable { isBulbOn = !isBulbOn }
        .background(
            if (isBulbOn) Color.Yellow else category.color,
            RoundedCornerShape(8.dp))
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

@Composable
fun BottomNav2(navController: NavController){
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
                      //  .padding(9.dp),

                    )
            }

            Spacer(modifier = Modifier.width(260.dp))

            // Fan & AC Screen navigation
            IconButton(
                onClick = { navController?.navigate("FanAcScreen") },

                )
            {
                Image(
                    painter = painterResource(id = R.drawable.img8),
                    contentDescription = "",
                    modifier = Modifier
                        .size(75.dp)
                        //.padding(9.dp),
                )
            }

        }

    }
}



//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview(){
//    Surface(Modifier.fillMaxSize()) {
//        LedBulbScreen()
//    }
//
//}