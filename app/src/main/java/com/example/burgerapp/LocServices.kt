package com.example.burgerapp

import com.example.burgerapp.Data.FoodsModel
import com.example.burgerapp.Data.LocationsModel
import retrofit2.Call
import retrofit2.http.GET

interface LocServices {

    @GET("TitouanCha/demo/location")
    fun getAllLocation(): Call<LocationsModel>
}