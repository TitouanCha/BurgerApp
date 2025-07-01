package com.example.burgerapp.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.burgerapp.R

class BurgerAdapter(val burger: List<String>): RecyclerView.Adapter<BurgerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BurgerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.burger_view_holder, parent, false)
        return BurgerViewHolder(view)
    }

    override fun getItemCount(): Int {
        return burger.size
    }

    override fun onBindViewHolder(holder: BurgerViewHolder, position: Int) {
        val burgerName = burger[position]
        holder.burgerTextView.text = burgerName
    }


}

class BurgerViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val burgerTextView: TextView = itemView.findViewById<TextView>(R.id.burger_name_tv)
}