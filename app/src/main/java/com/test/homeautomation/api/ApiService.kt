package com.test.homeautomation.api

import com.test.homeautomation.models.Device
import com.test.homeautomation.models.TemperatureResponse
import com.test.homeautomation.models.requests.AddDeviceRequest
import com.test.homeautomation.models.requests.UpdateStateRequest
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @get:GET("devices")
    val devices: Call<List<Device>>

    @get:GET("temperature")
    val temperature: Call<TemperatureResponse>

//    @GET("devices/{id}")
//    fun getDevice(@Path("id") id: Int): Call<Device>

    @POST("devices")
    fun addDevice(@Body request: AddDeviceRequest): Call<Device>

    @PATCH("devices/{id}")
    fun updateState(@Path("id") id: Int, @Body request: UpdateStateRequest): Call<Device>

//    @DELETE("devices/{id}")
//    fun deleteDevice(@Path("id") id: Int): Call<Device>
}
