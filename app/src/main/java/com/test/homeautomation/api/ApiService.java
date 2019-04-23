package com.test.homeautomation.api;

import com.test.homeautomation.models.Device;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface ApiService {

    @GET("devices")
    Call<List<Device>> getDevices();

    @GET("devices/{id}")
    Call<Device> getDevice(@Path("id") int id);

    @GET("temperature")
    Call<Float> getTempreture();

    @POST("devices")
    Call<Device> addDevice(@Field("name") String name, @Field("pin") int pin);

    @PATCH("devices/{id}")
    Call<Device> updateState(@Path("id") int id, @Field("state") int state);

    @DELETE("devices/{id}")
    Call<Device> deleteDevice(@Path("id") int id);
}
