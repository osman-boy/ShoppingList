package com.example.shoppinglist.domain.usecase

import com.example.shoppinglist.domain.ShopListRepository
import com.example.shoppinglist.domain.model.ShopItem

class GetShopItemUseCase(private val shopListRepository: ShopListRepository) {

    fun getShopItem(shopItemId: Int): ShopItem {
        return shopListRepository.getShopItem(shopItemId)
    }

}