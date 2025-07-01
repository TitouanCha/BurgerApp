package com.example.burgerapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.burgerapp.R
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.burgerapp.Adapter.BurgerAdapter
import com.example.burgerapp.Adapter.CategoriesAdapter

class HomeFragment : Fragment() {

    private lateinit var mainActivityCategoriesRecyclerView: RecyclerView
    private lateinit var mainActivityBurgerRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        setupBurgerRecyclerView(view)
        setupCategoriesRecyclerView(view)
        return view
    }

    private fun setupBurgerRecyclerView(view: View) {
        this.mainActivityBurgerRecyclerView = view.findViewById(R.id.main_activity_burger_rv)
        this.mainActivityBurgerRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        var burgers = listOf(
            "Classic Burger",
            "Cheeseburger",
            "Bacon Burger",
            "Veggie Burger",
            "Spicy Burger",
            "Double Burger",
            "BBQ Burger",
            "Mushroom Burger",
            "Fish Burger",
            "Turkey Burger"
        )
        this.mainActivityBurgerRecyclerView.adapter = BurgerAdapter(burgers)

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