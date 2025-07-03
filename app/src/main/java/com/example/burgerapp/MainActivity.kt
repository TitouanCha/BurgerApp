package com.example.burgerapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.burgerapp.fragments.CartFragment
import com.example.burgerapp.fragments.ChatFragment
import com.example.burgerapp.fragments.HistoryFragment
import com.example.burgerapp.fragments.HomeFragment
import com.example.burgerapp.fragments.PromotionFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.Firebase
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.analytics

class MainActivity : AppCompatActivity() {
    //private lateinit var crashTestButton: Button
    //private lateinit var fireBaseButton: Button

    private lateinit var buttomNavigationView: BottomNavigationView

    private lateinit var firebaseAnalytics: FirebaseAnalytics

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        firebaseAnalytics = Firebase.analytics

        buttomNavigationView = findViewById(R.id.bottom_navigation_view)
        replaceFragment(HomeFragment())
        setupBottomNavigation()



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }


    private fun setupBottomNavigation() {
        buttomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    replaceFragment(HomeFragment())
                    true
                }
                R.id.navigation_promotions -> {
                    replaceFragment(PromotionFragment())
                    true
                }
                R.id.navigation_cart -> {
                    replaceFragment(CartFragment())
                    true
                }
                R.id.navigation_chat -> {
                    replaceFragment(ChatFragment())
                    true
                }
                R.id.navigation_history -> {
                    replaceFragment(HistoryFragment())
                    true
                }
                else -> false
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.content_frame, fragment)
            .commit()
    }
}



/* Initialize the Crash Test Button
   this.crashTestButton = findViewById(R.id.crash_test_button)
   this.crashTestButton.setOnClickListener {
       throw RuntimeException("Test Crash")
   }
   this.fireBaseButton = findViewById(R.id.firebase_button)
   this.fireBaseButton.setOnClickListener {
      firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SHARE) {
          param(FirebaseAnalytics.Param.ITEM_ID, 1234)
          param(FirebaseAnalytics.Param.ITEM_NAME, "NAME")
          param(FirebaseAnalytics.Param.CONTENT_TYPE, "image")
      }
   }
   */