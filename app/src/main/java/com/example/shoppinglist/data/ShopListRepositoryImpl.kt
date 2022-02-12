package com.example.shoppinglist.data

import androidx.lifecycle.MutableLiveData
import com.example.shoppinglist.domain.ShopListRepository
import com.example.shoppinglist.domain.model.ShopItem
import com.example.shoppinglist.domain.model.ShopItem.Companion.UNDEFINED_ID
import java.lang.RuntimeException

class ShopListRepositoryImpl : ShopListRepository {

    private val _shopList = MutableLiveData<List<ShopItem>>()
    private val shopList = mutableListOf<ShopItem>()
    private var autoIncrementId = 0

    init {
        for (i in 0..10) {
            val shopItem = ShopItem("Item$i", i * 2, false)
            addShopItem(shopItem)
        }
    }

    override fun addShopItem(shopItem: ShopItem) {
        if (shopItem.id == UNDEFINED_ID) {
            shopItem.id = autoIncrementId++
        }
        shopList.add(shopItem)
        _shopList.value = shopList
    }

    override fun editShopItem(shopItem: ShopItem) {
        val oldShopItem = getShopItem(shopItem.id)
        shopList.remove(oldShopItem)
        addShopItem(shopItem)
    }

    override fun getShopItem(shopItemId: Int): ShopItem {
        return shopList.find { it.id == shopItemId }
            ?: throw RuntimeException("Element with id: $shopItemId not found")
    }

    override fun removeShopItem(shopItem: ShopItem) {
        shopList.remove(shopItem)
        _shopList.value = shopList
    }

    override fun getShopList(): List<ShopItem> {
        _shopList.value = shopList
        return _shopList.value!!
    }
}