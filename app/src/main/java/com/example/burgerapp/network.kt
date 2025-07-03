package com.example.burgerapp

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Retrocfitclient {
    val client: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://my-json-server.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}