package com.example.burgerapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.burgerapp.Data.Review
import com.google.android.material.button.MaterialButton

class BurgerDetailActivity : AppCompatActivity() {
    lateinit var foodId : String
    lateinit var foodReview: List<Review>
    lateinit var foodDescription: String
    lateinit var foodName: String
    lateinit var foodPrice: String
    lateinit var foodImage: String


    lateinit var backButton: MaterialButton
    lateinit var userLocationButton: MaterialButton

    lateinit var productTitleTv: TextView
    lateinit var productReview: TextView
    lateinit var productPrice: TextView
    lateinit var productDescription: TextView
    lateinit var productImage: ImageView
    lateinit var addToCartButton: CardView

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_burger_details)

        getIntentData()
        linkViews()
        setupClickListeners()

        productDescription.text = this.foodDescription
        productTitleTv.text = this.foodName
        productPrice.text = "${this.foodPrice} €"
        productReview.text = "2/5"

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun linkViews() {
        backButton = findViewById(R.id.custom_back_button)
        userLocationButton = findViewById(R.id.user_location_btn)

        productTitleTv = findViewById(R.id.product_title_tv)
        productReview = findViewById(R.id.product_rating_tv)
        productPrice = findViewById(R.id.product_price_tv)
        productDescription = findViewById(R.id.product_description_tv)
        productImage = findViewById(R.id.product_image_iv)
        addToCartButton = findViewById(R.id.add_to_cart_button)
    }

    private fun setupClickListeners() {
        backButton.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        userLocationButton.setOnClickListener {
            // Handle user location button click
        }

        addToCartButton.setOnClickListener {
            // Handle add to cart button click
        }
    }

    private fun getIntentData() {
        foodId = intent.getStringExtra("burgerId") ?: ""
        foodDescription = intent.getStringExtra("burgerDescription") ?: ""
        foodName = intent.getStringExtra("burgerName") ?: ""
        foodPrice = intent.getStringExtra("burgerPrice") ?: ""
        foodImage = intent.getStringExtra("burgerImage") ?: ""


    }
}