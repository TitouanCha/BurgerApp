package com.example.burgerapp.Data

import android.accessibilityservice.GestureDescription
import com.google.gson.annotations.SerializedName

data class FoodModelItem(
    val category: String,
    val id: Int,
    val description: String,
    @SerializedName("image_url")
    val imageUrl: String,
    val isFavorite: Boolean,
    val price: Float,
    val reviews: List<Review>,
    val title: String
)