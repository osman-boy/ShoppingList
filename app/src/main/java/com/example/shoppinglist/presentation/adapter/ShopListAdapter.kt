package com.example.shoppinglist.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.shoppinglist.R
import com.example.shoppinglist.domain.model.ShopItem
import com.example.shoppinglist.presentation.ShopViewHolder


typealias OnShopItemLongClickListener = (shopItem: ShopItem) -> Unit
typealias OnShopItemClickListener = (shopItem: ShopItem) -> Unit

class ShopListAdapter : ListAdapter<ShopItem, ShopViewHolder>(ItemDiffUtilCallback()) {

    var onShopItemLongClickListener: OnShopItemLongClickListener? = null
    var onShopItemClickListener: OnShopItemClickListener? = null


    override fun getItemViewType(position: Int): Int {
        return if (getItem(position).enabled) VIEW_TYPE_ENABLED else VIEW_TYPE_DISABLED
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
        val item = getItem(position)
        holder.shopName.text = item.name
        holder.shopCount.text = item.count.toString()

        holder.itemView.setOnLongClickListener {
            onShopItemLongClickListener?.invoke(item)
            true
        }

        holder.itemView.setOnClickListener {
            onShopItemClickListener?.invoke((item))
        }
    }



    companion object {
        const val VIEW_TYPE_DISABLED = 0
        const val VIEW_TYPE_ENABLED = 1
        const val MAX_POOL_SIZE = 25
    }


}