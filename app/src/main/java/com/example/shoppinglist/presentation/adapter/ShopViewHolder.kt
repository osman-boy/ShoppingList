package com.example.shoppinglist.presentation.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppinglist.R

class ShopViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val shopName = view.findViewById<TextView>(R.id.tv_name)
    val shopCount = view.findViewById<TextView>(R.id.tv_count)

}