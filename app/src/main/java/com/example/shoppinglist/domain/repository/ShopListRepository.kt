package com.example.shoppinglist.domain.repository

import androidx.lifecycle.LiveData
import com.example.shoppinglist.domain.model.ShopItem

interface ShopListRepository {

  suspend fun addShopItem(shopItem: ShopItem)

    suspend fun editShopItem(shopItem: ShopItem)

    suspend fun getShopItem(shopItemId: Int): ShopItem

    suspend fun removeShopItem(shopItem: ShopItem)

     fun getShopList(): LiveData<List<ShopItem>>

}

