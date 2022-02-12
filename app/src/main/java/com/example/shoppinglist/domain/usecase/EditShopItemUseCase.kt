package com.example.shoppinglist.domain.usecase

import com.example.shoppinglist.domain.ShopListRepository
import com.example.shoppinglist.domain.model.ShopItem

class EditShopItemUseCase(private val shopListRepository: ShopListRepository) {

    fun editShopItem(shopItem: ShopItem) {
        shopListRepository.editShopItem(shopItem)
    }

}