package com.test.homeautomation.screens.main

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.test.homeautomation.R
import com.test.homeautomation.api.ApiManager
import com.test.homeautomation.models.Device
import com.test.homeautomation.models.TemperatureResponse
import com.test.homeautomation.screens.add_device.AddDeviceActivity
import com.test.homeautomation.screens.main.recycler.RecyclerViewAdapter
import com.test.homeautomation.utils.MessageHelper
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity() {

    private val devices = ArrayList<Device>()
    internal var adapter: RecyclerViewAdapter = RecyclerViewAdapter(devices)

    private lateinit var socketsHelper: SocketsHelper

    var waitingCalls: Int by Delegates.observable(0) { _, _, newValue ->
        main_refresh.isRefreshing = (newValue > 0)
    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        main_add_device.setOnClickListener {
            startActivity(Intent(this@MainActivity, AddDeviceActivity::class.java))
        }

        main_recycler.adapter = adapter
        main_recycler.layoutManager = LinearLayoutManager(this)

        socketsHelper = SocketsHelper { newTemperature ->
            main_temperature.text = "$newTemperature ℃"
            Unit
        }

        main_refresh.setOnRefreshListener { populate() }
    }

    override fun onStart() {
        super.onStart()
        populate()
    }

    override fun onDestroy() {
        super.onDestroy()
        socketsHelper.close()
    }

    // helper function to populate the screen (devices and temperature)
    private fun populate() {
        waitingCalls = 2

        // update the devices and their states
        ApiManager.service.devices.enqueue(object : Callback<List<Device>> {
            override fun onResponse(call: Call<List<Device>>, response: Response<List<Device>>) {
                if (!response.isSuccessful) {
                    MessageHelper.showToast("Error")
                    return
                }

                adapter.setDevices(response.body()!!)
                --waitingCalls
            }

            override fun onFailure(call: Call<List<Device>>, t: Throwable) =
                MessageHelper.showToast("Unable to call API")
        })

        // update the temperature
        ApiManager.service.temperature.enqueue(object : Callback<TemperatureResponse> {
            @SuppressLint("SetTextI18n")
            override fun onResponse(call: Call<TemperatureResponse>, response: Response<TemperatureResponse>) {
                if (!response.isSuccessful) {
                    MessageHelper.showToast("Error")
                    return
                }

                main_temperature.text = String.format("%.2f ℃", response.body()!!.temperature)
                --waitingCalls
            }

            override fun onFailure(call: Call<TemperatureResponse>, t: Throwable) =
                    MessageHelper.showToast("Unable to call API")
        })
    }
}






