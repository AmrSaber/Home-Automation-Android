package com.test.homeautomation.screens.main

import android.app.Activity
import android.content.Context
import com.github.nkzawa.emitter.Emitter
import com.github.nkzawa.socketio.client.IO
import com.github.nkzawa.socketio.client.Socket
import com.test.homeautomation.api.ApiManager
import org.json.JSONObject

import java.net.URISyntaxException

class SocketsHelper(
    temperatureCallback: (Double)->Unit
//    deviceChangedCallback: ()->Unit,
//    devicesChangedCallback: ()->Unit
) {

    private val socket: Socket = IO.socket(ApiManager.BASE_URL)

    init {

        // listen on temperature change
        socket.on("temperature-changed") { data ->
            val temperatureData = data[0] as JSONObject
            temperatureCallback(temperatureData.getDouble("temperature"))
        }

        // listen on (one) device change
//        socket.on("device-state-changed") { deviceChangedCallback() }

        // listen on devices change
//        socket.on("devices-changed") { devicesChangedCallback() }

        socket.connect()
    }

    fun close() {
        socket.disconnect()
        socket.off()
    }

}
