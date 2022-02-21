package com.example.shoppinglist.di

import android.app.Application
import com.example.shoppinglist.data.ShopListRepositoryImpl
import com.example.shoppinglist.data.room.ShopDao
import com.example.shoppinglist.data.room.ShopDataBase
import com.example.shoppinglist.domain.repository.ShopListRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

/**
 * Created by osmanboy on 2/21/2022.
 */
@Module
interface DataModule {

    @ApplicationScope
    @Binds
    fun bindShopListRepository(impl: ShopListRepositoryImpl): ShopListRepository

    companion object{

        @ApplicationScope
        @Provides
        fun provideShopListDao(application:Application): ShopDao {
           return ShopDataBase.newInstance(application).shopDao()
        }
    }
}