package com.example.burgerapp.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.burgerapp.R
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.burgerapp.adapter.BurgerAdapter
import com.example.burgerapp.adapter.CategoriesAdapter
import com.example.burgerapp.Data.FoodsModel
import com.example.burgerapp.FoodServices
import com.example.burgerapp.Retrocfitclient
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.analytics.logEvent
import com.google.firebase.ktx.Firebase
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {
    private fun setUpFirebaseAnalytics() {

        val firebaseAnalytics = Firebase.analytics
        firebaseAnalytics.logEvent("home_fragment_view") {
            param(FirebaseAnalytics.Param.ITEM_ID, "home_fragment")
        }
    }


    private lateinit var mainActivityCategoriesRecyclerView: RecyclerView
    private lateinit var mainActivityBurgerRecyclerView: RecyclerView

    private lateinit var burgers: FoodsModel
    private lateinit var categories: List<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpFirebaseAnalytics()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setUpFirebaseAnalytics()
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        getFoodData(view)
        return view
    }

    private fun getFoodData(view: View) {
        val foodService: FoodServices =
            Retrocfitclient.client.create(FoodServices::class.java)

        val call = foodService.getAllFoods()

        call.enqueue(
            object : Callback<FoodsModel> {
                override fun onResponse(call: Call<FoodsModel>, response: Response<FoodsModel>) {
                    if(response.isSuccessful) {
                        response.body()?.let{
                            Log.d("HomeFragment", "Response: $it")
                            burgers = it
                            setupCategoriesRecyclerView(view)
                            setupBurgerRecyclerView(view, burgers)
                        }
                    }
                }

                override fun onFailure(call: Call<FoodsModel>, t: Throwable) {
                    Log.d("HomeFragment", "Error: ${t.message}")
                }

            }
        )

    }

    private fun setupBurgerRecyclerView(view: View, burgersData: FoodsModel) {
        val firebaseAnalytics = Firebase.analytics
        firebaseAnalytics.logEvent("burger_detail_view") {
            param(FirebaseAnalytics.Param.ITEM_ID, "burger")
        }

        this.mainActivityBurgerRecyclerView = view.findViewById(R.id.main_activity_burger_rv)
        this.mainActivityBurgerRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        this.mainActivityBurgerRecyclerView.adapter = BurgerAdapter(burgersData)

    }

    private fun setupCategoriesRecyclerView(view: View) {
        this.mainActivityCategoriesRecyclerView = view.findViewById(R.id.main_activity_categories_rv)
        this.mainActivityCategoriesRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        val categories = listOf(
            "Burgers",
            "Salads",
            "Drinks",
            "Desserts",
            "Sides",
            "Combos",
            "Specials",
            "Vegan",
            "Gluten-Free",
            "Kids"
        )
        this.mainActivityCategoriesRecyclerView.adapter = CategoriesAdapter(categories)
    }

}