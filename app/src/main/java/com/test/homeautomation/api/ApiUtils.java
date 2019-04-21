package com.test.homeautomation.api;

public class ApiUtils {

    private static String baseUrl = "http//:localhost:3000/";

    public static JsonPlaceHolderInterface getApiService(){

        return RetrofitClientInstance.getClient(baseUrl).create(JsonPlaceHolderInterface.class);
    }



}
