package com.test.homeautomation.screens.add_device

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.test.homeautomation.R
import com.test.homeautomation.api.ApiManager
import com.test.homeautomation.models.Device
import com.test.homeautomation.models.requests.AddDeviceRequest
import com.test.homeautomation.utils.MessageHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddDeviceActivity : AppCompatActivity() {
    private val addedDeviceName: EditText = this.findViewById(R.id.device_name_editText)
    private val addedDevicePin: EditText = this.findViewById(R.id.pin_id_editText)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_device_popup)
    }

    fun submitDevice(view: View) {
        val deviceName = addedDeviceName.text.toString()
        val pin = Integer.parseInt(addedDevicePin.text.toString())

        val add = ApiManager.service.addDevice(AddDeviceRequest(deviceName, pin))

        add.enqueue(object : Callback<Device> {
            override fun onResponse(call: Call<Device>, response: Response<Device>) {
                if (!response.isSuccessful) {
                    MessageHelper.showToast("Error")
                    return
                }

                MessageHelper.showToast("Device added successfully")
                this@AddDeviceActivity.finish()
            }

            override fun onFailure(call: Call<Device>, t: Throwable) {
                MessageHelper.showToast("Unable to call API")
            }
        })
    }

    fun closeActivity(view: View) {
        this@AddDeviceActivity.finish()
    }
}
