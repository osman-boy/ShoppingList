package com.example.shoppinglist.domain.usecase

import com.example.shoppinglist.domain.ShopListRepository
import com.example.shoppinglist.domain.model.ShopItem

class RemoveShopItemUseCase(private val shopListRepository: ShopListRepository) {

    fun removeShopItem(shopItem: ShopItem) {
        shopListRepository.removeShopItem(shopItem)
    }

}