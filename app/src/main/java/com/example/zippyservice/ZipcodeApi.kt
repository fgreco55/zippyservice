package com.example.zippyservice

import android.util.Log
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

object ZipcodeApi {
    private const val BASE_URL = "https://api.zippopotam.us"

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(ScalarsConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()

    val retrofitService: ZipcodeApiService by lazy {
        Log.i("Frank", "Creating the retrofit service...")
        retrofit.create(ZipcodeApiService::class.java)
    }
}

interface ZipcodeApiService {
    @GET("/us/{zipcode}")
    suspend fun getProperties(@Path("zipcode") zipcode:String) : String
}