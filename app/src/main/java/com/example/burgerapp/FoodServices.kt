package com.example.burgerapp

import com.example.burgerapp.Data.FoodsModel
import retrofit2.Call
import retrofit2.http.GET


interface FoodServices {

    @GET("TitouanCha/demo/food")
    fun getAllFoods(): Call<FoodsModel>
}