package com.example.shoppinglist.presentation

import androidx.recyclerview.widget.DiffUtil
import com.example.shoppinglist.domain.model.ShopItem

class ItemDiffUtilCallback : DiffUtil.ItemCallback<ShopItem>() {

    override fun areItemsTheSame(oldItem: ShopItem, newItem: ShopItem) = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: ShopItem, newItem: ShopItem) = oldItem == newItem

}