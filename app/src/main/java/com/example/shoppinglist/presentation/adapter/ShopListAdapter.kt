package com.example.shoppinglist.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.shoppinglist.databinding.ItemShopDisabledBinding
import com.example.shoppinglist.databinding.ItemShopEnabledBinding
import com.example.shoppinglist.domain.model.ShopItem


typealias OnShopItemLongClickListener = (shopItem: ShopItem) -> Unit
typealias OnShopItemClickListener = (shopItem: ShopItem) -> Unit

class ShopListAdapter : ListAdapter<ShopItem, ShopViewHolder>(ItemDiffUtilCallback()) {

    var onShopItemLongClickListener: OnShopItemLongClickListener? = null
    var onShopItemClickListener: OnShopItemClickListener? = null


    override fun getItemViewType(position: Int): Int {
        return if (getItem(position).enabled) VIEW_TYPE_ENABLED else VIEW_TYPE_DISABLED
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = when (viewType) {
            VIEW_TYPE_ENABLED -> ItemShopEnabledBinding.inflate(inflater, parent, false)
            VIEW_TYPE_DISABLED -> ItemShopDisabledBinding.inflate(inflater, parent, false)
            else -> throw RuntimeException("Unknown viewType: $viewType")
        }
        return ShopViewHolder(binding)
    }


    override fun onBindViewHolder(holder: ShopViewHolder, position: Int) {
        val item = getItem(position)
        when (holder.binding) {
            is ItemShopDisabledBinding -> holder.binding.shopItem = item
            is ItemShopEnabledBinding -> holder.binding.shopItem = item
        }
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