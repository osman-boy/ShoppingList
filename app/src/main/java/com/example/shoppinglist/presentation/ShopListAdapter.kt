package com.example.shoppinglist.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppinglist.R
import com.example.shoppinglist.domain.model.ShopItem

class ShopListAdapter : RecyclerView.Adapter<ShopListAdapter.ShopViewHolder>() {

    var shopList: List<ShopItem> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    class ShopViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val shopName = view.findViewById<TextView>(R.id.tv_name)
        val shopCount = view.findViewById<TextView>(R.id.tv_count)

    }


    override fun getItemViewType(position: Int): Int {
        return if (shopList[position].enabled) VIEW_TYPE_ENABLED else VIEW_TYPE_DISABLED
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopViewHolder {

        val layout = when (viewType) {
            VIEW_TYPE_ENABLED -> R.layout.item_shop_enabled
            VIEW_TYPE_DISABLED -> R.layout.item_shop_disabled
            else -> throw RuntimeException("Unknown viewType: $viewType")
        }
        val inflater = LayoutInflater.from(parent.context)
        return ShopViewHolder(inflater.inflate(layout, parent, false))
    }


    override fun onBindViewHolder(holder: ShopViewHolder, position: Int) {
        val item = shopList[position]
        holder.shopName.text = item.name
        holder.shopCount.text = item.count.toString()
    }


    override fun getItemCount() = shopList.size

    companion object {
         const val VIEW_TYPE_DISABLED = 0
         const val VIEW_TYPE_ENABLED = 1
        const val MAX_POOL_SIZE = 25
    }
}