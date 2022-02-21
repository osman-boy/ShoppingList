package com.example.shoppinglist.domain.usecase

import com.example.shoppinglist.domain.repository.ShopListRepository
import com.example.shoppinglist.domain.model.ShopItem
import javax.inject.Inject

class EditShopItemUseCase @Inject constructor(private val shopListRepository: ShopListRepository) {

    suspend fun editShopItem(shopItem: ShopItem) {
        shopListRepository.editShopItem(shopItem)
    }

}