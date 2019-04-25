package com.test.homeautomation.screens.main.recycler

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import com.test.homeautomation.R
import com.test.homeautomation.api.ApiManager
import com.test.homeautomation.models.Device
import com.test.homeautomation.models.requests.UpdateStateRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val name: TextView = itemView.findViewById(R.id.deviceName_text_view)
    private val deviceSwitch: Switch = itemView.findViewById(R.id.device_state_switch)

    private var currentDevice: Device? = null

    fun bind(newDevice: Device) {
        this.currentDevice = newDevice

        this.name.text = newDevice.getName()
        this.deviceSwitch.setOnCheckedChangeListener(null)
        this.deviceSwitch.isChecked = newDevice.getState() == 1

        this.deviceSwitch.setOnCheckedChangeListener { _, isChecked ->
            val newState = if (isChecked) 1 else 0
            ApiManager.service
                .updateState(currentDevice!!.id, UpdateStateRequest(newState))
                .enqueue(object : Callback<Device> {
                    override fun onResponse(call: Call<Device>, response: Response<Device>) {
                        if (!response.isSuccessful) {
                            onFail()
                        }
                    }

                    override fun onFailure(call: Call<Device>, t: Throwable) = onFail()
                })
        }
    }

    private fun onFail() {
        Toast.makeText(
            this@ViewHolder.itemView.context,
            "Could not update device " + currentDevice!!.name,
            Toast.LENGTH_SHORT
        ).show()
    }
}
