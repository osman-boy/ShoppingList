package com.example.shoppinglist.presentation

import androidx.recyclerview.widget.DiffUtil
import com.example.shoppinglist.domain.model.ShopItem

class DiffUtilCallback(val newList: List<ShopItem>, val oldList: List<ShopItem>) :
    DiffUtil.Callback() {

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id==newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition]==newList[newItemPosition]

    }
}