package com.test.homeautomation.screens.main.recycler

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.test.homeautomation.R
import com.test.homeautomation.models.Device
import java.util.*

class RecyclerViewAdapter(private val devices: ArrayList<Device>) : RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(
                R.layout.device_status_row,
                viewGroup,
                false
            )
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) = viewHolder.bind(devices[i])

    override fun getItemCount(): Int = devices.size

    fun setDevices(newDevicesList: List<Device>) {
        this.devices.clear()
        this.devices.addAll(newDevicesList)
        this.notifyDataSetChanged()
    }

}

