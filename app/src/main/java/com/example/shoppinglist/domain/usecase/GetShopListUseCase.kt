package com.example.shoppinglist.domain.usecase

import com.example.shoppinglist.domain.ShopListRepository
import com.example.shoppinglist.domain.model.ShopItem

class GetShopListUseCase(private val shopListRepository: ShopListRepository) {

    fun getShopList(): List<ShopItem> {
        return shopListRepository.getShopList()
    }

}