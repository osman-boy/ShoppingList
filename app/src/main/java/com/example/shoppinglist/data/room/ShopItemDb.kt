package com.example.shoppinglist.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by osmanboy on 2/19/2022.
 */
@Entity(tableName = "shop_item")
data class ShopItemDb(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    val name: String,
    val count: Int,
    var enabled: Boolean,
)

