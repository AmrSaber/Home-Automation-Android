package com.test.homeautomation.screens.main

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.test.homeautomation.R
import com.test.homeautomation.api.ApiUtils
import com.test.homeautomation.models.Device
import com.test.homeautomation.models.TemperatureResponse
import com.test.homeautomation.screens.add_device.AddDeviceActivity
import com.test.homeautomation.screens.main.recycler.RecyclerViewAdapter
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    private val devices = ArrayList<com.test.homeautomation.models.Device>()

    internal var adapter: RecyclerViewAdapter = RecyclerViewAdapter(devices)

    private lateinit var socketsHelper: SocketsHelper

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

    private fun populate() {
        // update the devices and their states
        ApiUtils.getApiService().devices.enqueue(object : Callback<List<Device>> {
            override fun onResponse(call: Call<List<Device>>, response: Response<List<Device>>) {
                if (!response.isSuccessful) {
                    Toast.makeText(
                        applicationContext,
                        "Unable to call API",
                        Toast.LENGTH_SHORT
                    ).show()

                }
                adapter.setDevices(response.body())
                main_refresh.isRefreshing = false
            }

            override fun onFailure(call: Call<List<Device>>, t: Throwable) {
                Toast.makeText(
                    applicationContext,
                    "Unable to call API", Toast.LENGTH_SHORT
                ).show()
                call.cancel()
            }
        })

        // update the temperature
        ApiUtils.getApiService().temperature.enqueue(object: Callback<TemperatureResponse> {
            @SuppressLint("SetTextI18n")
            override fun onResponse(call: Call<TemperatureResponse>, response: Response<TemperatureResponse>) {
                if (!response.isSuccessful) return
                main_temperature.text = String.format("%.2f ℃", response.body()!!.temperature)
            }

            override fun onFailure(call: Call<TemperatureResponse>, t: Throwable){}
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        socketsHelper.close()
    }
}






