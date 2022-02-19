package com.example.shoppinglist.data.room

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * Created by osmanboy on 2/19/2022.
 */
@Database(entities = [ShopItemDb::class], version = 1)
abstract class ShopDataBase : RoomDatabase() {


    abstract fun shopDao(): ShopDao

    companion object {
        private var instance: ShopDataBase? = null
        private const val DB_NAME = "shop_item.db"
        private const val LOCK = "Lock"

        fun newInstance(application: Application): ShopDataBase {

            return instance ?: synchronized(LOCK) {
                instance?.let { return it }
                val newInstance =
                    Room.databaseBuilder(application, ShopDataBase::class.java, DB_NAME).build()
                instance = newInstance
                newInstance
            }

        }

    }
}