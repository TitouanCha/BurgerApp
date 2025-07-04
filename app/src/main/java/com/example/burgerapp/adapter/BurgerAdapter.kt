package com.example.burgerapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.burgerapp.R
import android.widget.ImageView
import com.example.burgerapp.Data.FoodsModel

class BurgerAdapter(
    val burgersData: FoodsModel,
    val onItemClicked: (Int) -> Unit
): RecyclerView.Adapter<BurgerViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BurgerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.burger_view_holder, parent, false)
        return BurgerViewHolder(view)
    }

    override fun getItemCount(): Int {
        return burgersData.size
    }

    override fun onBindViewHolder(holder: BurgerViewHolder, position: Int) {
        var burger = burgersData[position]
        var ratingReviews: List<Int> = burger.reviews.map { reviews ->
            reviews.rating
        }
        holder.burgerTextView.text = burger.title
        holder.burgerRatingReviews.text = buildString {
            append(averageRatingReviews(ratingReviews))
            append("/5")
        }
        holder.burgerPriceTextView.text = buildString {
            append(burger.price.toString())
            append(" €")
        }
        if(!burger.isFavorite){
            holder.burgerIsFavorite.setImageResource(R.drawable.favorite)
        }else{
            holder.burgerIsFavorite.setImageResource(R.drawable.baseline_favorite_border_24)
        }
        holder.itemView.setOnClickListener {
            onItemClicked(position)
        }
    }


    private fun averageRatingReviews(reviews: List<Int>): Double{
        return reviews.average()
    }

}

class BurgerViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val burgerTextView: TextView = itemView.findViewById<TextView>(R.id.burger_name_tv)
    val burgerRatingReviews: TextView = itemView.findViewById<TextView>(R.id.review_and_rating_tv)
    val burgerPriceTextView: TextView = itemView.findViewById<TextView>(R.id.food_price_tv)
    val burgerIsFavorite: ImageView = itemView.findViewById<ImageView>(R.id.like_icon_iv)
}