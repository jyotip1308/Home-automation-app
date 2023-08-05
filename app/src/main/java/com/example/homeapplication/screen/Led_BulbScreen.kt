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
import androidx.compose.runtime.mutableStateListOf
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
fun LedBulbScreen(context :Context){


    val mqttClient by lazy { MqttClientHelper(context) }
    mqttClient.setCallback(object : MqttCallbackExtended {
        override fun connectComplete(reconnect: Boolean, serverURI: String) {
            Log.w(MqttClientHelper.TAG, "MQTT reconnect...$reconnect")

        }

        override fun connectionLost(cause: Throwable) {
            Log.e(MqttClientHelper.TAG, "MQTT lost..." + cause.message)
            //val st = "MQTT lost! " + cause.message
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


    LazyColumn(modifier = Modifier
        .fillMaxWidth()
        .fillMaxSize()
        //.padding(20.dp)
        .background(Color(0, 0, 51)))
    {
        item {
            LedText()
            Spacer(modifier = Modifier.height(35.dp))
            BulbRow(mqttClient)
            Spacer(modifier = Modifier.height(25.dp))
            BulbRow22(mqttClient )
            Spacer(modifier = Modifier.height(25.dp))
            BulbRow33(mqttClient)
            Spacer(modifier = Modifier.height(25.dp))
            BulbRow44(mqttClient)
        }
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
fun BulbRow(mqttClient: MqttClientHelper){

    // Initialize a list to store the state of each bulb
    val bulbStates = remember { mutableStateListOf<Boolean>() }

    // Populate the list with initial states
    if (bulbStates.isEmpty()) {
        categoryList3.forEach { _ ->
            bulbStates.add(false)
        }
    }

    LazyRow {
        items(categoryList3.zip(bulbStates)) { (category, isBulbOn) ->
            BulbRow1(category = category, isBulbOn = isBulbOn) {
                // Toggle the state of the clicked bulb
                bulbStates[categoryList3.indexOf(category)] = !isBulbOn
                val message = if (!isBulbOn) "on" else "off"
                mqttClient.publish("bulb/status", message)
            }
        }
    }

}

@Composable
fun BulbRow22(mqttClient: MqttClientHelper){

    // Initialize a list to store the state of each bulb
    val bulbStates = remember { mutableStateListOf<Boolean>() }

    // Populate the list with initial states
    if (bulbStates.isEmpty()) {
        categoryList4.forEach { _ ->
            bulbStates.add(false)
        }
    }

    LazyRow {
        items(categoryList4.zip(bulbStates)) { (category, isBulbOn) ->
            BulbRow2(category = category, isBulbOn = isBulbOn) {
                // Toggle the state of the clicked bulb
                bulbStates[categoryList4.indexOf(category)] = !isBulbOn
                val message = if (!isBulbOn) "on" else "off"
                mqttClient.publish("bulb/status", message)
            }
        }
    }

}

@Composable
fun BulbRow33(mqttClient: MqttClientHelper){

    // Initialize a list to store the state of each bulb
    val bulbStates = remember { mutableStateListOf<Boolean>() }

    // Populate the list with initial states
    if (bulbStates.isEmpty()) {
        categoryList5.forEach { _ ->
            bulbStates.add(false)
        }
    }

    LazyRow {
        items(categoryList5.zip(bulbStates)) { (category, isBulbOn) ->
            BulbRow3(category = category, isBulbOn = isBulbOn) {
                // Toggle the state of the clicked bulb
                bulbStates[categoryList5.indexOf(category)] = !isBulbOn
                val message = if (!isBulbOn) "on" else "off"
                mqttClient.publish("bulb/status", message)
            }
        }
    }
}

@Composable
fun BulbRow44(mqttClient: MqttClientHelper){

    // Initialize a list to store the state of each bulb
    val bulbStates = remember { mutableStateListOf<Boolean>() }

    // Populate the list with initial states
    if (bulbStates.isEmpty()) {
        categoryList6.forEach { _ ->
            bulbStates.add(false)
        }
    }

    LazyRow {
        items(categoryList6.zip(bulbStates)) { (category, isBulbOn) ->
            BulbRow4(category = category, isBulbOn = isBulbOn) {
                // Toggle the state of the clicked bulb
                bulbStates[categoryList6.indexOf(category)] = !isBulbOn
                val message = if (!isBulbOn) "on" else "off"
                mqttClient.publish("bulb/status", message)
            }
        }
    }

}


@Composable
fun BulbRow1(
    category: Category,
    isBulbOn: Boolean,
    onClick: () -> Unit
){
   // var isBulbOn by remember { mutableStateOf(false) }

    Box(modifier = Modifier
        .padding(end = 15.dp, start = 15.dp)
        .clickable { onClick() } // Call the provided onClick callback
        .background(
            if (isBulbOn) Color(255, 255, 153) else category.color,
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
    category: Category,
    isBulbOn: Boolean,
    onClick: () -> Unit
){

    Box(modifier = Modifier
        .padding(end = 15.dp, start = 15.dp)
        .clickable { onClick() } // Call the provided onClick callback
        .background(
            if (isBulbOn) Color(255, 255, 153) else category.color,
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
fun BulbRow3(
    category: Category,
    isBulbOn: Boolean,
    onClick: () -> Unit
){

    Box(modifier = Modifier
        .padding(end = 15.dp, start = 15.dp)
        .clickable { onClick() } // Call the provided onClick callback
        .background(
            if (isBulbOn) Color(255, 255, 153) else category.color,
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
fun BulbRow4(
    category: Category,
    isBulbOn: Boolean,
    onClick: () -> Unit
){

    Box(modifier = Modifier
        .padding(end = 15.dp, start = 15.dp)
        .clickable { onClick() } // Call the provided onClick callback
        .background(
            if (isBulbOn) Color(255, 255, 153) else category.color,
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




