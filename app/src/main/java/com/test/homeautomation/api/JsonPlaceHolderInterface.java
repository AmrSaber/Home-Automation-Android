package com.test.homeautomation.api;

import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface JsonPlaceHolderInterface {


@GET("deviceRequest")
    Call<List<deviceRequest>> getDevices();



@GET("deviceRequest/:{id}")
    Call<List<deviceRequest>> getDevice(@Path("id") int id);



@GET("deviceRequest")
    Call<List<deviceRequest>> getTempreture();



@POST("deviceRequest")
    Call<List<deviceRequest>> addDevice(@Field("name") String name,
                                 @Field("pin") int pin);

@POST("deviceRequest")
    Call <List<deviceRequest>> updateState(@Field("id") int id ,
                                    @Field("state") boolean state);

@DELETE("deviceRequest/{id}")
     Call<Void> deleteDevice(@Path("id") int id);





}
