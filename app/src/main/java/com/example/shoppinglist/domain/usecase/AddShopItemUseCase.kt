package com.example.shoppinglist.domain.usecase

import com.example.shoppinglist.domain.repository.ShopListRepository
import com.example.shoppinglist.domain.model.ShopItem
import javax.inject.Inject

class AddShopItemUseCase @Inject constructor(private val shopListRepository: ShopListRepository) {

   suspend fun addShopItem(shopItem: ShopItem) {
        shopListRepository.addShopItem(shopItem)
    }

}