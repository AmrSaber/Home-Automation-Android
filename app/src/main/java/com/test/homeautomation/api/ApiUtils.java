package com.test.homeautomation.api;

public class ApiUtils {

    public static ApiService getApiService() {
        return ApiManager.getInstance();
    }
}
