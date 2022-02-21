package com.example.shoppinglist.data.mapper

import com.example.shoppinglist.data.room.ShopItemDb
import com.example.shoppinglist.domain.model.ShopItem
import javax.inject.Inject

/**
 * Created by osmanboy on 2/19/2022.
 */
class ShopListMapper @Inject constructor() {

    fun mapEntityToDbModel(shopItem: ShopItem) =
        with(shopItem) { ShopItemDb(id, name, count, enabled) }


    fun mapDbModelToEntity(shopItemDb: ShopItemDb) =
        with(shopItemDb) { ShopItem(id = id, name = name, count = count, enabled = enabled) }


    fun mapListDbModelToEntity(list: List<ShopItemDb>) = list.map {
        ShopItem(id = it.id, name = it.name, count = it.count, enabled = it.enabled)
    }


}