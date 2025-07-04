package com.example.burgerapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.burgerapp.R

class CategoriesAdapter(val categories: List<String>): RecyclerView.Adapter<CategoriesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.categories_view_holder, parent, false)
        return CategoriesViewHolder(view)
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        val cat = categories[position]
        holder.categoriesTextView.text = cat
    }

}

class CategoriesViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val categoriesTextView: TextView = itemView.findViewById<TextView>(R.id.category_name_tv)
}
