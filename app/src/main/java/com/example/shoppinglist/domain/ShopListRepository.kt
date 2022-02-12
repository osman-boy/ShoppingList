package com.example.shoppinglist.domain

import com.example.shoppinglist.domain.model.ShopItem

interface ShopListRepository {

    fun addShopItem(shopItem: ShopItem)

    fun editShopItem(shopItem: ShopItem)

    fun getShopItem(shopItemId: Int): ShopItem

    fun removeShopItemUseCase(shopItem: ShopItem)

    fun getShopList(): List<ShopItem>

}

