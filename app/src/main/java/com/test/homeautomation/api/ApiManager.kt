package com.test.homeautomation.api

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiManager {

    const val BASE_URL = "http://192.168.43.17:3000/"

    val service: ApiService by lazy {
        val gson = GsonBuilder()
            .setLenient()
            .create()

        val okHttpClient = OkHttpClient.Builder()
            .readTimeout(15, TimeUnit.MINUTES)
            .connectTimeout(15, TimeUnit.MINUTES)
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()

        retrofit.create(ApiService::class.java)
    }

}
