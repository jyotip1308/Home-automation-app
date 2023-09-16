package com.example.homeapplication.viewModel

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homeapplication.mqtt.MqttClientHelper
import kotlinx.coroutines.launch
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended
import org.eclipse.paho.client.mqttv3.MqttMessage


//class HomeAppViewModel(context:Context):ViewModel(){
    class HomeAppViewModel(private val context: Context):ViewModel(){

    private val mqttClient = MqttClientHelper(context.applicationContext)
    var bulbsState = mutableListOf<Boolean>()
    var acState = mutableListOf<Boolean>()//fan and ac state

    init {
        viewModelScope.launch {
            Log.d("not","find me if u can")
            startFetchingData()
        }
        // Populate the list with initial states
        if (bulbsState.isEmpty()) {
            for(i in 0..64){
                bulbsState.add(false)
                acState.add(false)
            }
        }
    }

    private suspend fun startFetchingData() {
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
    }

//    fun bulbSwitch(id: Int) {
//        bulbsState[id] = !bulbsState[id] // as button triggered state should be change
//        Log.w("tag","${bulbsState[id].toString()}")
//        val message = if (bulbsState[id]) "UPDATE_HOME_AUTOMATION_DATA=${id},0,0"
//                             else "UPDATE_HOME_AUTOMATION_DATA=0,0,0"
//      mqttClient.publish("bulb/${id}", message)
//        mqttClient.publish("HA/70:04:1D:55:92:48/$/command", message)
//    }
    fun bulbSwitch(id: Int) {
        bulbsState[id] = !bulbsState[id] // Toggle the state for the given ID
        Log.w("tag","${bulbsState[id].toString()}")

        // Calculate the sum of all IDs
        val sumOfIds = bulbsState.indices.sumBy { if (bulbsState[it]) it else 0 }

        // Build the message with the sum
        val message = "UPDATE_HOME_AUTOMATION_DATA=$sumOfIds,0,0"
        Log.w("tag", message)
        mqttClient.publish("HA/70:04:1D:55:92:48/$/command", message)

        mqttClient.subscribe("HA/HOME_AUTO/status${id}")
        // Show a Toast message when the status changes
        val statusMessage = if (bulbsState[id]) "Device id $id is on" else "Device id $id is off"
        Toast.makeText(context, statusMessage, Toast.LENGTH_SHORT).show()
    }

    //switch for fan and ac
//    fun acSwitch(id: Int) {
//        acState.add(id,!acState[id]) // as button triggered state should be change
//        Log.w("tag","${acState[id].toString()}")
//        val message = if (acState[id]) "UPDATE_HOME_AUTOMATION_DATA=${id},0,0"
//                             else "UPDATE_HOME_AUTOMATION_DATA=0,0,0"
//        mqttClient.publish("HA/70:04:1D:55:92:48/$/command", message)
// }
    fun acSwitch(id: Int) {
        acState[id] = !acState[id] // Toggle the state for the given ID
        Log.w("tag","${acState[id].toString()}")

        // Calculate the sum of all IDs
        val sumOfIds = acState.indices.sumBy { if (acState[it]) it else 0 }

        // Build the message with the sum
        val message = "UPDATE_HOME_AUTOMATION_DATA=$sumOfIds,0,0"
        Log.w("tag", message)
        mqttClient.publish("HA/70:04:1D:55:92:48/$/command", message)

        mqttClient.subscribe("HA/HOME_AUTO/status${id}")
        // Show a Toast message when the status changes
        val statusMessage = if (bulbsState[id]) "Device id $id is on" else "Device id $id is off"
        Toast.makeText(context, statusMessage, Toast.LENGTH_SHORT).show()

    }
}