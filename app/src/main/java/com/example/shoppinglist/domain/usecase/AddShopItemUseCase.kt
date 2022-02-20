package com.example.shoppinglist.domain.usecase

import com.example.shoppinglist.domain.repository.ShopListRepository
import com.example.shoppinglist.domain.model.ShopItem

class AddShopItemUseCase(private val shopListRepository: ShopListRepository) {

   suspend fun addShopItem(shopItem: ShopItem) {
        shopListRepository.addShopItem(shopItem)
    }

}