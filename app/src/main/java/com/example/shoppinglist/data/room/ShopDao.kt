package com.example.shoppinglist.data.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/**
 * Created by osmanboy on 2/19/2022.
 */

@Dao
interface ShopDao {
    @Query("SELECT * FROM shop_item")
    fun getShopList(): LiveData<List<ShopItemDb>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addShopItem(shopItemDb: ShopItemDb)

    @Query("DELETE FROM shop_item WHERE id=:shopItemId")
    fun removeShopItem(shopItemId: Int)

    @Query("SELECT * FROM shop_item WHERE id=:shopItemId LIMIT 1")
    fun getShopItem(shopItemId: Int): ShopItemDb
}