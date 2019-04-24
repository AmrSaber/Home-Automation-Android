package com.test.homeautomation.api;

import com.test.homeautomation.models.Device;
import com.test.homeautomation.models.TemperatureResponse;
import com.test.homeautomation.models.requests.AddDeviceRequest;
import com.test.homeautomation.models.requests.UpdateStateRequest;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface ApiService {

    @GET("devices")
    Call<List<Device>> getDevices();

    @GET("devices/{id}")
    Call<Device> getDevice(@Path("id") int id);

    @GET("temperature")
    Call<TemperatureResponse> getTemperature();

    @POST("devices")
    Call<Device> addDevice(@Body AddDeviceRequest request);

    @PATCH("devices/{id}")
    Call<Device> updateState(@Path("id") int id, @Body UpdateStateRequest request);

    @DELETE("devices/{id}")
    Call<Device> deleteDevice(@Path("id") int id);
}
