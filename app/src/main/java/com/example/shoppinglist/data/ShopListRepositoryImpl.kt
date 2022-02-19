package com.example.shoppinglist.data

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.shoppinglist.data.mapper.ShopListMapper
import com.example.shoppinglist.data.room.ShopDataBase
import com.example.shoppinglist.domain.model.ShopItem
import com.example.shoppinglist.domain.repository.ShopListRepository

class ShopListRepositoryImpl(application: Application) : ShopListRepository {

    private val shopDao = ShopDataBase.newInstance(application).shopDao()
    private val mapper = ShopListMapper()

    override suspend fun addShopItem(shopItem: ShopItem) {
        shopDao.addShopItem(mapper.mapEntityToDbModel(shopItem))
    }

    override suspend fun editShopItem(shopItem: ShopItem) {
        shopDao.addShopItem(mapper.mapEntityToDbModel(shopItem))

    }

    override suspend fun getShopItem(shopItemId: Int): ShopItem {
        return mapper.mapDbModelToEntity(shopDao.getShopItem(shopItemId))

    }

    override suspend fun removeShopItem(shopItem: ShopItem) {
        shopDao.removeShopItem(shopItem.id)
    }

    override  fun getShopList(): LiveData<List<ShopItem>> {
        return Transformations.map(shopDao.getShopList()) {
            mapper.mapListDbModelToEntity(it)
        }
    }


}